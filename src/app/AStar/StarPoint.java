package app.AStar;

import java.util.Objects;

public class StarPoint {
    private final Point currentPoint;

    private Point parent;
    private final Cost cost=new Cost();


    public StarPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }


    public Point getParent() {
        return parent;
    }

    public void setParent(Point parent) {
        this.parent = parent;
    }

    public Integer GetCostFromStart() {
        return cost.getCostFromStart();

    }

    public void SetCostFromStart(Integer costFromStart) {
        cost.setCostFromStart(costFromStart);
    }
    public void SetHeuristicCost(Integer heuristicCost) {
        cost.setHeuristicCost(heuristicCost);
    }

    public Integer GetHeuristicCost() {
        return cost.getHeuristicCost();
    }





    public Integer GetTotalCost() {

        return cost.getTotalCost();
    }





    @Override
    public int hashCode() {
        return Objects.hash(currentPoint);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarPoint starPoint = (StarPoint) o;
        return  Objects.equals(currentPoint, starPoint.currentPoint) && Objects.equals(parent, starPoint.parent) && Objects.equals(cost, starPoint.cost);
    }

    @Override
    public String toString() {
        return currentPoint.toString();
    }
}
