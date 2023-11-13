package org.example;

import org.example.Controller.GameController;
import org.example.Models.*;
import org.example.Strategies.PlayingStrategy.EasyBotStrategy;
import org.example.Strategies.WinningStrategy.ColWinningStrategy;
import org.example.Strategies.WinningStrategy.DiagonalWinningStrategy;
import org.example.Strategies.WinningStrategy.RowWinningStrategy;
import org.example.Strategies.WinningStrategy.WinningStrategies;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        GameController gameController = new GameController();
        int size =3;
        int numbersofPlayers = size-1;
        List<Player> players = new ArrayList<>();

        players.add(new Player("Fatema" ,new Symbol('X'), PlayerType.HUMAN));
        players.add(new Bot("BotPlayer" , new Symbol('0') , PlayerType.BOT , BotDifficultyLevel.EASY ));

        List<WinningStrategies> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        //start the game
        Game game = gameController.startGame(size,players,winningStrategies);

        //start playing the game
        while(gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
            //display the board
            System.out.println("This is the current state off board");
            gameController.displayBoard(game);
            ////Do you want to UNDO ? If yes, call the UNDO functionality else call the makeMove.
            gameController.makeMove(game);

        }

        if(gameController.getGameState(game).equals(GameState.ENDED)){
            //somebody has won
            gameController.displayBoard(game);
            System.out.println("Winner is "+gameController.getWinner(game).getName());

        }
        else{
            System.out.println("Game is drawn");
        }

    }
}