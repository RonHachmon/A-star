package app.gui;

import app.AStar.AStarPath;
import app.AStar.Point;
import app.AStar.StarPoint;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class PathPrinter {

    private final AStarPath path;
    private final ColoredString[][] coloredMap;

    public PathPrinter(AStarPath path) {
        this.path = path;
        coloredMap = new ColoredString[path.getGrid().length][path.getGrid()[0].length];
        buildColoredMap(path);
    }

    private void buildColoredMap(AStarPath path) {
        StarPoint[][] grid = path.getGrid();
        Point end = path.getEnd().getCurrentPoint();
        Point start = path.getStart().getCurrentPoint();
        for (int i = 0; i < coloredMap.length; i++) {
            for (int j = 0; j < coloredMap[0].length; j++) {
                Point point = grid[i][j].getCurrentPoint();
                if (point.isBlocked()) {
                    ColoredString coloredString = new ColoredString("#");
                    coloredString.setColor(StringPrintEffect.RED);
                    coloredMap[i][j] = coloredString;
                } else {

                    if (start == point || end == point) {

                        if (start == point) {
                            ColoredString coloredString = new ColoredString("S");
                            coloredString.setEffect(StringPrintEffect.BOLD);
                            coloredMap[i][j] = coloredString;

                        }

                        else {
                            ColoredString coloredString = new ColoredString("E");
                            coloredString.setEffect(StringPrintEffect.BOLD);
                            coloredMap[i][j] = coloredString;
                        }

                    } else {
                        ColoredString coloredString = new ColoredString(".");
                        coloredString.setColor(StringPrintEffect.WHITE);
                        coloredMap[i][j] = coloredString;
                    }
                }

            }
        }
    }

    public void Print() {
        printMaze();
        Set<StarPoint> enteredPoints = path.getFinishedPoints();
        System.out.println();
        for (StarPoint enterPoint : enteredPoints) {
            if(enterPoint!=path.getStart()&& enterPoint!=path.getEnd())
            {
                ColoredString coloredString = coloredMap[enterPoint.getCurrentPoint().getX()][enterPoint.getCurrentPoint().getY()];
                coloredString.setColor(StringPrintEffect.BLUE);
                coloredString.setEffect(StringPrintEffect.BOLD);
                sleep(300);
                clearWindowsTerminalScreen();
                printMaze();
            }
        }
        this.markCorrectPath();
        clearWindowsTerminalScreen();
        printMaze();



    }

    private void markCorrectPath() {
        List<Point> pathToEnd = path.getPathToEnd();
        for (Point point : pathToEnd) {
            ColoredString coloredString = coloredMap[point.getX()][point.getY()];
            coloredString.setColor(StringPrintEffect.GREEN);
            coloredString.setEffect(StringPrintEffect.BOLD);
        }
    }

    private  void clearWindowsTerminalScreen() {
        if (System.getProperty("os.name").contains("Windows")) {

            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls").inheritIO();
            try {
                processBuilder.start().waitFor();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private  void sleep(int sleepTimeInMilliSeconds) {
        try {
            Thread.sleep(sleepTimeInMilliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printMaze() {
        System.out.println("_________________________");
        for (int i = 0; i < coloredMap.length; i++) {
            for (int j = 0; j < coloredMap[0].length; j++) {
                System.out.print(coloredMap[i][j]);
            }
            System.out.println();
        }
        System.out.println("_________________________");
    }
}
