package servlets;

import database.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        User user = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                                                            , "anna"
                                                            , "12345678");
            user = UsersTable.getUser(con, login, password);
        } catch (UserNotFoundException e){
            this.sendErrorMessage(out, e.getMessage());
            return;
        } catch (SQLException e){
            sendErrorMessage(out, "ERROR 500: Internal Error");
            return;
        }

        out.println("<html><body>");
        if(user == null) {
            out.println("Login Error");
            return;
        }

        if (user.isAdmin()) {
            request.getRequestDispatcher("admin_head.html").include(request, response);
            HttpSession session = request.getSession();
            session.setAttribute("name", user.getId());
            out.println("Hello, admin"); // for check
        } else if (user.isBot()){
            // do something
        } else {
            request.getRequestDispatcher("user_profile.html").include(request, response);
            HttpSession session = request.getSession();
            session.setAttribute("name", user.getId());
            out.println("Welcome, " + user.getName()); // for check
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