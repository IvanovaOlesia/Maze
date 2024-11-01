package edu.school21.maze.services;

import edu.school21.maze.model.SetData;

import java.util.HashMap;
import java.util.Map;

public class SetService {
    private final Map<Integer, SetData> setData;

    public SetService() {
        this.setData = new HashMap<>();
    }

    public int getNumberOfCellsInSetByIndex(int set) {
        return this.setData.get(set).getNumberOfCellsInSet();
    }
    public boolean containsSet(int set){
        return setData.containsKey(set);
    }

    public void incrementNumberOfCellsInSet(int set) {
        this.setData.get(set).setNumberOfCellsInSet(getNumberOfCellsInSetByIndex(set) + 1);
    }
    public void incrementNumberOfHorizontalWallInSet(int set) {
        this.setData.get(set).setNumberOfHorizontalWallsInSet(getNumberOfHorizontalWallsInSetByIndex(set) + 1);
    }

    private int getNumberOfHorizontalWallsInSetByIndex(int set) {
        return this.setData.get(set).getNumberOfHorizontalWallsInSet();
    }

    public void decrementNumberOfCellsInSet(int set) {
        this.setData.get(set).setNumberOfCellsInSet(getNumberOfCellsInSetByIndex(set) - 1);
    }

    public void addNewSet(int set, SetData setData){
        this.setData.put(set, setData);
    }
    public int  getNumberOfCellsWithoutHorizontalWall(int set ){
      return setData.get(set).getNumberOfCellsInSet() - setData.get(set).getNumberOfHorizontalWallsInSet();
    }
    public void resetNumberOfHorizontalWallsInSet(int set){
        setData.get(set).setNumberOfHorizontalWallsInSet(0);
    }
    public void resetNumberOfCellsInSet(int set){
        setData.get(set).setNumberOfCellsInSet(0);
    }
}
