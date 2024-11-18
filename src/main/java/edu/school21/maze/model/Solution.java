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

    public void convertingPixelsToCells(double cellHeight, double cellWidth){
        getCell(start, cellHeight, cellWidth);
        getCell(finish, cellHeight, cellWidth);
    }

    private void getCell(Point point, double cellHeight, double cellWidth) {
        double cellX = point.getX() / cellWidth;
        double cellY  = point.getY() / cellHeight;
        point.setX((int)cellX);
        point.setY((int)cellY);
    }

    public void convertingCellsToPixels(double cellHeight, double cellWidth){
        for (var step: solutionArray) {
            double cellX = step.getX() * cellWidth + cellWidth / 2.00;
            double cellY = step.getY() * cellHeight + cellHeight / 2.00;
            step.setX((int)cellX);
            step.setY((int)cellY);
        }
    }
}
