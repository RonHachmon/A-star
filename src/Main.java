import AStar.AStarPath;
import AStar.Point;

public class Main {
    public static void main(String[] args) {
        runPathFind2();

    }

    private static void runPathFind() {
        Point[][] grid=initGrid(4,7);
        AStarPath path = new AStarPath(grid);
        blockPath(grid,1,3);
        blockPath(grid,1,4);
        blockPath(grid,2,4);
        path.SetStartPoint(grid[3][1]);
        path.SetEndPoint(grid[1][6]);
        path.getPath();
    }
    private static void runPathFind2() {
        Point[][] grid=initGrid(6,11);
        AStarPath path = new AStarPath(grid);
        blockPath(grid,1,3);
        blockPath(grid,2,3);
        blockPath(grid,2,4);
        blockPath(grid,2,5);
        blockPath(grid,2,6);
        blockPath(grid,2,7);
        path.SetStartPoint(grid[4][7]);
        path.SetEndPoint(grid[1][4]);
        path.getPath();
    }


    private static Point[][] initGrid(int width,int length) {
        Point[][] grid= new Point[width][length];

        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[i].length ; j++) {
                grid[i][j] = new Point(i,j);
            }
        }
        return grid;
    }
    private static void blockPath(Point[][] grid,int width,int length) {

        grid[width][length].setBlocked(true);
    }
}