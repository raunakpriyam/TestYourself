package com.example.phoneauth;

public class eachquestion {
    private String mquestion_num ;
    private String mquestion;
    private String moptionA;
    private String moptionB;
    private String moptionC;
    private String moptionD;
    private String mcorrectansr;



    public eachquestion(String question_num,String question,String optionA,String optionB,String optionC,String optionD,String correctansr){
        mquestion_num=question_num;
        mquestion=question;
        moptionA=optionA;
        moptionB=optionB;
        moptionC=optionC;
        moptionD=optionD;
        mcorrectansr=correctansr;



    }

    public String getMquestion_num() {
        return mquestion_num;
    }

    public String getMquestion() {
        return mquestion;
    }

    public String getMoptionA() {
        return moptionA;
    }

    public String getMoptionB() {
        return moptionB;
    }

    public String getMoptionC() {
        return moptionC;
    }

    public String getMoptionD() {
        return moptionD;
    }

    public String getMcorrectansr() {
        return mcorrectansr;
    }

}
