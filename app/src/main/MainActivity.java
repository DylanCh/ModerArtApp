package com.dylanhelps.modernartapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final static Class aClass = MainActivity.class;
    private final static String APPNAME = aClass.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            // add fragment
            getSupportFragmentManager().beginTransaction().add(R.id.main_window,new MainFrame()).commit();
        } //
    } // end oncreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater;
        if (Build.VERSION.SDK_INT > 15)
            menuInflater=getMenuInflater();
        else
            menuInflater = new MenuInflater(this);

        if(menuInflater==null)
            return false;
        else {
            Log.i(APPNAME,"options menu created");
            try {
                menuInflater.inflate(R.menu.action_bar_items, menu);
            }
            catch (InflateException e){
                Log.e(APPNAME,e.getMessage());
                return false;
            }
            return true;
        }
    }

    public void getMenuOverflow(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.more_information: new MoreInfoDialog().show(getFragmentManager(), APPNAME);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
