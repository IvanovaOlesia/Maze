package edu.school21.maze.waveAlgoritm;


import edu.school21.maze.model.Maze;
import edu.school21.maze.model.Point;
import edu.school21.maze.model.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaveAlgorithm {
    private final Maze maze;
    private int step;
    private final List<List<Integer>> mazeSolution;
    private boolean isStep;
    private boolean waveLaunched;
    private final Solution solution;
    private final CellWalker circularMotion = new CellWalker();

    public WaveAlgorithm(Maze maze, Solution solution) {
        this.maze = maze;
        this.solution = solution;
        this.step = 1;
        this.mazeSolution = initializeSolutionArray();

    }

    private static final List<Point> clockwiseWalk = new ArrayList<>(Arrays.asList(
            new Point(-1, 0),
            new Point(0, 1),
            new Point(1, 0),
            new Point(0, -1)
    ));


    public void findPath() {
        waveLaunched = true;
        startWave(solution.getStart().getX(), solution.getStart().getY());
        waveLaunched = false;
        getPath(solution.getStart().getX(), solution.getStart().getY(), solution.getFinish().getX(), solution.getFinish().getY());
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
    }

    private void goingAroundInCircle(List<Point> wave, List<Point> oldWave) {
        for (Point cell : oldWave) {
            for (int i = 0; i < 4; i++) {
                int newX = cell.getX() + clockwiseWalk.get(i).getX();
                int newY = cell.getY() + clockwiseWalk.get(i).getY();
                if(circularMotion.getCircularMotions()[i].step(new Point(newX,newY),cell, mazeSolution, maze, waveLaunched)){
                    mazeSolution.get(newY).set(newX, step);
                    wave.add(new Point(newX, newY));
                }
            }
        }
    }


    public void getPath(int startX, int startY, int finishX, int finishY) {
        Point currentWave = new Point(finishX, finishY);
        Point goal = new Point(startX, startY);
        while (!currentWave.equals(goal)) {
            isStep = true;
            for (int i = 0; i < 4 && isStep; i++) {
                int newX = currentWave.getX() + clockwiseWalk.get(i).getX();
                int newY = currentWave.getY() + clockwiseWalk.get(i).getY();
                if(circularMotion.getCircularMotions()[i].step(new Point(newX,newY),currentWave, mazeSolution, maze, waveLaunched)){
                    addSolutionCoordinates(currentWave, newX, newY);
                }
            }
        }
        solution.addSolutionStep(new Point(goal.getX(), goal.getY()));
    }


    private void addSolutionCoordinates(Point currentWave, int newX, int newY) {
        solution.addSolutionStep(new Point(currentWave.getX(), currentWave.getY()));
        currentWave.setX(newX);
        currentWave.setY(newY);
        isStep = false;
    }


    private List<List<Integer>> initializeSolutionArray() {
        List<List<Integer>> solutionArray = new ArrayList<>();
        for (int i = 0; i < maze.getNumberOfRows(); i++) {
            solutionArray.add(new ArrayList<>());
            for (int j = 0; j < maze.getNumberOfCols(); j++) {
                solutionArray.get(i).add(0);
            }
        }
        return solutionArray;
    }
}