package com.example.pizzahut;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {
    int noOfTabs;

    public PageAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.noOfTabs = NumberOfTabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                NON_VEG_PIZZA non_veg_pizza = new NON_VEG_PIZZA();
                return non_veg_pizza;
            case 1:
                VEG_PIZZA veg_pizza = new VEG_PIZZA();
                return veg_pizza;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
