package AStar;

public  class Cost {
    private Integer costFromStart;
    private Integer heuristicCost;
    private Integer totalCost;

    public Cost()
    {
        costFromStart = null;
        heuristicCost = null;
        totalCost = null;
    }

    public Integer getCostFromStart() {
        return costFromStart;
    }

    public void setCostFromStart(Integer costFromStart) {
        this.costFromStart = costFromStart;
        setTotalCost();
    }

    public Integer getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(Integer heuristicCost) {
        this.heuristicCost = heuristicCost;
        setTotalCost();
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    private void setTotalCost() {
        if (costFromStart == null || heuristicCost == null) {
            this.totalCost = null;
        } else {
            this.totalCost = costFromStart + heuristicCost;
        }
    }
}