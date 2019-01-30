package servlets;

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

public class NewGameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enemy = request.getParameter("enemy");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        int id = Integer.parseInt(session.getAttribute("name").toString());

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            int enemyID = UsersTable.getID(con, enemy);
            GamesTable.startGame(con, id, enemyID);
            con.close();
        } catch (GameNotFoundException e){
            this.sendErrorMessage(out, e.getMessage());
            return;
        } catch (SQLException e){
            sendErrorMessage(out, "ERROR 500: Internal Error");
            return;
        }
        request.getRequestDispatcher("user_games.html").include(request, response);
    }

    private void sendErrorMessage(PrintWriter out, String message){
        out.println("<html><body>");
        out.println(message);
        out.println("</html></body>");
        out.close();
    }
}
