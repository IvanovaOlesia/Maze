package edu.school21.maze.model;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private final int NumberOfCols;
    private final int NumberOfRows;

    private List<Integer> bottomWall;
    private List<Integer> rightWall;

    public Maze(int numberOfCols, int numberOfRows) {
        NumberOfCols = numberOfCols;
        NumberOfRows = numberOfRows;
        bottomWall = new ArrayList<>();
        rightWall = new ArrayList<>();
    }

    public int getNumberOfCols() {
        return NumberOfCols;
    }


    public int getNumberOfRows() {
        return NumberOfRows;
    }


    public List<Integer> getBottomWall() {
        return bottomWall;
    }

    public List<Integer> getRightWall() {
        return rightWall;
    }

    public void putRightWall(Integer value){
        rightWall.add(value);
    }
    public void putBottomWall(Integer value){
        bottomWall.add(value);
    }
}
