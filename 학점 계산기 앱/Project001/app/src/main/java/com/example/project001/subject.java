package com.example.project001;

public class subject {
    //과목 학점 점수 전공의 4개의 변수를 지정
    String sName; //과목이름
    String sScore;

    //넘버파커안의 데이터
    //private String[] sCategory = new String[]{"부전선택","부전필수","복전선택","복전필수","전공선택","전공필수","교양선택","교양필수","일반선택","기타"};
    //private String[] sScore = new String[]{"A+","A","B+","B","C+","C","D+","D","F","P"};
    //private String[] sCredit = new String[]{"6","5","4","3","2","1","0"};

    public String getTitle(){
        return sName;
    }
    public String getAuthor() {
        return sScore;
    }

    public  subject(String sName, String sScore){
        this.sName = sName;
        this.sScore = sScore;
    }
}
