package edu.school21.maze.waveAlgoritm;


import edu.school21.maze.model.Maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaveAlgorithm {
    private final Maze maze;
    private int step;
    private final List<List<Integer>> mazeSolution;
    private boolean isStep;

    public WaveAlgorithm(Maze maze) {
        this.maze = maze;
        this.step = 1;
        this.mazeSolution = initializeSolutionArray();
    }

    private static final List<Point> clockwiseWalk = new ArrayList<>(Arrays.asList(
            new Point(-1, 0),
            new Point(0, 1),
            new Point(1, 0),
            new Point(0, -1)
    ));


    public void findPath(int startX, int startY, int finishX, int finishY) {
        startWave(startX, startY);
        getPath(startX, startY, finishX, finishY);
        for (var value : mazeSolution) {
            System.out.println(value);
        }
        System.out.println();
    }

    public void startWave(int startX, int startY) {
        List<Point> wave;
        List<Point> oldWave = new ArrayList<>();
        oldWave.add(new Point(startX, startY));
        mazeSolution.get(startY).set(startX, 1);
        while (!oldWave.isEmpty()) {
            step++;
            wave = new ArrayList<>();
            goingAroundInCircle(wave, oldWave);
            oldWave = new ArrayList<>();
            for (var value : wave) {
                oldWave.add(new Point(value.getX(), value.getY()));
            }
        }
        for (var value : mazeSolution) {
            System.out.println(value);
        }
        System.out.println();

    }

    private void goingAroundInCircle(List<Point> wave, List<Point> oldWave) {
        for (Point cell : oldWave) {
            stepRight(cell, wave);
            stepLeft(cell, wave);
            stepUp(cell, wave);
            stepDown(cell, wave);
        }
    }

    private void stepDown(Point cell, List<Point> wave) {
        int newX = cell.getX() + clockwiseWalk.get(1).getX();
        int newY = cell.getY() + clockwiseWalk.get(1).getY();
        if (boundaryCheck(newX, newY) && (mazeSolution.get(newY).get(newX) == 0) && (checkingForHorizontalWall(cell.getY(), cell.getX()))) {
            mazeSolution.get(newY).set(newX, step);
            wave.add(new Point(newX, newY));
        }
    }


    private void stepUp(Point cell, List<Point> wave) {
        int newX = cell.getX() + clockwiseWalk.get(3).getX();
        int newY = cell.getY() + clockwiseWalk.get(3).getY();
        if (boundaryCheck(newX, newY) && (mazeSolution.get(newY).get(newX) == 0) && (checkingForHorizontalWall(newY, newX))) {
            mazeSolution.get(newY).set(newX, step);
            wave.add(new Point(newX, newY));
        }
    }

    private void stepLeft(Point cell, List<Point> wave) {
        int newX = cell.getX() + clockwiseWalk.get(0).getX();
        int newY = cell.getY() + clockwiseWalk.get(0).getY();
        if (boundaryCheck(newX, newY) && (mazeSolution.get(newY).get(newX) == 0) && (checkingForVerticalWall(newY, newX))) {
            mazeSolution.get(newY).set(newX, step);
            wave.add(new Point(newX, newY));
        }
    }


    private void stepRight(Point cell, List<Point> wave) {
        int newX = cell.getX() + clockwiseWalk.get(2).getX();
        int newY = cell.getY() + clockwiseWalk.get(2).getY();
        if (boundaryCheck(newX, newY) && (mazeSolution.get(newY).get(newX) == 0) && (checkingForVerticalWall(cell.getY(), cell.getX()))) {
            mazeSolution.get(newY).set(newX, step);
            wave.add(new Point(newX, newY));
        }
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

    public void getPath(int startX, int startY, int finishX, int finishY) {
        Point currentWave = new Point(finishX, finishY);
        Point goal = new Point(startX, startY);
        while (!currentWave.equals(goal)) {
            isStep = true;
            right(currentWave);
            left(currentWave);
            up(currentWave);
            down(currentWave);
        }
    }

    private void down(Point currentWave) {
        int newX = currentWave.getX() + clockwiseWalk.get(1).getX();
        int newY = currentWave.getY() + clockwiseWalk.get(1).getY();
        if (isStep && boundaryCheck(newX, newY) && (mazeSolution.get(currentWave.getY()).get(currentWave.getX()) - 1 == mazeSolution.get(newY).get(newX)) && (checkingForHorizontalWall(currentWave.getY(), currentWave.getX()))) {
            mazeSolution.get(currentWave.getY()).set(currentWave.getX(), 0);
            currentWave.setX(newX);
            currentWave.setY(newY);
            isStep = false;
        }
    }

    private void up(Point currentWave) {
        int newX = currentWave.getX() + clockwiseWalk.get(3).getX();
        int newY = currentWave.getY() + clockwiseWalk.get(3).getY();
        if (isStep && boundaryCheck(newX, newY) && (mazeSolution.get(currentWave.getY()).get(currentWave.getX()) - 1 == mazeSolution.get(newY).get(newX)) && (checkingForHorizontalWall(newY, newX))) {
            mazeSolution.get(currentWave.getY()).set(currentWave.getX(), 0);
            currentWave.setX(newX);
            currentWave.setY(newY);
            isStep = false;
        }

    }

    private void left(Point currentWave) {
        int newX = currentWave.getX() + clockwiseWalk.get(0).getX();
        int newY = currentWave.getY() + clockwiseWalk.get(0).getY();
        if (isStep && boundaryCheck(newX, newY) && (mazeSolution.get(currentWave.getY()).get(currentWave.getX()) - 1 == mazeSolution.get(newY).get(newX)) && (checkingForVerticalWall(newY, newX))) {
            mazeSolution.get(currentWave.getY()).set(currentWave.getX(), 0);
            currentWave.setX(newX);
            currentWave.setY(newY);
            isStep = false;
        }
    }

    private void right(Point currentWave) {
        int newX = currentWave.getX() + clockwiseWalk.get(2).getX();
        int newY = currentWave.getY() + clockwiseWalk.get(2).getY();
        if (isStep && boundaryCheck(newX, newY) && (mazeSolution.get(currentWave.getY()).get(currentWave.getX()) - 1 == mazeSolution.get(newY).get(newX)) && (checkingForVerticalWall(currentWave.getY(), currentWave.getX()))) {
            mazeSolution.get(currentWave.getY()).set(currentWave.getX(), 0);
            currentWave.setX(newX);
            currentWave.setY(newY);
            isStep = false;
        }
    }

    private List<List<Integer>> initializeSolutionArray() {
        List<List<Integer>> mazeSolution = new ArrayList<>();
        for (int i = 0; i < maze.getNumberOfRows(); i++) {
            mazeSolution.add(new ArrayList<>());
            for (int j = 0; j < maze.getNumberOfCols(); j++) {
                mazeSolution.get(i).add(0);
            }
        }
        return mazeSolution;
    }


}
