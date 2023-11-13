package org.example.Strategies.WinningStrategy;

import org.example.Models.Board;
import org.example.Models.Move;
import org.example.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategies{
    private  Map<Symbol, Integer> leftdiagonal = new HashMap<>();
    private Map<Symbol,Integer> rightdiagonal = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row==col){
            if(leftdiagonal.containsKey(symbol)){
                leftdiagonal.put(symbol,leftdiagonal.get(symbol)+1);
            }
            else{
                leftdiagonal.put(symbol,1);
            }
        }
        if(row + col == board.getSize()-1){
            if(rightdiagonal.containsKey(symbol)){
                rightdiagonal.put(symbol,rightdiagonal.get(symbol)+1);
            }
            else{
                rightdiagonal.put(symbol,1);
            }


        }
        if(row==col && leftdiagonal.get(symbol)==board.getSize()){
            return  true;
        }
        if(row+col == board.getSize()-1 && rightdiagonal.get(symbol)==board.getSize()){
            return true;
        }
        return false;
    }
}
