function callAJAX(){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/Battleship/get_rating",
        success: function (data) {
                    $("#block1").html("");
                    var table = $("<table></table>")
                                    .addClass("table")
                                    .append(
                                        $("<tr></tr>")
                                            .addClass("table_row")
                                            .append($("<th></th>").addClass("table_cell").html("#"))
                                            .append($("<th></th>").addClass("table_cell").html("Name"))
                                            .append($("<th></th>").addClass("table_cell").html("Scores"))
                                    );

                    data.players.forEach(function(d, i){
                        table.append(
                            $("<tr></tr>")
                                .addClass("table_row")
                                .append(
                                    $("<td></td>").addClass("table_cell").html(i + 1)
                                )
                                .append(
                                    $("<td></td>").addClass("table_cell").html(d.name)
                                )
                                .append(
                                    $("<td></td>").addClass("table_cell").html(d.scores)
                                )
                        );
                    });
                    $("#block1").append(table);

                    setTimeout(callAJAX(), 1000);
                 }
    });
}

callAJAX();
