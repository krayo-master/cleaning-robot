import com.fasterxml.jackson.databind.ObjectMapper;
import model.Coordinates;
import model.CoordinatesWithDirection;
import model.io.Input;
import model.io.Output;

import java.io.File;
import java.io.IOException;

public class CleaningRobotApp {

    private static final String[][] backOffSequence = {{"TR", "A", "TL"}, {"TR", "A", "TR"}, {"TR", "A", "TR"}, {"TR", "B", "TR", "A"}, {"TL", "TL", "A"}};
    private static int battery;

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Output output = new Output();
        try {
            //TODO FILE? READER? STREAM?
            Input input = objectMapper.readValue(new File("src/main/resources/test2.json"), Input.class);
            int[] position = new int[]{input.getStart().getX(), input.getStart().getY()};
            output.getVisited().add(new Coordinates(position[0], position[1]));
            char facing = input.getStart().getFacing();
            battery = input.getBattery();
            processCommands(input.getCommands(), position, facing, input.getMap(), output);
            output.setBattery(battery);
            System.out.println("output - cleaned: " + output.toString());
        } catch (IOException ioex) {
            System.out.println("ERROR: json file not found " + ioex.getMessage());
        }
    }


    private static boolean processCommands(String[] commands, int[] position, char facing, Character[][] map, Output output) {
        int backOffSequenceIndex = 0;
        for (int i = 0; i < commands.length; i++) {
            System.out.println("current position : " + position[0] + "," + position[1] + " , facing: " + facing);
            switch (commands[i]) {
                case "TL":
                    facing = switchFacing("TL", facing);
                    battery--;
                    break;
                case "TR":
                    facing = switchFacing("TR", facing);
                    battery--;
                    break;
                case "A":
                    if (!move(position, facing, map, true, output) && backOffSequenceIndex < 5) {
                        processCommands(backOffSequence[backOffSequenceIndex], position, facing, map, output);
                        backOffSequenceIndex++;
                    }
                    output.getVisited().add(new Coordinates(position[0], position[1]));
                    battery = battery - 2;
                    break;
                case "B":
                    if (!move(position, facing, map, false, output) && backOffSequenceIndex < 5) {
                        processCommands(backOffSequence[backOffSequenceIndex], position, facing, map, output);
                        backOffSequenceIndex++;
                    }
                    output.getVisited().add(new Coordinates(position[0], position[1]));
                    battery = battery - 3;
                    break;
                case "C":
                    output.getCleaned().add(new Coordinates(position[0], position[1]));
                    battery = battery - 5;
                    break;

            }
            output.setResult(new CoordinatesWithDirection(position[0],position[1],facing));
        }
        return true;
    }

    private static char switchFacing(String turn, char currentFacing) {
        if (turn.equals("TL")) {
            switch (currentFacing) {
                case 'N':
                    return 'W';
                case 'E':
                    return 'N';
                case 'S':
                    return 'E';
                case 'W':
                    return 'S';
            }
        } else if (turn.equals("TR")) {
            switch (currentFacing) {
                case 'N':
                    return 'E';
                case 'E':
                    return 'S';
                case 'S':
                    return 'W';
                case 'W':
                    return 'N';
            }
        }
        return 'X';
    }

    private static boolean move(int[] position, char facing, Character[][] map, boolean isForward, Output output) {
        int x = position[0];
        int y = position[1];

        int newX = 0;
        int newY = 0;

        if (isForward) {
            switch (facing) {
                case 'N':
                    newY = y - 1;
                    newX = x;
                    break;
                case 'E':
                    newX = x + 1;
                    newY = y;
                    break;
                case 'S':
                    newY = y + 1;
                    newX = x;
                    break;
                case 'W':
                    newX = x - 1;
                    newY = y;
                    break;
            }
        } else {
            switch (facing) {
                case 'N':
                    newY = y + 1;
                    newX = x;
                    break;
                case 'E':
                    newX = x - 1;
                    newY = y;
                    break;
                case 'S':
                    newY = y - 1;
                    newX = x;
                    break;
                case 'W':
                    newX = x + 1;
                    newY = y;
                    break;
            }
        }

        if (newX >= 0 && newY >= 0 && newX < 4 && newY < 4 && map[newY][newX] != 'C' && map[newY][newX] != null) {
            position[0] = newX;
            position[1] = newY;
            return true;
        } else {
            return false;
        }
    }
}
