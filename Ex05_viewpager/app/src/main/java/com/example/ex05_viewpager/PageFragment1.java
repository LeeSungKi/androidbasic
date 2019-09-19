package com.example.ex05_viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class PageFragment1 extends Fragment {

    // @Nullable : null 일 가능성 있음
    // @NonNull  : null 일 가능성 없음

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RelativeLayout layout =
                (RelativeLayout)inflater.inflate(R.layout.page1, container, false);

        return layout;
    }
}
