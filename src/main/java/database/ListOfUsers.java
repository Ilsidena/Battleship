package database;

import java.util.ArrayList;

public class ListOfUsers {
    private ArrayList<Integer> id;
    private ArrayList<String> name;
    private ArrayList<String> login;
    private ArrayList<String> password;

    public ListOfUsers() {
        this.id = new ArrayList<>();
        this.name = new ArrayList<>();
        this.login = new ArrayList<>();
        this.password = new ArrayList<>();
    }

    public ArrayList<Integer> getId() {
        return id;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public ArrayList<String> getLogin() {
        return login;
    }

    public ArrayList<String> getPassword() {
        return password;
    }
}
