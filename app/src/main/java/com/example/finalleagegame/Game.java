package com.example.finalleagegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game extends AppCompatActivity {
    public String characterName;
    public EditText nameInput;
    public Button submitButton;
    public Button restartButton;
    public TextView msg;
    public TextView scoring;
    private static final int championCount = 147;
    public static int questionCount = 0;
    public static int questionNumber = 1;
    public static int answerCount = 0;
    public static List<Integer> order = null;

    ArrayList<String> champList = new ArrayList<>();
    ArrayList<String> hintList = new ArrayList<>();

    Random random = new Random();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int a = random.nextInt(championCount);
        getJson();

        String questionLower = hintList.get(a);
        String question = questionLower.toUpperCase();
        final String answer = champList.get(a);

        msg = (TextView) findViewById(R.id.changeText);
        scoring = (TextView) findViewById(R.id.theScore);


        msg.setText(questionNumber + ". " + question);
        scoring.setText(Integer.toString(answerCount));

        restartButton = (Button) findViewById(R.id.restart);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRestart();
            }
        });

        nameInput = (EditText) findViewById(R.id.nameInput);
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                characterName = nameInput.getText().toString();
                questionCount++;
                questionNumber++;
                if (characterName.toLowerCase().equals(answer)) {
                    answerCount++;
                }
                if (questionCount == 10) {
                    openEnd();
                } else {
                    openAgain();
                }
            }
        });
    }

    public void openEnd() {
        Intent intent = new Intent(this, EndOfTheGame.class);
        startActivity(intent);
    }

    public void openRestart() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openAgain() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    public void getJson() {
        String json;
        try {
            InputStream is = getAssets().open("LeagueCharacter.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "utf-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                champList.add(obj.getString("name"));
                hintList.add(obj.getString("nickname"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException o) {
            o.printStackTrace();
        }
    }
}
