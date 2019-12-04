package com.example.scrumpokeradmin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.scrumpokeradmin.Fragment.CreateFragment;
import com.example.scrumpokeradmin.Fragment.EditFragment;
import com.example.scrumpokeradmin.Fragment.ListFragment;
import com.example.scrumpokeradmin.R;

public class CreateActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //CREATE A FRAGMENT
        CreateFragment createFragment = new CreateFragment();
        ListFragment listFragment = new ListFragment();
        //CREATE A FRAGMENT MANAGER
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.createFragment, createFragment);
        fragmentTransaction.add(R.id.listFragment, listFragment);
        fragmentTransaction.commit();

    }

}
