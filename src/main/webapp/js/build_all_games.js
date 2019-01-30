function callAJAX(){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/Battleship/get_all_games",
        success: function (data) {
                     var table = $("<table></table>")
                     				.addClass("table")
                     				.append(
                     					$("<tr></tr>")
                     						.addClass("table-row")
                     						.append($("<th></th>").html("#"))
                     						.append($("<th></th>").html("Players"))
                     						.append($("<th></th>").html("Status"))
                     				);

                     data.list.forEach(function(d, i){
                     	table.append(
                     		$("<tr></tr>")
                     			.addClass("table-row")
                     			.append(
                     				$("<td></td>").html("#" + (i + 1))
                     			)
                     			.append(
                     				$("<td></td>").html(d.players)
                     			)
                     			.append(
                     				$("<td></td>").html(d.status)
                     			)
                     	);
                     });

                     var tableDiv = $("#tableDiv");
                     tableDiv.html("");

                     tableDiv.append(
                     		$("<h2></h2>")
                     			.addClass("title-of-table")
                     			.html("")
                     	)
                     	.append(table);

                     setTimeout(callAJAX(), 1000);
                 }

    });
}

callAJAX();