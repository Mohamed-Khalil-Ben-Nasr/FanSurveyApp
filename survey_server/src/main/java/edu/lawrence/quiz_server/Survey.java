package edu.lawrence.quiz_server;

import java.sql.Date;

public class Survey {
    private String question;
    private String answers;
    private String day;
    
    public Survey(){}

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
