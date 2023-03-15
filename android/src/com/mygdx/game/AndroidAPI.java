package com.mygdx.game;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mygdx.game.Model.Score;

import java.util.ArrayList;
import java.util.Collections;

public class AndroidAPI implements API{

    private FirebaseDatabase database;
    private DatabaseReference highscores;

    public AndroidAPI() {
        database = FirebaseDatabase.getInstance();
        highscores = database.getReference("highscores");
    }
    @Override
    public void submitHighscore(Score score) {
        highscores.push().setValue(score);
    }

    @Override
    public void getHighscores(ArrayList<Score> dataHolder) {
        System.out.println("Getting highscores");
        highscores.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                System.out.println("Got highscores");
                Iterable<DataSnapshot> response = task.getResult().getChildren();
                for (DataSnapshot child : response) {
                    dataHolder.add(child.getValue(Score.class));
                }
                Collections.sort(dataHolder);
            }
        });
    }

    public static void main(String[] args) {
        AndroidAPI api = new AndroidAPI();
        Score score = new Score(300,"Chris");
        api.submitHighscore(score);
    }
}

