package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.Game;
import database.GameNotFoundException;
import database.GamesTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetGameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String gameID = request.getParameter("id");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Game game = null;
        HttpSession session = request.getSession();
        int id = Integer.parseInt(session.getAttribute("name").toString());

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                                                            , "anna"
                                                            , "12345678");
            game = GamesTable.getGame(con, Integer.parseInt(gameID), id);
            con.close();
        } catch (GameNotFoundException e){
            this.sendErrorMessage(out, e.getMessage());
            return;
        } catch (SQLException e){
            sendErrorMessage(out, "ERROR 500: Internal Error");
            return;
        }

        if(game == null) {
            sendErrorMessage(out, "ERROR 500: Internal Error");
            return;
        }

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        out.println(gson.toJson(game));

        out.close();
    }

    private void sendErrorMessage(PrintWriter out, String message){
        out.println("<html><body>");
        out.println(message);
        out.println("</html></body>");
        out.close();
    }
}
