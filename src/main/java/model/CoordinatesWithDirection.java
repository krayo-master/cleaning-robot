package model;

public class CoordinatesWithDirection extends Coordinates{

    private char facing;

    public CoordinatesWithDirection(int x, int y, char facing) {
        super(x, y);
        this.facing = facing;
    }

    public CoordinatesWithDirection() {

    }

    public char getFacing() {
        return facing;
    }

    public void setFacing(char facing) {
        this.facing = facing;
    }

    @Override
    public String toString() {
        return "CoordinatesWithDirection{" +
                "Coordinates{" +
                "x=" + getX() +
                ", y=" + getY() +
                '}' +
                "facing=" + facing +
                '}';
    }
}
