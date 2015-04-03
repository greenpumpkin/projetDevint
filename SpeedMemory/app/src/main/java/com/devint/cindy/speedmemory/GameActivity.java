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
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
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
import java.util.Map;
import java.util.Random;

public class GameActivity extends ActionBarActivity {

    private static ArrayList<Button> identifiants = new ArrayList<Button>();
    private GridLayout gridLayout;
    private CountDownTimer countDownTimer;
    private int heightCarte, widthCarte;
    private Button b1, b2, b3, b4, b5, b6;
    private HashMap<Integer, Card> listeDesImages = new HashMap<>();
    private Random random = new Random();
    private MediaPlayer mPlayer;
    private int score;
    private long timeLast = 0;
    private int cartesRetournees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        score = 0;
        Typeface font = Typeface.createFromAsset(getAssets(), "Megalopolis.ttf");

        b1 = (Button) findViewById(R.id.carte1);
        listeDesImages.put(b1.getId(), new Card(b1.getId(), R.raw.morceau1, b1));
        b2 = (Button) findViewById(R.id.carte2);
        listeDesImages.put(b2.getId(), new Card(b2.getId(), R.raw.morceau1, b2));
        b3 = (Button) findViewById(R.id.carte3);
        listeDesImages.put(b3.getId(), new Card(b3.getId(), R.raw.morceau2, b3));
        b4 = (Button) findViewById(R.id.carte4);
        listeDesImages.put(b4.getId(), new Card(b4.getId(), R.raw.morceau2, b4));
        b5 = (Button) findViewById(R.id.carte5);
        listeDesImages.put(b5.getId(), new Card(b5.getId(), R.raw.morceau3, b5));
        b6 = (Button) findViewById(R.id.carte6);
        listeDesImages.put(b6.getId(), new Card(b6.getId(), R.raw.morceau3, b6));

        cartesRetournees = listeDesImages.size();

        melangeCartes(listeDesImages, random);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        widthCarte = width / 6;
        heightCarte = height / 4;

