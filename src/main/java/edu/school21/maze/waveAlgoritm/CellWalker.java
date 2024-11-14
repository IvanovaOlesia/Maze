package edu.school21.maze.waveAlgoritm;

import edu.school21.maze.model.Maze;
import edu.school21.maze.model.Point;

import java.util.List;

public class CellWalker {
    private final CircularMotion[] circularMotions =  {
                 this::stepLeft,
                this::stepDown,
                this::stepRight,
                this::stepUp
    };

    public CircularMotion[] getCircularMotions() {
        return circularMotions;
    }

    private boolean stepLeft(Point newPoint, Point oldPoint, List<List<Integer>>mazeSolution, Maze maze, boolean waveLaunched){
        return checkingValidValues(newPoint, oldPoint ,mazeSolution, maze, waveLaunched)  && (checkingForVerticalWall(newPoint.getY(), newPoint.getX(),maze));
    }
    private boolean stepDown(Point newPoint, Point oldPoint,List<List<Integer>>mazeSolution, Maze maze, boolean waveLaunched){
        return  checkingValidValues(newPoint, oldPoint ,mazeSolution, maze, waveLaunched) && (checkingForHorizontalWall(oldPoint.getY(), oldPoint.getX(),maze));
    }
    private boolean stepRight(Point newPoint, Point oldPoint,List<List<Integer>>mazeSolution, Maze maze, boolean waveLaunched){
        return checkingValidValues(newPoint, oldPoint ,mazeSolution, maze, waveLaunched) && (checkingForVerticalWall(oldPoint.getY(), oldPoint.getX(), maze));
    }
    private boolean stepUp(Point newPoint, Point oldPoint,List<List<Integer>>mazeSolution, Maze maze, boolean waveLaunched){
        return checkingValidValues(newPoint, oldPoint ,mazeSolution, maze, waveLaunched) && (checkingForHorizontalWall(newPoint.getY(), newPoint.getX(),maze));
    }
    private boolean checkingValidValues(Point newPoint,Point oldPoint, List<List<Integer>>mazeSolution, Maze maze, boolean waveLaunched) {
        return boundaryCheck(newPoint.getX(), newPoint.getY(), maze) && checkCorrectValue(newPoint,oldPoint ,mazeSolution,waveLaunched);
    }

    private boolean checkCorrectValue(Point newPoint,Point oldPoint, List<List<Integer>> mazeSolution, boolean waveLaunched) {
        if (waveLaunched) {
            return (mazeSolution.get(newPoint.getY()).get(newPoint.getX()) == 0);
        }else{
            return (mazeSolution.get(oldPoint.getY()).get(oldPoint.getX()) - 1 == mazeSolution.get(newPoint.getY()).get(newPoint.getX()));
        }

    }

    private boolean boundaryCheck(int newX, int newY, Maze maze) {
        return (newX < maze.getNumberOfCols()) && (newY < maze.getNumberOfRows()) && (newX >= 0) && (newY >= 0);
    }
    private boolean checkingForHorizontalWall(int y, int x, Maze maze) {
        return maze.getHorizontalWall().get(y * maze.getNumberOfCols() + x) == 0;
    }

    private boolean checkingForVerticalWall(int y, int x, Maze maze) {
        return maze.getVerticalWall().get(y * maze.getNumberOfCols() + x) == 0;
    }
}
