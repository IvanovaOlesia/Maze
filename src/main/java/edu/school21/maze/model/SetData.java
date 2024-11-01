package edu.school21.maze.model;


public class SetData {

    private int numberOfCellsInSet;
    private int numberOfHorizontalWallsInSet;

    public SetData(int numberOfCellsInSet, int numberOfHorizontalWallsInSet) {
        this.numberOfCellsInSet = numberOfCellsInSet;
        this.numberOfHorizontalWallsInSet = numberOfHorizontalWallsInSet;
    }


    public int getNumberOfCellsInSet() {
        return numberOfCellsInSet;
    }

    public void setNumberOfCellsInSet(int numberOfCellsInSet) {
        this.numberOfCellsInSet = numberOfCellsInSet;
    }

    public int getNumberOfHorizontalWallsInSet() {
        return numberOfHorizontalWallsInSet;
    }

    public void setNumberOfHorizontalWallsInSet(int numberOfHorizontalWallsInSet) {
        this.numberOfHorizontalWallsInSet = numberOfHorizontalWallsInSet;
    }
}
