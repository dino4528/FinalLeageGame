package com.example.finalleagegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.finalleagegame.Game;

public class EndOfTheGame extends AppCompatActivity {
    public TextView msg;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_the_game);

        button = (Button) findViewById(R.id.restartButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRestart();
            }
        });
    }

    public void openRestart() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
