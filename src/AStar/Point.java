package AStar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {
    private final int x;
    private final int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }



    public int getY() {
        return y;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public List<Point> GetSquareNeighbours(int squareLength) {
        List<Point> neighbours = new ArrayList<>();
        for (int i=-squareLength;i<=squareLength;i++)
        {
            for (int j = -squareLength; j <=squareLength ; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int newX = x + i;
                int newY = y + j;
                neighbours.add(new Point(newX,newY));
            }
        }
        return neighbours;
    }
    public List<Point> GetCrossNeighbours(int crossLength) {
        List<Point> neighbours = new ArrayList<>();
        for (int i = 1; i <=crossLength ; i++) {
            neighbours.add(new Point(x -i , y ));
            neighbours.add(new Point(x +i , y ));
            neighbours.add(new Point(x  , y-i ));
            neighbours.add(new Point(x  , y+i ));
        }
        return neighbours;
    }

    public boolean IsVerticalOrHorizontalToPoint(Point currentPoint) {
        return x == currentPoint.x || y == currentPoint.y;
    }
}
