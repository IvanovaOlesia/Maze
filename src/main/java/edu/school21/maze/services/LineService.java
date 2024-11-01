package edu.school21.maze.services;
import edu.school21.maze.model.LineOfSets;

import java.util.List;

public class LineService {
    private LineOfSets lineOfSets;

    public LineService(int size) {
        this.lineOfSets = new LineOfSets(size);
    }

    public int getSetByIndex(int index) {
        return lineOfSets.getLine().get(index);
    }

    public void setCellSet(int index, int set) {
        lineOfSets.getLine().set(index, set);
    }

    public List<Integer> getLine() {
        return lineOfSets.getLine();
    }

    public List<Integer> getIndexSetWithHorizontalWall(){
        return lineOfSets.getIndexSetWithHorizontalWall();
    }
    public int getSize() {
        return lineOfSets.getLine().size();
    }

    public Integer getCellByIndex(int index) {
        return lineOfSets.getLine().get(index);
    }

    public void setCellByIndex(int index, int value) {
        lineOfSets.getLine().set(index, value);
    }

    public void addIndexSetWithHorizontalWall(int index) {
        lineOfSets.getIndexSetWithHorizontalWall().add(index);
    }

    public boolean compareSets(int indexOne, int set) {
        return lineOfSets.getLine().get(indexOne).equals(set);
    }
}
