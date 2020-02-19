//John Semaan Whack-A-Mole game!
//When player whacks 10 moles game ends, then proceeds to get faster once the start button is pressed again.
package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable moleImage;
    private ImageView[] imageViews;
    private int moleLocation;
    private Random rand;
    private Handler handler;
    private UpdateMole upmole;
    private TextView counter;
    private int count;
    private boolean hit;
    private boolean end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridLayout);
        moleImage = getDrawable(R.drawable.mole);
        counter = findViewById(R.id.Counter);
        imageViews = new ImageView[16];
        rand = new Random();
        handler = new Handler();
        moleLocation = rand.nextInt(16);
        upmole = new UpdateMole();
        end = false;
        count = 0;
        hit = false;

        for (int i = 0; i < 16; i++) {
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.mole_view, null);
            imageViews[i].setMinimumWidth(270);
            imageViews[i].setMinimumHeight(270);
            if (i == moleLocation) {
                imageViews[i].setImageDrawable(moleImage);
            }
            grid.addView(imageViews[i]);
        }
    }

    public void startPressed(View v) {
        count = 0;
        end = false;
        hit = false;
        counter.setText(count+"");
        handler.postDelayed(upmole, 1000);
    }
    public void imageclick(View v){
        if (imageViews[moleLocation]== v  && hit == false && end == false) {
            hit = true;
            count++;
            counter.setText(count+"");
            if(count == 10){
                counter.setText("You won!");
                end = true;
            }
        }else{
            hit =false;
        }
    }

    private class UpdateMole implements Runnable {
        @Override
        public void run() {
            imageViews[moleLocation].setImageDrawable(null);
            moleLocation = rand.nextInt(16);
            imageViews[moleLocation].setImageDrawable(moleImage);
            handler.postDelayed(upmole, 1000);

        }
    }
}
