package database;

public class ListOfGamesRow {
    private int gameID;
    private String players;
    private int status;

    public ListOfGamesRow(int gameID, String players, int status) {
        this.gameID = gameID;
        this.players = players;
        this.status = status;
    }
}
