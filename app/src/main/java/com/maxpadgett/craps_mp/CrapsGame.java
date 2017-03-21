package com.maxpadgett.craps_mp;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Thread.sleep;


public class CrapsGame extends AppCompatActivity {

    boolean firstTurn = true;
    int point;
    AnimationDrawable diceRollAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craps_game);




        final Button button = (Button) findViewById(R.id.Roll_Button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                int dice1 = (int)(Math.random()* 6) + 1;
                int dice2 = (int)(Math.random()* 6) + 1;

                ImageView diceA = (ImageView) findViewById(R.id.imageView2);
                ImageView diceB = (ImageView) findViewById(R.id.imageView3);


                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("die1", R.drawable.die1);
                map.put("die2", R.drawable.die2);
                map.put("die3", R.drawable.die3);
                map.put("die4", R.drawable.die4);
                map.put("die5", R.drawable.die5);
                map.put("die6", R.drawable.die6);

                //rollDice();

                diceA.setImageResource(map.get("die" + dice1));
                diceB.setImageResource(map.get("die" + dice2));

                int roll = dice1 + dice2;
                TextView text = (TextView) findViewById(R.id.textView);


                    if(firstTurn) {
                        if(roll == 7 || roll == 11) {
                            text.setText(R.string.text2);

                        }
                        else if(roll == 2 || roll == 3 || roll == 12){
                            text.setText(R.string.text3);
                        }
                        else{
                            point = roll;
                            firstTurn = false;
                            Resources res = getResources();
                            text.setText(String.format(res.getString(R.string.point_stuff), point));
                        }
                    }
                    else{
                        if(roll == 7){
                            text.setText(R.string.text4);
                            firstTurn = true;
                        }
                        if(roll == point){
                            text.setText(R.string.text5);
                            firstTurn = true;
                        }
                    }


                }

        });
    }


    public boolean onClick(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_BUTTON_PRESS) {
            diceRollAnim.start();
            return true;
        }
        return super.onTouchEvent(event);
    }


}
