package AStar;

import java.util.*;

public class AStarPath {
    private StarPoint[][] grid = new StarPoint[4][7];
    private StarPoint start=null;
    private StarPoint end=null;
    private final int GRID_WIDTH = 4;
    private final int GRID_LENGTH = 7;
    private Set<StarPoint> enteredPoints = new HashSet<>();
    private Set<StarPoint> finishedPoints = new HashSet<>();

    public AStarPath() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                grid[i][j] = new StarPoint(new Point(i, j));
            }
        }
        start = grid[3][1];
        end = grid[1][6];
        start.SetCostFromStart(0);
        setBlockedPath();
    }

    private void setBlockedPath() {
        grid[1][3].setBlocked(true);
        grid[1][4].setBlocked(true);
        grid[2][4].setBlocked(true);
    }

    public void getPath() {
        enteredPoints.add(start);
        this.updateAllValidNeighboursCost(start);
        visitPoint(start);
        while (!enteredPoints.contains(end) || enteredPoints.isEmpty()) {
            StarPoint minCostPoint = this.findMinCostPoint();
            System.out.println("last visited Min point (" + minCostPoint.getCurrentPoint().getX() + " " + minCostPoint.getCurrentPoint().getY() + ")");
            visitPoint(minCostPoint);
            updateAllValidNeighboursCost(minCostPoint);

        }

        if(enteredPoints.contains(end)) {
            this.printPath();
        }
        else {
            System.out.println("Path not found");
        }
    }

    private void visitPoint(StarPoint start) {
        enteredPoints.remove(start);
        finishedPoints.add(start);
    }

    private void printPath() {
        Stack<String> path = new Stack<>();
        Point parent = end.getCurrentPoint();
        do {
            path.add("(" + parent.getX() + " " + parent.getY() + ")");
            parent = grid[parent.getX()][parent.getY()].getParent();
        }
        while (parent != null);

        while (!path.isEmpty()) {
            if(path.size() == 1)
                System.out.print(path.pop());
            else
                System.out.print(path.pop() + " -> ");

        }

    }

    private StarPoint findMinCostPoint() {
        StarPoint minPoint = null;
        for (StarPoint point : enteredPoints) {
            if (minPoint == null) {
                minPoint = point;
            } else {
                if (point.GetTotalCost() < minPoint.GetTotalCost()) {
                    minPoint = point;
                }
                if (point.GetTotalCost() == minPoint.GetTotalCost() && point.GetHeuristicCost() < minPoint.GetHeuristicCost()) {
                    minPoint = point;
                }
            }
        }

        return minPoint;
    }

    private void updateAllValidNeighboursCost(StarPoint currentPoint) {
        List<Point> neighbours = currentPoint.getCurrentPoint().GetSquareNeighbours(1);
        for (Point neighbour : neighbours) {
            if (isPointInGridBorder(neighbour)) {
                StarPoint neighbourPoint = grid[neighbour.getX()][neighbour.getY()];
                if (!neighbourPoint.isBlocked() && !this.finishedPoints.contains(neighbourPoint)) {
                    updateStarPointCost(currentPoint, neighbourPoint);

                }
            }
        }
    }

    private void updateStarPointCost(StarPoint currentPoint, StarPoint neighbourPoint) {
        neighbourPoint.SetHeuristicCost(this.calculateHcost(neighbourPoint.getCurrentPoint()));
        int addValue;
        if (this.isPointVerticalOrHorizontal(currentPoint, neighbourPoint)) {
            addValue = 10;
        } else {
            addValue = 14;
        }
        if (neighbourPoint.GetCostFromStart() == null || neighbourPoint.GetCostFromStart() > currentPoint.GetCostFromStart() + addValue) {
            neighbourPoint.SetCostFromStart(currentPoint.GetCostFromStart() + addValue);
            neighbourPoint.setParent(currentPoint.getCurrentPoint());

        }
        enteredPoints.add(neighbourPoint);
    }


    private int calculateHcost(Point currentPoint) {
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
}
