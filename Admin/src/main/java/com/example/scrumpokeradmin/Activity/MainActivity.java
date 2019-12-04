package com.example.scrumpokeradmin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.scrumpokeradmin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
        mDatabaseReference.child("SESSION").child("Alma").setValue("Korte");
    }
}
