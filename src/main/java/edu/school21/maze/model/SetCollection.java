package edu.school21.maze.model;


public class SetCollection {

    private int numberOfCellsInSet;
    private int numberOfBottomWallsInSet;

    public SetCollection(int numberOfCellsInSet, int numberOfBottomWallsInSet) {
        this.numberOfCellsInSet = numberOfCellsInSet;
        this.numberOfBottomWallsInSet = numberOfBottomWallsInSet;
    }


    public int getNumberOfCellsInSet() {
        return numberOfCellsInSet;
    }

    public int getNumberOfBottomWallsInSet() {
        return numberOfBottomWallsInSet;
    }

    public void setNumberOfCellsInSet(int numberOfCellsInSet) {
        this.numberOfCellsInSet = numberOfCellsInSet;
    }

    public void setNumberOfBottomWallsInSet(int numberOfBottomWallsInSet) {
        this.numberOfBottomWallsInSet = numberOfBottomWallsInSet;
    }
}
