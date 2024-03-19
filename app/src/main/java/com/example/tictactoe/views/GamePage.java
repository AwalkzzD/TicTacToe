package com.example.tictactoe.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.tictactoe.R;
import com.example.tictactoe.databinding.FragmentGamePageBinding;
import com.example.tictactoe.models.GameBoard;
import com.example.tictactoe.models.Player;
import com.example.tictactoe.viewmodels.TicTacToeViewModel;

public class GamePage extends Fragment {

    private TicTacToeViewModel ticTacToeViewModel;

    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentGamePageBinding gamePageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_page, container, false);
        ticTacToeViewModel = new ViewModelProvider(requireActivity()).get(TicTacToeViewModel.class);

        setGame();

        gamePageBinding.setViewModel(ticTacToeViewModel);

        Toolbar toolbar = gamePageBinding.toolbar;
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if (activity != null) activity.setSupportActionBar(toolbar);

        return gamePageBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = requireActivity().getSupportFragmentManager();

        Button quitGame = view.findViewById(R.id.quit_game_button);
        Button playAgain = view.findViewById(R.id.play_again_button);

        quitGame.setOnClickListener(v -> fragmentManager.beginTransaction().replace(R.id.fragment_container, new WelcomePage()).commit());

        playAgain.setOnClickListener(v -> {
            ticTacToeViewModel.clearBoard();
            setGame();
        });
    }

    private void checkWinner(Player winner) {
        if (winner.equals(GameBoard.NO_ONE)) {
            ticTacToeViewModel.currentPlayer.set("It's a Draw!");
            ticTacToeViewModel.isGameOver = true;
        } else if (winner.equals(ticTacToeViewModel.player1)) {
            ticTacToeViewModel.currentPlayer.set("Congratulations!!! " + winner.playerName + " has Won!");
            ticTacToeViewModel.isGameOver = true;
        } else if (winner.equals(ticTacToeViewModel.player2)) {
            ticTacToeViewModel.currentPlayer.set("Congratulations!!! " + winner.playerName + " has Won!");
            ticTacToeViewModel.isGameOver = true;
        }
    }

    private void setGame() {
        ticTacToeViewModel.setGameBoard();
        ticTacToeViewModel.getWinner().observe(getViewLifecycleOwner(), this::checkWinner);
    }

    @BindingAdapter("source")
    public static void setImageSource(ImageView view, int image) {
        view.setImageResource(image);
    }
}