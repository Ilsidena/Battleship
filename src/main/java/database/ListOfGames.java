package database;

import java.util.ArrayList;
import java.util.Objects;

public class ListOfGames {
    private ArrayList<ListOfGamesRow> list;

    public ListOfGames() {
        this.list = new ArrayList<>();
    }

    public ArrayList<ListOfGamesRow> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListOfGames that = (ListOfGames) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
