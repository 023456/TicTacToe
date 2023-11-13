package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board ;


    public Board(int size) {

        this.size = size;
        this.board = new ArrayList<>();
        for(int i=0;i<size;i++){
            List<Cell> row = new ArrayList<>();
            for(int j=0;j<size;j++){
                row.add(new Cell(i,j));
            }
            board.add(row);
        }

    }

    public void displayBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              Cell cell = board.get(i).get(j);
              if(cell.getCellState()==CellState.EMPTY){
                  System.out.print("| |");
              }
              else{
                  System.out.print("|"+ cell.getPlayer().getSymbol().getSymbol()+" |");
              }
            }
            System.out.println();
        }
    }


    public int getSize() {
        return size;
    }

    public List<List<Cell>> getBoard() {

        return board;
    }
}
