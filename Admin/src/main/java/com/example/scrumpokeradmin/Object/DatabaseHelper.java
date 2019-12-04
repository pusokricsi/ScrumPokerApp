package com.example.scrumpokeradmin.Object;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatabaseHelper {

    private String ownerName,sessionName;
    private ArrayList<Question> questions;
    private Integer lastKey = 0;


    private DatabaseReference mDatabaseReference;
    private Query query;

    public DatabaseHelper() {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
        questions = new ArrayList<Question>();
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        mDatabaseReference.child(String.valueOf(lastKey)).child("ownerName").setValue(ownerName);
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
        mDatabaseReference.child(String.valueOf(lastKey)).child("sessionName").setValue(sessionName);
    }

    public Integer getLastKey() {
        return lastKey;
    }

    public void setLastKey(Integer lastKey) {
        ++lastKey;
        this.lastKey = lastKey;
        getData();
    }

    public void setQuestion(Question question){
        mDatabaseReference.child(String.valueOf(lastKey)).child("question").push().setValue(question);
    }

    public void setQuestion(Question question,String key){
        mDatabaseReference.child(String.valueOf(lastKey)).child("question").child(String.valueOf(key)).setValue(question);
    }

    public ArrayList<Question> getQuestions(){
        return questions;
    }

    public void getData(){
        query = mDatabaseReference.child(String.valueOf(lastKey));
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();
                Log.i("FBDB","onChildAdded "+dataSnapshot.getKey());
                if (key.equals("ownerName"))
                {
                    ownerName = dataSnapshot.getValue().toString();
                    Log.i("FBDB","onChildAdded/ownerName: "+dataSnapshot.getValue());
                }
                if (key.equals("sessionName"))
                {
                    sessionName = dataSnapshot.getValue().toString();
                    Log.i("FBDB","onChildAdded/sessionName: "+dataSnapshot.getValue());
                }
                if (key.equals("question"))
                {
                    for (DataSnapshot child1: dataSnapshot.getChildren())
                    {
                        String question = "",minute = "",hour = "";
                        Log.i("FBDB","onChildAdded/questionKey: "+child1.getKey());
                        String qKey = child1.getKey();
                        for (DataSnapshot child2: child1.getChildren())
                        {
                            if (child2.getKey().equals("question"))
                            {
                                question = child2.getValue().toString();
                            }
                            if (child2.getKey().equals("minute"))
                            {
                                minute = child2.getValue().toString();
                            }
                            if (child2.getKey().equals("hour"))
                            {
                                hour = child2.getValue().toString();
                            }

                            Log.i("FBDB","onChildAdded/questionKey5555: "+child2.getKey());
                        }
                        Question question1 = new Question(question,minute,hour,qKey);
                        questions.add(question1);
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();
                Log.i("FBDB","onChildAdded "+dataSnapshot.getKey());
                if (key.equals("ownerName"))
                {
                    ownerName = dataSnapshot.getValue().toString();
                    Log.i("FBDB","onChildAdded/ownerName: "+dataSnapshot.getValue());
                }
                if (key.equals("sessionName"))
                {
                    sessionName = dataSnapshot.getValue().toString();
                    Log.i("FBDB","onChildAdded/sessionName: "+dataSnapshot.getValue());
                }
                if (key.equals("question"))
                {
                    questions.clear();
                    for (DataSnapshot child1: dataSnapshot.getChildren())
                    {
                        String question = "",minute = "",hour = "";
                        Log.i("FBDB","onChildAdded/questionKey: "+child1.getKey());
                        String qKey = child1.getKey();
                        for (DataSnapshot child2: child1.getChildren())
                        {
                            if (child2.getKey().equals("question"))
                            {
                                question = child2.getValue().toString();
                            }
                            if (child2.getKey().equals("minute"))
                            {
                                minute = child2.getValue().toString();
                            }
                            if (child2.getKey().equals("hour"))
                            {
                                hour = child2.getValue().toString();
                            }

                            Log.i("FBDB","onChildAdded/questionKey5555: "+child2.getKey());
                        }
                        Question question1 = new Question(question,minute,hour, qKey);
                        questions.add(question1);
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                Log.i("FBDB","onChildAdded "+dataSnapshot.getKey());
                if (key.equals("ownerName"))
                {
                    ownerName = dataSnapshot.getValue().toString();
                    Log.i("FBDB","onChildAdded/ownerName: "+dataSnapshot.getValue());
                }
                if (key.equals("sessionName"))
                {
                    sessionName = dataSnapshot.getValue().toString();
                    Log.i("FBDB","onChildAdded/sessionName: "+dataSnapshot.getValue());
                }
                if (key.equals("question"))
                {
                    questions.clear();
                    for (DataSnapshot child1: dataSnapshot.getChildren())
                    {
                        String question = "",minute = "",hour = "";
                        Log.i("FBDB","onChildAdded/questionKey: "+child1.getKey());
                        String qKey = child1.getKey();
                        for (DataSnapshot child2: child1.getChildren())
                        {
                            if (child2.getKey().equals("question"))
                            {
                                question = child2.getValue().toString();
                            }
                            if (child2.getKey().equals("minute"))
                            {
                                minute = child2.getValue().toString();
                            }
                            if (child2.getKey().equals("hour"))
                            {
                                hour = child2.getValue().toString();
                            }

                            Log.i("FBDB","onChildAdded/questionKey5555: "+child2.getKey());
                        }
                        Question question1 = new Question(question,minute,hour, qKey);
                        questions.remove(question1);
                    }
                }

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public String getQuestionKey (String mQuestion){
        String key = null;
        for (Question question:questions)
        {
            CHECK:
            if (question.getQuestion().equals(mQuestion))
            {
                key = question.getKey();
                Log.i("FBDB","THEKEY: "+key);
                break CHECK;
            }
        }
        return key;
    }

    public void deleteQuestion(String key){
        mDatabaseReference.child(String.valueOf(lastKey)).child("question").child(String.valueOf(key)).removeValue();
    }

}
