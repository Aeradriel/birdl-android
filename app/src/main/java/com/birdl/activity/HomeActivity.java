package com.birdl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import hof.HofViewAdapter;
import hof.RowItemsHof;
import hof.SimpleDividerItemDecoration;

public class HomeActivity extends Fragment {

    RecyclerView recyclerView;
    ArrayList<RowItemsHof> itemsList = new ArrayList<>();
    HofViewAdapter adapter;
    ProgressBar englighBar;
    ProgressBar otherBar;

    public HomeActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home, container, false);

        englighBar = (ProgressBar) rootView.findViewById(R.id.progressBarEnglish);
        englighBar.setVisibility(View.VISIBLE);
        otherBar = (ProgressBar) rootView.findViewById(R.id.progressBarOther);
        otherBar.setVisibility(View.VISIBLE);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.HallOfFame);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        adapter = new HofViewAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public ArrayList<RowItemsHof> getData()
    {
        ArrayList<RowItemsHof> it = new ArrayList<RowItemsHof>();
        RowItemsHof items1 = new RowItemsHof();
        items1.setPos("1");
        items1.setName("Android");
        items1.setGrade("Grade");
        it.add(items1);
        RowItemsHof items2 = new RowItemsHof();
        items2.setPos("2");
        items2.setName("Java");
        items2.setGrade("Grade");
        it.add(items2);
        RowItemsHof items3 = new RowItemsHof();
        items3.setPos("3");
        items3.setName("iphone");
        items3.setGrade("Grade");
        it.add(items3);
        RowItemsHof items4 = new RowItemsHof();
        items4.setPos("4");
        items4.setName("Ios");
        items4.setGrade("Grade");
        it.add(items4);
        RowItemsHof items5 = new RowItemsHof();
        items5.setPos("5");
        items5.setName("Tomcat");
        items5.setGrade("Grade");
        it.add(items5);
        return it;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
