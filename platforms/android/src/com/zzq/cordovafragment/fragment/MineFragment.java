package com.zzq.cordovafragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.zzq.cordovafragment.Constants;
import com.zzq.cordovafragment.base.BaseFragment;
import com.zzq.cordovafragment.cordova.CordovaFragment;

import org.apache.cordova.Config;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.PluginEntry;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class MineFragment extends CordovaFragment {

    @Override
    protected String loadUrl() {
        return Constants.URL;
    }
}
