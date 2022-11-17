package no.ntnu.cardsnap.rest.repositories;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import no.ntnu.cardsnap.core.Card;

/**
 * Abstract implementation of something that can handle read/write operations on
 * a {@link Card} value.
 *
 * <p>Current implementation writes to disk, in the future it might be interesting
 * to store in a relational database.
 */
public interface AbstractCardRepository {
  /**
   * Create a new card with the given name.
   *
   * @param id       The UUID to assign to the card
   * @param question The front of the card
   * @param answer   The back of the card
   * @param owner    The UUID of the deck it should belong to
   * @return The newly created card
   * @throws IOException              If an I/O error occurred during creation.
   * @throws IllegalArgumentException If the owner deck does not exist
   */
  Card create(UUID id, String question, String answer, UUID owner) throws IOException;

  /**
   * Update the question and answer of a card.
   *
   * @param id       The UUID of the card to update
   * @param question The new question
   * @param answer   The new answer
   * @param owner    UUID of deck to update card in
   * @return The updated card
   * @throws IOException If an I/O error occurred during creation.
   */
  Optional<Card> update(
      UUID id,
      String question,
      String answer,
      UUID owner) throws IOException;

  /**
   * Get all the cards.
   *
   * @param owner UUID of deck to list cards for
   * @return All the cards
   * @throws IOException If an I/O error occurred during creation.
   */
  Set<Card> all(UUID owner) throws IOException;

  /**
   * Find a card by a given id.
   *
   * @param owner UUID of deck to find cards in
   * @param id    The UUID of the card to find
   * @return The card, if it exists
   * @throws IOException If an I/O error occurred during creation.
   */
  Optional<Card> find(UUID id, UUID owner) throws IOException;

  /**
   * Delete a card with a given id.
   *
   * <p>If the card does not exist, nothing happens.
   *
   * @param owner UUID of deck to delete card in
   * @param id    The UUID of the card to delete
   * @throws IOException If an I/O error occurred during creation.
   */
  void delete(UUID id, UUID owner) throws IOException;
}
