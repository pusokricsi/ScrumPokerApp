package com.example.scrumpokeradmin.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scrumpokeradmin.Activity.LoginActivity;
import com.example.scrumpokeradmin.Adapter.MyQuestionItemRecyclerViewAdapter;
import com.example.scrumpokeradmin.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ListFragment extends Fragment {

    private View view;
    private MyQuestionItemRecyclerViewAdapter mAdapter;
    private DatabaseReference mDatabaseReference;

    public ListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.questionListRecyclerView);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");

        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mAdapter = new MyQuestionItemRecyclerViewAdapter(LoginActivity.db.getQuestions());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mAdapter);
                Log.i("FBDB","Listener childAdded");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mAdapter = new MyQuestionItemRecyclerViewAdapter(LoginActivity.db.getQuestions());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mAdapter);
                Log.i("FBDB","Listener childChanged");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                mAdapter = new MyQuestionItemRecyclerViewAdapter(LoginActivity.db.getQuestions());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mAdapter);
                Log.i("FBDB","Listener childRemoved");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mAdapter = new MyQuestionItemRecyclerViewAdapter(LoginActivity.db.getQuestions());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }



}
