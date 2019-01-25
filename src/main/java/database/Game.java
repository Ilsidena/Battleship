package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Game {
    static private final int N = 10;

    private int id = 0;
    //private Cell [] field;
    private String field;
    private int idPlayer1 = 0;
    private int idPlayer2 = 0;
    private boolean isReady1 = false;
    private boolean isReady2 = false;
    private int move = 0;
    private int status = 0;
    private long startTime = 0;

    public Game(int id, /*Cell[]*/ String field, int idPlayer1, int idPlayer2, boolean isReady1, boolean isReady2, int move, int status, long startTime) {
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

    public /*Cell[]*/ String getField() {
        return field;
    }

    public void setField(/*Cell[]*/ String field) {
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
}
