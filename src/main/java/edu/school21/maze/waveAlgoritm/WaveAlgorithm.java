package edu.school21.maze.waveAlgoritm;


import edu.school21.maze.model.Maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaveAlgorithm {
    private final Maze maze;
    private static List<Point> stepsForEnemies;
    private static Point stepForEnemies;

    public static List<Point> getStepsForEnemies() {
        return stepsForEnemies;
    }

    public static Point getStepForEnemies() {
        return stepForEnemies;
    }

    public WaveAlgorithm(Maze maze) {
        this.maze = maze;
    }

    private static final List<Point> clockwiseWalk = new ArrayList<>(Arrays.asList(
            new Point(-1, 0),
            new Point(0, 1),
            new Point(1, 0),
            new Point(0, -1)
    ));

    public static List<Point> getClockwiseWalk() {
        return clockwiseWalk;
    }

    public void  findPath(int startX, int startY, int finishX, int finishY) {
        startWave(startX, startY, finishX, finishY);
    }

    public void  startWave(int startX, int startY, int finishX, int finishY) {
        List<Point> wave = new ArrayList<>();
        List<Point> oldWave = new ArrayList<>();
        List<List<Integer>> mazeSolution = initializeSolutionArray();
        int step = 0;
        oldWave.add(new Point(startX, startY));
        while (!oldWave.isEmpty()) {
            step++;
            wave.clear();
            for (Point cell : oldWave) {
                    rightStepCheck(cell,wave, finishX, finishY);
                    leftStepCheck(cell,wave, finishX, finishY);
                    upStepCheck(cell,wave, finishX, finishY);
                    downStepCheck(cell,wave, finishX, finishY);
//                    int newX = cell.getX() + clockwiseWalk.get(i).getX();
//                    int newY = cell.getY() + clockwiseWalk.get(i).getY();
//                    if ((newX < maze.getNumberOfCols()) &&
//                            (newY < maze.getNumberOfRows()) &&
//                            (newX >= 0) &&
//                            (newY >= 0) &&
//                            ((copyGameField.get(newX).get(newY) == emptyChar || copyGameField.get(newX).get(newY) == playerChar))) {
//                        copyGameField.get(newX).set(newY, step);
//                        wave.add(new Point(newX, newY));
//                    }

            }
            oldWave = new ArrayList<>(wave);
        }
        return getPath(copyGameField, placePlay, placeGoal, playerChar);
    }

    private void downStepCheck(Point cell, List<Point> wave, int finishX, int finishY) {
        int newX = cell.getX() + clockwiseWalk.get(1).getX();
        int newY = cell.getY() + clockwiseWalk.get(1).getY();
        if (boundaryCheck(newX, newY) && (checkingForHorizontalWall(cell.getY(),cell.getX()) || gotToTheEnd(newX, newY, finishX, finishY))){
            wave.add(new Point(newX, newY));
        }
    }



    private void upStepCheck(Point cell, List<Point> wave, int finishX, int finishY) {
        int newX = cell.getX() + clockwiseWalk.get(3).getX();
        int newY = cell.getY() + clockwiseWalk.get(3).getY();
        if (boundaryCheck(newX, newY) && (checkingForHorizontalWall(newY, newX) || gotToTheEnd(newX, newY, finishX, finishY))){
            wave.add(new Point(newX, newY));
        }
    }

    private void leftStepCheck(Point cell, List<Point> wave, int finishX, int finishY) {
        int newX = cell.getX() + clockwiseWalk.get(0).getX();
        int newY = cell.getY() + clockwiseWalk.get(0).getY();
        if (boundaryCheck(newX, newY) && checkingForVerticalWall(newY, newX) || gotToTheEnd(newX, newY, finishX, finishY)){
            wave.add(new Point(newX, newY));
        }
    }



    private void rightStepCheck(Point cell, List<Point> wave, int finishX, int finishY) {
        int newX = cell.getX() + clockwiseWalk.get(2).getX();
        int newY = cell.getY() + clockwiseWalk.get(2).getY();
        if (boundaryCheck(newX, newY) && checkingForVerticalWall(cell.getY(), cell.getX()) || gotToTheEnd(newX, newY, finishX, finishY)){
            wave.add(new Point(newX, newY));
        }
    }
    private boolean gotToTheEnd(int newX, int newY,  int finishX, int finishY) {
        return (newX == finishX) && (newY == finishY);
    }

    private boolean checkingForHorizontalWall(int y, int x) {
        return maze.getHorizontalWall().get(y * maze.getNumberOfCols() + x) == 0;
    }
    private boolean checkingForVerticalWall(int y, int x) {
        return maze.getVerticalWall().get(y * maze.getNumberOfCols() + x) == 0;
    }

    private boolean boundaryCheck(int newX, int newY) {
        return (newX < maze.getNumberOfCols()) && (newY < maze.getNumberOfRows()) && (newX >= 0) && (newY >= 0);
    }

    public static boolean getPath(ArrayList<ArrayList<Integer>> copyGameField, Point placePlay, Point placeGoal, int playerChar) {
        if (copyGameField.get(placePlay.x).get(placePlay.y) == playerChar) {
            return false;
        }
        stepsForEnemies = new ArrayList<>();
        stepForEnemies = new Point();
        copyGameField.get(placeGoal.x).set(placeGoal.y, 0);

        Point bypass = new Point();
        bypass.x = placePlay.x;
        bypass.y = placePlay.y;

        while (!bypass.equals(placeGoal)) {
            for (int i = 0, flag = 0; i < 4 && flag == 0; i++) {
                int newX = bypass.x + clockwiseWalk.get(i).x;
                int newY = bypass.y + clockwiseWalk.get(i).y;
                if ((newX < copyGameField.size()) &&
                        (newY < copyGameField.size()) &&
                        (newX >= 0) &&
                        (newY >= 0) && (copyGameField.get(bypass.x).get(bypass.y) - 1 == copyGameField.get(newX).get(newY))) {
                    bypass.x = newX;
                    bypass.y = newY;
                    flag = 1;
                    if (!bypass.equals(placeGoal)) {
                        stepForEnemies.x = newX;
                        stepForEnemies.y = newY;
                        stepsForEnemies.add(new Point(newX, newY));
                    }
                }
            }

        }
        return true;

    }

    private List<List<Integer>>initializeSolutionArray() {
        List<List<Integer>> mazeSolution = new ArrayList<>();
        for (int  i = 0; i < maze.getNumberOfRows(); i++) {
            mazeSolution.add(new ArrayList<>());
            for (int j = 0; j < maze.getNumberOfCols(); j++) {
                mazeSolution.get(i).add(0);
            }
        }
        return mazeSolution;
    }

}
