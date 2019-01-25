package database;

public class ListOfGamesRow {
    private int GameID;
    private String players;
    private int status;

    public ListOfGamesRow(int gameID, String players, int status) {
        GameID = gameID;
        this.players = players;
        this.status = status;
    }
}
