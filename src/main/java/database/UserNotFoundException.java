package database;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String login){
        super("Not found user \"" + login + "\"\n");
    }
}