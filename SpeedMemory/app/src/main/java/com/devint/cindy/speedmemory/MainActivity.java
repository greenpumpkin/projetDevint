package com.devint.cindy.speedmemory;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    private int result;
    private TextToSpeech textToSpeech;

    private final String textModeDeJeu = "Le jeu propose une grille composée de plusieurs cartes. " +
            "Chaque carte est associée à un morceau de musique qui est dévoilé lorsque vous cliquez dessus. " +
            "Chaque morceau de musique a son double, quelque part caché dans la grille. " +
            "Le but du jeu est de retrouver les bonnes paires musicales " +
            "parmi toutes les cartes retournées en un temps record !";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    result = textToSpeech.setLanguage(Locale.FRANCE);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Votre appareil ne supporte pas cette version", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textToSpeech.setLanguage(Locale.FRANCE);

        final Button buttonSuiv = (android.widget.Button) findViewById(R.id.commencer);
        buttonSuiv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                    Toast.makeText(getApplicationContext(), "Votre appareil ne supporte pas cette version", Toast.LENGTH_SHORT).show();
                }
                else {
                    textToSpeech.speak("Commencer jeu", TextToSpeech.QUEUE_FLUSH, null);
                }
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonHelp = (android.widget.Button) findViewById(R.id.regle);
        buttonHelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                    Toast.makeText(getApplicationContext(), "Votre appareil ne supporte pas cette version", Toast.LENGTH_SHORT).show();
                }
                else {
                    textToSpeech.speak(textModeDeJeu, TextToSpeech.QUEUE_FLUSH, null);
                }
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
