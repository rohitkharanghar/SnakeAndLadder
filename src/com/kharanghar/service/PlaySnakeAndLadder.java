package com.kharanghar.service;

import com.kharanghar.model.Board;
import com.kharanghar.model.Game;
import com.kharanghar.model.Player;
import com.kharanghar.model.ToFrom;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlaySnakeAndLadder {

    public List<Player> getPlayers(List<String> names) {
        return names.stream().map(it -> new Player(it, UUID.randomUUID().toString())).collect(Collectors.toList());
    }

    public Board getBoard(Integer maxNumber, List<ToFrom> ladders, List<ToFrom> snakes) {
        Board board = new Board();
        board.setMaxDestination(maxNumber);
        ladders.forEach(it -> board.appendLadder(it.getFrom(), it.getTo()));
        snakes.forEach(it -> board.appendSnake(it.getFrom(), it.getTo()));
        return board;
    }

    public Game startNewGame(Board board, List<Player> players) {
        Game game = new Game(board);
        game.assignTurn(players);
        return game;
    }

    public void playGame(Game game) {
        while (!singleTurn(game)) {
            game.printPlayersPosition();
            game.nextTurn();
            System.out.println("===============================Next Turn========================");
        }
        System.out.println("The Winner is " + game.whosTurn());
    }

    private boolean winCondition(Integer maxDestination, Integer currentDestination) {
        return maxDestination.equals(currentDestination);
    }

    private boolean singleTurn(Game game) {
        int diceRoll;
        Player currentPlayer = game.whosTurn();
        Board board = game.getBoard();
        do {
            System.out.println("Who's Turn " + game.whosTurn().getName());
            diceRoll = randomNum();
            System.out.println("Number Rolled " + diceRoll);
            int currentPosition = game.getPositions().get(currentPlayer) + diceRoll;
            if (currentPosition < board.getMaxDestination()) {
                game.updatePlayerPosition(currentPlayer, currentPosition);
            }
            while (board.getLadders().containsKey(currentPosition)) {
                System.out.println("Yeyy!! Ladder @ " + currentPosition);
                game.updatePlayerPosition(currentPlayer, board.getLadders().get(currentPosition));
                currentPosition = board.getLadders().get(currentPosition);
            }
            while (board.getSnakes().containsKey(currentPosition)) {
                System.out.println("Hisss!! Snake @ " + currentPosition);
                game.updatePlayerPosition(currentPlayer, board.getSnakes().get(currentPosition));
                currentPosition = board.getSnakes().get(currentPosition);
            }
            if (winCondition(board.getMaxDestination(), currentPosition)) {
                return true;
            }
        } while (diceRoll == 6);
        return false;
    }

    private int randomNum() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

}
