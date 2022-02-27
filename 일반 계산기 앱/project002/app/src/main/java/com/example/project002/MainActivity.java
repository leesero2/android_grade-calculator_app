package com.example.project002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project002.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
    }

    public void numButtonClick(View view){

    }


    public void num1Click(View view) {
        Button button = findViewById(view.getId());
        if (isFirstInput) {
            activityMainBinding.resultTxt.setText("1");
            activityMainBinding.mathTxt.setText("1");
        } else {
            activityMainBinding.resultTxt.append("1");
            activityMainBinding.mathTxt.append("1");
        }
        //activityMainBinding.resultTxt.setVisibility(View.GONE);
    }

    public void num2Click(View view) {
        if (isFirstInput) {
            activityMainBinding.resultTxt.setText("2");
            activityMainBinding.mathTxt.setText("2");
        } else {
            activityMainBinding.resultTxt.append("2");
            activityMainBinding.mathTxt.append("2");
        }
    }

    public void num3Click(View view) {
        if (isFirstInput) {
            activityMainBinding.resultTxt.setText("3");
            activityMainBinding.mathTxt.setText("3");
        } else {
            activityMainBinding.resultTxt.append("3");
            activityMainBinding.mathTxt.append("3");
        }
    }

    public void num4Click(View view) {
        if (isFirstInput) {
            activityMainBinding.resultTxt.setText("4");
            activityMainBinding.mathTxt.setText("4");
        } else {
            activityMainBinding.resultTxt.append("4");
            activityMainBinding.mathTxt.append("4");
        }
    }

    public void num5Click(View view) {
        if (isFirstInput) {
            activityMainBinding.resultTxt.setText("5");
            activityMainBinding.mathTxt.setText("5");
        } else {
            activityMainBinding.resultTxt.append("5");
            activityMainBinding.mathTxt.append("5");
        }
    }

    public void num6Click(View view) {
        if (isFirstInput) {
            activityMainBinding.resultTxt.setText("6");
            activityMainBinding.mathTxt.setText("6");
        } else {
            activityMainBinding.resultTxt.append("6");
            activityMainBinding.mathTxt.append("6");
        }
    }

    public void num7Click(View view) {
        if (isFirstInput) {
            activityMainBinding.resultTxt.setText("7");
            activityMainBinding.mathTxt.setText("7");
        } else {
            activityMainBinding.resultTxt.append("7");
            activityMainBinding.mathTxt.append("7");
        }
    }

    public void num8Click(View view) {
        if (isFirstInput) {
            activityMainBinding.resultTxt.setText("8");
            activityMainBinding.mathTxt.setText("8");
        } else {
            activityMainBinding.resultTxt.append("8");
            activityMainBinding.mathTxt.append("8");
        }
    }

    public void num9Click(View view) {
        if (isFirstInput) {
            activityMainBinding.resultTxt.setText("9");
            activityMainBinding.mathTxt.setText("9");
        } else {
            activityMainBinding.resultTxt.append("9");
            activityMainBinding.mathTxt.append("9");
        }
    }
}
