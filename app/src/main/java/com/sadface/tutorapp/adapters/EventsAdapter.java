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
import com.sadface.tutorapp.constr.EventDTO;
import com.sadface.tutorapp.fragments.MyFragmentContainer;
import com.sadface.tutorapp.fragments.StudentFragment;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by SADFACE on 13.06.2017.
 */

    public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
        public View view;
        private List<EventDTO> data;
        private Context context;

        public EventsAdapter(List<EventDTO> data) {
            this.data = data;
            this.view = view;
        }

        @Override
        public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);

            return new EventViewHolder(view);
        }

        @Override
        public void onBindViewHolder(EventViewHolder holder, int position) {
            final EventDTO item = data.get(position);
            holder.title.setText(item.getEventName());
            holder.text.setText(item.getEventText());
            SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
            holder.dat.setText(simpleDate.format(item.getEventDate()));
            holder.cardView.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v){
                        Bundle arguments = new Bundle();
                        arguments.putInt("EventID",item.getId_event());
                        Fragment fragment = new MyFragmentContainer();
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

        public void setData(List<EventDTO> data) {
            this.data = data;
        }

        public class EventViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            TextView title,text, dat;


            public EventViewHolder(View itemView) {
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

