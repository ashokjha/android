package com.ashu.external.androidnaviandfrag;

import android.app.FragmentManager;
import android.app.Fragment;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ashu.external.androidnaviandfrag.adp.CustomAdapter;
import com.ashu.external.androidnaviandfrag.bean.RowItem;
import com.ashu.external.androidnaviandfrag.frag.EMail_Fragment;
import com.ashu.external.androidnaviandfrag.frag.Message_Fragment;
import com.ashu.external.androidnaviandfrag.frag.SMS_Fragment;

import java.util.ArrayList;
import java.util.List;
//FragmentActivity
public class MainActivity extends AppCompatActivity  implements ISubjectMessage {
    String[] menutitles;
    TypedArray menuIcons;

    // nav drawer title
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private List<RowItem> rowItems;
    private CustomAdapter adapter;
    private String commMsg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();

        menutitles = getResources().getStringArray(R.array.titles);
        menuIcons = getResources().obtainTypedArray(R.array.icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.slider_list);

        rowItems = new ArrayList<RowItem>();

        for (int i = 0; i < menutitles.length; i++) {
            RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(
                    i, -1));
            rowItems.add(items);
        }
        menuIcons.recycle();
        adapter = new CustomAdapter(getApplicationContext(), rowItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new SlideitemListener());

        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
///*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
               null , R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
//*/
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            updateDisplay(0);
        }
    }

    class SlideitemListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            updateDisplay(position);
        }

    }

    private void updateDisplay(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Message_Fragment();
                break;
            case 1:
                fragment = new SMS_Fragment();
                break;
            case 2:
                fragment = new EMail_Fragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
            // update selected item and title, then close the drawer
            setTitle(menutitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setMessage(String msg, int frag) {
        commMsg = msg;
        Log.d("MainActivity", commMsg);
        Log.d("MainActivity", Integer.valueOf(frag).toString());
        switch (frag) {
            case 1:
                SMS_Fragment sms = (SMS_Fragment) getFragmentManager()
                                                          .findFragmentById(R.id.sms_fragmentlay);
                if (sms == null) {
                    System.out.println("Dual fragment - 1");
                    sms = new SMS_Fragment();
                    sms.setSMSMessage(commMsg);
                    // We are in dual fragment (Tablet and so on)
                    FragmentManager fm = getFragmentManager();
                    android.app.FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frame_container, sms);
                    ft.commit();
                }

                else {
                    Log.d("SwA", "Dual Fragment update");
                    // sms.getEMailSubject(commMsg);
                }
                break;
            case 2:
                EMail_Fragment email = (EMail_Fragment) getFragmentManager()
                                                                .findFragmentById(R.id.email_fragmentlay);
                if (email == null) {
                    System.out.println("Dual fragment - 1");
                    email = new EMail_Fragment();
                    email.setEMailMessage(commMsg);
                    // We are in dual fragment (Tablet and so on)
                    FragmentManager fm = getFragmentManager();
                    android.app.FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frame_container, email);
                    ft.commit();
                }

                else {
                    Log.d("SwA", "Dual Fragment update");
                    // sms.getEMailSubject(commMsg);
                }
        }

    }

    @Override
    public String getMessage() {
        Log.d("MainActivity", commMsg);
        return commMsg;
    }
}
