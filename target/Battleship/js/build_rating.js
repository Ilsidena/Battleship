function callAJAX(){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/Battleship/get_user_games",
        success: function (data) {
                    var table = $("<table></table>")
                                    .addClass("table")
                                    .append(
                                        $("<tr></tr>")
                                            .addClass("table-row")
                                            .append($("<th></th>").html("#"))
                                            .append($("<th></th>").html("name"))
                                            .append($("<th></th>").html("scores"))
                                    );

                    data.players.forEach(function(d, i){
                        table.append(
                            $("<tr></tr>")
                                .addClass("table-row")
                                .append(
                                    $("<td></td>").html(i + 1)
                                )
                                .append(
                                    $("<td></td>").html(d.name)
                                )
                                .append(
                                    $("<td></td>").html(d.scores)
                                )
                        );
                    });

                    var tableDiv = $("#tableDiv");
                    tableDiv.html("");

                    tableDiv.append(
                            $("<h2></h2>")
                                .addClass("title-of-table")
                                .html("the title")
                        )
                        .append(table);

                    setTimeout(callAJAX(), 1000);
                 }
    });
}

callAJAX();