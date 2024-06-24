package com.example.myvideo.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myvideo.models.PagerModelClass;

import java.util.ArrayList;

public class PagerAdabter extends FragmentPagerAdapter {

    ArrayList<PagerModelClass> model;



    public PagerAdabter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public void setData(ArrayList<PagerModelClass> model) {
        this.model = model;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return model.get(position).getFragment();
    }

    @Override
    public int getCount() {
        if(model==null) return 0;
        else {
            return model.size();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return model.get(position).getTitle();
    }
}
