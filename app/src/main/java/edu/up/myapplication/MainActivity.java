/*
@author Ryan Hunter-Bliss
*/

package edu.up.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] hairNames = {"Select an Option", "Tall", "Long", "Wide"};
    Button randomButton;
    RadioButton hairRadio;
    RadioButton eyesRadio;
    RadioButton skinRadio;

    Face instance;

    private String selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = new Face(this);
        faceView face = findViewById(R.id.face_view);
        face.setFace(instance);

        randomButton = (Button)findViewById(R.id.random_button);
        randomButton.setOnClickListener(instance);

        hairRadio = (RadioButton)findViewById(R.id.hair_radio);
        hairRadio.setOnClickListener(instance);

        eyesRadio = (RadioButton)findViewById(R.id.eyes_radio);
        eyesRadio.setOnClickListener(instance);

        skinRadio = (RadioButton)findViewById(R.id.skin_radio);
        skinRadio.setOnClickListener(instance);



        SeekBar sbR = (SeekBar) findViewById(R.id.r_bar);
        SeekBar sbG = (SeekBar) findViewById(R.id.g_bar);
        SeekBar sbB = (SeekBar) findViewById(R.id.b_bar);

        Face faceSbR = new Face(this);
        Face faceSbG = new Face(this);
        Face faceSbB = new Face(this);

        sbR.setOnSeekBarChangeListener(faceSbR);
        sbG.setOnSeekBarChangeListener(faceSbG);
        sbB.setOnSeekBarChangeListener(faceSbB);



        //Set data of spinner
        ArrayAdapter<String> hairAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, hairNames);
        Spinner hairSpinner = findViewById(R.id.hair_spinner);
        hairSpinner.setAdapter(hairAdapter);
        hairSpinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selection = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public String getSelection() {
        return selection;
    }
}