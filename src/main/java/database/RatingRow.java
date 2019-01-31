package database;

import java.util.Objects;

public class RatingRow {
    private String name;
    private int scores;

    public RatingRow(String name, int scores) {
        this.name = name;
        this.scores = scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingRow ratingRow = (RatingRow) o;
        return scores == ratingRow.scores &&
                Objects.equals(name, ratingRow.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scores);
    }
}
