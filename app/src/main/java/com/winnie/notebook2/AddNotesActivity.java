package com.winnie.notebook2;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNotesActivity extends AppCompatActivity {

    private ImageView category, work, family, study, personal;

    private EditText edt_write_note;

    private boolean isCategoryClicked, isWorkClicked, isFamilyClicked,
            isStudyClicked, isPersonalClicked;

    private NotebookModel notebookModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        //back

        notebookModel = (NotebookModel) getIntent().getExtras().getSerializable("content");


        category = findViewById(R.id.category);
        work = findViewById(R.id.work);
        family = findViewById(R.id.family);
        study = findViewById(R.id.study);
        personal = findViewById(R.id.personal);
        edt_write_note = findViewById(R.id.edt_write_note);

        edt_write_note.setText(notebookModel.getContent());




        category.setOnClickListener(view -> {

            Toast toast = Toast.makeText(AddNotesActivity.this, "Uncategorized", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            if (!isCategoryClicked) {
                category.setImageResource(R.drawable.category_filled);
                isCategoryClicked = true;


                isStudyClicked = false;
                study.setImageResource(R.drawable.study_outlined);

                isWorkClicked = false;
                work.setImageResource(R.drawable.work_outlined);

                isFamilyClicked = false;
                family.setImageResource(R.drawable.family_outlined);

                isPersonalClicked = false;
                personal.setImageResource(R.drawable.personal_outlined);
            }
        });

        work.setOnClickListener(view -> {

            Toast toast = Toast.makeText(AddNotesActivity.this, "Work", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            if (!isWorkClicked) {
                work.setImageResource(R.drawable.work_filled);
                isWorkClicked = true;

                isCategoryClicked = false;
                category.setImageResource(R.drawable.category_outlined);

                isStudyClicked = false;
                study.setImageResource(R.drawable.study_outlined);

                isFamilyClicked = false;
                family.setImageResource(R.drawable.family_outlined);

                isPersonalClicked = false;
                personal.setImageResource(R.drawable.personal_outlined);
            }
        });

        family.setOnClickListener(view -> {

            Toast toast = Toast.makeText(AddNotesActivity.this, "Family affair", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            if (!isFamilyClicked) {
                family.setImageResource(R.drawable.family_filled);
                isFamilyClicked = true;

                isCategoryClicked = false;
                category.setImageResource(R.drawable.category_outlined);

                isStudyClicked = false;
                study.setImageResource(R.drawable.study_outlined);

                isWorkClicked = false;
                work.setImageResource(R.drawable.work_outlined);

                isPersonalClicked = false;
                personal.setImageResource(R.drawable.personal_outlined);
            }
        });

        study.setOnClickListener(view -> {

            Toast toast = Toast.makeText(AddNotesActivity.this, "Study", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();


            if (!isStudyClicked) {
                study.setImageResource(R.drawable.study_filled);
                isWorkClicked = false;
                work.setImageResource(R.drawable.work_outlined);

                isCategoryClicked = false;
                category.setImageResource(R.drawable.category_outlined);

                isStudyClicked = true;

                isFamilyClicked = false;
                family.setImageResource(R.drawable.family_outlined);

                isPersonalClicked = false;
                personal.setImageResource(R.drawable.personal_outlined);
            }
        });

        personal.setOnClickListener(view -> {

            Toast toast = Toast.makeText(AddNotesActivity.this, "Personal", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();


            if (!isPersonalClicked) {
                personal.setImageResource(R.drawable.personal_filled);
                isPersonalClicked = true;

                isWorkClicked = false;
                work.setImageResource(R.drawable.work_outlined);

                isCategoryClicked = false;
                category.setImageResource(R.drawable.category_outlined);

                isStudyClicked = false;
                study.setImageResource(R.drawable.study_outlined);

                isFamilyClicked = false;
                family.setImageResource(R.drawable.family_outlined);

            }
        });


    }
}