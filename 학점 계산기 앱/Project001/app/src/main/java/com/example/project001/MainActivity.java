package com.example.project001;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    subjectAdapter subjectAdapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.lv);

        subjectAdapter = new subjectAdapter(this,R.layout.item);
        lv.setAdapter(subjectAdapter);

        //임시 리스트 어뎁터 데이터
//        bookAdapter.addBook(new Book("C언어","언휴"));
//        bookAdapter.addBook(new Book("Java","EH"));
//        bookAdapter.addBook(new Book("파이썬","언제나"));

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
    }
    public  void addButtonClick(View view){
        EditText et_name = (EditText)findViewById(R.id.txtName) ;
        String sName = et_name.getText().toString();
        EditText et_score = (EditText)findViewById(R.id.txtScore1) ;
        String sScore = et_score.getText().toString();

        subjectAdapter.addSubject(new subject(sName,sScore));
        et_name.setText("");
        et_score.setText("");
    }
}