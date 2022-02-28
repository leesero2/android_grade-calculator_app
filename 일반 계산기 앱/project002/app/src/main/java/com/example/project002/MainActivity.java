package com.example.project002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project002.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true;
    double resultNumber = 0;
    String operator = "+";


    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
    }

    public void numButtonClick(View view){
        String getButtonText = view.getTag().toString(); //xml 디자인에 tag값을 넣어주면 따로 findid값을 지정안해도 getTag()를 통해 사용가능
        if(isFirstInput){
            //activityMainBinding.resultTxt.setText(view.getTag().toString()); 처럼 사용가능
            activityMainBinding.resultTxt.setText(getButtonText);
            //activityMainBinding.mathTxt.setText(getButtonText);
            isFirstInput = false;
        }else{
            activityMainBinding.resultTxt.append(getButtonText);
            //activityMainBinding.mathTxt.append(getButtonText);
        }
    }

    public void operatorClick (View view){
        double inputNumber = Double.parseDouble(activityMainBinding.resultTxt.getText().toString());

        if(operator.equals("+")){
            resultNumber = resultNumber + inputNumber;
        }else if(operator.equals("-")){
            resultNumber = resultNumber - inputNumber;
        }else if(operator.equals("x")){
            resultNumber = resultNumber * inputNumber;
        }else if(operator.equals("÷")){
            resultNumber = resultNumber / inputNumber;
        }
        activityMainBinding.resultTxt.setText(String.valueOf(resultNumber));
        isFirstInput = true;
        operator = view.getTag().toString();
        activityMainBinding.mathTxt.append(inputNumber + " " + operator + " ");

    }


}
