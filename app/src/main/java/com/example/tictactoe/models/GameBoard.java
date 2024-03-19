package com.example.tictactoe.models;

import androidx.lifecycle.MutableLiveData;

import com.example.tictactoe.R;

public class GameBoard {

    public static final Player NO_ONE = new Player("No One", R.drawable.black_token);

    public Cell[][] cells;
    public Player currentPlayer;
    public MutableLiveData<Player> winner;

    Player player1;
    Player player2;

    int movesLeft;

    public GameBoard(Player player1, Player player2) {
        resetGame(player1, player2);
    }

    private void resetGame(Player player1, Player player2) {
        cells = new Cell[3][3];
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = this.player1;
        movesLeft = 9;
        winner = new MutableLiveData<>();
    }

    public boolean checkWinner(Cell... cells) {
        if (cells[0] == null || cells[1] == null || cells[2] == null) return false;
        return cells[0].player.playerSymbol == cells[1].player.playerSymbol && cells[1].player.playerSymbol == cells[2].player.playerSymbol;
    }

    public boolean checkVerticalCells() {
        Cell[] checkCell = new Cell[3];
        for (int j = 0; j < 3; j++) {
            checkCell[0] = cells[0][j];
            checkCell[1] = cells[1][j];
            checkCell[2] = cells[2][j];
            if (checkWinner(checkCell)) return true;
        }
        return false;
    }

    public boolean checkHorizontalCells() {
        for (Cell[] c : cells)
            if (checkWinner(c)) return true;
        return false;
    }

    public boolean checkDiagonalCells() {
        Cell[] checkCell = new Cell[3];
        checkCell[0] = cells[0][0];
        checkCell[1] = cells[1][1];
        checkCell[2] = cells[2][2];
        if (checkWinner(checkCell)) return true;
        checkCell[0] = cells[0][2];
        checkCell[1] = cells[1][1];
        checkCell[2] = cells[2][0];
        return checkWinner(checkCell);
    }

    public void addPlayerMove(int row, int column) {
        cells[row][column] = new Cell(currentPlayer);
        movesLeft -= 1;
        if (checkVerticalCells()) {
            winner.postValue(currentPlayer);
            return;
        } else if (checkHorizontalCells()) {
            winner.postValue(currentPlayer);
            return;
        } else if (checkDiagonalCells()) {
            winner.postValue(currentPlayer);
            return;
        } else if (movesLeft == 0) {
            winner.postValue(NO_ONE);
            return;
        }
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}
