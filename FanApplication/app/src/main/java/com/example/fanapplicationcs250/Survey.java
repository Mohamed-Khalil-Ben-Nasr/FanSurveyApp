package com.example.fanapplicationcs250;

public class Survey {
    private int surveyId;
    private String question;
    private String answers;
    private String day;

    public Survey(){}
    public int getSurveyId(){return surveyId;};

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}

