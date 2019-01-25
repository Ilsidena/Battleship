package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.GameNotFoundException;
import database.GamesTable;
import database.ListOfGames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetAllGamesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ListOfGames games = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            games = GamesTable.getAllGames(con);
            con.close();
        } catch (GameNotFoundException e){
            this.sendErrorMessage(out, e.getMessage());
            return;
        } catch (SQLException e){
            sendErrorMessage(out, "ERROR 500: Internal Error");
            return;
        }

        if(games == null) {
            sendErrorMessage(out, "ERROR 500: Internal Error");
            return;
        }

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        out.println(gson.toJson(games));

        out.close();
    }

    private void sendErrorMessage(PrintWriter out, String message){
        out.println("<html><body>");
        out.println(message);
        out.println("</html></body>");
        out.close();
    }
}
