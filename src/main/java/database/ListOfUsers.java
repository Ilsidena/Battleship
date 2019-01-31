package database;

import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListOfUsers that = (ListOfUsers) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password);
    }
}
