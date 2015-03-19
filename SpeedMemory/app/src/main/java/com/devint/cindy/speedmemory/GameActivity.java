package com.devint.cindy.speedmemory;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends ActionBarActivity {

    private Chronometer chrono;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        chrono = (Chronometer)findViewById(R.id.chronometer);
        chrono.start();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
