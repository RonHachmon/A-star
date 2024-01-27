import AStar.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileToMazeConvertor {
    private final char WALL= '#';
    private final char PATH= '.';
    private final char START= 'S';
    private final char END= 'E';
    private Point startPoint;
    private Point endPoint;
    private final Point[][] maze;
    public FileToMazeConvertor(String fileName) {
        try {
            List<String> strings = Files.readAllLines(Paths.get(fileName));
            maze=new Point[strings.size()][strings.get(0).length()];
            buildMaze(strings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildMaze(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            String currentMazeRow = strings.get(i);
            for (int j = 0; j < currentMazeRow.length(); j++) {
                char mazeChar = currentMazeRow.charAt(j);
                Point currentMazePoint = new Point(i, j);
                assembleMazeChar(mazeChar, currentMazePoint);


            }
        }
    }

    private void assembleMazeChar(char mazeChar, Point currentMazePoint) {
        switch (mazeChar) {
            case WALL -> currentMazePoint.setBlocked(true);
            case PATH -> currentMazePoint.setBlocked(false);
            case START -> startPoint = currentMazePoint;
            case END -> endPoint = currentMazePoint;
            default -> throw new RuntimeException("Invalid character X" + mazeChar + "X in maze");
        }
        maze[currentMazePoint.getX()][currentMazePoint.getY()]=currentMazePoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point[][] getMaze() {
        return maze;
    }
}
