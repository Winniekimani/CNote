package com.winnie.notebook2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    RecyclerView notebook_rc;
    ArrayList<NotebookModel> notebookModelArrayList;
    FloatingActionButton add_note_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);

        notebook_rc = findViewById(R.id.notebook_rc);
        add_note_fab = findViewById(R.id.add_note_fab);

        notebookModelArrayList = Paper.book().read("notes", new ArrayList<>());


        NotebookAdapter notebookAdapter = new NotebookAdapter(this, notebookModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        notebook_rc.setAdapter(notebookAdapter);
        notebook_rc.setLayoutManager(linearLayoutManager);

        add_note_fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddNotesActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("from","A");
            intent.putExtras(bundle);
            startActivity(intent);
        });

        PopupMenu popup = new PopupMenu(MainActivity.this, findViewById(R.id.overflow_menu));
        popup.inflate(R.menu.overflow_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.delete_all:


                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
                        sweetAlertDialog.setTitleText("Are you sure?");
                        sweetAlertDialog.setContentText("All notes will be deleted!");
                        sweetAlertDialog.setConfirmButton("Delete", new SweetAlertDialog.OnSweetClickListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                notebookModelArrayList.clear();
                                Paper.book().delete("notes");
                                Objects.requireNonNull(notebook_rc.getAdapter()).
                                        notifyDataSetChanged();
                                sweetAlertDialog.dismissWithAnimation();

                            }
                        });
                        sweetAlertDialog.setConfirmButtonTextColor(Color.parseColor("#ff0000"));

                        sweetAlertDialog.setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
                        sweetAlertDialog.setCancelButtonTextColor(Color.parseColor("#000000"));
                        sweetAlertDialog.show();

                }

                return true;
            }
        });

        findViewById(R.id.overflow_menu).setOnClickListener(v -> popup.show());
        findViewById(R.id.overflow_menu).setOnTouchListener(popup.getDragToOpenListener());

    }
}