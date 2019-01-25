package servlets;

import database.ExistingUserExceptiont;
import database.User;
import database.UserNotFoundException;
import database.UsersTable;

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

public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        User user = null;

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            user = UsersTable.newUser(con, login, password, name);
            con.close();
        } catch (UserNotFoundException e){
            this.sendErrorMessage(out, e.getMessage());
            return;
        } catch (ExistingUserExceptiont e){
            this.sendErrorMessage(out, e.getMessage());
            return;
        } catch (SQLException e){
            sendErrorMessage(out, "ERROR 500: Internal Error");
            return;
        }

        out.println("<html><body>");
        if(user == null) {
            out.println("Signup Error");
            return;
        }

        if (user.isAdmin()) {
            request.getRequestDispatcher("admin_header.html").include(request, response);
            HttpSession session = request.getSession();
            session.setAttribute("name", user.getId());
            out.println("Hello, admin"); // for check
        } else if (user.isBot()){
            // do something
        } else {
            request.getRequestDispatcher("user_header.html").include(request, response);
            HttpSession session = request.getSession();
            session.setAttribute("name", user.getId());
            out.println("Welcome"); // for check
        }
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
