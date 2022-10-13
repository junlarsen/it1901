package no.ntnu.cardsnap.domain;

/**
 * Card domain type.
 * <p>
 * The Card type is a simple flashcard that has a question on the front, and an
 * answer to that question on the back.
 */
public class Card {
    /**
     * The card question.
     */
    private String question;

    /**
     * The card answer.
     */
    private String answer;

    /**
     * Create a new Card.
     *
     * @param cardQuestion The question
     * @param cardAnswer   The answer
     */
    public Card(final String cardQuestion, final String cardAnswer) {
        question = cardQuestion;
        answer = cardAnswer;
    }

    /**
     * Get the answer.
     *
     * @return The answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set a new answer.
     *
     * @param newAnswer Answer to replace old
     */
    public void setAnswer(final String newAnswer) {
        answer = newAnswer;
    }

    /**
     * Get the question.
     *
     * @return The question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Set a new question.
     *
     * @param newQuestion Question to replace old
     */
    public void setQuestion(final String newQuestion) {
        question = newQuestion;
    }
}
