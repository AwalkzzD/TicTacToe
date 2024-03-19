package com.example.tictactoe.models;

import androidx.annotation.Nullable;

public class Player {

    public String playerName;
    public int playerSymbol;

    public Player(String playerName, int playerSymbol) {
        this.playerName = playerName;
        this.playerSymbol = playerSymbol;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (this == obj) return true;

        Player player = (Player) obj;

        return playerName.compareTo(player.playerName) == 0 && playerSymbol == player.playerSymbol;

    }
}
