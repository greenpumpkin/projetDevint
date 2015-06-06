package com.devint.cindy.speedmemory.activities;

import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.devint.cindy.speedmemory.R;
import com.devint.cindy.speedmemory.context.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassementActivity extends ActionBarActivity {

    private ListView niveau1, niveau2, niveau3;
    private String[] items, items2, items3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        niveau1 = (ListView) findViewById(R.id.listViewScoreNiveau1);
        niveau2 = (ListView) findViewById(R.id.listViewScoreNiveau2);
        niveau3 = (ListView) findViewById(R.id.listViewScoreNiveau3);

        List<Score> listeNiveau1 = MainActivity.db.getAllScoreByNiveau(1);
        if(listeNiveau1 == null) {
            items = new String[1];
            items[0] = "Aucun score";
        }
        else {
            items = new String[listeNiveau1.size()];
            Collections.sort(listeNiveau1);
            for(int i = 0; i < listeNiveau1.size(); i++) {
                items[i] = listeNiveau1.get(i).toString();
            }
        }


        List<Score> listeNiveau2 = MainActivity.db.getAllScoreByNiveau(2);
        if(listeNiveau2 == null) {
            items2 = new String[1];
            items2[0] = "Aucun score";
        }
        else {

            items2 = new String[listeNiveau2.size()];
            Collections.sort(listeNiveau2);
            for(int i = 0; i < listeNiveau2.size(); i++) {
                items2[i] = listeNiveau2.get(i).toString();
            }
        }

       List<Score> listeNiveau3 = MainActivity.db.getAllScoreByNiveau(3);
        if(listeNiveau3 == null) {
            items3 = new String[1];
            items3[0] = "Aucun score";
        }
        else {
            items3 = new String[listeNiveau3.size()];
            Collections.sort(listeNiveau3);
            for(int i = 0; i < listeNiveau3.size(); i++) {
                items3[i] = listeNiveau3.get(i).toString();
            }
        }

        ArrayAdapter<String> adapteurNiveau1 = new ArrayAdapter<String>(ClassementActivity.this, android.R.layout.simple_list_item_1, items);
        niveau1.setAdapter(adapteurNiveau1);
        ArrayAdapter<String> adapteurNiveau2 = new ArrayAdapter<String>(ClassementActivity.this, android.R.layout.simple_list_item_1, items2);
        niveau2.setAdapter(adapteurNiveau2);
        ArrayAdapter<String> adapteurNiveau3 = new ArrayAdapter<String>(ClassementActivity.this, android.R.layout.simple_list_item_1, items3);
        niveau3.setAdapter(adapteurNiveau3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_classement, menu);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ClassementActivity.this.finish();
    }
}
