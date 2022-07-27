package com.winnie.notebook2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView notebook_rc;
    ArrayList<NotebookModel>notebookModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        notebook_rc = findViewById(R.id.notebook_rc);
        notebookModelArrayList = new ArrayList<>();

        NotebookModel notebookModel = new NotebookModel();
        notebookModel.setCategory("study");
        notebookModel.setContent("This is a test note for study ilkmunjycfedxs");

        NotebookModel notebookModel1 = new NotebookModel();
        notebookModel1.setCategory("work");
        notebookModel1.setContent("This is a test note for work ilkmunjycfedxs");


        NotebookModel notebookModel2 = new NotebookModel();
        notebookModel2.setCategory("personal");
        notebookModel2.setContent("This is a test note for personal ilkmunjycfedxs");

        NotebookModel notebookModel3 = new NotebookModel();
        notebookModel3.setCategory("family");
        notebookModel3.setContent("This is a test note for work ilkmunjycfedxs");

        NotebookModel notebookModel4 = new NotebookModel();
        notebookModel4.setCategory("uncategorized");
        notebookModel4.setContent("This is a test note for work ilkmunjycfedxs");

        notebookModelArrayList.add(notebookModel);
        notebookModelArrayList.add(notebookModel1);
        notebookModelArrayList.add(notebookModel2);
        notebookModelArrayList.add(notebookModel3);
        notebookModelArrayList.add(notebookModel4);

        //adding notebooks to 50
        for (int i = 0; i < 50; i++){
            notebookModelArrayList.add(notebookModelArrayList.get(i));
        }




        NotebookAdapter notebookAdapter = new NotebookAdapter(this, notebookModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        notebook_rc.setAdapter(notebookAdapter);
        notebook_rc.setLayoutManager(linearLayoutManager);
    }
}