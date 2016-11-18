package com.example.administrator.mylvshi.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.administrator.mylvshi.R;

/**
 * Created by Administrator on 2016/11/9.
 */

public class FragmentUtils {
    public static void judgeToShow(FragmentManager fragmentManager, Fragment fragment) {
        if (!fragment.isAdded()) {
            fragmentManager.beginTransaction()
                    .add(R.id.fl_main_fragments, fragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .show(fragment)
                    .commit();
        }
    }
}
