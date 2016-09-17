package com.zzq.cordovafragment.cordova;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zzq.cordovafragment.R;
import com.zzq.cordovafragment.base.BaseFragment;

import org.apache.cordova.Config;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewClient;
import org.apache.cordova.engine.SystemWebViewEngine;

import java.util.concurrent.ExecutorService;

import static android.webkit.WebView.setWebContentsDebuggingEnabled;

/**
 * Created by zhuzhiqiang on 2016/8/11 0011.
 */
public abstract class CordovaFragment extends BaseFragment implements CordovaInterface {
    protected CordovaWebView cordovaWebView;
    protected SystemWebView systemWebView;

    @Override
    public View initView(LayoutInflater inflater) {
        LayoutInflater localInflater = inflater.cloneInContext(new CordovaContext(getActivity(), this));
        View contentView = localInflater.inflate(R.layout.fragment_home_cordova, null);
        systemWebView = (SystemWebView) contentView.findViewById(R.id.cordovaWebView);
        cordovaWebView = new CordovaWebViewImpl(new SystemWebViewEngine(systemWebView));
        Config.init(getActivity());
        initSDKVersion();
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(context);
        cordovaWebView.init(new MyCordovaInterfaceImpl(getActivity()), parser.getPluginEntries(), parser.getPreferences());
        cordovaWebView.loadUrl(loadUrl());
        return contentView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    protected abstract String loadUrl();

    /**
     * 根据SDK版本设置WebView是否支持调试
     */
    private void initSDKVersion() {
        final String packageName = getActivity().getPackageName();
        final PackageManager pm = getActivity().getPackageManager();
        ApplicationInfo appInfo = null;
        try {
            appInfo = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            if ((appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0 &&
                    android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                setWebContentsDebuggingEnabled(true);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {

    }

    @Override
    public void setActivityResultCallback(CordovaPlugin plugin) {

    }

    @Override
    public Object onMessage(String id, Object data) {
        return null;
    }

    @Override
    public ExecutorService getThreadPool() {
        return null;
    }

    @Override
    public void requestPermission(CordovaPlugin plugin, int requestCode, String permission) {

    }

    @Override
    public void requestPermissions(CordovaPlugin plugin, int requestCode, String[] permissions) {

    }

    @Override
    public boolean hasPermission(String permission) {
        return false;
    }

}
