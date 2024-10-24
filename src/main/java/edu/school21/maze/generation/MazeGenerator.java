package edu.school21.maze.generation;

import edu.school21.maze.model.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MazeGenerator {
    private Maze maze;
    private int setFillCounter;
    private List<Integer> decisionArray;
    private List<List<Integer>> listSet;
    private  List<Integer> list;
    private  List<Integer> list2;
    private int indexDecisionArray;


    public MazeGenerator(Maze maze) {
        this.maze = maze;
        decisionArray = new ArrayList<>();
        listSet = new ArrayList<>();
        setFillCounter = 1;
        indexDecisionArray = 0;

    }

    /**
     * The method generates a random number 0 or 1.
     */
    public void generateRandomNumber() {
        decisionArray = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(2))
                .limit(maze.getNumberOfCols() * maze.getNumberOfRows() * 3L)
                .boxed()
                .collect(Collectors.toList());
       mazeGeneration();
    }
    public void mazeGeneration(){
        list2 = Stream.generate(() -> 0).limit(maze.getNumberOfCols()).collect(Collectors.toCollection(ArrayList::new));

         list = new ArrayList<>(list2);
         fillingArrayWithNumbersInOrder(list);
         placeWallOnTheRight(list);
         listSet.add(list);


    }

    private void placeWallOnTheRight(List<Integer> list) {
        if (decisionArray.get(indexDecisionArray) == 0){

        }

    }

    private void fillingArrayWithNumbersInOrder(List<Integer> list) {
        for (int i = 0; i < maze.getNumberOfCols(); i++) {
            if(list.get(i) == 0){
                list.set(i, setFillCounter++);
            }
        }
    }
}
