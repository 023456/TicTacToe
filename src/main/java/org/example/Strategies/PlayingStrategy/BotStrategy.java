package org.example.Strategies.PlayingStrategy;

import org.example.Models.Board;
import org.example.Models.Move;

public interface BotStrategy  {
    public Move makeMove(Board board);

}
