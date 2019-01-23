package database;

public class ExistingUserExceptiont extends RuntimeException {
    public ExistingUserExceptiont(String login){
        super("User \"" + login + "\" has already been created\n");
    }
}
