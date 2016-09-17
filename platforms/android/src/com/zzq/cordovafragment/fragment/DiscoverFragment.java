package com.zzq.cordovafragment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.zzq.cordovafragment.Constants;
import com.zzq.cordovafragment.base.BaseFragment;
import com.zzq.cordovafragment.cordova.CordovaFragment;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class DiscoverFragment extends CordovaFragment {

    @Override
    protected String loadUrl() {
        return Constants.URL;
    }
}
