package no.ntnu.cardsnap.domain;

import java.util.Objects;

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
    public Card(String cardQuestion, String cardAnswer) {
        setQuestion(cardQuestion);
        setAnswer(cardAnswer);
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
        if (newAnswer == null) {
            throw new IllegalArgumentException("Answer cannot be null");
        }
        if (newAnswer.isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be empty");
        }
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
        if (newQuestion == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        if (newQuestion.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be empty");
        }
        this.question = newQuestion;
    }

    /**
     * Check if two Cards are equal.
     *
     * @param other Card to compare
     * @return If the cards are equal
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Card card)) {
            return false;
        }
        return question.equals(card.question) && answer.equals(card.answer);
    }

    /**
     * Get the hash for this object. Used for comparison in HashSet sets.
     *
     * @return The hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }
}
