package com.example.tictactoe.models;

import androidx.annotation.Nullable;

public class Player {

    public String name;
    public int value;

    public Player(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (this == obj) return true;

        Player player = (Player) obj;

        return name.compareTo(player.name) == 0 && value == player.value;

    }
}
