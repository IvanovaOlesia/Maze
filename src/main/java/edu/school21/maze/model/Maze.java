package edu.school21.maze.model;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private final int numberOfCols;
    private final int numberOfRows;

    private List<Integer> horizontalWall;
    private List<Integer> verticalWall;

    public Maze(int numberOfRows, int numberOfCols) {
        this.numberOfCols = numberOfCols;
        this.numberOfRows = numberOfRows;
        horizontalWall = new ArrayList<>();
        verticalWall = new ArrayList<>();
    }

    public int getNumberOfCols() {
        return numberOfCols;
    }


    public int getNumberOfRows() {
        return numberOfRows;
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
