package com.zzq.cordovafragment.fragment;

import com.zzq.cordovafragment.Constants;
import com.zzq.cordovafragment.cordova.CordovaFragment;

public class HomeFragment extends CordovaFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected String loadUrl() {
        return Constants.URL;
    }
}
