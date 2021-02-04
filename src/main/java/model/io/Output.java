package model.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.BatteryBaseObject;
import model.Coordinates;
import model.CoordinatesWithDirection;

import java.util.List;

public class Output extends BatteryBaseObject {

    private List<Coordinates> visited;
    private List<Coordinates> cleaned;

    @JsonProperty("abstract")
    private CoordinatesWithDirection result;
    

}
