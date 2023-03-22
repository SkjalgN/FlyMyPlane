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

public class AndroidFireBaseAPI implements API{

    private FirebaseDatabase database;
    private DatabaseReference myRef; /*highscores*/

    public AndroidFireBaseAPI() {
        database = FirebaseDatabase.getInstance("https://flymyplane-6119e-default-rtdb.europe-west1.firebasedatabase.app");
        myRef = database.getReference("message");
    }
    @Override
    public void submitHighscore(Score score) {

        myRef.push().setValue(score);
    }

   @Override
    public void getHighscores(ArrayList<Score> dataHolder) {
        System.out.println("Getting highscores");
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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

    @Override
    public void someFunction() {
        System.out.println("Just someFunction from Anroid");
    }

    @Override
    public void FirstFireBaseTest() {
        if (myRef != null){
            myRef.setValue("Hello, World!");
        }else {
            System.out.println("Database reference could not be set -> could not write to DB");
        }
    }


}

