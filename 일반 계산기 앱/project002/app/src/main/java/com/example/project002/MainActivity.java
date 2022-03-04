package com.example.project002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.project002.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true; //계산기에선 이 버튼이 첫번째로 입력한 숫자냐 아니냐를 확인하기위해 boolean으로 변수 isFirstInput 생성
    boolean isOperatorClick = false; //연산자가 눌렀는지 안눌렀는지 체크하는 변수 생성
    double resultNumber = 0; //결과값을 저장할 변수 생성
    double inputNumber = 0; //전역변수로 선언 (마지막에 사용된 값을 알수없기 때문에)
    String operator = "="; //연산자를 저장할 변수 생성
    String lastOperator = "+"; //마지막 연산자 변수 생성

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
                isOperatorClick = false; //연산을 끝냈으니 false 로 변경
            }
        }else{ //isFirstInput 값이 false이면 조건 발동
            if(activityMainBinding.resultTxt.getText().toString().equals("0")){ //0버튼 (테그값이 0) 이라면
                Toast.makeText(this, "0으로 시작되는 숫자는 없습니다.", Toast.LENGTH_LONG).show(); //토스트를 발생시킴
                isFirstInput = true; //처음에 0으로 입력받았기 때문에 데이터가 입력이 안된상황이라 isFirstInput 데이터를 true으로 선언 ( 이거안하면 두번재 입력받았다고 인식하기 때문)
            }else{ //그게 아니라면
                activityMainBinding.resultTxt.append(view.getTag().toString()); //append 함수를 통해 0값을 이어붙임 ( 맨처음이 0으로 시작하는 조건이 아니기 때문 )
            }
        }
    }

    //전부 지우는 메소드
    public void allClearButtonClick (View view){
        activityMainBinding.resultTxt.setText("0"); //결과를 setText 함수를 통해 0으로 셋팅
        activityMainBinding.mathTxt.setText(""); //mathTxt부분도 지움
        resultNumber = 0; //결과 변수도 0으로 초기화
        operator = "+"; //연산자도 +로 초기화
        isFirstInput = true; //아무것도 없는 상황이라 처음 입력으로 돌아왔기 때문에 true로 초기화
        isOperatorClick = false; //초기화 했으니 연산을 false로 변경
    }

    //소수점 버튼 메소드
    public void pointButtonClick (View view){
        if(isFirstInput){
            activityMainBinding.resultTxt.setText("0" + view.getTag().toString()); //append가 아닌 setText를 통해 0.을 구현하기위해 "0"을 더해줌
            isFirstInput = false; //첫번째 입력이 끝났기에 false값으로 초기화
        }else{
            if(activityMainBinding.resultTxt.getText().toString().contains(".")){ //contains함수를 통해 소수점이 이미 존재한다면
                Toast.makeText(this, "이미 소숫점이 존재합니다.",Toast.LENGTH_LONG).show(); //이미 소숫점이 존재한다는 토스트 발생
            }else{
                activityMainBinding.resultTxt.append(view.getTag().toString()); //아니라면 resulrTxt에 값을 붙임
            }
        }
    }

    //연산자 메소드
    public void operatorClick (View view){
        isOperatorClick = true; //연산자를 눌렀으니 true로 초기화
        lastOperator = view.getTag().toString(); //마지막에 입력된 연산자를 갖고옴
        if(isFirstInput){
            if(operator.equals("=")){ //연산자 비교를 통해 참이면
                operator = view.getTag().toString(); //지금 누른 연산자를 저장
                resultNumber = Double.parseDouble(activityMainBinding.resultTxt.toString());
                activityMainBinding.mathTxt.setText(resultNumber + " " + operator + " ");
            }else{
                operator = view.getTag().toString(); //지금 누른 연산자를 저장
                String getMathTxt = activityMainBinding.mathTxt.getText().toString();
                String subString = getMathTxt.substring(0, getMathTxt.length() - 2); //0번부터 getMathTxt 길이에서 2개를 삭제
                activityMainBinding.mathTxt.setText(subString);
                activityMainBinding.mathTxt.append(operator + " ");
            }

        }else{
            inputNumber = Double.parseDouble(activityMainBinding.resultTxt.getText().toString()); //맨앞에 Double inputNumber... 로 지정하게되면 지정된 함수의 자료형은 double인데 결과를 받는데이터는 String이라 Double.parseDouble을 통해 문자열을 double형으로 변환하게끔 선언
            resultNumber = calculator(resultNumber, inputNumber, operator); //받아온 연산 값을 resultNumber에 저장
            activityMainBinding.resultTxt.setText(String.valueOf(resultNumber));
            isFirstInput = true;
            operator = view.getTag().toString();
            activityMainBinding.mathTxt.append(inputNumber + " " + operator + " ");

        }
    }

    // '=' 메소드 (이걸 만든 이유는 일반 윈도우 계산기에선 =을 연달아 누르면 마지막에 했던 연산을 쭉 이어가기 때문에 예외처리를 함)
    public void equalsButtonClick (View view){
        if(isFirstInput){
            if(isOperatorClick){
                activityMainBinding.mathTxt.setText(resultNumber + " " + lastOperator + " " + inputNumber + " ="); //마지막 결과, 마지막 연산, 인풋 넘버
                resultNumber = calculator(resultNumber,inputNumber,lastOperator);
                activityMainBinding.resultTxt.setText(String.valueOf(resultNumber));
            }
        }else {
            inputNumber = Double.parseDouble(activityMainBinding.resultTxt.getText().toString());
            resultNumber = calculator(resultNumber, inputNumber, operator); //받아온 연산 값을 resultNumber에 저장
            lastOperator = operator; //마지막 연산자를 lastOperator에 저장
            activityMainBinding.resultTxt.setText(String.valueOf(resultNumber)); //resultTxt에 결과 함수를 넣음
            isFirstInput = true; //결과가 끝났기에 true로 변경
            operator = view.getTag().toString(); //현재 연산자를 operator에 저장
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
    private double calculator(double resultNumber, double inputNumber, String operator) { //resultNumber inputNumber operator 을 파라메타로 받아옴
        switch (operator){ //operator 변수를 스위치문을 통해 각 버튼에 맞는 함수를 실행
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
        return resultNumber; //결과값을 반환
    }


}
