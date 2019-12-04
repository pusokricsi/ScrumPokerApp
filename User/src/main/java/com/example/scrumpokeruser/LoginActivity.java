package com.example.scrumpokeruser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText,sessionNameEditText;
    private String userKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicialize();
    }

    public void inicialize(){
        usernameEditText = findViewById(R.id.usernameEditText);
        sessionNameEditText = findViewById(R.id.sessionNameEditText);
    }

    public void getSession(){
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
        Query query = mDatabaseReference.orderByChild("SESSION");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userDatabaseKey = "";
                String sessionname = sessionNameEditText.getText().toString();
                String key = null;
                for (DataSnapshot child:dataSnapshot.getChildren()){
                    if (child.child("sessionName").getValue().equals(sessionname)){
                        Log.i("FBDB",sessionname+" IS EXIST AT ID: "+child.getKey());
                        key = child.getKey();
                        for (DataSnapshot child1:child.child("users").getChildren()){
                            Log.i("FBDB","USER KEYS: "+child1.getKey());
                            if (usernameEditText.getText().toString().equals(child1.child("username").getValue())){
                                userDatabaseKey = String.valueOf(child1.getKey());
                            }
                        }
                    }
                }
                if (usernameEditText.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Username is empty!", Toast.LENGTH_SHORT).show();
                }else{
                    if (key == null) {
                        Toast.makeText(LoginActivity.this, "Sessionid is emplty/not exist!", Toast.LENGTH_SHORT).show();
                    } else if (userDatabaseKey.equals("")){
                        addNewUser(usernameEditText.getText().toString(), key);
                        Intent intent = new Intent(LoginActivity.this,QuestionActivity.class);
                        intent.putExtra("key_value",key);
                        intent.putExtra("userkey_value",userKey);
                        startActivity(intent);
                    }else{
                        userKey = userDatabaseKey;
                        Intent intent = new Intent(LoginActivity.this,QuestionActivity.class);
                        intent.putExtra("key_value",key);
                        intent.putExtra("userkey_value",userKey);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void joinButtonClick(View view) {
        getSession();
    }

    public void addNewUser(String username,String key){
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
        userKey = mDatabaseReference.child(key).child("users").push().getKey();
        mDatabaseReference.child(key).child("users").child(userKey).child("username").setValue(username);
    }

}
