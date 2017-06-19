package com.sadface.tutorapp.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.constr.EventDTO;
import com.sadface.tutorapp.constr.EventStudent;
import com.sadface.tutorapp.services.URL;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SADFACE on 14.06.2017.
 */

public class EventInfoFragment extends Fragment{

    private Context context;
    private List<EventStudent> event;
    private TextView title,text;
    private int event_id;



    public static EventInfoFragment getInstance(Context context, List<EventStudent> data){
        Bundle args = new Bundle();
        EventInfoFragment fragment = new EventInfoFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setData(data);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_event_info,
                container, false);

        title =(TextView) view.findViewById(R.id.eventInfoTitle);
        text =(TextView) view.findViewById(R.id.eventInfoText);
        Bundle args =this.getArguments();
        event_id = args.getInt("EventID");
        title.setText(event.get(0).getEvent().getEventName());
        text.setText(event.get(0).getEvent().getEventText());
        //new EventInfoTast().execute();

        return view;
    }


    private void setData(List<EventStudent> data){
        this.event = data;
    }

    public void setContext(Context context){
        this.context=context;
    }

  /*  private class EventInfoTast extends AsyncTask<Object, Object, List<EventStudent>> {

        @Override
        protected List<EventStudent> doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            EventStudent[] ev =template.getForObject(URL.GET_EVENT_INFO+event_id, EventStudent[].class );
            event= Arrays.asList(ev);
            return event;
        }

        @Override
        protected void onPostExecute(List<EventStudent> ventStudent) {
            super.onPostExecute(ventStudent);

           title.setText(ventStudent.get(0).getEvent().getEventName());
            text.setText(ventStudent.get(0).getEvent().getEventText());
        }
    }*/
}
