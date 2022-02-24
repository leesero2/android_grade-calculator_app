package com.example.project001;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    subjectAdapter subjectAdapter;
    ListView lv;
    private NumberPicker npCategory,npScore, npCredit;
    private TextView txtCategory,txtScore, txtCredit;
    private LinearLayout category, score, credit;
    private Button btnNext1, btnNext2, btnNext3;
    private EditText edtSubject;
    private InputMethodManager imm;


    //넘버피커 안의 데이터
    private String[] sCategory = new String[]{"부전선택","부전필수","복전선택","복전필수","전공선택","전공필수","교양선택","교양필수","일반선택","기타"};
    private String[] sScore = new String[]{"A+","A","B+","B","C+","C","D+","D","F","P"};
    private String[] sCredit = new String[]{"6","5","4","3","2","1","0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.lv);

        subjectAdapter = new subjectAdapter(this,R.layout.item);
        lv.setAdapter(subjectAdapter);


        npCategory = findViewById(R.id.npCategory);
        txtCategory = findViewById(R.id.txtCategory);
        txtScore = findViewById(R.id.txtScore);
        txtCredit = findViewById(R.id.txtCredit);
        btnNext1 = findViewById(R.id.btnNext1);
        btnNext2 = findViewById(R.id.btnNext2);
        btnNext3 = findViewById(R.id.btnNext3);
        category = findViewById(R.id.category);
        score = findViewById(R.id.score);
        credit = findViewById(R.id.credit);
        edtSubject = findViewById(R.id.edtSubject);
        npScore = findViewById(R.id.npScore);
        npCredit = findViewById(R.id.npCredit);
        edtSubject = findViewById(R.id.edtSubject);
        //키보드 객체
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        //초기화
        category.setVisibility(View.GONE);
        //넘버피커 : category 설정
        npCategory.setMinValue(0);
        npCategory.setMaxValue(sCategory.length-1);
        npCategory.setValue(5);
        npCategory.setWrapSelectorWheel(false);
        npCategory.setDisplayedValues(sCategory);
        npCategory.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                txtCategory.setText(sCategory[newVal]);
            }
        });

        score.setVisibility(View.GONE);
        //넘버피커 : score 설정
        npScore.setMinValue(0);
        npScore.setMaxValue(sScore.length-1);
        npScore.setValue(0);
        npScore.setWrapSelectorWheel(false);
        npScore.setDisplayedValues(sScore);
        npScore.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                txtScore.setText(sScore[newVal]);
            }
        });

        credit.setVisibility(View.GONE);
        //넘버피커 : credit 설정
        npCredit.setMinValue(0);
        npCredit.setMaxValue(sCredit.length-1);
        npCredit.setValue(3);
        npCredit.setWrapSelectorWheel(false);
        npCredit.setDisplayedValues(sCredit);
        npCredit.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                txtCredit.setText(sCredit[newVal]);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                ListView lv = (ListView)parent;
                subject subject = (subject) lv.getItemAtPosition(position);
                TextView tv_name = (TextView)findViewById(R.id.tv_name);
                tv_name.setText(subject.getTitle());
                TextView tv_score = (TextView)findViewById(R.id.tv_score);
                tv_score.setText(subject.getAuthor());
            }
        });

        //input_넘버피커
        txtCategory.setOnClickListener(txtButton);
        txtScore.setOnClickListener(txtButton);
        txtCredit.setOnClickListener(txtButton);
        //input_키보드
        edtSubject.setOnFocusChangeListener(edtFocus);
        //edtSubject.setOnEditorActionListener(complete);
    }

    //end onResume===============================================================================

    private TextView.OnClickListener txtButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //버튼 등장 효과
            Animation animation = new AlphaAnimation(0,1);
            animation.setDuration(400);
            switch (v.getId()){
                case R.id.txtCategory:
                    if(txtCategory.getText().toString().equals("")){
                        txtCategory.setText(sCategory[npCategory.getValue()]);
                        npCategory.setValue(5);
                    }
                    else {
                        int i=0;
                        while (!sCategory[i].equals(txtCategory.getText().toString())){
                            i++;
                        }
                        npCategory.setValue(i);
                    }
                    txtScore.setSelected(false);
                    txtCredit.setSelected(false);
                    score.setVisibility(View.GONE);
                    credit.setVisibility(View.GONE);
                    //보이기
                    if(category.getVisibility()!=View.VISIBLE){
                        txtCategory.setSelected(true);
                        category.setAnimation(animation);
                        category.setVisibility(View.VISIBLE);
                        if(txtScore.getText().toString().equals("")){
                            btnNext1.setText("다음");
                        }
                        else{
                            btnNext1.setText("완료");
                        }
                    }
                    //다시 닫기
                    else {
                        txtCategory.setSelected(false);
                        category.setVisibility(View.GONE);
                    }
                    break;

                case R.id.txtScore:
                    if(txtScore.getText().toString().equals("")){
                        txtScore.setText(sScore[npScore.getValue()]);
                        npScore.setValue(0);
                    }
                    else {
                        int i=0;
                        while (!sScore[i].equals(txtScore.getText().toString())){
                            i++;
                        }
                        npScore.setValue(i);
                    }
                    txtCategory.setSelected(false);
                    txtCredit.setSelected(false);
                    category.setVisibility(View.GONE);
                    credit.setVisibility(View.GONE);
                    //보이기
                    if(score.getVisibility()!=View.VISIBLE){
                        txtScore.setSelected(true);
                        score.setAnimation(animation);
                        score.setVisibility(View.VISIBLE);
                        if(txtCredit.getText().toString().equals("")){
                            btnNext2.setText("다음");
                        }
                        else{
                            btnNext2.setText("완료");
                        }
                    }
                    //다시닫기
                    else{
                        txtScore.setSelected(false);
                        score.setVisibility(View.GONE);
                    }
                    break;

                case R.id.txtCredit:
                    if(txtCredit.getText().toString().equals("")){
                        txtCredit.setText(sCredit[npCredit.getValue()]);
                        npCredit.setValue(3);
                    }
                    else{
                        int i=0;
                        while (!sCredit[i].equals(txtCredit.getText().toString())){
                            i++;
                        }
                        npCredit.setValue(i);
                    }
                    txtCategory.setSelected(false);
                    txtScore.setSelected(false);
                    category.setVisibility(View.GONE);
                    score.setVisibility(View.GONE);
                    //보이기
                    if(credit.getVisibility()!=View.VISIBLE){
                        txtCredit.setSelected(true);
                        credit.setAnimation(animation);
                        credit.setVisibility(View.VISIBLE);
                        if(edtSubject.getText().toString().equals("")){
                            btnNext3.setText("다음");
                        }
                        else{
                            btnNext3.setText("완료");
                        }
                    }
                    //다시닫기
                    else{
                        txtCredit.setSelected(false);
                        credit.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };

    private EditText.OnFocusChangeListener edtFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(!hasFocus){
                //포커스를 잃을 경우 키보드 닫기
                imm.hideSoftInputFromWindow(edtSubject.getWindowToken(), 0);
            }
            else {
                txtCategory.setSelected(false);
                txtScore.setSelected(false);
                txtCredit.setSelected(false);
                category.setVisibility(View.GONE);
                score.setVisibility(View.GONE);
                credit.setVisibility(View.GONE);
                imm.showSoftInput(edtSubject,0);
            }
        }
    };//end EditText.OnFocusChangeListener edtFocus

    private Button.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnNext1:
                    //숨기기
                    category.setVisibility(View.GONE);
                    txtCategory.setSelected(false);
                    //보이기
                    if(txtScore.getText().toString().equals("")){
                        npScore.setValue(0);
                        //버튼 등장 효과
                        Animation animation = new AlphaAnimation(0,1);
                        animation.setDuration(400);
                        score.setAnimation(animation);
                        score.setVisibility(View.VISIBLE);
                        txtScore.setSelected(true);
                        txtScore.setText(sScore[npScore.getValue()]);
                    }
                    break;

                case R.id.btnNext2:
                    score.setVisibility(View.GONE);
                    txtScore.setSelected(false);
                    if(txtCredit.getText().toString().equals("")){
                        npCredit.setValue(3);
                        //버튼 등장 효과
                        Animation animation = new AlphaAnimation(0,1);
                        animation.setDuration(400);
                        credit.setAnimation(animation);
                        credit.setVisibility(View.VISIBLE);
                        txtCredit.setSelected(true);
                        txtCredit.setText(sCredit[npCredit.getValue()]);
                    }
                    break;

                case R.id.btnNext3:
                    credit.setVisibility(View.GONE);
                    txtCredit.setSelected(false);
                    if(edtSubject.getText().toString().equals("")){
                        edtSubject.requestFocus();
                    }
                    break;
            }
        }
    };

    public void addButtonClick(View view){
        EditText et_name = (EditText)findViewById(R.id.txtName) ;
        String sName = et_name.getText().toString();
        EditText et_score = (EditText)findViewById(R.id.txtScore1) ;
        String sScore = et_score.getText().toString();

        subjectAdapter.addSubject(new subject(sName,sScore));
        et_name.setText("");
        et_score.setText("");
    }
}