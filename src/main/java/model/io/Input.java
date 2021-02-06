package model.io;

import model.BatteryBaseObject;
import model.CoordinatesWithDirection;

import java.util.Arrays;

public class Input extends BatteryBaseObject {

    private Character[][] map;
    private CoordinatesWithDirection start;
    private String[] commands;

    public Character[][] getMap() {
        return map;
    }

    public void setMap(Character[][] map) {
        this.map = map;
    }

    public CoordinatesWithDirection getStart() {
        return start;
    }

    public void setStart(CoordinatesWithDirection start) {
        this.start = start;
    }

    public String[] getCommands() {
        return commands;
    }

    public void setCommands(String[] commands) {
        this.commands = commands;
    }

    @Override
    public String toString() {
        return "Input{" +
                "map=" + Arrays.toString(map) +
                ", start=" + start +
                ", commands=" + Arrays.toString(commands) +
                ", battery=" + getBattery() +
                '}';
    }
}
