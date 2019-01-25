var _gameID = 1;
var _myID = 3;

function callAJAX(){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/Battleship/get_game?id=1",
        success: function (data) {
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
                     var list = $("DIV");
                     var length = list.length;
                     var i = 0;

                     for (var j = 0; j < length; ++j){
                         if ($(list[j]).hasClass("sb-element")){
                             list[j]["my"] = i / 2;
                             list[j].onclick = function(){
                                  if (move == 1){
                                      $("#player_move").html("enemy");
                                      if (this["my"] >= 100) {
                                         if (field[2 * this["my"] + 1] == 0) {
                                            field[2 * this["my"] + 1] = 1;

                                            $.ajax({
                                                dataType: "json",
                                                url: "http://localhost:8080/Battleship/move?gameID=" + _gameID + "&cell=" + this["my"]
                                            });
                                         }
                                         else {
                                            alert("You can not do it twice!");
                                         }
                                      }
                                  }
                             };
                             if (field[i] == 1){
                                 if (i < 200){
                                     $(list[j]).addClass(" sb-our-ship");
                                 }
                                 else {
                                     $(list[j]).addClass(" sb-enemy-ship");
                                 }
                             }
                             i++;

                             if (field[i] == 1) {
                                 if (field[i - 1] == 0){
                                     $(list[j]).addClass(" sb-miss");
                                 }
                                 else {
                                     $(list[j]).addClass(" sb-hit");
                                 }
                             }
                             i++;
                         }
                     }

                     setTimeout(callAJAX, 1000);
                 }

    });
}

callAJAX();