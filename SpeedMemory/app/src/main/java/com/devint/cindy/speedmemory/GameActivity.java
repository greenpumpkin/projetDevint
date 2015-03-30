package com.devint.cindy.speedmemory;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameActivity extends ActionBarActivity {

    private static ArrayList<Button> identifiants = new ArrayList<Button>();
    private GridLayout gridLayout;
    private CountDownTimer countDownTimer;
    private int heightCarte, widthCarte;
    private Button b1, b2, b3, b4, b5, b6;
    private HashMap<Integer, Card> listeDesCouleurs = new HashMap<>();
    private Random random = new Random();
    private MediaPlayer mPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        b1 = (Button) findViewById(R.id.carte1);
        listeDesCouleurs.put(b1.getId(), new Card(b1.getId(),R.raw.morceau1,b1));
        b2 = (Button) findViewById(R.id.carte2);
        listeDesCouleurs.put(b2.getId(), new Card(b2.getId(),R.raw.morceau1,b2));
        b3 = (Button) findViewById(R.id.carte3);
        listeDesCouleurs.put(b3.getId(), new Card(b3.getId(),R.raw.morceau2,b3));
        b4 = (Button) findViewById(R.id.carte4);
        listeDesCouleurs.put(b4.getId(), new Card(b4.getId(),R.raw.morceau2,b4));
        b5 = (Button) findViewById(R.id.carte5);
        listeDesCouleurs.put(b5.getId(), new Card(b5.getId(),R.raw.morceau3,b5));
        b6 = (Button) findViewById(R.id.carte6);
        listeDesCouleurs.put(b6.getId(), new Card(b6.getId(),R.raw.morceau3,b6));

        melangeCartes(listeDesCouleurs, random);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        widthCarte = width / 6;
        heightCarte = height / 4;

        b1.setWidth(widthCarte);
        b1.setHeight(heightCarte);
        b2.setWidth(widthCarte);
        b2.setHeight(heightCarte);
        b3.setWidth(widthCarte);
        b3.setHeight(heightCarte);
        b4.setWidth(widthCarte);
        b4.setHeight(heightCarte);
        b5.setWidth(widthCarte);
        b5.setHeight(heightCarte);
        b6.setWidth(widthCarte);
        b6.setHeight(heightCarte);

        b1.setBackgroundColor(Color.GRAY);
        b2.setBackgroundColor(Color.GRAY);
        b3.setBackgroundColor(Color.GRAY);
        b4.setBackgroundColor(Color.GRAY);
        b5.setBackgroundColor(Color.GRAY);
        b6.setBackgroundColor(Color.GRAY);

        Typeface font = Typeface.createFromAsset(getAssets(),"Megalopolis.ttf");
        b1.setTypeface(font);
        b2.setTypeface(font);
        b3.setTypeface(font);
        b4.setTypeface(font);
        b5.setTypeface(font);
        b6.setTypeface(font);

        b1.setOnClickListener(new ClickActionListener());
        b2.setOnClickListener(new ClickActionListener());
        b3.setOnClickListener(new ClickActionListener());
        b4.setOnClickListener(new ClickActionListener());
        b5.setOnClickListener(new ClickActionListener());
        b6.setOnClickListener(new ClickActionListener());


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

    private void melangeCartes(HashMap<Integer,Card> map, Random r) {

        Couleur bleu = new Couleur(Color.BLUE);
        Couleur rouge = new Couleur(Color.RED);
        Couleur vert = new Couleur(Color.GREEN);
        int son1 = R.raw.morceau1;
        int son2 = R.raw.morceau2;
        int son3 = R.raw.morceau3;

        HashMap<Couleur, Integer> listeCouleurEtSonAssocie = new HashMap<>();
        listeCouleurEtSonAssocie.put(bleu, son1);
        listeCouleurEtSonAssocie.put(rouge, son2);
        listeCouleurEtSonAssocie.put(vert, son3);

        ArrayList<Couleur> listeCouleurs = new ArrayList<Couleur>();
        listeCouleurs.add(bleu);
        listeCouleurs.add(rouge);
        listeCouleurs.add(vert);

        for (Card carte : map.values()) {

            int indiceRandom = r.nextInt(listeCouleurs.size());
            Couleur c = listeCouleurs.get(indiceRandom);
            int idAudio = listeCouleurEtSonAssocie.get(c);
            int reste = c.getReste();
            c.setReste(--reste);

            if (c.getReste() == 0) {
                listeCouleurs.remove(indiceRandom);
            }

            carte.setColorAndAudio(c.getColor(), idAudio);

        }
    }

    private class ClickActionListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            tournerCarte(v.getId());
            playSound(listeDesCouleurs.get(v.getId()).getAudioId());

        }

        private void tournerCarte(int id) {

            int color = listeDesCouleurs.get(id).getColor();
            final Button tmp = (Button) findViewById(id);
            tmp.setBackgroundColor(color);

            identifiants.add(tmp);


            countDownTimer = new CountDownTimer(5000, 1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    bloquerBouton();

                }

                @Override
                public void onFinish() {
                    deBloquerBouton();

                    if (identifiants.size() == 2) {
                        checkCartesIdentiques();
                        identifiants.clear();
                    }
                    if (isGameFinished()) {
                        Context context = getApplicationContext();
                        CharSequence text = "Félicitations, vous avez gagné !";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }.start();
        }

        private HashMap<Integer, Card> getBoutonNonUtilise(int id) {
            HashMap<Integer, Card> ret = new HashMap<>();
            for (Card c : listeDesCouleurs.values()) {
                if (c.getId() != id)
                    ret.put(id, c);
            }
            return ret;
        }

        private boolean isGameFinished() {
            for (Card c : listeDesCouleurs.values()) {
                int idCard = c.getId();
                Button b = (Button) findViewById(idCard);
                ColorDrawable couleurDuBouton = (ColorDrawable) b.getBackground();
                int colorId = couleurDuBouton.getColor();
                if (colorId == Color.GRAY) return false;
            }
            return true;
        }

        private void bloquerBouton() {
            for (Card c : listeDesCouleurs.values()) {
                Button tmp = (Button) findViewById(c.getId());
                if (!c.isCardFind())
                    tmp.setEnabled(false);
            }
        }

        private void deBloquerBouton() {
            for (Card c : listeDesCouleurs.values()) {
                Button tmp = (Button) findViewById(c.getId());
                if (!c.isCardFind())
                    tmp.setEnabled(true);
            }
        }

        private void checkCartesIdentiques() {

            int id1 = identifiants.get(0).getId();
            int id2 = identifiants.get(1).getId();
            if (listeDesCouleurs.get(id1).getColor() != listeDesCouleurs.get(id2).getColor()) {
                identifiants.get(0).setBackgroundColor(Color.GRAY);
                identifiants.get(1).setBackgroundColor(Color.GRAY);
                listeDesCouleurs.get(id1).setIsCardFind(false);
                listeDesCouleurs.get(id2).setIsCardFind(false);
            } else {
                listeDesCouleurs.get(id1).setIsCardFind(true);
                listeDesCouleurs.get(id2).setIsCardFind(true);
                bloquerTrouver(listeDesCouleurs.get(id1));
                bloquerTrouver(listeDesCouleurs.get(id2));
            }
        }

        private HashMap<Integer, Card> getButtonTrouver() {
            HashMap<Integer, Card> ret = new HashMap<>();
            for (Card c : listeDesCouleurs.values()) {
                if (c.isCardFind() == true)
                    ret.put(c.getId(), c);
            }
            return null;
        }

        private void bloquerTrouver(Card c) {
            if (c.isCardFind()) {
                Button b = (Button) findViewById(c.getId());
                b.setEnabled(false);
            }
        }

        private void playSound(int resId) {
            if(mPlayer != null) {
                mPlayer.stop();
                mPlayer.release();
            }
            mPlayer = MediaPlayer.create(GameActivity.this, resId);
            mPlayer.start();
        }
    }
}