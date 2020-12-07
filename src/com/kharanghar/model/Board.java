package com.kharanghar.model;

import java.util.HashMap;

public class Board {
    private HashMap<Integer, Integer> ladders;
    private HashMap<Integer, Integer> snakes;
    private Integer maxDestination;

    public Board() {
        ladders = new HashMap<>();
        snakes = new HashMap<>();
    }

    public HashMap<Integer, Integer> getLadders() {
        return ladders;
    }

    public HashMap<Integer, Integer> getSnakes() {
        return snakes;
    }

    public Integer getMaxDestination() {
        return maxDestination;
    }

    public void setMaxDestination(Integer maxDestination) {
        this.maxDestination = maxDestination;
    }

    public void appendLadder(int from, int to) {
        if (from > to || from > this.maxDestination || to < 1) {
            throw new RuntimeException("Not a valid entry for ladder from " + from + " to " + to);
        }
        if (to > this.maxDestination) {
            throw new RuntimeException("Final destination cannot be larger than maxDestination of board");
        }
        if (this.ladders.containsKey(from) && this.ladders.get(from) < to) {
            this.ladders.replace(from, to);
        } else {
            this.ladders.put(from, to);
        }
    }

    public void appendSnake(int from, int to) {
        if (to > from || from > this.maxDestination || from < 1) {
            throw new RuntimeException("Not a valid entry for snake from " + from + " to " + to);
        }
        if (to < 1) {
            throw new RuntimeException("Final destination cannot be smaller than starting of board");
        }
        if (this.snakes.containsKey(from) && this.snakes.get(from) > to) {
            this.snakes.replace(from, to);
        } else {
            this.snakes.put(from, to);
        }
    }
}
