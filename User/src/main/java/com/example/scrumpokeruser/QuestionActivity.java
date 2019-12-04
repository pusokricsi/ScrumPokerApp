package com.example.scrumpokeruser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class QuestionActivity extends AppCompatActivity {

    private TextView questionTextView,voteStatusTextView;
    private String key,questionKey,userKey;
    private int currentHour,currentMinute,nextCurrentHour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        inicialize();
        getSession();
    }

    public void inicialize(){
        questionTextView = findViewById(R.id.questionTextView);
        voteStatusTextView = findViewById(R.id.voteStatusTextView);
        Intent intent = getIntent();
        key = intent.getStringExtra("key_value");
        userKey = intent.getStringExtra("userkey_value");
        getCurrentDate();
    }

    public void getSession(){
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
        Query query = mDatabaseReference.child(key).child("question");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child:dataSnapshot.getChildren()){
                    Log.i("FBDB","CHILD: "+child.getKey()+" QUESTION: "+child.child("question").getValue());
                    int hour = Integer.valueOf(String.valueOf(child.child("hour").getValue()));
                    int minute = Integer.valueOf(String.valueOf(child.child("minute").getValue()));
                    if (currentHour==hour && currentMinute>=minute){
                        questionTextView.setText((CharSequence) child.child("question").getValue());
                        Log.i("FBDB"," QUESTION: "+child.child("question").getValue()+" TIME: "+hour+" "+minute);
                        questionKey = child.getKey();
                    }
                    if (currentHour==hour+1 && currentMinute<=minute){
                        questionTextView.setText((CharSequence) child.child("question").getValue());
                        Log.i("FBDB"," QUESTION: "+child.child("question").getValue()+" TIME: "+hour+" "+minute);
                        questionKey = child.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        this.currentHour = cal.get(Calendar.HOUR_OF_DAY);
        this.currentMinute = cal.get(Calendar.MINUTE);
        this.nextCurrentHour = this.currentHour+1;
        Log.i("FBDB","Current time"+currentHour+" : "+currentMinute);
    }

    public void buttonOneClick(View view) {

        setQuestionVoteStatus("1");
    }

    public void buttonTwoClick(View view) {

        setQuestionVoteStatus("2");
    }

    public void buttonThreeClick(View view) {

        setQuestionVoteStatus("3");
    }

    public void buttonFourClick(View view) {

        setQuestionVoteStatus("4");
    }

    public void buttonFiveClick(View view) {

        setQuestionVoteStatus("5");
    }

    public void setQuestionVoteStatus(String status){

        if (questionTextView.getText().equals("No question")){
            voteStatusTextView.setText("NAN");
            Toast.makeText(QuestionActivity.this, "No question", Toast.LENGTH_SHORT).show();
        }else {
            voteStatusTextView.setText(status);
            DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
            mDatabaseReference.child(key).child("users").child(userKey).child(questionKey).setValue(status);
        }
    }
}
