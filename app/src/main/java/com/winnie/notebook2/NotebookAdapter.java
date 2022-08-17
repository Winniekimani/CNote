package com.winnie.notebook2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotebookAdapter extends RecyclerView.Adapter<NotebookAdapter.NotebookViewHolder> {

    Context context;
    ArrayList<NotebookModel>notebookModelArrayList;
    private final static int WORK_NOTEBOOK = 0;
    private final static int STUDY_NOTEBOOK = 1;
    private final static int UNCATEGORIZED_NOTEBOOK = 2;
    private final static int PERSONAL_NOTEBOOK = 3;
    private final static int FAMILY_AFFAIR_NOTEBOOK = 4;


    public NotebookAdapter(Context context, ArrayList<NotebookModel> notebookModelArrayList) {
        this.context = context;
        this.notebookModelArrayList = notebookModelArrayList;
    }

    @NonNull
    @Override
    public NotebookAdapter.NotebookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;
       if (viewType == 0 )
       {
            view = LayoutInflater.from(context).inflate(R.layout.work_notebookview,parent,false);
       }else if (viewType == 1){
           view = LayoutInflater.from(context).inflate(R.layout.study_notebookview,parent,false);
       }else if (viewType == 3){
           view = LayoutInflater.from(context).inflate(R.layout.personal_notebookview,parent,false);
       }else if (viewType == 4){
           view = LayoutInflater.from(context).inflate(R.layout.familyaffair_notebookview,parent,false);
       }else {
           view = LayoutInflater.from(context).inflate(R.layout.uncategorized_notebookview, parent, false);
       }

        return new NotebookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotebookAdapter.NotebookViewHolder holder, int position) {


        NotebookModel notebookModel = notebookModelArrayList.get(position);

        holder.content.setText(notebookModel.getContent());

        long long_date = notebookModel.getDatetime(); //765467756
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyHHmm");
        Date date = null;
        try {
            date = (Date) dateFormat.parseObject(long_date + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);

        holder.datetime.setText(String.valueOf(date));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,NoteDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("note",notebookModel);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return notebookModelArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (notebookModelArrayList.get(position).getCategory().equals("study")){
            return STUDY_NOTEBOOK;
        }else if (notebookModelArrayList.get(position).getCategory().equals("work")){
            return WORK_NOTEBOOK;
        }else if (notebookModelArrayList.get(position).getCategory().equals("personal")){
            return PERSONAL_NOTEBOOK;
        }else if (notebookModelArrayList.get(position).getCategory().contains("family")){
            return FAMILY_AFFAIR_NOTEBOOK;
        }else
            return UNCATEGORIZED_NOTEBOOK;
    }

    public class NotebookViewHolder extends RecyclerView.ViewHolder{

        TextView content;
        TextView datetime;

        public NotebookViewHolder(@NonNull View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.content);
            datetime = itemView.findViewById(R.id.datetime);


        }
    }
}
