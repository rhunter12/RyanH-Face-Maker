/*
@author Ryan Hunter-Bliss
*/

package edu.up.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.Random;


public class Face implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, Spinner.OnItemSelectedListener {

    //Define instance variables
    Paint skinColor = new Paint();
    Paint eyeColor = new Paint();
    Paint hairColor = new Paint();
    int hairStyle;
    faceView myFaceView;
    int selectedVal = 0;

    /**
    External citation
    Date: 6 September 2020
    Problem: Couldn't use findviewbyid from an external class
    Resource: https://stackoverflow.com/questions/8323777/using-findviewbyid-in-a-class-that-does-not-extend-activity-in-android
    Solution: followed example from post
     **/
    private Activity activity;
    public Face (Activity _activity) {
        this.activity = _activity;

        myFaceView = _activity.findViewById(R.id.face_view);
    }


    /**
    Random function makes a random color for each part of
    the face and also sets the hair to a random style
     **/
    public void random() {
        Random rand = new Random();

        int RandSkinR = rand.nextInt(255);
        int RandSkinG = rand.nextInt(255);
        int RandSkinB = rand.nextInt(255);
        int RandEyeR = rand.nextInt(255);
        int RandEyeG = rand.nextInt(255);
        int RandEyeB = rand.nextInt(255);
        int RandHairR = rand.nextInt(255);
        int RandHairG = rand.nextInt(255);
        int RandHairB = rand.nextInt(255);

        int randSkin = Color.argb(255, RandSkinR, RandSkinG, RandSkinB);
        int randEye = Color.argb(255, RandEyeR, RandEyeG, RandEyeB);
        int randHair = Color.argb(255, RandHairR, RandHairG, RandHairB);

        skinColor.setColor(randSkin);
        eyeColor.setColor(randEye);
        hairColor.setColor(randHair);
    }


    /**
    External citation
    Date: 8 September 2020
    Problem: couldn't figure out how to access seekbar data from another class
    Resource: https://stackoverflow.com/questions/23896216/set-3-seekbars-to-change-rgb-channels/23896425
    Solution: I followed the example shown and made a few changes
              to match the implementation of the button onclick.
     **/

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    int sliderR = 100;
    int sliderG = 100;
    int sliderB = 100;
    int sliderColor;
    /**
    Reads input from seekbar and makes a color,
    called sliderColor from the three values.
     **/
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.r_bar:
                sliderR = progress;
                break;
            case R.id.g_bar:
                sliderG = progress;
                break;
            case R.id.b_bar:
                sliderB = progress;
                break;
        }
        sliderColor = Color.argb(255, sliderR, sliderG, sliderB);
        myFaceView.invalidate();
    }


    /**
    External citation
    Date: 9 September 2020
    Problem: could not get buttons to perform actions
    Resource: https://stackoverflow.com/questions/33285588/calling-ui-functions-outside-of-mainactivity-in-android
    Solution: I used the example code from this post

    onClick looks for a button press and does
    an action based on what the button is used for.
     **/
    @Override
    public void onClick(View arg0) {
        switch(arg0.getId()) {
            case R.id.random_button:
                random();
                break;
            case R.id.hair_radio:
                hairColor.setColor(sliderColor);
                break;
            case R.id.eyes_radio:
                eyeColor.setColor(sliderColor);
                break;
            case R.id.skin_radio:
                skinColor.setColor(sliderColor);
                break;
        }
        myFaceView.invalidate();
    }


    /**
    Draws the head along with all of the other face features
     **/
    public void drawHead(Canvas canvas, float left, float bottom, String sel) {
        if (selectedVal == 2) {
            canvas.drawRect(left-200, bottom+400, left+200, bottom, hairColor);
        }
        else if (selectedVal == 1) {
            canvas.drawRect(left-200, bottom-400, left+200, bottom, hairColor);
        }
        else if (selectedVal == 3) {
            canvas.drawRect(left-300, bottom-300, left+300, bottom-100, hairColor);
        }


        canvas.drawCircle(left, bottom, 200.0f, skinColor);
        canvas.drawCircle(left-100, bottom-50, 25.0f, eyeColor);
        canvas.drawCircle(left+100, bottom-50, 25.0f, eyeColor);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedVal = i;
        myFaceView.invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


/**
External citation
Date: 6 September 2020
Problem: Didn't know how to draw on view from another class
Resource: https://stackoverflow.com/questions/21221649/android-how-to-use-the-ondraw-method-in-a-class-extending-activity
Solution: followed example from post
 **/
class faceView extends View {

    Face myFace = null;

    public void setFace(Face newFace) {
        myFace = newFace;
    }

    public faceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (myFace != null) {
            myFace.drawHead(canvas, 900, 400, "");
        }

    }
}
