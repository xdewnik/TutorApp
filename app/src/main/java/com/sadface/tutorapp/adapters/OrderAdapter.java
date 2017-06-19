package com.sadface.tutorapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sadface.tutorapp.R;
import com.sadface.tutorapp.constr.OrderDTO;
import com.sadface.tutorapp.fragments.InfoOrderFragment;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by SADFACE on 18.06.2017.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyOrderHolder> {

    public View view;
    private List<OrderDTO> data;
    private Context context;

    public OrderAdapter(List<OrderDTO> data){
        this.data = data;
        this.view = view;
    }

    @Override
    public OrderAdapter.MyOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);

        return new OrderAdapter.MyOrderHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.MyOrderHolder holder, int position) {
        final OrderDTO item = data.get(position);
        holder.title.setText(item.getOrderName());
        holder.text.setText(item.getOrderText());
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        holder.dat.setText(simpleDate.format(item.getOrderDate()));
        holder.cardView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Bundle arguments = new Bundle();
                arguments.putInt("OrderID",item.getId_order());
                Fragment fragment = new InfoOrderFragment();
                fragment.setArguments(arguments);
                FragmentManager fragmentManager =((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<OrderDTO> data) {
        this.data = data;
    }

    public class MyOrderHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title,text, dat;


        public MyOrderHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            view = itemView;
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.eventTitle);
            text = (TextView) itemView.findViewById(R.id.eventText);
            dat = (TextView) itemView.findViewById(R.id.dataEvent);
        }
    }


}
