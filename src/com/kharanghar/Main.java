package com.kharanghar;

import com.kharanghar.model.Board;
import com.kharanghar.model.Game;
import com.kharanghar.model.Player;
import com.kharanghar.model.ToFrom;
import com.kharanghar.service.PlaySnakeAndLadder;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<ToFrom> ladders = new ArrayList<>();
        ladders.add(new ToFrom(3, 10));
        ladders.add(new ToFrom(15, 45));
        ladders.add(new ToFrom(30, 70));
        ladders.add(new ToFrom(45, 65));
        ladders.add(new ToFrom(50, 85));
        ladders.add(new ToFrom(7, 90));

        List<ToFrom> snakes = new ArrayList<>();
        snakes.add(new ToFrom(11, 4));
        snakes.add(new ToFrom(43, 12));
        snakes.add(new ToFrom(63, 23));
        snakes.add(new ToFrom(80, 35));
        snakes.add(new ToFrom(67, 17));
        snakes.add(new ToFrom(95, 9));

        PlaySnakeAndLadder playSnakeAndLadder = new PlaySnakeAndLadder();
        Board board = playSnakeAndLadder.getBoard(100, ladders, snakes);

        List<String> names = new ArrayList<>();
        names.add("Rohit");
        names.add("Veena");
        names.add("Moni");
        List<Player> players = playSnakeAndLadder.getPlayers(names);
        Game game = playSnakeAndLadder.startNewGame(board, players);
        playSnakeAndLadder.playGame(game);
    }
}
