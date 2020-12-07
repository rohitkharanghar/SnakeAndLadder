package com.kharanghar.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private Board board;
    private Queue<Player> turn = new LinkedList<>();
    private HashMap<Player, Integer> positions = new HashMap<>();

    public Game(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public HashMap<Player, Integer> getPositions() {
        return positions;
    }

    public void updatePlayerPosition(Player player, Integer newPosition) {
        this.positions.replace(player, newPosition);
    }

    public void assignTurn(List<Player> players) {
        this.turn.addAll(players);
        players.forEach(it -> this.positions.put(it, 0));
    }

    public Player whosTurn() {
        return this.turn.peek();
    }

    public void nextTurn() {
        Player previous = this.turn.peek();
        this.turn.poll();
        this.turn.add(previous);
    }

    public void printPlayersPosition() {
        positions.forEach((key, value) -> System.out.println("Player " + key.toString() + " @ " + value));
    }
}
