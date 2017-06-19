package com.sadface.tutorapp.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.adapters.DutyAdapter;
import com.sadface.tutorapp.adapters.GroupAdapter;
import com.sadface.tutorapp.constr.DutyRosterDTO;
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

public class DutyFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<DutyRosterDTO> dutyList;
    private DutyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_duty,
                container, false);
        final Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewDuty);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        new DutyTask().execute();
        return view;

    }


    private class DutyTask extends AsyncTask<Object, Object, List<DutyRosterDTO>> {

        @Override
        protected List<DutyRosterDTO> doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            DutyRosterDTO[] dutyArray =template.getForObject(URL.GET_DUTIES, DutyRosterDTO[].class );
            dutyList= Arrays.asList(dutyArray);
            return dutyList;
        }

        @Override
        protected void onPostExecute(List<DutyRosterDTO> dutyRosterDTO) {
            super.onPostExecute(dutyRosterDTO);
            adapter = new DutyAdapter(dutyList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
