package com.devint.cindy.speedmemory;
import android.content.Context;
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
    private HashMap<Integer, Card> imgList = new HashMap<>();
    private Random random = new Random();
    private MediaPlayer mPlayer;
    private int score;
    private long timeLast = 0;
    private int returnedCards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        score = 0;
        Typeface font = Typeface.createFromAsset(getAssets(), "Megalopolis.ttf");

        b1 = (Button) findViewById(R.id.carte1);
        imgList.put(b1.getId(), new Card(b1.getId(), R.raw.morceau1, b1));
        b2 = (Button) findViewById(R.id.carte2);
        imgList.put(b2.getId(), new Card(b2.getId(), R.raw.morceau1, b2));
        b3 = (Button) findViewById(R.id.carte3);
        imgList.put(b3.getId(), new Card(b3.getId(), R.raw.morceau2, b3));
        b4 = (Button) findViewById(R.id.carte4);
        imgList.put(b4.getId(), new Card(b4.getId(), R.raw.morceau2, b4));
        b5 = (Button) findViewById(R.id.carte5);
        imgList.put(b5.getId(), new Card(b5.getId(), R.raw.morceau3, b5));
        b6 = (Button) findViewById(R.id.carte6);
        imgList.put(b6.getId(), new Card(b6.getId(), R.raw.morceau3, b6));

        returnedCards = imgList.size();

        Card.mixCards(imgList, random);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        widthCarte = width / 6;
        heightCarte = height / 4;

        for (Map.Entry<Integer, Card> entry : imgList.entrySet()) {
            int cle = entry.getKey();
            Card value = entry.getValue();

            for (int i = 0; i < imgList.size(); i++) {
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
     * Listeners
     */
    private class ClickActionListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            score++;

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

            int color = imgList.get(id).getColor();
            int image = imgList.get(id).getImageId();
            final Button tmp = (Button) findViewById(id);
            tmp.setBackgroundColor(Color.BLACK);
            tmp.setTextColor(Color.WHITE);
            identifiants.add(tmp);


            countDownTimer = new CountDownTimer(5000, 1) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {

                    if (identifiants.size() == 2) {
                        checkCards();
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
         * Identifies buttons that are not used in the grid
         * @param id
         * @return
         */
        private HashMap<Integer, Card> getUnusedButton(int id) {
            HashMap<Integer, Card> ret = new HashMap<>();
            for (Card c : imgList.values()) {
                if (c.getId() != id)
                    ret.put(id, c);
            }
            return ret;
        }

        /**
         * When the game ends, we call this method
         * @return
         */
        private boolean isGameFinished() {

            if (returnedCards == 0)
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
                identifiants.get(0).setBackgroundColor(Color.LTGRAY);
                identifiants.get(1).setBackgroundColor(Color.LTGRAY);
                identifiants.get(0).setTextColor(Color.BLACK);
                identifiants.get(1).setTextColor(Color.BLACK);
                imgList.get(id1).setIsCardFound(false);
                imgList.get(id2).setIsCardFound(false);
            } else {
                imgList.get(id1).setIsCardFound(true);
                imgList.get(id2).setIsCardFound(true);
                returnedCards -= 2;
                imgList.get(id1).getButton().setText("              ");
                imgList.get(id2).getButton().setText("              ");
                imgList.get(id1).getButton().setBackgroundResource(imgList.get(id1).getImageId());
                imgList.get(id2).getButton().setBackgroundResource(imgList.get(id2).getImageId());
                lockFound(imgList.get(id1));
                lockFound(imgList.get(id2));
            }
        }

        private void lockFound(Card c) {
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