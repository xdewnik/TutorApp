package com.sadface.tutorapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.constr.DutyRosterDTO;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by SADFACE on 14.06.2017.
 */

public class DutyAdapter extends RecyclerView.Adapter<DutyAdapter.MyDutyHolder> {
    private List<DutyRosterDTO> dutyRosterDTOList;

    public class MyDutyHolder extends RecyclerView.ViewHolder {
        TextView tutor, date;

        public MyDutyHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.dutyDate);
            tutor = (TextView) view.findViewById(R.id.dutyTutorName);

        }
    }

    public DutyAdapter(List<DutyRosterDTO> dutyRosterDTO) {
        this.dutyRosterDTOList = dutyRosterDTO;
    }

    @Override
    public DutyAdapter.MyDutyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item_row, parent, false);

        return new DutyAdapter.MyDutyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DutyAdapter.MyDutyHolder holder, int position) {
        DutyRosterDTO studentDTO = dutyRosterDTOList.get(position);
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");

        holder.tutor.setText(studentDTO.getTutor().getTutorName());
        holder.date.setText((simpleDate.format(studentDTO.getDutyDate())));

    }

    @Override
    public int getItemCount() {
        return dutyRosterDTOList.size();
    }

}
