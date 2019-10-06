package com.example.conversioncalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static int mode = 0;
    public static final int VICE_SELECTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText toField = (EditText) findViewById(R.id.toTextField);
        EditText fromField = (EditText) findViewById(R.id.fromTextField);


        TextView toLabel = (TextView) findViewById(R.id.toLabel);
        TextView fromLabel = (TextView) findViewById(R.id.fromLabel);
        TextView titleLabel = (TextView) findViewById(R.id.titleLabel);

        Button clearButton = (Button) findViewById(R.id.clearButton);
        Button modeButton = (Button) findViewById(R.id.modeButton);
        Button calculateButton = (Button) findViewById(R.id.calculateButton);

        toLabel.setText("Meters");
        fromLabel.setText("Yards");



        clearButton.setOnClickListener((View v) -> {
            hideKeyboard();
            toField.setText("");
            fromField.setText("");
        });

        modeButton.setOnClickListener((View v) -> {
            hideKeyboard();
            toField.setText("");
            fromField.setText("");
            if (mode == 0) {
                titleLabel.setText("Volume Converter");
                fromField.setHint("Enter Volume in Liters");
                toField.setHint("Enter From Value");
                toLabel.setText("Gallons");
                fromLabel.setText("Liters");
                mode++;
            } else {
                titleLabel.setText("Length Converter");
                fromField.setHint("Enter Length in Yards");
                toField.setHint("Enter To Value");
                toLabel.setText("Meters");
                fromLabel.setText("Yards");
                mode--;
            }



        });

        calculateButton.setOnClickListener((View v) -> {
            hideKeyboard();
            if(!fromField.getText().toString().equals("") && toField.getText().toString().equals("")){
                if(mode == 0) {
                    double top = Double.parseDouble(fromField.getText().toString());
                    UnitsConverter.LengthUnits topLabel = UnitsConverter.LengthUnits.valueOf(fromLabel.getText().toString());
                    UnitsConverter.LengthUnits bottomLabel = UnitsConverter.LengthUnits.valueOf(toLabel.getText().toString());
                    double lengthConv = UnitsConverter.convert(top, topLabel, bottomLabel);
                    toField.setText(""+lengthConv);
                }
                if(mode == 1) {
                    double top = Double.parseDouble(fromField.getText().toString());
                    UnitsConverter.VolumeUnits topLabel2 = UnitsConverter.VolumeUnits.valueOf(fromLabel.getText().toString());
                    UnitsConverter.VolumeUnits bottomLabel2 = UnitsConverter.VolumeUnits.valueOf(toLabel.getText().toString());
                    double lengthConv = UnitsConverter.convert(top, topLabel2, bottomLabel2);
                    toField.setText(""+lengthConv);
                }

            }
            if(fromField.getText().toString().equals("") && !toField.getText().toString().equals("")) {
                if (mode == 0) {
                    double bottom = Double.parseDouble(toField.getText().toString());
                    UnitsConverter.LengthUnits topLabel = UnitsConverter.LengthUnits.valueOf(fromLabel.getText().toString());
                    UnitsConverter.LengthUnits bottomLabel = UnitsConverter.LengthUnits.valueOf(toLabel.getText().toString());
                    double lengthConv = UnitsConverter.convert(bottom, bottomLabel, topLabel);
                    fromField.setText("" + lengthConv);
                }
                if (mode == 1) {
                    double bottom = Double.parseDouble(toField.getText().toString());
                    UnitsConverter.VolumeUnits topLabel2 = UnitsConverter.VolumeUnits.valueOf(fromLabel.getText().toString());
                    UnitsConverter.VolumeUnits bottomLabel2 = UnitsConverter.VolumeUnits.valueOf(toLabel.getText().toString());
                    double lengthConv = UnitsConverter.convert(bottom, bottomLabel2, topLabel2);
                    fromField.setText("" + lengthConv);
                }
            }

            fromField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (hasFocus) {
                        toField.setText("");
                    }
                    if (!hasFocus) {
                        hideKeyboard();
                        //hide keyboard
                    }
                }
            });
            toField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (hasFocus) {
                        fromField.setText("");
                    }
                    if (!hasFocus) {
                        hideKeyboard();
                        // hide keyboard
                    }
                }
            });

        });
    }

    public void hideKeyboard() {
        if(getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == VICE_SELECTION) {
//            TextView from = (TextView) findViewById(R.id.fromTextField);
//            TextView to = (TextView) findViewById(R.id.toTextField);
//            from.setText(data.getStringExtra("vice"));
//            to.setText((data.getStringExtra("vice2")));
//
//
//        }
//
//    }



}
