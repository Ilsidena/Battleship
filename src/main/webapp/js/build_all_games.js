function callAJAX(){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/Battleship/get_all_games",
        success: function (data) {
                    $("#block1").html("");
                    var table = $("<table></table>")
                                    .addClass("table")
                                    .append(
                                        $("<tr></tr>")
                                            .addClass("table_row")
                                            .append($("<th></th>").addClass("table_cell").html("#"))
                                            .append($("<th></th>").addClass("table_cell").html("Players"))
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
                            else if (d.status > 0) {
                                statusString = "Completed";
                            }

                            table.append(
                            $("<tr></tr>")
                                .addClass("table_row")
                                .append(
                                    $("<td></td>").addClass("table_cell").html(i + 1)
                                )
                                .append(
                                    $("<td></td>").addClass("table_cell").html(d.players)
                                )
                                .append(
                                    $("<td></td>").addClass("table_cell").html(statusString)
                                )
                        );
                    });
                    $("#block1").append(table);

                    setTimeout(callAJAX(), 1000);
                 }
    });
}

callAJAX();
