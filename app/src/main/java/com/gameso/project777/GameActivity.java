package com.gameso.project777;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    private ImageView imageSlot1, imageSlot2, imageSlot3;
    private Button buttonBet50, buttonBet100, buttonBet250, buttonSpin;
    private TextView textViewScore;
    private TextView textViewBet;
    private boolean gameRunning = false;
    private final static int START_POINTS = 10000;
    private final static int MINIMAL_BET = 50;
    private int points;
    private int bet = MINIMAL_BET;
    private ImageView[] slotItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        imageSlot1 = findViewById(R.id.imageSlot1);
        imageSlot2 = findViewById(R.id.imageSlot2);
        imageSlot3 = findViewById(R.id.imageSlot3);
        textViewScore = findViewById(R.id.textViewScore);
        textViewBet = findViewById(R.id.textViewBet);
        buttonBet50 = findViewById(R.id.buttonBet50);
        buttonBet100 = findViewById(R.id.buttonBet100);
        buttonBet250 = findViewById(R.id.buttonBet250);
        buttonSpin = findViewById(R.id.buttonSpin);
        slotItems = new ImageView[]{imageSlot1, imageSlot2, imageSlot3};

        points = getSharedPreferences("save", 0).getInt("points", START_POINTS);
        textViewScore.setText(String.format(Locale.getDefault(), "Points : %d", points));
        textViewBet.setText(String.format(Locale.getDefault(), "Your current bet is : %d", bet));

        Button[] betButtons = new Button[]{buttonBet50, buttonBet100, buttonBet250};
        for(Button betButton: betButtons){
            betButton.setOnClickListener(view -> {
                bet = Integer.parseInt(view.getTag().toString());
                Toast.makeText(getApplicationContext(), "Bet set to " + bet, Toast.LENGTH_SHORT).show();
                textViewBet.setText(String.format(Locale.getDefault(), "Your current bet is : %d", bet));
            });
        }
        buttonSpin.setOnClickListener(onClickListener);
        }

        View.OnClickListener onClickListener = view -> {
            if(!gameRunning){
                startAnimation();
            } else{
                Toast.makeText(getApplicationContext(), "You already did your spin!", Toast.LENGTH_SHORT).show();
            }
        };

        public void changePoints(int value){
            points += value;
            textViewScore.setText(String.format(Locale.getDefault(), "Points: %d", points));
        }

        public void startAnimation(){
            if(!gameRunning){
                gameRunning = true;
                CountDownTimer countDownTimer = new CountDownTimer(3000, 100) {
                    @Override
                    public void onTick(long l) {
                        int[] imageResources = {R.drawable.sloticon1, R.drawable.sloticon2, R.drawable.sloticon3};
                        for(ImageView item : slotItems){
                            int random = (int) (Math.random() * slotItems.length);
                            item.setImageResource(imageResources[random]);
                            item.setTag(imageResources[random]);
                        }
                    }

                    @Override
                    public void onFinish() {
                        int value;

                        if (imageSlot1.getTag().equals(imageSlot2.getTag())){
                            if(imageSlot2.getTag().equals(imageSlot3.getTag())){
                                value = bet * 3;
                            } else {
                                value = bet * 2;
                            }
                        } else {
                            value = -bet;
                        }
                        changePoints(value);
                        gameRunning = false;
                    }
                }.start();
            }
        }
    }
