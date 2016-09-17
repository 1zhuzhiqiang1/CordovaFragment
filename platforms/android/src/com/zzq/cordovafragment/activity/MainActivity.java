package com.zzq.cordovafragment.activity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zzq.cordovafragment.R;
import com.zzq.cordovafragment.base.ContainFragmentActivity;
import com.zzq.cordovafragment.fragment.ActivityFragment;
import com.zzq.cordovafragment.fragment.DiscoverFragment;
import com.zzq.cordovafragment.fragment.HomeFragment;
import com.zzq.cordovafragment.fragment.MineFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends ContainFragmentActivity {

    private RelativeLayout[] mTabs = new RelativeLayout[4];
    private LinearLayout[] mInsideLinear = new LinearLayout[4];
    // tab页签
    private ImageView[] mTabImageViews = new ImageView[4];
    private TextView[] mTabTextViews = new TextView[4];
    private int[] mTabSelectIcons = new int[]{R.drawable.icon, R.drawable.icon, R.drawable.icon, R.drawable.icon};
    private int[] mTabNormalIcons = new int[]{R.drawable.icon, R.drawable.icon, R.drawable.icon, R.drawable.icon};

    public static final int FRAGMENT_TAG_SQUARE = 0;
    public static final int FRAGMENT_TAG_DISCOVER = 1;
    public static final int FRAGMENT_TAG_ACTIVITY = 2;
    public static final int FRAGMENT_TAG_MINE = 3;

    private int mColorNormal = Color.rgb(120, 125, 135);
    private int mColorSelected = Color.rgb(255, 0, 0);

    private String mLastKey;
    private Fragment mLastFragment;
    private Map<String, Fragment> mFragments;
    public static final String TAB_TAG = "tab_tag";

    private long backTime = 0;
    private long mExitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragments = new HashMap<String, Fragment>();
        initViews();
        initEvents();
        setTab(0);
        setFragment(0, false);
        String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS};
        ActivityCompat.requestPermissions(MainActivity.this, mPermissionList, 100);

    }

    private void initViews() {
        mTabs[0] = findView(R.id.main_tab_square);
        mTabs[1] = findView(R.id.main_tab_discover);
        mTabs[2] = findView(R.id.main_tab_activity);
        mTabs[3] = findView(R.id.main_tab_mine);

        mTabImageViews[0] = findView(R.id.tab_image_1);
        mTabImageViews[1] = findView(R.id.tab_image_2);
        mTabImageViews[2] = findView(R.id.tab_image_3);
        mTabImageViews[3] = findView(R.id.tab_image_4);

        mTabTextViews[0] = findView(R.id.tab_text_1);
        mTabTextViews[1] = findView(R.id.tab_text_2);
        mTabTextViews[2] = findView(R.id.tab_text_3);
        mTabTextViews[3] = findView(R.id.tab_text_4);

        mInsideLinear[0] = findView(R.id.main_tab_square_inside);
        mInsideLinear[1] = findView(R.id.main_tab_discover_inside);
        mInsideLinear[2] = findView(R.id.main_tab_activity_inside);
        mInsideLinear[3] = findView(R.id.main_tab_mine_inside);
    }

    private void initEvents() {
        mTabs[0].setOnClickListener(this);
        mTabs[1].setOnClickListener(this);
        mTabs[2].setOnClickListener(this);
        mTabs[3].setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tab_square:
                setFragment(FRAGMENT_TAG_SQUARE, false);
                setTab(FRAGMENT_TAG_SQUARE);
                break;
            case R.id.main_tab_discover:
                setFragment(FRAGMENT_TAG_DISCOVER, false);
                setTab(FRAGMENT_TAG_DISCOVER);
                break;
            case R.id.main_tab_activity:
                setFragment(FRAGMENT_TAG_ACTIVITY, false);
                setTab(FRAGMENT_TAG_ACTIVITY);
                break;

            case R.id.main_tab_mine:
                setFragment(FRAGMENT_TAG_MINE, false);
                setTab(FRAGMENT_TAG_MINE);
                break;
        }
    }

    private void setTab(int position) {
        for (int i = 0; i < mTabs.length; i++) {
            if (i == position) {
                mTabTextViews[i].setTextColor(mColorSelected);
                mTabImageViews[i].setImageResource(mTabSelectIcons[i]);
                //mInsideLinear[i].setBackgroundResource(R.color.bar_bg_color);
            } else {
                mTabTextViews[i].setTextColor(mColorNormal);
                mTabImageViews[i].setImageResource(mTabNormalIcons[i]);
                //mInsideLinear[i].setBackgroundResource(R.color.white);
            }
        }
    }


    private void setFragment(int key, boolean isForceRefresh) {
        String sKey = createKey(key);
        // 防止重复点击同一tab
        if (mLastKey != null && mLastKey.equals(sKey) && !isForceRefresh) {
            return;
        }
        //强制重新生成，则将原来的给移除掉
        if (isForceRefresh && containSpecifiedFragment(key)) {
            mFragments.remove(sKey);
        }
        Fragment fragment = null;
        if (containSpecifiedFragment(key)) {
            fragment = mFragments.get(sKey);
            showFragment(fragment);
        } else {
            switch (key) {
                case FRAGMENT_TAG_SQUARE:
                    fragment = HomeFragment.newInstance();
                    break;
                case FRAGMENT_TAG_DISCOVER:
                    fragment = new DiscoverFragment();
                    break;
                case FRAGMENT_TAG_ACTIVITY:
                    fragment = new ActivityFragment();
                    break;
                case FRAGMENT_TAG_MINE:
                    fragment = new MineFragment();
                    break;
                default:
                    throw new RuntimeException("no this tab");
            }
            mFragments.put(sKey, fragment);
            addFragment(fragment, R.id.main_fragment_container);
        }
        if (mLastFragment != null) {
            hideFragment(mLastFragment);
        }
        mLastFragment = fragment;
        mLastKey = sKey;
    }

    private String createKey(int key) {
        return "tab" + key;
    }

    private boolean containSpecifiedFragment(int tag) {
        return mFragments.containsKey(createKey(tag));
    }

    @Override
    public boolean onKeyDown(int kCode, KeyEvent kEvent) {
        switch (kCode) {
            case KeyEvent.KEYCODE_BACK:
                exit();
                return true;
        }
        return super.onKeyDown(kCode, kEvent);
    }

    private void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    private <T extends View> T findView(int resId) {
        View v = findViewById(resId);
        if (v == null) return null;
        return (T) v;
    }
}
