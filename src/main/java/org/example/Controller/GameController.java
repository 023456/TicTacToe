package org.example.Controller;

import org.example.Models.*;
import org.example.Strategies.WinningStrategy.WinningStrategies;

import java.util.List;

public class GameController {

    public Game startGame(int size, List<Player> players , List<WinningStrategies> winningStrategies) throws Exception{
       Game game = Game.getBuilder().setDimension(size).setPlayers(players).setWinningStrategies(winningStrategies).build();
     return game;
    }

    public void  makeMove(Game game){

        game.makeMove();
    }

    public void displayBoard(Game game){
        game.printBoard();
    }
    public Player checkWinner(Game game){

        return game.getWinner();
    }
    public Player getWinner(Game game){
        return  game.getWinner();
    }
    public void undo(Game game){
        game.undo();

    }
    public GameState getGameState(Game game){
        return game.getGameState();

    }

}
