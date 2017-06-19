package com.sadface.tutorapp.adapters;

import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.constr.DutyRosterDTO;
import com.sadface.tutorapp.constr.EventStudent;
import com.sadface.tutorapp.constr.StudentDTO;
import com.sadface.tutorapp.services.URL;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by SADFACE on 14.06.2017.
 */

public class EventStudentAdapter extends RecyclerView.Adapter<EventStudentAdapter.MyEventStudentHolder> {

    private  EventStudent foo;
    private List<EventStudent> eventStudent;

    public EventStudentAdapter(List<EventStudent> eventStudent) {
        this.eventStudent = eventStudent;
    }

    @Override
    public EventStudentAdapter.MyEventStudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_student_item, parent, false);

        return new EventStudentAdapter.MyEventStudentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyEventStudentHolder holder, int position) {
        foo  = eventStudent.get(position);

        holder.checked.setChecked(foo.getChecked());
        holder.studentName.setText(foo.getStudent().getStudentName());
        holder.checked.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               boolean isChecked = holder.checked.isChecked();
                if(isChecked){
                    foo.setChecked(holder.checked.isChecked());
                }
                else {
                    foo.setChecked(holder.checked.isChecked());
                }
                new StudentPostTask().execute();
            }
        });

    }

    @Override
    public int getItemCount() {
            return eventStudent.size();
    }

    public class MyEventStudentHolder extends RecyclerView.ViewHolder {
        CheckBox checked;
        TextView studentName;


        public MyEventStudentHolder(View itemView) {
            super(itemView);
            checked = (CheckBox) itemView.findViewById(R.id.eventstudentChecked);
            studentName = (TextView) itemView.findViewById(R.id.eventstudentName);
        }
    }


    private class StudentPostTask extends AsyncTask<Object, Object, EventStudent> {

        @Override
        protected EventStudent doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            EventStudent returns = template.postForObject(URL.GET_EVENT_INFO ,foo, EventStudent.class);
            return returns;
        }

        @Override
        protected void onPostExecute(EventStudent studentDTO) {
            super.onPostExecute(studentDTO);
        }
    }
}
