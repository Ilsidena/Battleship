function callAJAX(){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/Battleship/get_game?id=1",
        success: function (data) {
                     var field = data.field;
                     console.log(data.field);
                     var list = $("DIV");
                     var length = list.length;
                     var i = 0;

                     for (var j = 0; j < length; ++j){
                         names = list[j].className.split(" ");

                         if (names.includes("sb-element")){
                             if (field[i] == 1){
                                 if (i < 200){
                                     list[j].className += " sb-our-ship";
                                 }
                                 else {
                                     list[j].className += " sb-enemy-ship";
                                 }
                             }
                             i++;

                             if (field[i] == 1) {
                                 if (field[i - 1] == 0){
                                     list[j].className += " sb-miss";
                                 }
                                 else {
                                     list[j].className += " sb-hit";
                                 }
                             }
                             i++;
                         }
                     }

                     setTimeout(callAJAX(), 1000);
                 }

    });
}

callAJAX();