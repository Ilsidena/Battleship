package database;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(int id){
        super("Not found game \"" + id + "\"\n");
    }
}
