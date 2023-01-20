package com.example.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.TextView;


public class WinDialogue extends Dialog {
    public final String message;
    private final MainActivity mainActivity;

    public WinDialogue(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_dialogue_layout);

        final TextView messageText = findViewById(R.id.messageText);
        final Button startAgainBtn = findViewById(R.id.startGameButton);

        messageText.setText(message);

        startAgainBtn.setOnClickListener(e ->{
            mainActivity.restartMatch();
            dismiss();
        });

    }
}
