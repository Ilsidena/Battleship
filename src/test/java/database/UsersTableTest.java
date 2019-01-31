package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class UsersTableTest {
    @Test
    public void getUser() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");

            assertEquals(0, UsersTable.getUser(con, 3).getScores());
            assertEquals(0, UsersTable.getUser(con, 4).getScores());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUser1() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");

            assertEquals(0, UsersTable.getUser(con, "mike", "1").getScores());
            assertEquals(0, UsersTable.getUser(con, "christina", "1").getScores());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUsersRating() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            Rating rating = new Rating();
            rating.getPlayers().add(new RatingRow("Mike", 0));
            rating.getPlayers().add(new RatingRow("Christina", 0));
            assertEquals(rating, UsersTable.getUsersRating(con));
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getID() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            assertEquals(3, UsersTable.getID(con, "Mike"));
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}