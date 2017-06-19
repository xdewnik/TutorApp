package com.sadface.tutorapp.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.adapters.PagerFragmentAdapter;
import com.sadface.tutorapp.constr.EventStudent;
import com.sadface.tutorapp.services.URL;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SADFACE on 14.06.2017.
 */

public class MyFragmentContainer extends Fragment {

    private View view;
    private  Context context;
    private int event_id;
    private ViewPager vp;
    private PagerFragmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


         view = inflater.inflate(R.layout.mycontainer_layout,
                container, false);
         context = view.getContext();
        Bundle args = this.getArguments();
        event_id = args.getInt("EventID");
        vp = (ViewPager) view.findViewById(R.id.viewPager);
        new EventInfoTast().execute();
        return view;
    }

    private class EventInfoTast extends AsyncTask<Object, Object, List<EventStudent>> {

        @Override
        protected List<EventStudent> doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            EventStudent[] ev =template.getForObject(URL.GET_EVENT_INFO+event_id, EventStudent[].class );
            List<EventStudent>event= Arrays.asList(ev);
            return event;
        }

        @Override
        protected void onPostExecute(List<EventStudent> ventStudent) {
            super.onPostExecute(ventStudent);
            adapter = new PagerFragmentAdapter(context,getActivity().getSupportFragmentManager(),ventStudent);
            vp.setAdapter(adapter);
            adapter.notifyDataSetChanged();




        }
    }

}
