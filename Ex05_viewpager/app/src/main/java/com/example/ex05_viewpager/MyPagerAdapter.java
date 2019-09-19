package com.example.ex05_viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    MyPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new PageFragment1();
            case 1:
                return new PageFragment2();
            case 2:
                return new PageFragment3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 12;
    }
}
