package no.ntnu.cardsnap;

public class Card {
    private String question;
    private String answer; 

    public Card(String question, String answer){
        setQuestion(question);
        setAnswer(answer);

    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        //TODO validation 
        if (question.length() < 2) {
            throw new IllegalArgumentException("Question is too short.");
        }
        if (question.length() > 250) {
            throw new IllegalArgumentException("Question is too long.");
        }
        this.question = question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        //TODO validation
        if (answer.length() < 2) {
            throw new IllegalArgumentException("Answer is too short.");
        }
        if (answer.length() > 250) {
            throw new IllegalArgumentException("Answer is too long.");
        }
        this.answer = answer;
    }
    
    
}
