package org.example.Models;

import java.util.Scanner;

public class Player {
    private String name;
    private  Symbol symbol;
    private PlayerType playerType;


    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {

        this.playerType = playerType;
    }

    public Move makeMove(Board board){
        Scanner sc = new Scanner(System.in);

        //Ask the Player to provide the index to make a move.
        System.out.println("Please tell the row index to make a move");
        int rownum = sc.nextInt();

        System.out.println("Please tell the col index to make a move");
        int colnum = sc.nextInt();

        return new Move(new Cell(rownum,colnum),this);


    }
}
