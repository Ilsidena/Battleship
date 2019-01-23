package database;

public class User {
    private String id;
    private String name;
    private String login;
    private String password;
    private boolean isAdmin;
    private boolean isBot;

    User(String data){
        String [] array = data.split(":");
        this.id = array[0];
        this.name = array[1];
        this.login = array[2];
        this.password = array[2];
        this.isAdmin = array[4].equals("true");
        this.isBot = array[5].equals("true");
    }

    public User(String id, String name, boolean isAdmin, boolean isBot) {
        this.id = id;
        this.name = name;
        this.isAdmin = isAdmin;
        this.isBot = isBot;
    }

    public User(String id, String name, String login, String password, boolean isAdmin, boolean isBot) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBot = isBot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
