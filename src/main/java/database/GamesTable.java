package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GamesTable {
    public static Game getGame (Connection con, int gameID, int id) {
        try {
            String sql = "SELECT field, idPlayer1, idPlayer2, isReady1, isReady2, move, status, startTime FROM battleship.games WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, gameID);
            int move = 0;

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next() == false)
                throw new GameNotFoundException(gameID);

            if ((rs.getInt(6) % 2 == 1 && id == rs.getInt(2))
                    || (rs.getInt(6) % 2 == 0 && id == rs.getInt(3))) {
                move = 1;
            }

            return new Game(gameID
                    , rs.getString(1) /*createCellArray(rs.getString(1))*/ // create Cell array
                    , rs.getInt(2)
                    , rs.getInt(3)
                    , rs.getString(4).equals("1")
                    , rs.getString(5).equals("1")
                    , move
                    , rs.getInt(7)
                    , rs.getLong(8));

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

//    static private Cell [] createCellArray(String data){
//        if (data.length() == 1)
//            return new Cell[1];
//
//        Cell array [] = new Cell [200];
//
//        for (int i = 0; i < 200; ++i)
//            array[i] = new Cell(data.charAt(2 * i) == '1'
//                                , data.charAt(2 * i + 1) == '1');
//
//        return array;
//    }

    public static void updateGame (Connection con, int id, String field, int isReady1, int isReady2, int move, int status, long startTime) {
        String sql = "UPDATE  games set field = ?, isReady1 = ?, isReady2 = ?,move = ?, status = ?, startTime = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, field);
            preparedStatement.setInt(2, isReady1);
            preparedStatement.setInt(3, isReady2);
            preparedStatement.setInt(4, move);
            preparedStatement.setInt(5, status);
            preparedStatement.setLong(6, startTime);
            preparedStatement.setInt(7, id);

            int rs = preparedStatement.executeUpdate();
            if(rs == 0)
                throw new GameUpdateException(id);
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }
    }

    public static int getActiveGames (Connection con, int id) {
        try {
            String sql = "SELECT COUNT(*) FROM battleship.games WHERE idPlayer1 = ? OR idPlayer2 = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next() == false)
                throw new GameNotFoundException(id);

            rs.first();
            return rs.getInt(1);

        } catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int getActiveGames (Connection con) {
        try {
            String sql = "SELECT COUNT(*) FROM battleship.games";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            rs.first();
            return rs.getInt(1);

        } catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public static ListOfGames getUserGames (Connection con, int id) {
        try {
            String sql = "SELECT id, idPlayer1, idPlayer2, status FROM battleship.games WHERE idPlayer1 = ? OR idPlayer2 = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);

            ResultSet rs = preparedStatement.executeQuery();
            ListOfGames listOfGames = new ListOfGames();
            rs.first();

            for (int i = 0; i < rs.getRow(); ++i){
                int enemyID = 0;

                if (id == rs.getInt(2))
                    enemyID = rs.getInt(3);
                else
                    enemyID = rs.getInt(2);

                sql = "SELECT name FROM battleship.users WHERE id = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, enemyID);
                ResultSet enemy = preparedStatement.executeQuery();
                enemy.first();

                listOfGames.getList().add(new ListOfGamesRow(rs.getInt(1)
                                            , enemy.getString(1)
                                            , rs.getInt(4)));

                rs.next();
            }
            return listOfGames;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static ListOfGames getAllGames (Connection con) {
        try {
            String sql = "SELECT id, idPlayer1, idPlayer2, status FROM battleship.games";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            ListOfGames listOfGames = new ListOfGames();
            rs.first();

            for (int i = 0; i < rs.getRow(); ++i){
                sql = "SELECT name FROM battleship.users WHERE id = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, rs.getInt(2));
                ResultSet player1 = preparedStatement.executeQuery();
                player1.first();

                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, rs.getInt(3));
                ResultSet player2 = preparedStatement.executeQuery();
                player2.first();

                listOfGames.getList().add(new ListOfGamesRow(rs.getInt(1)
                                                            , player1.getString(1) + "\n" + player2.getString(1)
                                                            , rs.getInt(4)));
                rs.next();
            }

            return listOfGames;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void fillField (Connection con, int id, int gameID, String field) {
        try {
            Game game = getGame(con, gameID, id);
            int n = 0;
            int move = 0;
            int status = -1;

            if (id == game.getIdPlayer1()) {
                n = 1;
                field += game.getField().substring(100);
            }
            else {
                n = 2;
                field = game.getField().substring(0, 100) + field;
            }

            if (game.isReady1() && game.isReady2()){
                move = 1;
                status = 0;
            }

            String sql = "UPDATE battleship.games SET field = ?, isReady" + n + " = ?, move = ?, status = ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, field);
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, move);
            preparedStatement.setInt(4, status);
            preparedStatement.setInt(5, gameID);

            int rs = preparedStatement.executeUpdate();
            if(rs == 0)
                throw new GameUpdateException(id);
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }

    }

    public static void move (Connection con, int id, int gameID, int cell) {
        try {
            String sql = "SELECT field, idPlayer1, idPlayer2, isReady1, isReady2, move, status, startTime FROM battleship.games WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, gameID);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next() == false)
                throw new GameNotFoundException(gameID);

            Game game = new Game(gameID
                    , rs.getString(1) /*createCellArray(rs.getString(1))*/ // create Cell array
                    , rs.getInt(2)
                    , rs.getInt(3)
                    , rs.getString(4).equals("1")
                    , rs.getString(5).equals("1")
                    , rs.getInt(6)
                    , rs.getInt(7)
                    , rs.getLong(8));

            int player = 0;
            int move = game.getMove() + 1;
            int status = game.getStatus();
            char [] field = game.getField().toCharArray();

            if (id == game.getIdPlayer1()){
                player = 1;
                field [2 * cell + 1] = '1';
            }
            else if (id == game.getIdPlayer2()){
                player = 2;
                field [2 * cell + 1] = '1';
            }
            else
                throw new UserNotFoundException(id);

            if (isWinner(field, player))
                status = player;

            sql = "UPDATE battleship.games SET field = ?, move = ?, status = ? WHERE id = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, new String(field));
            preparedStatement.setInt(2, move);
            preparedStatement.setInt(3, status);
            preparedStatement.setInt(4, gameID);

            int r = preparedStatement.executeUpdate();
            if(r == 0)
                throw new GameUpdateException(id);
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }

    }

    private static boolean isWinner (char [] field, int player){
        char sum = 0;
        int i = 0;
        int n = 200;

        if(player == 1) {
            i = n;
            n += 200;
        }

        for (i = 0; i < n; i += 2)
            sum += field[i] & field[i + 1];

        return sum == 20;
    }

    public static void startGame (Connection con, int idPlayer1, int idPlayer2) {
        try {
            if(idPlayer1 == idPlayer2)
                throw new GameUpdateException(-1);
            String sql = "INSERT INTO battleship.games (field, idPlayer1, idPlayer2, isReady1, isReady2, move, status, startTime) values ('0000000000000000000000101010000000001000000000000000000000000000001010001010000000100000000000000000000000100000000000000010001000001000001000100010000000000010001000100000000000000000000000001000000000000000000000000000001010100000000010000000000000000000000000000010100010100000001000000000000000000000001000000000000000100010000010000010001000100000000000100010001000000000000000000000000010000000', ?, ?, 1, 1, 0, 0, 0)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idPlayer1);
            preparedStatement.setInt(2, idPlayer2);
            int rs = preparedStatement.executeUpdate();
            if(rs == 0)
                throw new GameUpdateException(-1);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
