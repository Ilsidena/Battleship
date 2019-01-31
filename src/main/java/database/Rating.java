package database;

import java.util.ArrayList;
import java.util.Objects;

public class Rating {
    private ArrayList<RatingRow> players;

    public Rating() {
        this.players = new ArrayList<>();
    }

    public ArrayList<RatingRow> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(players, rating.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }
}
