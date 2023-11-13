package org.example.Strategies.WinningStrategy;

import org.example.Models.Board;
import org.example.Models.Move;

public interface WinningStrategies {
    boolean checkWinner(Move move , Board board);

}
