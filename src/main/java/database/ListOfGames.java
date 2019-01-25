package database;

import java.util.ArrayList;

public class ListOfGames {
    private ArrayList<ListOfGamesRow> list;

    public ListOfGames() {
        this.list = new ArrayList<>();
    }

    public ArrayList<ListOfGamesRow> getList() {
        return list;
    }
}
