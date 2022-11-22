package no.ntnu.cardsnap.core;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import no.ntnu.cardsnap.types.CardDeck;

/**
 * Abstract implementation of something that can handle read/write operations on
 * a {@link CardDeck} value.
 *
 * <p>Current implementation writes to disk, in the future it might be interesting
 * to store in a relational database.
 */
public interface AbstractCardDeckRepository {
  /**
   * Create a new card deck with the given name.
   *
   * @param id   The UUID to assign to the deck
   * @param name The name to give
   * @return The newly created card deck
   * @throws IOException If an I/O error occurred during creation.
   */
  CardDeck create(UUID id, String name) throws IOException;

  /**
   * Update the name of a card deck.
   *
   * @param id      The UUID of the card deck to update
   * @param newName The new name for the card deck
   * @return The updated card deck
   * @throws IOException If an I/O error occurred during creation.
   */
  Optional<CardDeck> update(UUID id, String newName) throws IOException;

  /**
   * Get all the card decks.
   *
   * @return All the decks
   * @throws IOException If an I/O error occurred during creation.
   */
  Set<CardDeck> all() throws IOException;

  /**
   * Find a card deck by a given id.
   *
   * @param id The UUID of the card deck to find
   * @return The card deck, if it exists
   * @throws IOException If an I/O error occurred during creation.
   */
  Optional<CardDeck> find(UUID id) throws IOException;

  /**
   * Delete a card deck with a given id.
   *
   * <p>If the deck does not exist, nothing happens.
   *
   * @param id      The UUID of the card deck to delete
   * @param cascade Whether it should cascade delete cards
   * @throws IOException If an I/O error occurred during creation.
   */
  void delete(UUID id, boolean cascade) throws IOException;
}
