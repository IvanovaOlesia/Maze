package edu.school21.maze.model;

import java.util.List;

public class Maze {
    private final int NumberOfCols;
    private final int NumberOfRows;

    private List<List<Integer>> bottomWall;
    private List<List<Integer>> rightWall;

    public Maze(int numberOfCols, int numberOfRows) {
        NumberOfCols = numberOfCols;
        NumberOfRows = numberOfRows;
    }

    public int getNumberOfCols() {
        return NumberOfCols;
    }


    public int getNumberOfRows() {
        return NumberOfRows;
    }


    public List<List<Integer>> getBottomWall() {
        return bottomWall;
    }

    public List<List<Integer>> getRightWall() {
        return rightWall;
    }

    public void putRightWall(int row, Integer value){
        rightWall.get(row).add(value);
    }
    public void putBottomWall(int row, Integer value){
        bottomWall.get(row).add(value);
    }
}
