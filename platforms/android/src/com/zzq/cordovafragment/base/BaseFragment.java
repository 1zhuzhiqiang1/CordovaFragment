package com.zzq.cordovafragment.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.cordova.Config;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.PluginEntry;

import java.util.ArrayList;

public abstract class BaseFragment extends Fragment {
    //根视图
    public View view;
    public Context context;

    // Read from config.xml:
    protected CordovaPreferences preferences;
    protected String launchUrl;
    protected ArrayList<PluginEntry> pluginEntries;
    protected CordovaInterfaceImpl cordovaInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //构建view，并且返回view
        view = initView(inflater);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initData(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }

    //拿到布局填充器去填充相应的xml,生成指定的view对象
    public abstract View initView(LayoutInflater inflater);

    //view填充数据的操作
    public abstract void initData(Bundle savedInstanceState);

    protected class MyCordovaInterfaceImpl extends CordovaInterfaceImpl {
        public MyCordovaInterfaceImpl(Activity activity) {
            super(activity);
        }
    }
}
