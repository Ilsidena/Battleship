package servlets;

import com.google.gson.Gson;
import database.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FillFieldServlet extends HttpServlet {

    class Feild {
        private int gameID;
        private String field;

        public int getGameID() {
            return gameID;
        }

        public String getField() {
            return field;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //String cellIndex = request.getParameter("cellIndex"); // must be counted by js script
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        int id = Integer.parseInt(session.getAttribute("name").toString());
        StringBuffer buffer = new StringBuffer();
        String line = null;

        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
            buffer.append(line);

        Gson gson = new Gson();
        Feild field = gson.fromJson(buffer.toString(), Feild.class);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");

            GamesTable.fillField(con, id, field.getGameID(), field.getField());
            con.close();
        } catch (GameNotFoundException e){
            this.sendErrorMessage(out, e.getMessage());
            return;
        } catch (SQLException e){
            sendErrorMessage(out, "ERROR 500: Internal Error");
            return;
        }
    }

    private void sendErrorMessage(PrintWriter out, String message){
        out.println("<html><body>");
        out.println(message);
        out.println("</html></body>");
        out.close();
    }
}
