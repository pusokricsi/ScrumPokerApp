package com.example.scrumpokeradmin.Fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.scrumpokeradmin.R;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),this, hour, min, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        //Do something with the user chosen time
        //Get reference of host activity (XML Layout File) TextView widget
        EditText tv = getActivity().findViewById(R.id.timeEditText);

        if (hourOfDay<=9 && minute<=9)
        {
            tv.setText("0"+hourOfDay+":"+"0"+minute);
        }else
        if (hourOfDay >=0 && hourOfDay <=9)
        {
            tv.setText("0"+hourOfDay+":"+minute);
        }else
        if (minute>=0 && minute<=9)
        {
            tv.setText(hourOfDay+":"+"0"+minute);
        }
        else
            {
                tv.setText(hourOfDay+":"+minute);
            }
    }
}
