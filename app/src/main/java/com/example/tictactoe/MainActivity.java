package com.example.tictactoe;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<int[]> combinationsList = new ArrayList<>();

    private int [] boxPostions = {0,0,0,0,0,0,0,0,0};

    private int playerTurn = 1;

    private int totalSelectedBoxes = 1;

    private LinearLayout playerOneLayout, playerTwoLayout;

    private TextView playerOneName, playerTwoName;

    private ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;

    private Button ExitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExitButton = findViewById(R.id.ExitBtn);

         playerOneName = findViewById(R.id.FirstPlayerName);
         playerTwoName = findViewById(R.id.SecondPlayerName);

         playerOneLayout = findViewById(R.id.playerOneLayout);
         playerTwoLayout = findViewById(R.id.playerTwoLayout);

         image1 = findViewById(R.id.image1);image2 = findViewById(R.id.image2);image3 = findViewById(R.id.image3);image4 = findViewById(R.id.image4);
         image5 = findViewById(R.id.image5);image6 = findViewById(R.id.image6);image7 = findViewById(R.id.image7);image8 = findViewById(R.id.image8);
         image9 = findViewById(R.id.image9);

         combinationsList.add(new int[]{0,1,2});
         combinationsList.add(new int[]{3,4,5});
         combinationsList.add(new int[]{6,7,8});
         combinationsList.add(new int[]{0,3,6});
         combinationsList.add(new int[]{1,4,7});
         combinationsList.add(new int[]{7,5,8});
         combinationsList.add(new int[]{2,4,6});
         combinationsList.add(new int[]{0,4,8});

          String getPlayerOneName = getIntent().getStringExtra("playerOne");
          String getPlayertwoName = getIntent().getStringExtra("playerTwo");

         playerOneName.setText(getPlayerOneName);
         playerTwoName.setText(getPlayertwoName);

         ExitButton.setOnClickListener(e -> {
             Intent intent = new Intent(MainActivity.this, AddPlayers.class);
             startActivity(intent);
         });
        image1.setOnClickListener(e ->{
            if(isBoxSelectable(0)){
                performAction((ImageView)e, 0);
            }
        });
        image2.setOnClickListener(e ->{
            if(isBoxSelectable(1)){
                performAction((ImageView)e, 1);
            }
        });
        image3.setOnClickListener(e ->{
            if(isBoxSelectable(2)){
                performAction((ImageView)e, 2);
            }
        });
        image4.setOnClickListener(e ->{
            if(isBoxSelectable(3)){
                performAction((ImageView)e, 3);
            }
        });
        image5.setOnClickListener(e ->{
            if(isBoxSelectable(4)){
                performAction((ImageView)e, 4);
            }
        });
        image6.setOnClickListener(e ->{
            if(isBoxSelectable(5)){
                performAction((ImageView)e, 5);
            }
        });
        image7.setOnClickListener(e ->{
            if(isBoxSelectable(6)){
                performAction((ImageView)e, 6);
            }
        });
        image8.setOnClickListener(e ->{
            if(isBoxSelectable(7)){
                performAction((ImageView)e, 7);
            }
        });
        image9.setOnClickListener(e ->{
            if(isBoxSelectable(8)){
                performAction((ImageView)e, 8);
            }
        });
    }
    private void performAction(ImageView imageview, int selectedBoxPosition){
        boxPostions[selectedBoxPosition] = playerTurn;
        if(playerTurn == 1){
            imageview.setImageResource(R.drawable.x);
            if(checkPlayerWin()){
                WinDialogue winDialogue = new WinDialogue(MainActivity.this,playerOneName.getText().toString() + "has won the match",MainActivity.this);
                winDialogue.setCancelable(false);
                winDialogue.show();
            }
            else if(totalSelectedBoxes == 9){
                WinDialogue winDialogue = new WinDialogue(MainActivity.this,"it is a Draw!!",MainActivity.this);
                winDialogue.setCancelable(false);
                winDialogue.show();
            }
            else{
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        }
        else{
            imageview.setImageResource(R.drawable.circle);
            if(checkPlayerWin()){
                WinDialogue winDialogue = new WinDialogue(MainActivity.this,playerTwoName.getText().toString() + "has won the match",MainActivity.this);
                winDialogue.setCancelable(false);
                winDialogue.show();
            }
            else if(totalSelectedBoxes == 9){
                WinDialogue winDialogue = new WinDialogue(MainActivity.this,"it is a Draw!!",MainActivity.this);
                winDialogue.setCancelable(false);
                winDialogue.show();
            }
            else{
                changePlayerTurn(1);

                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn){
        playerTurn = currentPlayerTurn;
        if(playerTurn == 1){
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
        else{
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
    }
    private boolean checkPlayerWin(){
        boolean response = false;
        for(int i = 0;i<combinationsList.size();i++){
            final int [] combination = combinationsList.get(i);

            if(boxPostions[combination[0]] == playerTurn && boxPostions[combination[1]]== playerTurn && boxPostions[combination[2]]==playerTurn){
                response = true;
            }
        }
        return  response;
    }
    private boolean isBoxSelectable(int boxPosititon){
        boolean response = false;
        if(boxPostions[boxPosititon] == 0){
            response = true;
        }
        return response;
    }
    public void restartMatch(){
        boxPostions = new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBoxes = 1;

        setTransparent(image1);setTransparent(image2);setTransparent(image3);setTransparent(image4);
        setTransparent(image5);setTransparent(image6);setTransparent(image7);setTransparent(image8);setTransparent(image9);
    }
    public void setTransparent(ImageView img){
        img.setImageResource(R.drawable.transparent);
    }

}