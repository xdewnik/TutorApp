package com.sadface.tutorapp.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.adapters.EventsAdapter;
import com.sadface.tutorapp.constr.EventDTO;
import com.sadface.tutorapp.services.URL;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SADFACE on 13.06.2017.
 */

public class EventFragment extends android.support.v4.app.Fragment {

    private List<EventDTO> eventList;
    private RecyclerView rv;
    private EventsAdapter adapter;
    private CardView card;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_event,
                container, false);
        final Context context = view.getContext();

        rv = (RecyclerView) view.findViewById(R.id.recycleViewEvent);
        rv.setLayoutManager(new LinearLayoutManager(context));

        new EventTask().execute();



        return view;
    }


    private class EventTask extends AsyncTask<Object, Object, List<EventDTO>> {

        @Override
        protected List<EventDTO> doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            EventDTO[] studArray =template.getForObject(URL.GET_EVENTS, EventDTO[].class );
            eventList= Arrays.asList(studArray);
            return eventList;
        }

        @Override
        protected void onPostExecute(List<EventDTO> eventDTO) {
            super.onPostExecute(eventDTO);
            adapter = new EventsAdapter(eventList);
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
