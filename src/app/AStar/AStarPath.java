package app.AStar;

import java.util.*;
import java.util.ArrayList;

public class AStarPath {
    private final int GRID_WIDTH ;
    private final int GRID_LENGTH ;
    private StarPoint[][] grid ;
    private StarPoint start=null;
    private StarPoint end=null;


    private boolean isDebugMode=false;
    private List<Point> pathToEnd = new ArrayList<>() ;

    private PriorityQueue<StarPoint> enteredPoints =new PriorityQueue<>();
    private Set<StarPoint> finishedPoints = new LinkedHashSet<>();

    public AStarPath(Point [] [] inputGrid) {
        GRID_WIDTH=inputGrid.length;
        GRID_LENGTH=inputGrid[0].length;
        initializeGrid(inputGrid);

    }
    public void SetStartPoint(Point start) {
        this.start=grid[start.getX()][start.getY()];
        this.start.SetCostFromStart(0);
    }
    public void SetEndPoint(Point end) {
        this.end=grid[end.getX()][end.getY()];
    }

    private void initializeGrid(Point[][] inputGrid) {
        this.grid=new StarPoint[GRID_WIDTH][GRID_LENGTH];
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_LENGTH; j++) {
                grid[i][j] = new StarPoint(inputGrid[i][j]);
            }
        }
    }



    public void findPath() {
        this.updateAllValidNeighboursCost(start);
        visitPoint(start);
        while (!enteredPoints.contains(end) || enteredPoints.isEmpty()) {
            StarPoint minCostPoint = this.removeMinCost();
            if(isDebugMode) {
                System.out.println("last visited Min point (" + minCostPoint.getCurrentPoint().getX() + " " + minCostPoint.getCurrentPoint().getY() + ")");
            }
            visitPoint(minCostPoint);
            updateAllValidNeighboursCost(minCostPoint);

        }
        this.buildPath();

    }

    private void visitPoint(StarPoint start) {
        finishedPoints.add(start);
    }
    private void buildPath() {
        Point parent = end.getCurrentPoint();
        do {
            this.pathToEnd.add(parent);
            parent = grid[parent.getX()][parent.getY()].getParent();
        }
        while (parent != null);
        Collections.reverse(this.pathToEnd);

    }

    private void printPath() {
        if(end.getParent()==null) {
            System.out.println("Path not found");
            return;
        }

        for (int i = 0; i <this.pathToEnd.size() ; i++) {
            Point point = pathToEnd.get(i);
            if(i!=this.pathToEnd.size()-1) {
                System.out.print(String.format("(%d %d) -> ", point.getX(), point.getY()));
            }
            else {
                System.out.print(String.format("(%d %d)", point.getX(), point.getY()));
            }


        }
    }

    private StarPoint removeMinCost() {

        return this.enteredPoints.poll();
    }

    private void updateAllValidNeighboursCost(StarPoint currentPoint) {
        List<Point> neighbours = currentPoint.getCurrentPoint().GetSquareNeighbours(1);
        for (Point neighbour : neighbours) {
            if (isPointInGridBorder(neighbour)) {
                StarPoint neighbourPoint = grid[neighbour.getX()][neighbour.getY()];
                if (!neighbourPoint.getCurrentPoint().isBlocked()&& !this.finishedPoints.contains(neighbourPoint)) {
                    updateStarPointCost(currentPoint, neighbourPoint);
                    enteredPoints.add(neighbourPoint);
                }
            }
        }
    }

    private void updateStarPointCost(StarPoint currentPoint, StarPoint neighbourPoint) {
        neighbourPoint.SetHeuristicCost(this.calculateHeuristicCost(neighbourPoint.getCurrentPoint()));
        int addValue =this.isPointVerticalOrHorizontal(currentPoint, neighbourPoint)? 10:14 ;

        if (neighbourPoint.GetCostFromStart() == null || neighbourPoint.GetCostFromStart() > currentPoint.GetCostFromStart() + addValue) {
            neighbourPoint.SetCostFromStart(currentPoint.GetCostFromStart() + addValue);
            neighbourPoint.setParent(currentPoint.getCurrentPoint());

        }
    }


    private int calculateHeuristicCost(Point currentPoint) {
        int a = Math.abs(currentPoint.getX() - end.getCurrentPoint().getX());
        int b = Math.abs(currentPoint.getY() - end.getCurrentPoint().getY());

        return Math.min(a, b) * 14 + (Math.max(a, b) - Math.min(a, b)) * 10;
    }

    private boolean isPointVerticalOrHorizontal(StarPoint pointOne, StarPoint pointTwo) {
        return pointOne.getCurrentPoint().IsVerticalOrHorizontalToPoint(pointTwo.getCurrentPoint());

    }

    private boolean isPointInGridBorder(Point point) {
        return point.getX() >= 0 && point.getX() < GRID_WIDTH && point.getY() >= 0 && point.getY() < GRID_LENGTH;
    }

    public Set<StarPoint> getFinishedPoints() {
        return finishedPoints;
    }

    public StarPoint[][] getGrid() {
        return grid;
    }

    public StarPoint getStart() {
        return start;
    }

    public StarPoint getEnd() {
        return end;
    }
    public List<Point> getPathToEnd() {
        return pathToEnd;
    }



}
