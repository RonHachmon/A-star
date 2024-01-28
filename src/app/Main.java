package app;

import app.AStar.AStarPath;
import app.gui.PathPrinter;



public class Main {
    public static void main(String[] args) {
        runPathFind3();

    }

    private static void runPathFind() {
        FileToMazeConvertor fileToMazeConvertor = new FileToMazeConvertor("resources/4X7_maze.txt");
        AStarPath path = setPathParameters(fileToMazeConvertor);
        path.getPath();
        PathPrinter pathPrinter = new PathPrinter(path);
        System.out.println();
        pathPrinter.Print();

    }

    private static AStarPath setPathParameters(FileToMazeConvertor fileToMazeConvertor) {
        AStarPath path = new AStarPath(fileToMazeConvertor.getMaze());
        path.SetStartPoint(fileToMazeConvertor.getStartPoint());
        path.SetEndPoint(fileToMazeConvertor.getEndPoint());
        return path;
    }

    private static void runPathFind2() {
        FileToMazeConvertor fileToMazeConvertor = new FileToMazeConvertor("resources/6X11_maze");
        AStarPath path = setPathParameters(fileToMazeConvertor);
        path.getPath();
    }
    private static void runPathFind3() {
        FileToMazeConvertor fileToMazeConvertor = new FileToMazeConvertor("resources/12X13_maze");
        AStarPath path = setPathParameters(fileToMazeConvertor);
        path.getPath();
        PathPrinter pathPrinter = new PathPrinter(path);
        System.out.println();
        pathPrinter.Print();

    }
}