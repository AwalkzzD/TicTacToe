package com.example.tictactoe.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.tictactoe.R;
import com.example.tictactoe.viewmodels.TicTacToeViewModel;

public class WelcomePage extends Fragment {

    private TicTacToeViewModel ticTacToeViewModel;
    private FragmentManager fragmentManager;

    private String player1Name;
    private String player2Name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticTacToeViewModel = new ViewModelProvider(requireActivity()).get(TicTacToeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = requireActivity().getSupportFragmentManager();

        EditText player1ET = view.findViewById(R.id.player1_et);
        EditText player2ET = view.findViewById(R.id.player2_et);

        Button startGame = view.findViewById(R.id.start_game);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1Name = player1ET.getText().toString();
                player2Name = player2ET.getText().toString();

                if (player1Name.length() > 0 && player2Name.length() > 0) {
                    ticTacToeViewModel.setPlayers(player1Name, player2Name);
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new GamePage()).commit();
                } else {
                    Toast.makeText(getActivity(), "Invalid Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}