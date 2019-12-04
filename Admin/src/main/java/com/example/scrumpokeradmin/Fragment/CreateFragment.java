package com.example.scrumpokeradmin.Fragment;


import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scrumpokeradmin.Activity.LoginActivity;
import com.example.scrumpokeradmin.Object.Question;
import com.example.scrumpokeradmin.R;


public class CreateFragment extends Fragment {
    private View view;
    private EditText questionEditText,timeEditText;
    private Button sendQuestionButton;
    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_create, container, false);
        inicialize();
        sendQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!questionEditText.getText().toString().equals("") && !timeEditText.getText().toString().equals("")){
                    String time = timeEditText.getText().toString();
                    int index = time.indexOf(":");
                    String hour = time.substring(0,index);
                    String minute = time.substring(index+1);
                    Question question = new Question(questionEditText.getText().toString(),minute,hour);
                    LoginActivity.db.setQuestion(question);
                }else{
                    Toast.makeText(view.getContext(),"You did'n write a question!",Toast.LENGTH_LONG).show();
                }
            }
        });

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getFragmentManager(), "time picker");
            }
        });
        return view;
    }

    public void inicialize()
    {
        questionEditText = view.findViewById(R.id.questionEditText);
        sendQuestionButton = view.findViewById(R.id.setQuestionButton);
        timeEditText = view.findViewById(R.id.timeEditText);
    }

}
