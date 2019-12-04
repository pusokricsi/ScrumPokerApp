package com.example.scrumpokeradmin.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.scrumpokeradmin.Activity.LoginActivity;
import com.example.scrumpokeradmin.Adapter.StateAdapter;
import com.example.scrumpokeradmin.Object.Question;
import com.example.scrumpokeradmin.Object.StateDataModel;
import com.example.scrumpokeradmin.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class EditFragment extends DialogFragment {
    private View view;
    private EditText questionEditText,timeEditText;
    private Button sendQuestionButton,deleteButton;
    String question,time;
    private DatabaseReference mDatabaseReference;
    private int lastKey;
    private ListView stateListView;
    ArrayList<StateDataModel> stateDataModel;

    public EditFragment(String question,String time) {
        this.question = question;
        this.time = time;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_edit, container, false);
        inicialize();
        questionEditText.setText(question);
        timeEditText.setText(time);
        final String key = LoginActivity.db.getQuestionKey(question);
        sendQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!questionEditText.getText().toString().equals("") && !timeEditText.getText().toString().equals("")){
                    String time = timeEditText.getText().toString();
                    int index = time.indexOf(":");
                    String hour = time.substring(0,index);
                    String minute = time.substring(index+1);
                    Question question = new Question(questionEditText.getText().toString(),minute,hour);
                    LoginActivity.db.setQuestion(question,key);
                }else{
                    Toast.makeText(view.getContext(),"You did'n write a question!",Toast.LENGTH_LONG).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.db.deleteQuestion(key);
            }
        });

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getFragmentManager(), "time picker");
            }
        });
        lastKey = LoginActivity.db.getLastKey();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("SESSION");
        Query query = mDatabaseReference.child(String.valueOf(lastKey)).child("users");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String statekey = null,username = null;
                for (DataSnapshot child:dataSnapshot.getChildren()){
                    Log.i("FBDB","EditFragment/userkey:  "+child.getKey());
                    if (child.getKey().equals(key)){
                        statekey = String.valueOf(child.getValue());
                    }
                    if (child.getKey().equals("username")){
                        username = String.valueOf(child.getValue());
                    }
                }
                stateDataModel.add(new StateDataModel(username,statekey));
                Log.i("FBDB","EditFragment/StateDataModelltoString:  "+stateDataModel.toString());
                StateAdapter stateAdapter = new StateAdapter(EditFragment.this.getContext(),0,stateDataModel);
                stateListView.setAdapter(stateAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

    public void inicialize()
    {
        questionEditText = view.findViewById(R.id.questionEditText);
        sendQuestionButton = view.findViewById(R.id.setQuestionButton);
        timeEditText = view.findViewById(R.id.timeEditText);
        deleteButton = view.findViewById(R.id.deleteQuestionButton);
        stateListView = view.findViewById(R.id.stateListView);
        stateDataModel = new ArrayList<>();
    }


}
