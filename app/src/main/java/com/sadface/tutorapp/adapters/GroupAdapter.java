package com.sadface.tutorapp.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.constr.StudentDTO;

import java.util.List;

/**
 * Created by SADFACE on 23.05.2017.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {

    private List<StudentDTO> studentDTOList;
    private View view;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, tutor;
        Button btn;
        View view;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            title = (TextView) view.findViewById(R.id.title);
            tutor = (TextView) view.findViewById(R.id.tutor);
            btn= (Button) view.findViewById(R.id.button);

        }
    }


    public GroupAdapter(List<StudentDTO> studentDTOList) {
        this.studentDTOList = studentDTOList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final StudentDTO studentDTO = studentDTOList.get(position);

        holder.title.setText(studentDTO.getStudentName());
        holder.tutor.setText(studentDTO.getStudentPhone());
        holder.btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(Intent.ACTION_DIAL);
                String p = "tel:"+ studentDTO.getStudentPhone();
                i.setData(Uri.parse(p));
                view.getContext().startActivity(i);

            }

        });

    }

    @Override
    public int getItemCount() {
        return studentDTOList.size();
    }

    public void setData(List<StudentDTO> studentDTO){
        this.studentDTOList = studentDTO;
    }

}