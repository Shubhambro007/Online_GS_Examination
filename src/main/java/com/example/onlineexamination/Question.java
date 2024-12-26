package com.example.onlineexamination;

import java.util.List;

//Class to Store Questions and Options
class Question {
    private String question;
    private String correctAnswer;
    private List<String> options;

    public Question(String question, String correctAnswer, List<String> options) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getOptions() {
        return options;
    }
}
