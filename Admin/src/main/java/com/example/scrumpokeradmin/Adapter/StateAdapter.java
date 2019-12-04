package com.example.scrumpokeradmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.scrumpokeradmin.Object.StateDataModel;
import com.example.scrumpokeradmin.R;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends ArrayAdapter<StateDataModel> {

    private Context context;
    private ArrayList<StateDataModel> stateDataModels;

    public StateAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public StateAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public StateAdapter(@NonNull Context context, int resource, @NonNull StateDataModel[] objects) {
        super(context, resource, objects);
    }

    public StateAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull StateDataModel[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public StateAdapter(@NonNull Context context, int resource, @NonNull ArrayList<StateDataModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.stateDataModels = objects;
    }

    public StateAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<StateDataModel> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.spaced_list_item, parent, false);
        }
        StateDataModel stateDataModel = stateDataModels.get(position);

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        nameTextView.setText(stateDataModel.getUsername());
        TextView stateTextView = convertView.findViewById(R.id.stateTextView);
        stateTextView.setText(stateDataModel.getState());

        return convertView;
    }
}
