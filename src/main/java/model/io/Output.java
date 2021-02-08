package model.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import model.BatteryBaseObject;
import model.Coordinates;
import model.CoordinatesWithDirection;

import java.util.HashSet;
import java.util.Set;

@JsonPropertyOrder({"visited", "cleaned", "final", "battery"})
public class Output extends BatteryBaseObject {

    @JsonUnwrapped
    private Set<Coordinates> visited = new HashSet<>();
    private Set<Coordinates> cleaned = new HashSet<>();

    @JsonProperty("final")
    private CoordinatesWithDirection result;

    public Set<Coordinates> getVisited() {
        return visited;
    }

    public void setVisited(Set<Coordinates> visited) {
        this.visited = visited;
    }

    public Set<Coordinates> getCleaned() {
        return cleaned;
    }

    public void setCleaned(Set<Coordinates> cleaned) {
        this.cleaned = cleaned;
    }

    public CoordinatesWithDirection getResult() {
        return result;
    }

    public void setResult(CoordinatesWithDirection result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Output{" +
                "visited=" + visited +
                ", cleaned=" + cleaned +
                ", result=" + result +
                ", battery=" + getBattery() +
                '}';
    }
}
