var enemy = 0;

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

function build_table(field, shiftN){
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
        var tr = $("<tr></tr>")
                    .addClass("table-row")
                    .append($("<th></th>").html(i + 1));
        for(var j = 0; j < 10; ++j){
            var div = $("<div></div>").addClass("sb-element");
            div.attr("my", i * 10 + j + shiftN);
            tr.append(
                $("<td></td>").append(div)
            );
        }
        field.append(tr);
    }
    return field;
}

function get_game(gameID){
    if(typeof gameID !== "undefined")
        this.gameID = gameID;

    if(typeof this.gameID === "undefined")
        throw new Error("this.gameID is undefined");

    var that = this;

    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/Battleship/get_game?id=" + this.gameID,
        success: function (data) {
                     var i = 0;
                     var field = data.field;
                      var move = data.move;
                      var status = data.status;

                      if (status > 0) {
                         if (move == 0) {
                             alert("YOU ARE THE WINNER!!!");
                         }
                         else {
                             alert("Sorry, you lost...");
                         }
                         return;
                      }

                      if (move == 1){
                         $("#player_move").html("you");
                      }
                      else {
                         $("#player_move").html("enemy");
                      }
                      console.log(move);
                      var list = $("div.sb-element");
                      var length = list.length;
                      var i = 0;

                      list.removeClass("sb-our-ship sb-miss sb-hit sb-enemy-ship");

                     for (var j = 0; j < length; ++j){
                         //if ($(list[j]).hasClass("sb-element")){
                             //list[j]["my"] = i / 2;
                             list[j].onclick = function(){
                                if (move == 1){
                                   if (this.getAttribute("my") >= 100) {
                                      if (field[2 * this.getAttribute("my") + 1] == 0) {
                                         field[2 * this.getAttribute("my") + 1] = 1;

                                         $.ajax({
                                             dataType: "json",
                                             url: "http://localhost:8080/Battleship/move?gameID=" + that.gameID + "&cell=" + this.getAttribute("my")
                                         });
                                         $("#player_move").html("enemy");


                                      }
                                      else {
                                         alert("You can not do it twice!");
                                      }
                                   }
                                }
                             };
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
                         //}
                     }
                     setTimeout(get_game, 2000);
                 }
    });
}



var newGameForm = $("<div></div>").html("<form  action=\"new_game\" method=\"post\">"
                                            + "Enemy:<input type=\"text\" name=\"enemy\" id=\"enemy_name\">"
                                            + "<input type=\"submit\" value=\"Start\" id=\"start_button\">"
                                            + "</form>");
$("#row1").append(newGameForm);

var game = $("<div></div>").addClass("game");
var gameStatus = $("<h3></h3>").html("---").attr("id", "player_move");
game.append(gameStatus);
build_block1();

var field1 = $("<table></table>");
var field2 = $("<table></table>");
var div1 = $("<div></div>").addClass("field")
    .append($("<h3></h3>").html("Your field"))
    .append(build_table(field1, 0));
var div2 = $("<div></div>").addClass("field")
    .append($("<h3></h3>").html("Enemy's field"))
    .append(build_table(field2, 100));
//var infobox = $("<div></div>").addClass("infobox").html("Enemy: ");
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