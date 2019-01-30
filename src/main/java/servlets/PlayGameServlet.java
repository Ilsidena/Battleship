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

public class PlayGameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String gameID = request.getParameter("gameID");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //request.getRequestDispatcher("field.html").include(request, response);
//        out.println("<html><head>\n" +
//                "    <meta charset=\"ISO-8859-1\">\n" +
//                "    <title>Battleship</title>\n" +
//                "    <script src=\"js/jquery-3.3.1.min.js\"></script>\n" +
//                "    <script>\n" +
//                "    $(function(){\n" +
//                "      $(\"#includedContent\").load(\"field.html\");\n" +
//                "    });\n" +
//                "    </script>\n" +
//                "</head><body>");
//        out.println("<div id=point gameID=\"" + gameID + "\"></div>" +
//                "<div id=\"includedContent\"></div>");
//        out.println("</body><script src=\"js/x.js\"></script></html>");


        out.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                "    <title>Battleship</title>\n" +
                "    <script src=\"js/jquery-3.3.1.min.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"input-form-container\">\n" +
                "        <div id=\"pointpoint\" gameID=\"" + gameID + "\"></div>" +
                "    <h2 id=\"player_move\"></h2>\n" +
                "    <div class=\"sb-field\">\n" +
                "        <h3>Your field</h3>\n" +
                "        <table>\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <th></th>\n" +
                "                <th>1</th> <th>2</th> <th>3</th> <th>4</th> <th>5</th>\n" +
                "                <th>6</th> <th>7</th> <th>8</th> <th>9</th> <th>10</th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tr>\n" +
                "                <th>A</th>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>B</th>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>C</th>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>D</th>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>E</th>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>F</th>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>G</th>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>H</th>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>I</th>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>J</th>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "                <td><div class=\"sb-element\"></div></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "    <div class=\"sb-field\">\n" +
                "        <h3>Enemy's field</h3>\n" +
                "        <table>\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <th></th>\n" +
                "                <th>1</th> <th>2</th> <th>3</th> <th>4</th> <th>5</th>\n" +
                "                <th>6</th> <th>7</th> <th>8</th> <th>9</th> <th>10</th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tr>\n" +
                "                <th>A</th>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>B</th>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>C</th>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>D</th>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>E</th>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>F</th>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>G</th>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>H</th>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>I</th>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>J</th>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "                <td><div class=\"sb-element sb-target\"></div></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<!--<script src=\"js/build_field.js\"></script>-->\n" +
                "</body>\n" +
                "<script src=\"js/x.js\"></script>\n" +
                "</html>");
        out.close();

    }

    private void sendErrorMessage(PrintWriter out, String message){
        out.println("<html><body>");
        out.println(message);
        out.println("</html></body>");
        out.close();
    }
}

