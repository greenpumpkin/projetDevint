package com.devint.cindy.speedmemory.activities;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.devint.cindy.speedmemory.R;

import java.util.ArrayList;
import java.util.List;

public class OptionsActivity extends ActionBarActivity {

    private Spinner spinnerNiveau, spinnerStyle;
    private int choiceLevel = 1;
    private int choiceGenre = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_options);
        Button valider = (Button) findViewById(R.id.button3);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsActivity.this.finish();
            }
        });

        // Initialisation des spinner
        spinnerNiveau = (Spinner) findViewById(R.id.spinnerNiveau);
        spinnerStyle = (Spinner) findViewById(R.id.spinnerstyle);
        addItemToSpinner();
        addListenerToItem();
        initializeSpinnerValue();
    }

    public void initializeSpinnerValue() {
        String level = MainActivity.settings.getString("niveauKey","Niveau 1");
        String genre = MainActivity.settings.getString("styleKey","Rap");

        switch (MainActivity.settings.getString("niveauKey", "Niveau 3")) {
            case "Niveau 1" : choiceLevel = 0; break;
            case "Niveau 2" : choiceLevel = 1; break;
            case "Niveau 3" : choiceLevel = 2; break;
        }

        switch (MainActivity.settings.getString("styleKey", "Rap")) {
            case "Rap" : choiceGenre = 0; break;
            case "Pop" : choiceGenre = 1; break;
            case "Electro" : choiceGenre = 2; break;
            case "Aléatoire" : choiceGenre = 3; break;
        }

        spinnerNiveau.setSelection(choiceLevel, true);
        spinnerStyle.setSelection(choiceGenre, true);
    }

    public void addItemToSpinner() {
        // Les items du spinner Niveau
        List<String> listNiveau = new ArrayList<>();
        listNiveau.add("Niveau 1");
        listNiveau.add("Niveau 2");
        listNiveau.add("Niveau 3");
        ArrayAdapter<String> niveauAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listNiveau);
        niveauAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNiveau.setAdapter(niveauAdapter);

        // Les items su spinner style
        List<String> listStyle = new ArrayList<>();
        listStyle.add("Rap");
        listStyle.add("Pop");
        listStyle.add("Electro");
        listStyle.add("Aléatoire");
        ArrayAdapter<String> styleAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listStyle);
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStyle.setAdapter(styleAdapter);
        //spinnerStyle.getAdapter().registerDataSetObserver
    }

    public void addListenerToItem() {
        spinnerNiveau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.editor.putString(MainActivity.niveau, parent.getItemAtPosition(position).toString());
                MainActivity.editor.commit();
                /*Toast.makeText(parent.getContext(),
                        "Niveau selectioné : " + parent.getItemAtPosition(position).toString()
                                + MainActivity.settings.getString("niveauKey", "Niveau 2"),
                        Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.editor.putString(MainActivity.style, parent.getItemAtPosition(position).toString());
                MainActivity.editor.commit();
                /*Toast.makeText(parent.getContext(),
                        "Style de musique selectioné : " + parent.getItemAtPosition(position).toString()
                                + MainActivity.settings.getString("styleKey", "RnB"),
                        Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return true;
        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        //return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OptionsActivity.this.finish();
    }
}
