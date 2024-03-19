package com.example.tictactoe.viewmodels;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tictactoe.R;
import com.example.tictactoe.models.GameBoard;
import com.example.tictactoe.models.Player;

public class TicTacToeViewModel extends ViewModel {

    private GameBoard gameBoard;

    public ObservableField<String> currentPlayer = new ObservableField<>();
    public ObservableArrayMap<String, Player> cells;

    public Player player1, player2;
    public Boolean isGameOver;

    public void setPlayers(String player1Name, String player2Name) {
        player1 = new Player(player1Name, R.drawable.red_token);
        player2 = new Player(player2Name, R.drawable.yellow_token);
    }

    public void setGameBoard() {
        gameBoard = new GameBoard(player1, player2);
        cells = new ObservableArrayMap<>();
        isGameOver = false;
        currentPlayer.set(gameBoard.currentPlayer.playerName + "'s Turn");
    }

    public void playMove(String key) {
        if (!isGameOver) {
            int row = Integer.parseInt(String.valueOf(key.charAt(0)));
            int column = Integer.parseInt(String.valueOf(key.charAt(1)));
            if (gameBoard.cells[row][column] == null) {
                cells.put(key, gameBoard.currentPlayer);
                gameBoard.addPlayerMove(row, column);
                currentPlayer.set(gameBoard.currentPlayer.playerName + "'s Turn");
            }
        }
    }

    public void clearBoard() {
        cells.clear();
    }

    public LiveData<Player> getWinner() {
        return gameBoard.winner;
    }
}
