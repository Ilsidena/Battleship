package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class GamesTableTest {
    private int id = 1;

    @Before
    public void setUp() throws Exception {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");

            String sql = "insert into battleship.games (field, idPlayer1, idPlayer2, isReady1, isReady2, move, status, startTime) values ('0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', 3, 4, 0, 0, 0, 8, 0)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");

            String sql = "delete from battleship.games where status = 8";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getGame() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            assertEquals(0, GamesTable.getGame(con,id + 1, 3).getMove());
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getActiveGames() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            assertEquals(2, GamesTable.getActiveGames(con, 3));
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getActiveGames1() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            assertEquals(2, GamesTable.getActiveGames(con));
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUserGames() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            assertEquals(2, GamesTable.getUserGames(con,3).getList().size());
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getAllGames() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            assertEquals(2, GamesTable.getAllGames(con).getList().size());
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void move() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");

            GamesTable.move(con, 3, id + 6, 0);
            assertEquals(1, GamesTable.getGame(con,id + 6, 3).getMove());
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void startGame() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/battleship?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "anna"
                    , "12345678");
            GamesTable.startGame(con, 3, 4);
            String sql = "SELECT COUNT(*) FROM battleship.games";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            rs.first();
            assertEquals(2, rs.getInt(1));
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}