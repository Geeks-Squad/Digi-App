package com.gadspiro.bharani.hack;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.andexert.expandablelayout.library.ExpandableLayoutListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Doc_view extends AppCompatActivity {



    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    /*public void onBackPressed() {
        super.onBackPressed();

        SharedPreferences preferences =getSharedPreferences("MyPref",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("driv");
        editor.remove("pan");
        editor.commit();
        finish();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_view);



        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                return false;
            }
        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final String[] array = {"Hello", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome"};

/*
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.view_row, R.id.header_text, array);
        final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) findViewById(R.id.listview);

        expandableLayoutListView.setAdapter(arrayAdapter);

        final VListAdapter arrayAdapter = new VListAdapter(this,  new String[]{"Approved", "Pending"},new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher});
        final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) findViewById(R.id.listview);

        expandableLayoutListView.setAdapter(arrayAdapter);*/



    }

}
