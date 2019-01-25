package database;

import java.util.ArrayList;
import java.util.HashMap;

public class Rating {
    private ArrayList<RatingRow> players;

    public Rating() {
        this.players = new ArrayList<>();
    }

    public ArrayList<RatingRow> getPlayers() {
        return players;
    }
}
