package org.example.Models;

import org.example.Strategies.PlayingStrategy.BotStrategy;
import org.example.Strategies.PlayingStrategy.DifficultBotStrategy;
import org.example.Strategies.PlayingStrategy.EasyBotStrategy;
import org.example.Strategies.PlayingStrategy.MediumBotStrategy;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotStrategy botStrategy;


    public Bot(String name, Symbol symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
        if(this.botDifficultyLevel==BotDifficultyLevel.EASY){
            this.botStrategy=new EasyBotStrategy();
        }
        else if (this.botDifficultyLevel==BotDifficultyLevel.MEDIUM){
            this.botStrategy=new MediumBotStrategy();
        }
        else{
            this.botStrategy=new DifficultBotStrategy();
        }


    }

    @Override
    public Move makeMove(Board board) {
        Move move = botStrategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }
}
