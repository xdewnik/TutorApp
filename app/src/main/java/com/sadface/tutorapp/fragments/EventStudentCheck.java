package com.sadface.tutorapp.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.activities.ChangeStudentActivity;
import com.sadface.tutorapp.adapters.EventStudentAdapter;
import com.sadface.tutorapp.constr.EventStudent;
import com.sadface.tutorapp.constr.StudentDTO;
import com.sadface.tutorapp.services.URL;
import com.sadface.tutorapp.utils.DividerItemDecoration;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SADFACE on 14.06.2017.
 */


public class EventStudentCheck extends Fragment{
    private Context context;
    private List<EventStudent> event;
    private EventStudentAdapter adapter;
    private int event_id;
    private RecyclerView recyclerView;

    public static EventStudentCheck getInstance(Context context, List<EventStudent> data){
        Bundle args = new Bundle();
        EventStudentCheck fragment = new EventStudentCheck();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setData(data);
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_event_student,
                container, false);
        Bundle args = this.getArguments();
        event_id = args.getInt("EventID");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewEventStudent);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

        adapter = new EventStudentAdapter(event);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.saveChecked);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   new CheckedStudentPostTask().execute();
                Snackbar.make(view, "Saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return view;
    }


    private void setData(List<EventStudent> data){
        this.event = data;
    }
    public void setContext(Context context){
        this.context=context;
    }


    private class CheckedStudentPostTask extends AsyncTask<Object, Object, List<EventStudent>> {

        @Override
        protected List<EventStudent> doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            EventStudent[] array = new EventStudent[event.size()];
            event.toArray(array);
            template.postForObject(URL.GET_EVENT_INFO ,array, EventStudent[].class);
            return event;
        }

        @Override
        protected void onPostExecute(List<EventStudent> studentDTO) {
            super.onPostExecute(studentDTO);
                  }
    }

}
