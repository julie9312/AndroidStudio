package com.julie.quizapp;

public class QuizModel {
    // 문제와 답을 저장하는 private 변수 만들기
    // 문제의 데이터 타입은 int, 답의 데이터 타입은 boolean
    // 문제는 values > strings.xml 에 저장한 문제를 불러오는 것이기 때문에 데이터 타입은 int 여야한다.
    private int mQuestion;
    private boolean mAnswer;

    public QuizModel(int mQuestion, boolean mAnswer) {
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
    }

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean getAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}
