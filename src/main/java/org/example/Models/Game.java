package org.example.Models;

import org.example.Exceptions.GameInvalidationException;
import org.example.Strategies.WinningStrategy.WinningStrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerMoveIndex;
    private GameState gameState;
    private List<WinningStrategies> winningStrategies;

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategies> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategies> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    private Game(List<Player> players, int size, List<WinningStrategies> winningStrategies) {
        this.players = players;
        this.board = new Board(size);
        this.moves = new ArrayList<>();
        this.nextPlayerMoveIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.winningStrategies = winningStrategies;
    }

    public static Builder getBuilder() {

        return new Builder();
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;
        private List<WinningStrategies> winningStrategies;


        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategies> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategies> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private boolean validate() throws GameInvalidationException {
            int bots = 0;
            HashSet<Character> hs = new HashSet<>();
            for (int i = 0; i < this.players.size(); i++) {
                if (this.players.get(i).getPlayerType() == PlayerType.BOT) {
                    bots++;
                    if (bots > 1) {
                        throw new GameInvalidationException("More than one bot present");
                    }
                }
                if (hs.contains(this.players.get(i).getSymbol().getSymbol())) {
                    throw new GameInvalidationException("Players have smae symbol");
                }
                hs.add(this.players.get(i).getSymbol().getSymbol());

            }
            return true;

        }

        public Game build() throws GameInvalidationException {
            validate();
            return new Game(players, dimension, winningStrategies);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public void printBoard() {
        board.displayBoard();
    }

    public void makeMove() {
        Player currentPlayer = players.get(nextPlayerMoveIndex);
        System.out.println("It is " + currentPlayer.getName() + "'s move.");
        Move move = currentPlayer.makeMove(board);
        System.out.println(currentPlayer.
                getName() + "has made a move at Row: " +
                move.getCell().getRow() + " ,col:  " + move.getCell().getColumn());

        //Validate the move before we apply the move on Board.
        if (!validateMove(move)) {
            System.out.println("Invalid move by player: " + currentPlayer.getName());
            return;
        }

        move.getCell().setCellState(CellState.FILLED);
        move.getCell().setPlayer(currentPlayer);

        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        Cell finalCelltoMakeMove = board.getBoard().get(row).get(col);
        finalCelltoMakeMove.setCellState(CellState.FILLED);
        finalCelltoMakeMove.setPlayer(currentPlayer);
//
//        Move finalMove = new Move(finalCelltoMakeMove,currentPlayer);
        moves.add(move);
        nextPlayerMoveIndex = nextPlayerMoveIndex + 1;
        nextPlayerMoveIndex = nextPlayerMoveIndex % players.size();


        //once the player has made a move, check the winner
        if (checkWinner(board, move)) {
            gameState = GameState.ENDED;
            winner = currentPlayer;
        } else if (moves.size() == board.getSize() * board.getSize()) {
            gameState = GameState.DRAW;
        }
    }

    public void undo() {

    }

    public boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        if (row < board.getSize() && row >= 0 && col < board.getSize()
                && col >= 0 && board.getBoard().get(row).get(col).getCellState().
                equals(CellState.EMPTY)) {
            return true;

        } else {
            return false;
        }
    }

    public boolean checkWinner(Board board, Move move) {
        for (WinningStrategies winningStrategies1 : winningStrategies) {
            if (winningStrategies1.checkWinner(move, board)) {
                return true;
            }
        }
        return false;

    }


}
