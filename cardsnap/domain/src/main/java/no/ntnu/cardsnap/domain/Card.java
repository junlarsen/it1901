package no.ntnu.cardsnap.domain;

import java.util.Objects;

public class Card {
    private String question;
    private String answer; 

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Card card)) {
            return false;
        }
        return question.equals(card.question) && answer.equals(card.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }
}
