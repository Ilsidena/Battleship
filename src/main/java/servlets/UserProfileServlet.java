package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.*;

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

public class UserProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        User user = null;
        int activeGames = 0;
        HttpSession session = request.getSession();
        String id = session.getAttribute("name").toString();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            user = UsersTable.getUser(con, Integer.parseInt(id));
            activeGames = GamesTable.getActiveGames(con, Integer.parseInt(id));
            con.close();
        } catch (GameNotFoundException e){
            this.sendErrorMessage(out, e.getMessage());
            return;
        } catch (SQLException e){
            sendErrorMessage(out, "ERROR 500: Internal Error");
            return;
        }

        if(user == null || activeGames == -1) {
            sendErrorMessage(out,"ERROR 500: Internal Error");
            return;
        }

        request.getRequestDispatcher("user_header.html").include(request, response);
        out.println("<html><body>");
        out.println("<p>Name: " + user.getName() + "</p>");
        out.println("<p>Active games: " + activeGames + "</p>");
        out.println("<p>Scores: " + user.getScores() + "</p>");
        out.println("<p>Status: online</p>");
        out.println("<form id=\"logout\" action=\"logout\" method=\"get\">"
                        + "<input type=\"submit\" value=\"Log Out\">"
                    + "</form>");
        out.println("</html></body>");
        out.close();
    }

    private void sendErrorMessage(PrintWriter out, String message){
        out.println("<html><body>");
        out.println(message);
        out.println("</html></body>");
        out.close();
    }
}
