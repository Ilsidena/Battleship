package database;

import java.sql.*;
import java.util.HashMap;

public class UsersTable {
    public static User getUser(Connection con, String login, String password) {
        try {
            String sql = "SELECT * FROM battleship.users WHERE login = ? and password = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next() == false)
                throw new UserNotFoundException(login);

            return new User(rs.getInt(1)
                            , rs.getString(2)
                            , rs.getString(3)
                            , rs.getString(4)
                            , Long.parseLong(rs.getString(5))
                            , rs.getString(6).equals("1")
                            , rs.getString(7).equals("1"));
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static User getUser(Connection con, int id) {
        try {
            String sql = "SELECT * FROM battleship.users WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next() == false)
                throw new UserNotFoundException(id);

            return new User(rs.getInt(1)
                    , rs.getString(2)
                    , rs.getString(3)
                    , rs.getString(4)
                    , Long.parseLong(rs.getString(5))
                    , rs.getString(6).equals("1")
                    , rs.getString(7).equals("1"));
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static User newUser(Connection con, String login, String password, String name) {
        try {
            getUser(con, login, password);
        } catch (UserNotFoundException e) {
            try {
                String sql = "INSERT INTO battleship.users (name, login, password, scores, isAdmin, isBot) VALUES (?, ?, ?, 0, 0, 0)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, login);
                preparedStatement.setString(3, password);

                int r = preparedStatement.executeUpdate();
                if (r == 0)
                    throw new UserNotFoundException(login);

                return getUser(con, login, password);
            } catch (SQLException se) {
                se.printStackTrace();
                return null;
            }
        }
        throw new ExistingUserExceptiont(login);
    }

    public static Rating getUsersRating(Connection con) {
        try {
            String sql = "SELECT name, scores FROM battleship.users WHERE isAdmin = ? AND isBot = ? ORDER BY scores DESC";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, 0);

            ResultSet rs = preparedStatement.executeQuery();
            Rating rating = new Rating();
            rs.first();

            for (int i = 0; i < rs.getRow(); ++i){
                rating.getPlayers().add(new RatingRow(rs.getString(1), rs.getInt(2)));
                rs.next();
            }

            return rating;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static ListOfUsers getAllUsers (Connection con) {
        try {
            String sql = "SELECT id, name, login, password FROM battleship.users";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            ListOfUsers listOfUsers = new ListOfUsers();
            rs.first();

            for (int i = 0; i < rs.getRow(); ++i){
                listOfUsers.getId().add(rs.getInt(1));
                listOfUsers.getName().add(rs.getString(2));
                listOfUsers.getLogin().add(rs.getString(3));
                listOfUsers.getPassword().add(rs.getString(4));
                rs.next();
            }

            return listOfUsers;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static int getID (Connection con, String name) throws SQLException {
            String sql = "SELECT id FROM battleship.users WHERE name = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            rs.first();

            return rs.getInt(1);
    }
}
