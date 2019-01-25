package database;

public class GameUpdateException extends RuntimeException {
    public GameUpdateException(int id) {
        super("Can not update game \"" + id + "\"\n");
    }
}
