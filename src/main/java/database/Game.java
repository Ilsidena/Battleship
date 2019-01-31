package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Game {
    static private final int N = 10;

    private int id = 0;
    private String field;
    private int idPlayer1 = 0;
    private int idPlayer2 = 0;
    private boolean isReady1 = false;
    private boolean isReady2 = false;
    private int move = 0;
    private int status = 0;
    private long startTime = 0;

    public Game(int id, String field, int idPlayer1, int idPlayer2, boolean isReady1, boolean isReady2, int move, int status, long startTime) {
        this.id = id;
        this.field = field;
        this.idPlayer1 = idPlayer1;
        this.idPlayer2 = idPlayer2;
        this.isReady1 = isReady1;
        this.isReady2 = isReady2;
        this.move = move;
        this.status = status;
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField( String field) {
        this.field = field;
    }

    public int getIdPlayer1() {
        return idPlayer1;
    }

    public void setIdPlayer1(int idPlayer1) {
        this.idPlayer1 = idPlayer1;
    }

    public int getIdPlayer2() {
        return idPlayer2;
    }

    public void setIdPlayer2(int idPlayer2) {
        this.idPlayer2 = idPlayer2;
    }

    public boolean isReady1() {
        return isReady1;
    }

    public void setReady1(boolean ready1) {
        isReady1 = ready1;
    }

    public boolean isReady2() {
        return isReady2;
    }

    public void setReady2(boolean ready2) {
        isReady2 = ready2;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id &&
                idPlayer1 == game.idPlayer1 &&
                idPlayer2 == game.idPlayer2 &&
                isReady1 == game.isReady1 &&
                isReady2 == game.isReady2 &&
                move == game.move &&
                status == game.status &&
                startTime == game.startTime &&
                Objects.equals(field, game.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, field, idPlayer1, idPlayer2, isReady1, isReady2, move, status, startTime);
    }
}
