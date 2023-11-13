package org.example.Strategies.WinningStrategy;

import org.example.Models.Board;
import org.example.Models.Move;
import org.example.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements  WinningStrategies{
    private Map<Integer,Map<Symbol,Integer>> rowMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!rowMaps.containsKey(row)){
            rowMaps.put(row ,new HashMap<>());
        }
        Map<Symbol,Integer> currentRowmap = rowMaps.get(row);
        if(currentRowmap.containsKey(symbol)){
            currentRowmap.put(symbol,currentRowmap.get(symbol)+1);

        }
        else{
            currentRowmap.put(symbol,1);
        }
        rowMaps.put(row,currentRowmap);


        return currentRowmap.get(symbol)==board.getSize();
    }
}
