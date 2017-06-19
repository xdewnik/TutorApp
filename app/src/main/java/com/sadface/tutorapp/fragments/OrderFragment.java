package com.sadface.tutorapp.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.adapters.OrderAdapter;
import com.sadface.tutorapp.constr.OrderDTO;
import com.sadface.tutorapp.services.URL;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SADFACE on 18.06.2017.
 */

public class OrderFragment extends Fragment {

    private List<OrderDTO> orderList;
    private RecyclerView rv;
    private OrderAdapter adapter;
    private CardView card;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_order,
                container, false);
        final Context context = view.getContext();

        rv = (RecyclerView) view.findViewById(R.id.recycleViewOrder);
        rv.setLayoutManager(new LinearLayoutManager(context));

        new OrderTask().execute();



        return view;
    }


    private class OrderTask extends AsyncTask<Object, Object, List<OrderDTO>> {

        @Override
        protected List<OrderDTO> doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            OrderDTO[] studArray =template.getForObject(URL.GET_ORDERS, OrderDTO[].class );
            orderList= Arrays.asList(studArray);
            return orderList;
        }

        @Override
        protected void onPostExecute(List<OrderDTO> eventDTO) {
            super.onPostExecute(eventDTO);
            adapter = new OrderAdapter(orderList);
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}


