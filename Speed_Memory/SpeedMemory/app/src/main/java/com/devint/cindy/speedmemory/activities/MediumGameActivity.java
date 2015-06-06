package com.devint.cindy.speedmemory.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import com.devint.cindy.speedmemory.R;
import com.devint.cindy.speedmemory.context.Card;
import com.devint.cindy.speedmemory.context.MusicStyles;
import com.devint.cindy.speedmemory.context.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MediumGameActivity extends ActionBarActivity {

    private static ArrayList<Button> identifiants = new ArrayList<Button>();
    private GridLayout gridLayout;
    private CountDownTimer countDownTimer;
    private int heightCarte, widthCarte;
    private Button b1, b2, b3, b4, b5, b6, b7, b8;
    private HashMap<Integer, Card> imgList = new HashMap<>();
    private Random random = new Random();
    private MediaPlayer mPlayer = new MediaPlayer();
    private int score;
    private long timeLast = 0;
    private int turnedCards;
    private TextView scoreView;
    private boolean countDownRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_game);
        score = 0;

        Typeface font = Typeface.createFromAsset(getAssets(), "Megalopolis.ttf");

        scoreView = (TextView) findViewById(R.id.score);

        String style = MainActivity.settings.getString("styleKey", "Rap");
        MusicStyles musicStyles = MusicStyles.getStyle(style);

        int son1 = musicStyles.getNextRandomSong();
        int son2 = musicStyles.getNextRandomSong();
        int son3 = musicStyles.getNextRandomSong();
        int son4 = musicStyles.getNextRandomSong();

        b1 = (Button) findViewById(R.id.carte1);
        imgList.put(b1.getId(), new Card(b1.getId(), son1, b1));
        b2 = (Button) findViewById(R.id.carte2);
        imgList.put(b2.getId(), new Card(b2.getId(), son1, b2));
        b3 = (Button) findViewById(R.id.carte3);
        imgList.put(b3.getId(), new Card(b3.getId(), son2, b3));
        b4 = (Button) findViewById(R.id.carte4);
        imgList.put(b4.getId(), new Card(b4.getId(), son2, b4));
        b5 = (Button) findViewById(R.id.carte5);
        imgList.put(b5.getId(), new Card(b5.getId(), son3, b5));
        b6 = (Button) findViewById(R.id.carte6);
        imgList.put(b6.getId(), new Card(b6.getId(), son3, b6));
        b7 = (Button) findViewById(R.id.carte7);
        imgList.put(b7.getId(), new Card(b7.getId(), son4, b7));
        b8 = (Button) findViewById(R.id.carte8);
        imgList.put(b8.getId(), new Card(b8.getId(), son4, b8));

        turnedCards = imgList.size();

        Card.mixCardsMedium(imgList, random);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.y;
        int height = size.y;

        widthCarte = width / 5;
        heightCarte = height / 5;

        for (Map.Entry<Integer, Card> entry : imgList.entrySet()) {
            Card value = entry.getValue();

            for (int i = 0; i < imgList.size(); i++) {
                value.getButton().setWidth(widthCarte);
                value.getButton().setHeight(heightCarte);
                value.getButton().setBackgroundColor(Color.GRAY);
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
        MediumGameActivity.this.finish();
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
     * Listeners
     */
    private class ClickActionListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            score++;
            scoreView.setText(score + "");

            // In case the user clicks on two buttons at the same time
            if (SystemClock.elapsedRealtime() - timeLast < 1000) {
                return;
            }
            timeLast = SystemClock.elapsedRealtime();

            turnCards(v.getId());
            playSound(imgList.get(v.getId()).getAudioId());

        }

        /**
         * Enables to turn a card
         * @param id
         */
        private void turnCards(int id) {
            final Button tmp = (Button) findViewById(id);
            tmp.setBackgroundColor(Color.BLACK);
            tmp.setTextColor(Color.WHITE);
            identifiants.add(tmp);
            final int SCORE_TMP = score;

            countDownTimer = new CountDownTimer(5000, 1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countDownRunning = true;
                    if (identifiants.size() == 2) {
                        countDownRunning = true;
                        getUnusedButton();
                        lockCards();
                        score = SCORE_TMP;
                    }
                }

                @Override
                public void onFinish() {
                    countDownRunning = false;
                    if (identifiants.size() == 2) {
                        checkCards();
                        unlockCards();
                        identifiants.clear();
                    }
                    stopCountDown();
                    if (isGameFinished()) {
                        launchFinal();
                       /* CharSequence text = "Félicitations, vous avez gagné \n en "+ score+ " clics !";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();*/
                    }
                }
            };

            if(countDownRunning) {
                countDownTimer.cancel();
                countDownTimer.start();
            }
            else {
                countDownTimer.start();
            }
        }

        public void stopCountDown() {
            if(countDownTimer != null) {
                countDownTimer.cancel();
                countDownTimer = null;
            }
        }

        public void launchFinal() {
            mPlayer.stop();
            // on récupère le score
            int s = Integer.parseInt(scoreView.getText().toString());
            MainActivity.editor.putInt("scoreKey", s);
            MainActivity.editor.commit();

            //On récupère le pseudo du joueur
            showDialogforPseudo();

        }

        public void showDialogforPseudo() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MediumGameActivity.this);

            // set title
            final EditText textPseudo = new EditText(getApplicationContext());
            textPseudo.setTextColor(Color.BLACK);
            alertDialogBuilder.setView(textPseudo);

            alertDialogBuilder.setTitle("Saisir votre Pseudo");
            alertDialogBuilder.setView(textPseudo);

            // set dialog message
            alertDialogBuilder
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.editor.putString("pseudoKey", textPseudo.getText().toString());
                            MainActivity.editor.commit();

                            // on ajoute le score à la base
                            String ps = MainActivity.settings.getString("pseudoKey", "inconnue");
                            int sc = MainActivity.settings.getInt("scoreKey", 0);
                            int ti = MainActivity.settings.getInt("timeKey", 0);
                            Score scoreToAdd = new Score(2, ps, sc, ti);
                            MainActivity.db.addScore(scoreToAdd);

                            Intent intent = new Intent(MediumGameActivity.this, FinalActivity.class);
                            startActivity(intent);
                            MediumGameActivity.this.finish();
                           /* Toast.makeText(getApplicationContext(),
                                    "Style de musique selectioné : " + MainActivity.settings.getString("pseudoKey", "inconnue"),
                                    Toast.LENGTH_SHORT).show();*/

                        }
                    });
            alertDialogBuilder.show();
        }

        /**
         * When the game ends, we call this method
         * @return
         */
        private boolean isGameFinished() {

            if (turnedCards == 0)
                return true;
            return false;
        }

        /**
         * Checks if two cards are similar
         */
        private void checkCards() {

            int id1 = identifiants.get(0).getId();
            int id2 = identifiants.get(1).getId();
            if (imgList.get(id1).getImageId() != imgList.get(id2).getImageId()) {
                /* the two cards are not the same, same state as in  the begginning */
                identifiants.get(0).setBackgroundColor(Color.GRAY);
                identifiants.get(1).setBackgroundColor(Color.GRAY);
                identifiants.get(0).setTextColor(Color.BLACK);
                identifiants.get(1).setTextColor(Color.BLACK);
            } else {
                /* the two cards are the same, we display the images associated to the cards */
                imgList.get(id1).setIsCardFound(true);
                imgList.get(id2).setIsCardFound(true);
                turnedCards -= 2;
                imgList.get(id1).getButton().setText("              ");
                imgList.get(id2).getButton().setText("              ");
                imgList.get(id1).getButton().setBackgroundResource(imgList.get(id1).getImageId());
                imgList.get(id2).getButton().setBackgroundResource(imgList.get(id2).getImageId());
                lockFound(imgList.get(id1));
                lockFound(imgList.get(id2));
            }
        }

        /**
         * Identifies buttons that are not used in the grid
         * @return ret
         *
         */
        private HashMap<Integer, Card> getUnusedButton() {
            HashMap<Integer, Card> ret = new HashMap<>();
            for (Card c : imgList.values()) {
                if ((c.getId() != identifiants.get(0).getId()) && (c.getId() != identifiants.get(1).getId()))
                    /* here the cards are different from the 2 selected */
                    ret.put(c.getId(), c);
            }
            return ret;
        }


        /**
         * Lock the cards that are unused
         */
        private void lockCards() {

            for (Card c : getUnusedButton().values() ) {
                c.getButton().setClickable(false);
            }
        }

        /**
         * Unlock the unused cards
         */
        private void unlockCards() {

            for (Card c : getUnusedButton().values()) {
                c.getButton().setClickable(true);
            }
            /* refreshing of the map of unused buttons */
            getUnusedButton().clear();
        }

        /**
         * Lock cards that are "found" in pairs
         * @param c
         */
        private void lockFound(Card c) {
            if (c.isCardFound()) {
                Button b = (Button) findViewById(c.getId());
                b.setClickable(false);
            }
        }

        /**
         * Play music associated to the button.
         * @param resId
         */
        private void playSound(int resId) {
            if(mPlayer != null) {
                mPlayer.stop();
                mPlayer.release();
            }
            mPlayer = MediaPlayer.create(MediumGameActivity.this, resId);
            mPlayer.start();
        }
    }
}
