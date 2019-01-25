package database;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String login){
        super("Not found user with login \"" + login + "\"\n");
    }

    public UserNotFoundException(int id){
        super("Not found user with id \"" + id + "\"\n");
    }
}
