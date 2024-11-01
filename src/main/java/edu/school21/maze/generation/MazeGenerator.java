package edu.school21.maze.generation;

import edu.school21.maze.model.LineOfSets;
import edu.school21.maze.model.Maze;
import edu.school21.maze.model.SetData;
import edu.school21.maze.services.SetService;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class MazeGenerator {
    private final Maze maze;
    private int setFillCounter;
    private List<Integer> decisionArray;
    private final LineOfSets lineOfSets;
    private int indexDecisionArray;
    private SetService setService;


    public MazeGenerator(Maze maze) {
        this.maze = maze;
        decisionArray = new ArrayList<>();
        setService = new SetService();
        setFillCounter = 1;
        indexDecisionArray = 0;
        lineOfSets = new LineOfSets(maze.getNumberOfCols());
    }

    public void mazeGeneration() {
        generateRandomNumber();
        for (int i = 0; i < maze.getNumberOfRows(); i++) {
            assignSetsToArrayCells(lineOfSets);
            verticalWallPlacement(lineOfSets);
            if (i == maze.getNumberOfRows() - 1){
                lastLineProcessing(lineOfSets);
            }else {
                placeWallOnTheHorizontal(lineOfSets);
                resetSetData();
                resetCellsWithHorizontalWalls(lineOfSets);
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
    private void assignSetsToArrayCells(LineOfSets lineOfSets) {
        IntStream.range(0, maze.getNumberOfCols())
                .filter(i -> lineOfSets.getLine().get(i) == 0)
                .forEach(i -> lineOfSets.getLine().set(i, setFillCounter++));
        incrementCellCount(lineOfSets);
    }


    private void lastLineProcessing(LineOfSets lineOfSets) {
        for (int i = 0; i < lineOfSets.getSize(); i++) {
            maze.putHorizontalWall(1);
            if ((i != lineOfSets.getSize() - 1) && (!lineOfSets.getCellByIndex(i).equals(lineOfSets.getCellByIndex(i + 1)))) {
                maze.getVerticalWall().set((maze.getNumberOfRows() - 1) * maze.getNumberOfCols() + i, 0);
                Integer currentSet = lineOfSets.getCellByIndex(i);
                Integer rightCell = lineOfSets.getCellByIndex(i + 1);
                for (int j = 0; j < lineOfSets.getSize(); j++) {
                    if (lineOfSets.getCellByIndex(j).equals(rightCell)) {
                        lineOfSets.setCellByIndex(j, currentSet);
                    }
                }
            }
        }
    }

    private void resetSetData() {
            for (Integer index : lineOfSets.getLine()) {
                setService.resetNumberOfCellsInSet(index);
                setService.resetNumberOfHorizontalWallsInSet(index);
            }
    }

    private void resetCellsWithHorizontalWalls(LineOfSets lineOfSets) {
        for (Integer index : lineOfSets.getIndexSetWithHorizontalWall()) {
            lineOfSets.setCellByIndex(index, 0);
        }
        lineOfSets.getIndexSetWithHorizontalWall().clear();
    }

    private void verticalWallPlacement(LineOfSets lineOfSets) {
        for (int i = 0; i < lineOfSets.getSize(); i++) {
            if (decisionArray.get(indexDecisionArray++) == 0) {
                if (i < lineOfSets.getSize() - 1) {
                    processingDecisionNotToPlaceVerticalWall(lineOfSets, i);
                } else {
                    maze.putVerticalWall(1);
                }
            } else {
                maze.putVerticalWall(1);
            }
        }
    }

    private void processingDecisionNotToPlaceVerticalWall(LineOfSets lineOfSets, int index) {
        if (compareWithRightCell(lineOfSets, index)) {
            maze.putVerticalWall(1);
        } else {
            equateSets(lineOfSets, index);
        }
    }

    private void equateSets(LineOfSets lineOfSets, int index) {
        maze.putVerticalWall(0);
        Integer currentSet = lineOfSets.getCellByIndex(index);
        Integer rightCell = lineOfSets.getCellByIndex(index + 1);
        for (int j = 0; j < lineOfSets.getSize(); j++) {
            if (lineOfSets.getCellByIndex(j).equals(rightCell)) {
                lineOfSets.setCellByIndex(j, currentSet);
                setService.incrementNumberOfCellsInSet(currentSet);
                setService.decrementNumberOfCellsInSet(rightCell);
            }
        }
    }

    private boolean compareWithRightCell(LineOfSets lineOfSets, int index) {
        return lineOfSets.getCellByIndex(index).equals(lineOfSets.getCellByIndex(index + 1));
    }

    private void placeWallOnTheHorizontal(LineOfSets lineOfSets) {
        for (int i = 0; i < lineOfSets.getSize(); i++) {
            if (decisionArray.get(indexDecisionArray++) == 1) {
                if (setService.getNumberOfCellsWithoutHorizontalWall(lineOfSets.getCellByIndex(i)) > 1) {
                    maze.putHorizontalWall(1);
                    setService.incrementNumberOfHorizontalWallInSet(lineOfSets.getCellByIndex(i));
                    lineOfSets.getIndexSetWithHorizontalWall().add(i);
                } else {
                    maze.putHorizontalWall(0);
                }
            } else {
                maze.putHorizontalWall(0);
            }
        }
    }

    private void incrementCellCount(LineOfSets lineOfSets) {
        for (Integer set : lineOfSets.getLine()) {
            if (setService.containsSet(set)) {
                setService.incrementNumberOfCellsInSet(set);
            } else {
                setService.addNewSet(set, new SetData(1, 0));
            }
        }
    }
}