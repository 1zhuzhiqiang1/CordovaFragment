package com.zzq.cordovafragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;


/**
 * 功能：包含Fragment的Activity，可以方便的添加、隐藏、显示。
 */
public abstract class ContainFragmentActivity extends FragmentActivity implements View.OnClickListener {
    protected FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        super.onCreate(savedInstanceState);
    }

    /**
     * 关闭并移除相应的fragment.
     *
     * @return true, 成功关闭fragment, 并将该fragment设置为null;false没有关闭fragment
     */
    protected boolean removeFragment(Fragment fragment) {
        if (fragment != null && fragment.isVisible()) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();
            fragment = null;
            return true;
        }
        return false;
    }

    /**
     * 添加fragment
     *
     * @param fragment    : 要添加的fragment
     * @param containerId : 存储fragment的ViewGroup
     */
    protected void addFragment(Fragment fragment, int containerId) {
        if (fragment == null || containerId < 0) {
            return;
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(containerId, fragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 显示相应的fragmnet
     */
    protected void showFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    /**
     * 隐藏相应的fragment
     */
    protected void hideFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }
}
