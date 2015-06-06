package com.devint.cindy.speedmemory.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.devint.cindy.speedmemory.R;
import com.devint.cindy.speedmemory.context.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FinalActivity extends ActionBarActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        int niveau = 1;
        if(MainActivity.settings.getString("niveauKey", "Niveau 1") == "Niveau 1") niveau = 1;
        else if(MainActivity.settings.getString("niveauKey", "Niveau 1") == "Niveau 2") niveau = 2;
        else if(MainActivity.settings.getString("niveauKey", "Niveau 1") == "Niveau 3") niveau = 3;

        textView = (TextView) findViewById(R.id.textfinal);
        textView.setText("Félicitations " + MainActivity.settings.getString("pseudoKey", "inconnue") + ", vous avez gagné !");
        textView.setTextSize(20);

       /** List<Score> liste = MainActivity.db.getAllScoreByNiveau(niveau);
        String[] items = new String[liste.size()];
        Collections.sort(liste);
        for(int i = 0; i < liste.size(); i++) {
            items[i] = liste.get(i).toString();
        } */

        final Button buttonGoBack = (android.widget.Button) findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalActivity.this, MainActivity.class);
                startActivity(intent);
                FinalActivity.this.finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_final, menu);
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
        FinalActivity.this.finish();
    }
}
