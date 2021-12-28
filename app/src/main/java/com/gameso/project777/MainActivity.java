package com.gameso.project777;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonStartGame;
    private Button buttonOptions;
    private Button buttonPrivacyPolicy;
    private Button buttonExit;
    private Bundle savedInstanceState;


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStartGame = findViewById(R.id.buttonStartGame);
        buttonOptions = findViewById(R.id.buttonOptions);
        buttonPrivacyPolicy = findViewById(R.id.buttonPrivacyPolicy);
        buttonExit = findViewById(R.id.buttonExit);
        Button[] buttons = new Button[]{buttonStartGame, buttonOptions, buttonPrivacyPolicy, buttonExit};
        for(Button b: buttons){
            b.setOnClickListener(onClickListener);
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int selectedItem = Integer.parseInt(view.getTag().toString());
            switch(selectedItem){
                case 0:
                    Intent intentStartGame = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(intentStartGame);
                    finish();
                    break;
                case 1:
                    Intent intentOptions = new Intent(MainActivity.this, OptionActivity.class);
                    startActivity(intentOptions);
                    finish();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), "Policy is not yet implemented", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    finishAffinity();
                    break;
            }
        }
    };
}