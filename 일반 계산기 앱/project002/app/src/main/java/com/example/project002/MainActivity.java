package com.example.project002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.project002.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true; //계산기에선 이 버튼이 첫번째로 입력한 숫자냐 아니냐를 확인하기위해 boolean으로 변수 isFirstInput 생성
    boolean isOperatorClick = false; //
    double resultNumber = 0; //결과값을 저장할 변수 생성
    double inputNumber = 0; //전역변수로 선언
    String operator = "="; //연산자를 저장할 변수 생성
    String lastOperator = "+"; //

    ActivityMainBinding activityMainBinding; //뷰바인딩 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater()); //뷰바인딩 선언
        setContentView(activityMainBinding.getRoot()); //뷰비안딩 등록
    }

    //숫자버튼 클릭 메소드
    public void numButtonClick(View view){
        String getButtonText = view.getTag().toString(); // 테그값을 받아오는 변수 getButtonText 변수를 생성
        //xml 디자인에 tag값을 넣어주면 따로 findid값을 지정안해도 getTag()를 통해 사용가능

        if(isFirstInput){ //isFirstInput 값이 true이면 조건 발동
            //activityMainBinding.resultTxt.setText(view.getTag().toString()); 처럼 사용가능
            activityMainBinding.resultTxt.setText(getButtonText); //resultTxt에 setText함수를 통해 버튼값을 셋팅함
            isFirstInput = false; //이제 첫번째 입력이 아니기 때문에 isFirstInput을 false로 변경
            if(operator.equals("=")){
                activityMainBinding.mathTxt.setText(null);
                isOperatorClick = false;
            }
        }else{ //isFirstInput 값이 false이면 조건 발동
            if(activityMainBinding.resultTxt.getText().toString().equals("0")){ //0버튼 (테그값이 0) 이라면
                Toast.makeText(this, "0으로 시작되는 숫자는 없습니다.", Toast.LENGTH_LONG).show(); //토스트를 발생시킴
                isFirstInput = true;
            }else{ //그게 아니라면
                activityMainBinding.resultTxt.append(view.getTag().toString()); //append 함수를 통해 값을 이어붙임
            }
        }
    }

    //전부 지우는 메소드
    public void allClearButtonClick (View view){
        activityMainBinding.resultTxt.setText("0");
        activityMainBinding.mathTxt.setText("");
        resultNumber = 0;
        operator = "+";
        isFirstInput = true;
        isOperatorClick = false;
    }

    //소수점 버튼 메소드
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

    //연산자 메소드
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

    // '=' 메소드
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

    //백스페이스 버튼 메소드
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

    //연산 함수 메소드
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
