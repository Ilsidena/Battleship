package database;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private long scores;
    private boolean isAdmin;
    private boolean isBot;

    User(String data){
        String [] array = data.split(":");
        this.id = Integer.parseInt(array[0]);
        this.name = array[1];
        this.login = array[2];
        this.password = array[3];
        this.scores = Long.parseLong(array[4]);
        this.isAdmin = array[5].equals("true");
        this.isBot = array[6].equals("true");
    }

    public User(int id, String name, String login, String password, long scores, boolean isAdmin, boolean isBot) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.scores = scores;
        this.isAdmin = isAdmin;
        this.isBot = isBot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                scores == user.scores &&
                isAdmin == user.isAdmin &&
                isBot == user.isBot &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, scores, isAdmin, isBot);
    }
}
