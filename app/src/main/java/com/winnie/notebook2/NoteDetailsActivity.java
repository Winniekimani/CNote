package com.winnie.notebook2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class NoteDetailsActivity extends AppCompatActivity {

    private TextView noteContent;
    private TextView note_category;
    private NotebookModel notebookModel;
    private ImageView image_uploaded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        findViewById(R.id.back_btn).setOnClickListener(view -> NoteDetailsActivity.super.onBackPressed());
        notebookModel = (NotebookModel) getIntent().getExtras().getSerializable("note");

        noteContent = findViewById(R.id.noteContent);
        note_category = findViewById(R.id.note_category);
        image_uploaded = findViewById(R.id.image_uploaded);


        noteContent.setText(notebookModel.getContent());
        note_category.setText(notebookModel.getCategory());

        Glide.with(this).load(notebookModel.getImage_url()).into(image_uploaded);

        if (notebookModel.getCategory().equals("study")){

            note_category.setTextColor(Color.parseColor("#FFBB86FC"));
        }else if (notebookModel.getCategory().equals("uncategorized")){
            note_category.setTextColor(Color.parseColor("#137813"));
        }else if ( notebookModel.getCategory().equals("work")){
            note_category.setTextColor(Color.parseColor("#CC1A237E"));
        }else if ( notebookModel.getCategory().contains("family")) {
            note_category.setTextColor(Color.parseColor("#FF0000"));
        }else if (notebookModel.getCategory().equals("personal")){
            note_category.setTextColor(Color.parseColor("#FFA500"));
        }



        noteContent.setOnClickListener(view -> toAddNotesActivity());


        findViewById(R.id.edit_note_fab).setOnClickListener(view -> toAddNotesActivity());


    }

    private void toAddNotesActivity() {
        Intent intent = new Intent(NoteDetailsActivity.this, AddNotesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("content",notebookModel);
        bundle.putString("from","B");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}