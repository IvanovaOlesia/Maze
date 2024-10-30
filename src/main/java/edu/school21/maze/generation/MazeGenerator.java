package edu.school21.maze.generation;

import edu.school21.maze.model.LineOfSets;
import edu.school21.maze.model.Maze;
import edu.school21.maze.model.SetCollection;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class MazeGenerator {
    private final Maze maze;
    private int setFillCounter;
    private List<Integer> decisionArray;
    private final LineOfSets lineOfSets;
    private final Map<Integer, SetCollection> setCollectionList;
    private int indexDecisionArray;


    public MazeGenerator(Maze maze) {
        this.maze = maze;
        decisionArray = new ArrayList<>();
        setCollectionList = new HashMap<>();
        setFillCounter = 1;
        indexDecisionArray = 0;
        lineOfSets = new LineOfSets(maze.getNumberOfCols());
    }

    public void mazeGeneration() {
        generateRandomNumber();
        for (int i = 0; i < maze.getNumberOfRows(); i++) {
            assignSetsToArrayCells(lineOfSets);
            placeWallOnTheRight(lineOfSets);
            if (i == maze.getNumberOfRows() - 1){
                lastLineProcessing(lineOfSets);
            }else {
                placeWallOnTheBottom(lineOfSets);
            }

            resetSetCollectionList();
            resetCellsWithBottomWalls(lineOfSets);

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
            maze.putBottomWall(1);
            if ((i != lineOfSets.getSize() - 1) && (!lineOfSets.getCellByIndex(i).equals(lineOfSets.getCellByIndex(i + 1)))) {
                maze.getRightWall().set((maze.getNumberOfRows() - 1) * maze.getNumberOfCols() + i, 0);
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

    private void resetSetCollectionList() {
            for (Integer index : lineOfSets.getLine()) {
                setCollectionList.get(index).setNumberOfCellsInSet(0);
                setCollectionList.get(index).setNumberOfBottomWallsInSet(0);
            }
    }

    private void resetCellsWithBottomWalls(LineOfSets lineOfSets) {
        for (Integer index : lineOfSets.getIndexSetWithBottomWall()) {
            lineOfSets.setCellByIndex(index, 0);
        }
        lineOfSets.getIndexSetWithBottomWall().clear();
    }

    private void placeWallOnTheRight(LineOfSets lineOfSets) {
        for (int i = 0; i < lineOfSets.getSize(); i++) {
            if (decisionArray.get(indexDecisionArray++) == 0) {
                if (i < lineOfSets.getSize() - 1) {
                    processNoRightWallDecision(lineOfSets, i);
                } else {
                    maze.putRightWall(1);
                }
            } else {
                maze.putRightWall(1);
            }
        }
    }

    private void processNoRightWallDecision(LineOfSets lineOfSets, int index) {
        if (compareWithRightCell(lineOfSets, index)) {
            maze.putRightWall(1);
        } else {
            maze.putRightWall(0);
            Integer currentSet = lineOfSets.getCellByIndex(index);
            Integer rightCell = lineOfSets.getCellByIndex(index + 1);
            for (int j = 0; j < lineOfSets.getSize(); j++) {
                if (lineOfSets.getCellByIndex(j).equals(rightCell)) {
                    lineOfSets.setCellByIndex(j, currentSet);
                    setCollectionList.get(currentSet).setNumberOfCellsInSet(setCollectionList.get(currentSet).getNumberOfCellsInSet() + 1);
                    setCollectionList.get(rightCell).setNumberOfCellsInSet(setCollectionList.get(rightCell).getNumberOfCellsInSet() - 1);
                }
            }
        }
    }

    private boolean compareWithRightCell(LineOfSets lineOfSets, int index) {
        return lineOfSets.getCellByIndex(index).equals(lineOfSets.getCellByIndex(index + 1));
    }

    private void placeWallOnTheBottom(LineOfSets lineOfSets) {
        for (int i = 0; i < lineOfSets.getSize(); i++) {
            if (decisionArray.get(indexDecisionArray++) == 1) {

                if (setCollectionList.get(lineOfSets.getCellByIndex(i)).getNumberOfCellsInSet() - setCollectionList.get(lineOfSets.getCellByIndex(i)).getNumberOfBottomWallsInSet() > 1) {
                    maze.putBottomWall(1);
                    setCollectionList.get(lineOfSets.getCellByIndex(i)).setNumberOfBottomWallsInSet(setCollectionList.get(lineOfSets.getCellByIndex(i)).getNumberOfBottomWallsInSet() + 1);
                    lineOfSets.getIndexSetWithBottomWall().add(i);

                } else {
                    maze.putBottomWall(0);
                }
            } else {
                maze.putBottomWall(0);
            }
        }
    }



    private void incrementCellCount(LineOfSets lineOfSets) {
        for (Integer set : lineOfSets.getLine()) {
            if (setCollectionList.containsKey(set)) {
                setCollectionList.get(set).setNumberOfCellsInSet(setCollectionList.get(set).getNumberOfCellsInSet() + 1);
            } else {
                setCollectionList.put(set, new SetCollection(1, 0));
            }

        }
    }
}