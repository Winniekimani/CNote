package com.winnie.notebook2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class AddNotesActivity extends AppCompatActivity {

    private ImageView category, work, family, study, personal;

    private EditText edt_write_note;

    private boolean isCategoryClicked, isWorkClicked, isFamilyClicked,
            isStudyClicked, isPersonalClicked;

    private NotebookModel notebookModel;
    private String from;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;
    private Uri image_uri;


    TextView txt_save_btn,txt_cancel_btn;
    ExtendedFloatingActionButton add_photo;
    private ImageView img_photo_uploaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        notebookModel = new NotebookModel();

        txt_save_btn=findViewById(R.id.txt_save_btn);
        txt_cancel_btn = findViewById(R.id.txt_cancel_btn);
        add_photo = findViewById(R.id.add_photo);
        img_photo_uploaded = findViewById(R.id.img_photo_uploaded);


        notebookModel.setCategory("");

        Paper.init(this);

        List<NotebookModel> notebookModelList = Paper.book().read("notes", new ArrayList<>());

        intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                image_uri = result.getData().getData();
                Glide.with(this).load(image_uri).into(img_photo_uploaded);

                notebookModel.setImage_url(image_uri.toString());

            }
        });

        txt_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (notebookModel.getCategory() != null){

                    notebookModel.setContent(edt_write_note.getText().toString());
                    notebookModel.setDatetime(new Date().getTime()); // 75645345465


                    notebookModelList.add(notebookModel);

                    Paper.book().write("notes", notebookModelList);
                    Toast.makeText(AddNotesActivity.this, "note saved", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(AddNotesActivity.this,MainActivity.class));


                } else {
                    notebookModel.setCategory("uncategorized");
                    notebookModel.setContent(edt_write_note.getText().toString());
                    notebookModel.setDatetime(new Date().getTime());


                    notebookModelList.add(notebookModel);

                    Paper.book().write("notes", notebookModelList);
                    Toast.makeText(AddNotesActivity.this, "note saved", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(AddNotesActivity.this,MainActivity.class));
                }

            }
        });

        txt_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddNotesActivity.super.onBackPressed();
            }
        });

        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();


            }
        });
        //back

        from = getIntent().getExtras().getString("from");
        if (from.equals("B")) {
            notebookModel = (NotebookModel) getIntent().getExtras().getSerializable("content");
        } else if (from.equals("A")){
            notebookModel = new NotebookModel();
        }



        category = findViewById(R.id.category);
        work = findViewById(R.id.work);
        family = findViewById(R.id.family);
        study = findViewById(R.id.study);
        personal = findViewById(R.id.personal);
        edt_write_note = findViewById(R.id.edt_write_note);

        edt_write_note.setText(notebookModel.getContent());


        category.setOnClickListener(view -> {

            notebookModel.setCategory("uncategorized");

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

            notebookModel.setCategory("work");

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

            notebookModel.setCategory("family affair");

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

            notebookModel.setCategory("study");
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

            notebookModel.setCategory("personal");
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

    private void openGallery() {

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intentActivityResultLauncher.launch(intent);

    }
}