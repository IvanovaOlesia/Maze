package edu.school21.maze.model;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final Point start;
    private final Point finish;
    private final List<Point> solutionArray;

    public Solution(List<Point> coordinates) {
        this.start = coordinates.get(0);
        this.finish = coordinates.get(1);
        this.solutionArray = new ArrayList<>();
    }

    public Point getStart() {
        return start;
    }

    public Point getFinish() {
        return finish;
    }

    public List<Point> getSolutionArray() {
        return solutionArray;
    }

    public void addSolutionStep(Point step) {
        this.solutionArray.add(step);
    }

    public void convertingPixelsToCells(int cellHeight, int cellWidth){
        getCell(start, cellHeight, cellWidth);
        getCell(finish, cellHeight, cellWidth);
    }

    private void getCell(Point point, int cellHeight, int cellWidth) {
        int cellX = point.getX() / cellWidth;
        int cellY  = point.getY() / cellHeight;
        point.setX(cellX);
        point.setY(cellY);
    }

    public void convertingCellsToPixels(int cellHeight, int cellWidth){
        for (var step: solutionArray) {
            int cellX = step.getX() * cellWidth + cellWidth / 2;
            int cellY = step.getY() * cellHeight + cellHeight / 2;
            step.setX(cellX);
            step.setY(cellY);
        }
    }
}
