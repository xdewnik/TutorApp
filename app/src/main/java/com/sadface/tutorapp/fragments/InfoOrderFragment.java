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
import com.sadface.tutorapp.constr.OrderDTO;
import com.sadface.tutorapp.services.URL;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by SADFACE on 18.06.2017.
 */

public class InfoOrderFragment extends Fragment {

    private Context context;
    private OrderDTO order;
    private TextView title, text;
    private int order_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.order_info_fragment,
                container, false);

        title = (TextView) view.findViewById(R.id.orderInfoTitle);
        text = (TextView) view.findViewById(R.id.ordertInfoText);
        Bundle args = this.getArguments();
        order_id = args.getInt("OrderID");
        new OrderInfoTask().execute();

        return view;
    }


    private class OrderInfoTask extends AsyncTask<Object, Object, OrderDTO> {

        @Override
        protected OrderDTO doInBackground(Object... params) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            order = template.getForObject(URL.GET_ORDERS + order_id, OrderDTO.class);
            return order;
        }

        @Override
        protected void onPostExecute(OrderDTO o) {
            super.onPostExecute(o);

            title.setText(order.getOrderName());
            text.setText(order.getOrderText());
        }
    }
}

