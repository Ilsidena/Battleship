package database;

import java.util.Objects;

public class ListOfGamesRow {
    private int gameID;
    private String players;
    private int status;

    public ListOfGamesRow(int gameID, String players, int status) {
        this.gameID = gameID;
        this.players = players;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListOfGamesRow that = (ListOfGamesRow) o;
        return gameID == that.gameID &&
                status == that.status &&
                Objects.equals(players, that.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, players, status);
    }
}