        for (Map.Entry<Integer, Card> entry : listeDesImages.entrySet()) {
            int cle = entry.getKey();
            Card value = entry.getValue();

            for (int i = 0; i < listeDesImages.size(); i++) {
                value.getButton().setWidth(widthCarte);
                value.getButton().setHeight(heightCarte);
                value.getButton().setBackgroundColor(Color.LTGRAY);
                value.getButton().setTypeface(font);
                value.getButton().setOnClickListener(new ClickActionListener());
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        mPlayer.stop();
        super.onBackPressed();
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

    /**
     * Méthode mélangeant les cartes aléatoirement au début de chaque partie.
     * @param map
     * @param r
     */
    private void melangeCartes(HashMap<Integer,Card> map, Random r) {

        /**
         * Initialisation images et sons des cartes.
         */
        Image img1 = new Image(R.drawable.sugar_maroon5);
        Image img2 = new Image(R.drawable.bailand_enrique_iglesias);
        Image img3 = new Image(R.drawable.born_in_babylon);
        int son1 = R.raw.morceau1;
        int son2 = R.raw.morceau2;
        int son3 = R.raw.morceau3;

        /**
         * On stocke tout dans des HashMaps
         */
        HashMap<Image, Integer> listeImgEtSonAssocie = new HashMap<>();
        listeImgEtSonAssocie.put(img1, son1);
        listeImgEtSonAssocie.put(img2, son2);
        listeImgEtSonAssocie.put(img3, son3);

        ArrayList<Image> listeImages = new ArrayList<Image>();
        listeImages.add(img1);
        listeImages.add(img2);
        listeImages.add(img3);

        /**
         * Distribution aléatoire
         */
        for (Card carte : map.values()) {

            int indiceRandom = r.nextInt(listeImages.size());
            Image img = listeImages.get(indiceRandom);
            int idAudio = listeImgEtSonAssocie.get(img);
            int reste = img.getReste();
            img.setReste(--reste);

            if (img.getReste() == 0) {
                listeImages.remove(indiceRandom);
            }

            carte.setImgAndAudio(img.getImage(), idAudio);

        }
    }

    /**
     * Écouteurs
     */
    private class ClickActionListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            score++;
            // Pour ne pas cliquer sur deux boutons en même temps
            if (SystemClock.elapsedRealtime() - timeLast < 1000) {
                return;
            }
            timeLast = SystemClock.elapsedRealtime();

            tournerCarte(v.getId());
            playSound(listeDesImages.get(v.getId()).getAudioId());

        }

        /**
         * Permet de retourner une carte
         * @param id
         */
        private void tournerCarte(int id) {

            int color = listeDesImages.get(id).getColor();
            int image = listeDesImages.get(id).getImageId();
            final Button tmp = (Button) findViewById(id);
            tmp.setBackgroundColor(Color.BLACK);
            tmp.setTextColor(Color.WHITE);
            identifiants.add(tmp);


            countDownTimer = new CountDownTimer(5000, 1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    //bloquerBouton();

                }

                @Override
                public void onFinish() {
                    //deBloquerBouton();

                    if (identifiants.size() == 2) {
                        checkCartesIdentiques();
                        identifiants.clear();
                    }
                    if (isGameFinished()) {
                        Context context = getApplicationContext();
                        CharSequence text = "Félicitations, vous avez gagné \n en "+ score+ " clics !";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }.start();
        }

        /**
         * Identifie les boutons non utilisés dans la grille
         * @param id
         * @return
         */
        private HashMap<Integer, Card> getBoutonNonUtilise(int id) {
            HashMap<Integer, Card> ret = new HashMap<>();
            for (Card c : listeDesImages.values()) {
                if (c.getId() != id)
                    ret.put(id, c);
            }
            return ret;
        }

        /**
         * Lorsque le jeu est terminé, on appelle cette méthode
         * @return
         */
        private boolean isGameFinished() {

            if (cartesRetournees == 0)
                return true;
            return false;
        }

        private void bloquerBouton() {
            for (Card c : listeDesImages.values()) {
                Button tmp = (Button) findViewById(c.getId());
                if (!c.isCardFound())
                    tmp.setEnabled(false);
            }
        }

        private void deBloquerBouton() {
            for (Card c : listeDesImages.values()) {
                Button tmp = (Button) findViewById(c.getId());
                if (!c.isCardFound())
                    tmp.setEnabled(true);
            }
        }

        /**
         * Vérifie si deux cartes retournées sont identiques
         */
        private void checkCartesIdentiques() {

            int id1 = identifiants.get(0).getId();
            int id2 = identifiants.get(1).getId();
            if (listeDesImages.get(id1).getImageId() != listeDesImages.get(id2).getImageId()) {
                identifiants.get(0).setBackgroundColor(Color.LTGRAY);
                identifiants.get(1).setBackgroundColor(Color.LTGRAY);
                identifiants.get(0).setTextColor(Color.BLACK);
                identifiants.get(1).setTextColor(Color.BLACK);
                listeDesImages.get(id1).setIsCardFound(false);
                listeDesImages.get(id2).setIsCardFound(false);
            } else {
                listeDesImages.get(id1).setIsCardFound(true);
                listeDesImages.get(id2).setIsCardFound(true);
                cartesRetournees -= 2;
                listeDesImages.get(id1).getButton().setText("              ");
                listeDesImages.get(id2).getButton().setText("              ");
                listeDesImages.get(id1).getButton().setBackgroundResource(listeDesImages.get(id1).getImageId());
                listeDesImages.get(id2).getButton().setBackgroundResource(listeDesImages.get(id2).getImageId());
                bloquerTrouve(listeDesImages.get(id1));
                bloquerTrouve(listeDesImages.get(id2));
            }
        }

        private HashMap<Integer, Card> getButtonTrouve() {
            HashMap<Integer, Card> ret = new HashMap<>();
            for (Card c : listeDesImages.values()) {
                if (c.isCardFound() == true)
                    ret.put(c.getId(), c);
            }
            return null;
        }

        private void bloquerTrouve(Card c) {
            if (c.isCardFound()) {
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