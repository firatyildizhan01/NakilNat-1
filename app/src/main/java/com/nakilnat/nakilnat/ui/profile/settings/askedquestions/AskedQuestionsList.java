package com.nakilnat.nakilnat.ui.profile.settings.askedquestions;

public class AskedQuestionsList {
    private String question;
    private String answer;
    private boolean expanded;

    public AskedQuestionsList(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
