package com.sadface.tutorapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sadface.tutorapp.constr.EventStudent;
import com.sadface.tutorapp.fragments.EventInfoFragment;
import com.sadface.tutorapp.fragments.EventStudentCheck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SADFACE on 14.06.2017.
 */

public class PagerFragmentAdapter extends FragmentStatePagerAdapter {

    private Map<Integer,Fragment> tabs;
    private Context context;

    public PagerFragmentAdapter(Context context, FragmentManager fm, List<EventStudent> data ){
        super(fm);
        this.context = context;
        initTabsMap(context, data);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context, List<EventStudent> data){
        tabs = new HashMap<>();
        tabs.put(0, EventInfoFragment.getInstance(context,data));
        tabs.put(1, EventStudentCheck.getInstance(context,data));
    }
}
