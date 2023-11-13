package org.example.Strategies.PlayingStrategy;

import org.example.Models.Board;
import org.example.Models.Cell;
import org.example.Models.CellState;
import org.example.Models.Move;

public class EasyBotStrategy implements BotStrategy {
    @Override
    public Move makeMove(Board board) {
      for(int i=0;i<board.getSize();i++){
          for(int j=0;j<board.getSize();j++){
              if(board.getBoard().get(i).get(j).getCellState()== CellState.EMPTY){
                  return new Move(board.getBoard().get(i).get(j),null);
              }
          }
      }
      return null;
    }
}
