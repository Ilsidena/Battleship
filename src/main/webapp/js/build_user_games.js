var currentGame = 0;

function build_block1(){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/Battleship/get_user_games",
        success: function (data) {
                    $("#block1").html("");

                    var table = $("<table></table>")
                                    .addClass("table")
                                    .append(
                                        $("<tr></tr>")
                                            .addClass("table_row")
                                            .append($("<th></th>").addClass("table_cell").html("#"))
                                            .append($("<th></th>").addClass("table_cell").html("Enemy"))
                                            .append($("<th></th>").addClass("table_cell").html("Status"))
                                    );

                    data.list.forEach(function(d, i){
                        var statusString = "";
                        if (d.status == -1){
                        statusString = "Preparing";
                        }
                        else if (d.status == 0) {
                        statusString = "In Progress";
                        }
                        else if (d.status == 1) {
                        statusString = "Win";
                        }
                        else if (d.status == 2) {
                        statusString = "Lose";
                        }
                        table.append(
                            $("<tr></tr>")
                                .addClass("table_row")
                                .append($("<td></td>").addClass("table_cell").html(i + 1))
                                .append($("<td></td>").addClass("table_cell").html(d.players))
                                .append($("<td></td>").addClass("table_cell toClick").html(statusString))
                        );
                    });
                    $("#block1").append(table);

                    var arrayToClick = $(".toClick");
                    var arrayToClickLength = arrayToClick.length;

                    for(var i = 0; i < arrayToClickLength; ++i){
                        arrayToClick[i]["wasClicked"] = 0;
                        arrayToClick[i]["gameID"] = data.list[i].gameID;
                        arrayToClick[i].onclick = function() {
                                        var thisWasClicked = this["wasClicked"];

                                        for (var j = 0; j < arrayToClickLength; ++j){
                                            arrayToClick[j]["wasClicked"] = 0;
                                        }

                                        var vis = $(".game").css("visibility");
                                        if (vis == "visible"){
                                            if (thisWasClicked == 1) {
                                                $(".game").css("visibility", "hidden");
                                                this["wasClicked"] = 0;
                                            }
                                            else{
                                                this["wasClicked"] = 1;
                                            }
                                        }
                                        else if (vis == "hidden") {
//                                            var list = $("DIV");
//                                            var length = list.length;
//                                            for (var k = 0; k < length; ++k){
//                                               if ($(list[k]).hasClass("sb-element")){
//                                                   $(list[k]).removeClass("sb-our-ship sb-miss sb-hit sb-enemy-ship");
//                                                   console.log(list[k].className);
//                                               }
//                                            }
//                                            list.html();

                                            $(arrayToClick[i]).removeClass("sb-our-ship sb-miss sb-hit sb-enemy-ship");

                                            $(".game").css("visibility", "visible");
                                            this["wasClicked"] = 1;


                                           get_game(this["gameID"]);
                                        }
                                    };
                    }

                    setTimeout(build_block1, 5000);
                }
    });
}

function build_table(field){
    field.addClass("table")
                .append(
                    $("<tr></tr>")
                        .addClass("table-row")
                        .append($("<th></th>").html(""))
                        .append($("<th></th>").html("A"))
                        .append($("<th></th>").html("B"))
                        .append($("<th></th>").html("C"))
                        .append($("<th></th>").html("D"))
                        .append($("<th></th>").html("E"))
                        .append($("<th></th>").html("F"))
                        .append($("<th></th>").html("G"))
                        .append($("<th></th>").html("H"))
                        .append($("<th></th>").html("I"))
                        .append($("<th></th>").html("J"))
                );

    for (var i = 0; i < 10; ++i) {
        field.append(
            $("<tr></tr>")
                .addClass("table-row")
                .append($("<th></th>").html(i + 1))
                .append($("<th></th>").html("<div class=\"sb-element\"></div>"))
                .append($("<th></th>").html("<div class=\"sb-element\"></div>"))
                .append($("<th></th>").html("<div class=\"sb-element\"></div>"))
                .append($("<th></th>").html("<div class=\"sb-element\"></div>"))
                .append($("<th></th>").html("<div class=\"sb-element\"></div>"))
                .append($("<th></th>").html("<div class=\"sb-element\"></div>"))
                .append($("<th></th>").html("<div class=\"sb-element\"></div>"))
                .append($("<th></th>").html("<div class=\"sb-element\"></div>"))
                .append($("<th></th>").html("<div class=\"sb-element\"></div>"))
                .append($("<th></th>").html("<div class=\"sb-element\"></div>"))
        );
    }
    return field;
}

