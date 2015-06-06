package com.devint.cindy.speedmemory.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devint.cindy.speedmemory.R;


public class HelpActivity extends ActionBarActivity {


    private String textModeDeJeu = "Le jeu propose une grille composée de plusieurs cartes. " +
            "Chaque carte est associée à un morceau de musique qui est dévoilé lorsque vous cliquez dessus. " +
            "Chaque morceau de musique a son double, quelque part caché dans la grille. " +
            "Le but du jeu est de retrouver les bonnes paires musicales " +
            "parmi toutes les cartes retournées en un temps record !";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        final Button buttonRetour = (android.widget.Button) findViewById(R.id.button3);
        buttonRetour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, MainActivity.class);
                startActivity(intent);
                MainActivity.textToSpeech.stop();
            }
        });

        final TextView textViewModeDeJeu = (TextView) findViewById(R.id.modeDeJeu);
        textViewModeDeJeu.setText(textModeDeJeu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
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
        MainActivity.textToSpeech.stop();
        super.onBackPressed();
        HelpActivity.this.finish();
    }
}
