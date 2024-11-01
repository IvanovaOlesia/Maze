package edu.school21.maze.model;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private final int NumberOfCols;
    private final int NumberOfRows;

    private List<Integer> horizontalWall;
    private List<Integer> verticalWall;

    public Maze(int numberOfCols, int numberOfRows) {
        NumberOfCols = numberOfCols;
        NumberOfRows = numberOfRows;
        horizontalWall = new ArrayList<>();
        verticalWall = new ArrayList<>();
    }

    public int getNumberOfCols() {
        return NumberOfCols;
    }


    public int getNumberOfRows() {
        return NumberOfRows;
    }


    public List<Integer> getHorizontalWall() {
        return horizontalWall;
    }

    public List<Integer> getVerticalWall() {
        return verticalWall;
    }

    public void putVerticalWall(Integer value){
        verticalWall.add(value);
    }
    public void putHorizontalWall(Integer value){
        horizontalWall.add(value);
    }

}
