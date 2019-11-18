package com.example.scrumpokeradmin.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scrumpokeradmin.Object.DatabaseHelper;
import com.example.scrumpokeradmin.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText nameEditText,sessionNameEditText;
    public static DatabaseHelper db;
    Integer key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicialize();
        getLastSessionId();
        db = new DatabaseHelper();
    }

    public void loginButtonClick(View view) {
        if (!nameEditText.getText().toString().equals("") && !sessionNameEditText.getText().toString().equals(""))
        {
            db.setLastKey(key);
            db.setOwnerName(nameEditText.getText().toString());
            db.setSessionName(sessionNameEditText.getText().toString());
            Intent intent = new Intent(LoginActivity.this,CreateActivity.class);
            intent.putExtra("ownerName",nameEditText.getText().toString());
            intent.putExtra("sessionName",sessionNameEditText.getText().toString());
            intent.putExtra("sessionId",key);
            Log.i("FBDB","LASTKEY: "+key);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Please fill all fields!",Toast.LENGTH_SHORT).show();
        }
    }

    public void inicialize() {
        loginButton = findViewById(R.id.loginButton);
        nameEditText = findViewById(R.id.nameEditText);
        sessionNameEditText = findViewById(R.id.sessionNameEditText);
        Log.i("FBDB","LOGIN ACTIVITY INICIALIZED");
    }

    public void getLastSessionId(){
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
        Query query = mDatabaseReference.orderByChild("SESSION").limitToLast(1);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String keyTemp = dataSnapshot.getKey();
                key = Integer.parseInt(keyTemp);
                Log.i("FBDB","LOGIN:SESSION LAST KEY"+ key);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String keyTemp = dataSnapshot.getKey();
                key = Integer.parseInt(keyTemp);
                Log.i("FBDB","LOGIN:SESSION LAST KEY"+ key);
                getLastSessionId();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
