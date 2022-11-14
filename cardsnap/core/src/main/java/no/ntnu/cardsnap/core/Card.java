package no.ntnu.cardsnap.core;

import java.util.Objects;
import java.util.UUID;

/**
 * A card, which models a simple flashcard with a question and an answer.
 *
 * <p>It also has reference to the card deck it belongs into through
 * {@link Card#getOwner()}.
 */
public final class Card {
  private final UUID id;
  private final String question;
  private final String answer;
  private final UUID owner;

  /**
   * No-args constructor for Gson.
   */
  private Card() {
    this(UUID.randomUUID(), "question", "answer", UUID.randomUUID());
  }

  /**
   * Create a new Card.
   *
   * @param id       Unique UUID for the card
   * @param question The question
   * @param answer   The answer
   * @throws IllegalArgumentException If front or back are blank, or uuid is null
   * @throws NullPointerException     If id, question, or answer is null
   */
  public Card(UUID id, String question, String answer, UUID owner) {
    this.id = id;
    this.question = question;
    this.answer = answer;
    this.owner = owner;

    Objects.requireNonNull(id);
    Objects.requireNonNull(question);
    Objects.requireNonNull(answer);
    Objects.requireNonNull(owner);
    if (question.isBlank()) {
      throw new IllegalArgumentException("Front cannot be blank");
    }
    if (answer.isBlank()) {
      throw new IllegalArgumentException("Back cannot be blank");
    }
  }

  public UUID getId() {
    return id;
  }

  public String getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }

  public UUID getOwner() {
    return owner;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof Card card) {
      return getId().equals(card.getId())
          && getQuestion().equals(card.getQuestion())
          && getAnswer().equals(card.getAnswer())
          && getOwner().equals(card.getOwner());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getQuestion(), getAnswer(), getOwner());
  }
}
