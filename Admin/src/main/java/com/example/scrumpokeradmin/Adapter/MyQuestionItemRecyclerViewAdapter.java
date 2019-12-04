package com.example.scrumpokeradmin.Adapter;

import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scrumpokeradmin.Fragment.EditFragment;
import com.example.scrumpokeradmin.Object.Question;
import com.example.scrumpokeradmin.R;

import java.util.ArrayList;

public class MyQuestionItemRecyclerViewAdapter extends RecyclerView.Adapter<MyQuestionItemRecyclerViewAdapter.ViewHolder> {

    private View itemView;
    private ArrayList<Question> mListQuestion;
    private Dialog myDialog;

    public MyQuestionItemRecyclerViewAdapter(ArrayList<Question> listQuestion) {
        this.mListQuestion = listQuestion;
        Log.i("FBDB",mListQuestion.toString());
    }

    @NonNull
    @Override
    public MyQuestionItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyQuestionItemRecyclerViewAdapter.ViewHolder holder, int position) {
        final int pos = position;
        if (holder!=null){
            holder.timeTextView.setText(mListQuestion.get(position).getHour()+":"+mListQuestion.get(position).getMinute());
            holder.questionTextView.setText(mListQuestion.get(position).getQuestion());

        }else{
            Log.i("FBDB","Adapter problem: Holder empty!");
        }


        holder.list.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(),"Clicked to: "+pos,Toast.LENGTH_LONG).show();
                FragmentManager manager = ((AppCompatActivity)itemView.getContext()).getSupportFragmentManager();
                Fragment prev = manager.findFragmentByTag("fragment_edit");
                FragmentTransaction fragmentTransaction=manager.beginTransaction().show(prev);
                EditFragment editFragment = new EditFragment(mListQuestion.get(pos).getQuestion(),mListQuestion.get(pos).getHour()+":"+mListQuestion.get(pos).getMinute());
                editFragment.show(manager, "fragment_edit");
            }

        });
    }



    @Override
    public int getItemCount() {
        return mListQuestion.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTextView,questionTextView;
        public LinearLayout list;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            list = itemView.findViewById(R.id.list);
        }
    }

}
