package fr.sogeti.mid.model;

public enum Direction {

    North("N"),
    East("E"),
    West("W"),
    South("S");



    Direction(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    private String direction;

    public static Direction fromValue(String direction) {
        for (Direction b : Direction.values()) {
            if (b.direction.equalsIgnoreCase(direction)) {
                return b;
            }
        }
        throw new RuntimeException("Direction "+direction+" is not supported");
    }



}
