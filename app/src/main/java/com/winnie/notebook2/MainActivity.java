package com.winnie.notebook2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView notebook_rc;
    ArrayList<NotebookModel> notebookModelArrayList;
    FloatingActionButton add_note_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //forgotten comment


        notebook_rc = findViewById(R.id.notebook_rc);
        add_note_fab = findViewById(R.id.add_note_fab);
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
        notebookModel3.setCategory("family affair");
        notebookModel3.setContent("This is a test note for family ilkmunjycfedxs");

        NotebookModel notebookModel4 = new NotebookModel();
        notebookModel4.setCategory("uncategorized");
        notebookModel4.setContent("This is a test note for uncat ilkmunjycfedxs");

        notebookModelArrayList.add(notebookModel);
        notebookModelArrayList.add(notebookModel1);
        notebookModelArrayList.add(notebookModel2);
        notebookModelArrayList.add(notebookModel3);
        notebookModelArrayList.add(notebookModel4);

        //adding notebooks to 50
        for (int i = 0; i < 50; i++) {
            notebookModelArrayList.add(notebookModelArrayList.get(i));
        }


        NotebookAdapter notebookAdapter = new NotebookAdapter(this, notebookModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        notebook_rc.setAdapter(notebookAdapter);
        notebook_rc.setLayoutManager(linearLayoutManager);

        add_note_fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddNotesActivity.class);
            startActivity(intent);
        });

        PopupMenu popup = new PopupMenu(MainActivity.this, findViewById(R.id.overflow_menu));
        popup.inflate(R.menu.overflow_menu);
        popup.setOnMenuItemClickListener(menuItem -> true);

        findViewById(R.id.overflow_menu).setOnClickListener(v -> popup.show());
        findViewById(R.id.overflow_menu).setOnTouchListener(popup.getDragToOpenListener());

    }
}