package com.example.project002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.project002.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true;
    boolean isOperatorClick = false;
    double resultNumber = 0;
    double inputNumber = 0; //전역변수로 선언
    String operator = "=";
    String lastOperator = "+";

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
            isFirstInput = false;
            if(operator.equals("=")){
                activityMainBinding.mathTxt.setText(null);
                isOperatorClick = false;
            }
        }else{
            if(activityMainBinding.resultTxt.getText().toString().equals("0")){
                Toast.makeText(this, "0으로 시작되는 숫자는 없습니다.", Toast.LENGTH_LONG).show();
                isFirstInput = true;
            }else{
                activityMainBinding.resultTxt.append(view.getTag().toString());
            }
        }
    }

    public void allClearButtonClick (View view){
        activityMainBinding.resultTxt.setText("0");
        activityMainBinding.mathTxt.setText("");
        resultNumber = 0;
        operator = "+";
        isFirstInput = true;
        isOperatorClick = false;
    }

    public void pointButtonClick (View view){
        if(isFirstInput){
            activityMainBinding.resultTxt.setText("0" +view.getTag().toString());
            isFirstInput = false;
        }else{
            if(activityMainBinding.resultTxt.getText().toString().contains(".")){
                Toast.makeText(this, "이미 소숫점이 존재합니다.",Toast.LENGTH_LONG).show();
            }else{
                activityMainBinding.resultTxt.append(view.getTag().toString());
            }
        }
    }

    public void operatorClick (View view){
        isOperatorClick = true;
        lastOperator = view.getTag().toString();
        if(isFirstInput){
            if(operator.equals("=")){
                operator = view.getTag().toString();
                resultNumber = Double.parseDouble(activityMainBinding.resultTxt.toString());
                activityMainBinding.mathTxt.setText(resultNumber + " " + operator + " ");
            }else{
                operator = view.getTag().toString();
                String getMathTxt = activityMainBinding.mathTxt.getText().toString();
                String subString = getMathTxt.substring(0, getMathTxt.length() - 2);
                activityMainBinding.mathTxt.setText(subString);
                activityMainBinding.mathTxt.append(operator + " ");
            }

        }else{
            inputNumber = Double.parseDouble(activityMainBinding.resultTxt.getText().toString());
            resultNumber = calculator(resultNumber, inputNumber, operator);

            activityMainBinding.resultTxt.setText(String.valueOf(resultNumber));
            isFirstInput = true;
            operator = view.getTag().toString();
            activityMainBinding.mathTxt.append(inputNumber + " " + operator + " ");

        }
    }

    public void equalsButtonClick (View view){
        if(isFirstInput){
            if(isOperatorClick){
                activityMainBinding.mathTxt.setText(resultNumber + " " + lastOperator + " " + inputNumber + " =");
                resultNumber = calculator(resultNumber,inputNumber,lastOperator);
                activityMainBinding.resultTxt.setText(String.valueOf(resultNumber));
            }
        }else {
            inputNumber = Double.parseDouble(activityMainBinding.resultTxt.getText().toString());

            resultNumber = calculator(resultNumber, inputNumber, operator);
            lastOperator = operator;
            activityMainBinding.resultTxt.setText(String.valueOf(resultNumber));
            isFirstInput = true;
            operator = view.getTag().toString();
            activityMainBinding.mathTxt.append(inputNumber + " " + operator + " ");
        }
    }

    public void backspaceButtonClick(View view){
        if(!isFirstInput){
            String getResultTxt = activityMainBinding.resultTxt.getText().toString();
            if(getResultTxt.length() > 0) {
                String subString = getResultTxt.substring(0, getResultTxt.length() - 1);
                activityMainBinding.resultTxt.setText(subString);
            }else{
                activityMainBinding.resultTxt.setText("0");
                isFirstInput = true;
            }
        }
    }

    private double calculator(double resultNumber, double inputNumber, String operator) {
        switch (operator){
            case "=":
                resultNumber = inputNumber;
                break;
            case "+":
                resultNumber = resultNumber + inputNumber;
                break;
            case "_":
                resultNumber = resultNumber - inputNumber;
                break;
            case "x":
                resultNumber = resultNumber * inputNumber;
                break;
            case "÷":
                resultNumber = resultNumber / inputNumber;
                break;
            default:
                Log.e("calculator", resultNumber + " " + inputNumber + " " + operator);
                break;
        }
        return resultNumber;
    }


}
