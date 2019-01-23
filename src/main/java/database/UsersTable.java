package database;

import java.sql.*;

public class UsersTable {
    public static User getUser(Connection con, String login, String password) {
        try {
            String sql = "SELECT id, name, isAdmin, isBot FROM battleship.users WHERE login = ? and password = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next() == false)
                throw new UserNotFoundException(login);

            return new User(rs.getString(1)
                            , rs.getString(2)
                            , rs.getString(3).equals("1")
                            , rs.getString(4).equals("1"));
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
                String sql = "INSERT INTO battleship.users (name, login, password, isAdmin, isBot) VALUES (?, ?, ?, 0, 0)";
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
}
