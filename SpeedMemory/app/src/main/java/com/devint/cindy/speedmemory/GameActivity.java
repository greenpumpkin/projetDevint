package com.devint.cindy.speedmemory;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
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
import java.util.HashMap;
import java.util.List;
public class GameActivity extends ActionBarActivity {

    private GridLayout gridLayout;
    private static Object lock = new Object();
    private CountDownTimer countDownTimer;
    private int heightCarte, widthCarte;
    private Button b1, b2, b3, b4, b5, b6;
    private HashMap<Integer, Card> listeDesCouleurs = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        b1 = (Button) findViewById(R.id.carte1);
        listeDesCouleurs.put(b1.getId(), new Card(b1,b1.getId(),Color.BLUE));
        b2 = (Button) findViewById(R.id.carte2);
        listeDesCouleurs.put(b2.getId(), new Card(b2,b2.getId(),Color.BLUE));
        b3 = (Button) findViewById(R.id.carte3);
        listeDesCouleurs.put(b3.getId(), new Card(b3,b3.getId(),Color.GREEN));
        b4 = (Button) findViewById(R.id.carte4);
        listeDesCouleurs.put(b4.getId(), new Card(b4,b4.getId(),Color.GREEN));
        b5 = (Button) findViewById(R.id.carte5);
        listeDesCouleurs.put(b5.getId(), new Card(b5,b5.getId(),Color.RED));
        b6 = (Button) findViewById(R.id.carte6);
        listeDesCouleurs.put(b6.getId(), new Card(b6,b6.getId(),Color.BLUE));

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

        /**Typeface font = Typeface.createFromAsset(getAssets(),"Megalopolis.ttf");
        b1.setTypeface(font);
        b2.setTypeface(font);
        b3.setTypeface(font);
        b4.setTypeface(font);
        b5.setTypeface(font);
        b6.setTypeface(font);**/



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

    private class ClickActionListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            int id = v.getId();
            tournerCarte(id);

        }

        private void tournerCarte(int id) {

            final Button tmp = (Button) findViewById(id);
            tmp.setBackgroundColor(listeDesCouleurs.get(id).getColor());

            countDownTimer = new CountDownTimer(5000,1) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    tmp.setBackgroundColor(Color.GRAY);
                }
            }.start();

        }
    }
}