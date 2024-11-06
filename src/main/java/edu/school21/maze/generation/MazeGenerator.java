package edu.school21.maze.generation;

import edu.school21.maze.model.Maze;
import edu.school21.maze.model.SetData;
import edu.school21.maze.services.LineService;
import edu.school21.maze.services.SetService;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class MazeGenerator {
    private int setFillCounter;
    private List<Integer> decisionArray;
    private int indexDecisionArray;
    private final SetService setService;

    private final LineService lineService;


    public MazeGenerator(Maze maze) {
        this.maze = maze;
        decisionArray = new ArrayList<>();
//        decisionArray = new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1));
        setService = new SetService();
        setFillCounter = 1;
        indexDecisionArray = 0;
        lineService = new LineService(maze.getNumberOfCols());
    }

    public void mazeGeneration() {
        generateRandomNumber();
        for (int i = 0; i < maze.getNumberOfRows(); i++) {
            assignSetsToArrayCells();
            verticalWallPlacement();
            if (i == maze.getNumberOfRows() - 1){
                lastLineProcessing();
            }else {
                placeWallOnTheHorizontal();
                resetSetData();
                resetCellsWithHorizontalWalls();
            }
        }
    }

    /**
     * The method generates a random number 0 or 1.
     */
    public void generateRandomNumber() {
        decisionArray = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(2))
                .limit(maze.getNumberOfCols() * maze.getNumberOfRows() * 2L)
                .boxed()
                .toList();
    }
    private void assignSetsToArrayCells() {
        IntStream.range(0, maze.getNumberOfCols())
                .filter(i -> lineService.getSetByIndex(i) == 0)
                .forEach(i -> lineService.setCellSet(i, setFillCounter++));
        incrementCellCount();
    }
    private void verticalWallPlacement() {
        for (int i = 0; i < lineService.getSize(); i++) {
            if (decisionArray.get(indexDecisionArray++) == 0) {
                if (i < lineService.getSize() - 1) {
                    processingDecisionNotToPlaceVerticalWall(i);
                } else {
                    maze.putVerticalWall(1);
                }
            } else {
                maze.putVerticalWall(1);
            }
        }
    }
    private void processingDecisionNotToPlaceVerticalWall(int index) {
        if (compareWithRightCell(index)) {
            maze.putVerticalWall(1);
        } else {
            equateSets(index);
        }
    }



    private void resetSetData() {
        for (Integer index : lineService.getLine()) {
            setService.resetNumberOfCellsInSet(index);
            setService.resetNumberOfHorizontalWallsInSet(index);
        }
    }

    private void resetCellsWithHorizontalWalls() {
        for (Integer index : lineService.getIndexSetWithHorizontalWall()) {
            lineService.setCellByIndex(index, 0);
        }
        lineService.getIndexSetWithHorizontalWall().clear();
    }


    private void lastLineProcessing() {
        for (int i = 0; i < lineService.getSize(); i++) {
            maze.putHorizontalWall(1);
            if ((i != lineService.getSize() - 1) && (!lineService.compareSets(i, lineService.getCellByIndex(i + 1)))) {
                maze.getVerticalWall().set((maze.getNumberOfRows() - 1) * maze.getNumberOfCols() + i, 0);
                Integer rightSet = lineService.getCellByIndex(i + 1);
                Integer currentSet = lineService.getCellByIndex(i);
                for (int j = 0; j < lineService.getSize(); j++) {
                    if (lineService.compareSets(j, rightSet)) {
                        lineService.setCellByIndex(j, currentSet);
                    }
                }
            }
        }
    }


    private void equateSets(int index) {
        maze.putVerticalWall(0);
        Integer currentSet = lineService.getCellByIndex(index);
        Integer rightCell = lineService.getCellByIndex(index + 1);
        for (int j = 0; j < lineService.getSize(); j++) {
            if (lineService.getCellByIndex(j).equals(rightCell)) {
                lineService.setCellByIndex(j, currentSet);
                setService.incrementNumberOfCellsInSet(currentSet);
                setService.decrementNumberOfCellsInSet(rightCell);
            }
        }
    }

    private boolean compareWithRightCell(int index) {
        return lineService.getCellByIndex(index).equals(lineService.getCellByIndex(index + 1));
    }

    private void placeWallOnTheHorizontal() {
        for (int i = 0; i < lineService.getSize(); i++) {
            if (decisionArray.get(indexDecisionArray++) == 1) {
                processingDecisionToPlaceHorizontalWall(i);
            } else {
                maze.putHorizontalWall(0);
            }
        }
    }
   private void processingDecisionToPlaceHorizontalWall(int index){
       if (setService.getNumberOfCellsWithoutHorizontalWall(lineService.getCellByIndex(index)) > 1) {
           maze.putHorizontalWall(1);
           setService.incrementNumberOfHorizontalWallInSet(lineService.getCellByIndex(index));
           lineService.addIndexSetWithHorizontalWall(index);
       } else {
           maze.putHorizontalWall(0);
       }
    }
    private void incrementCellCount() {
        for (Integer set : lineService.getLine()) {
            if (setService.containsSet(set)) {
                setService.incrementNumberOfCellsInSet(set);
            } else {
                setService.addNewSet(set, new SetData(1, 0));
            }
        }
    }
}