function get_game(gameID){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/Battleship/get_game?id=" + gameID,
        success: function (data) {
                     var field = data.field;

                     var list = $("DIV");
                     var length = list.length;
                     var i = 0;

//                     if (window.currentGame != gameID){
//                        for (var k = 0; k < length; ++k){
//                            if ($(list[k]).hasClass("sb-element")){
//                                $(list[k]).removeClass("sb-our-ship sb-miss sb-hit sb-enemy-ship");
//                                console.log($(list[k]).hasClass("sb-our-ship sb-miss sb-hit sb-enemy-ship"));
//                            }
//                        }
//                     }
//
//                     window.currentGame = gameID;

                     for (var j = 0; j < length; ++j){
                         if ($(list[j]).hasClass("sb-element")){
                             if (field[i] == 1){
                                 if (i < 200){
                                     $(list[j]).addClass("sb-our-ship");
                                 }
                             }
                             i++;

                             if (field[i] == 1) {
                                 if (field[i - 1] == 0){
                                     $(list[j]).addClass("sb-miss");
                                 }
                                 else {
                                     $(list[j]).addClass("sb-hit");
                                     if ((i- 1) > 200){
                                        $(list[j]).addClass("sb-enemy-ship");
                                     }
                                 }
                             }
                             i++;
                         }
                     }
                     setTimeout(get_game(gameID), 2000);
                 }
    });
}



var newGameForm = $("<div></div>").html("<form  action=\"new_game\" method=\"post\">"
                                            + "Enemy:<input type=\"text\" name=\"enemy\" id=\"enemy_name\">"
                                            + "<input type=\"submit\" value=\"Start\" id=\"start_button\">"
                                            + "</form>");
$("#row1").append(newGameForm);

var game = $("<div></div>").addClass("game");
build_block1();

var field1 = $("<table></table>");
var field2 = $("<table></table>");
var div1 = $("<div></div>").addClass("field")
    .append($("<h3></h3>").html("Your field"))
    .append(build_table(field1));
var div2 = $("<div></div>").addClass("field")
    .append($("<h3></h3>").html("Enemy's field"))
    .append(build_table(field2));
game.append(div1).append(div2);
$("#block2").append(game);


//function callAJAX(){
//    $.ajax({
//        dataType: "json",
//        url: "http://localhost:8080/Battleship/get_user_games",
//        success: function (data) {
//                     var table = $("<table></table>")
//                     				.addClass("table")
//                     				.append(
//                     					$("<tr></tr>")
//                     						.addClass("table-row")
//                     						.append($("<th></th>").html("#"))
//                     						.append($("<th></th>").html("Enemy"))
//                     						.append($("<th></th>").html("Status"))
//                     				);
//
//                     data.list.forEach(function(d, i){
//                         var statusString = "";
//                         if (d.status == 0) {
//                            //statusString = "<a href=\"/Battleship/play_game?gameID=" + d.gameID + "\">In Progress</a>";
//                            statusString = "<a href=\"/Battleship/field.html\">In Progress</a>";
//                         }
//                         else if (d.status == 1) {
//                            statusString = "Win";
//                         }
//                         else if (d.status == 2) {
//                            statusString = "Lose";
//                         }
//                     	 table.append(
//                     		$("<tr></tr>")
//                     			.addClass("table-row")
//                     			.append(
//                     				$("<td></td>").html("#" + (i + 1))
//                     			)
//                     			.append(
//                     				$("<td></td>").html(d.players)
//                     			)
//                     			.append(
//                     				$("<td></td>").html(statusString)
//                     			)
//                         );
//                     });
//
//                     var tableDiv = $("#tableDiv");
//                     tableDiv.html("");
//
//                     tableDiv.append(
//                     		$("<h2></h2>")
//                     			.addClass("title-of-table")
//                     			.html("")
//                     	)
//                     	.append(table);
//
//                     setTimeout(callAJAX(), 1000);
//                 }
//
//    });
//}
//
//callAJAX();