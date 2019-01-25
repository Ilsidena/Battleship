package database;

public class Cell {
    private boolean isShip = false;
    private boolean isSet = false;

    public Cell(boolean isShip, boolean isSet) {
        this.isShip = isShip;
        this.isSet = isSet;
    }

    public boolean isShip() {
        return isShip;
    }

    public void setShip(boolean ship) {
        isShip = ship;
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }
}
