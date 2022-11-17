package no.ntnu.cardsnap.rest.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.rest.exceptions.EntityNotFoundException;

/**
 * Abstract implementation of something that can handle business level logic
 * on the {@link Card} entity.
 */
public interface AbstractCardService {
  /**
   * Create a new card.
   *
   * @param question Question for front of card
   * @param answer   Answer for back of card
   * @param owner    UUID of card this should belong to
   * @return The newly created card
   * @throws IOException If underlying I/O error occurs
   */
  Card create(String question, String answer, UUID owner) throws IOException;

  /**
   * Get a view of all the stored cards.
   *
   * @param page  Offset to start the view at, cannot exceed size of list.
   * @param owner UUID of deck to list cards for
   * @return The requested cards.
   * @throws IOException              If underlying I/O error occurs
   * @throws IllegalArgumentException If invalid offset value
   */
  List<Card> list(int page, UUID owner) throws IOException, IllegalArgumentException;

  /**
   * Find a card with the given id.
   *
   * @param id    The id to look up
   * @param owner UUID of deck to find cards in
   * @return The card if it exists
   * @throws IOException             If underlying I/O error occurs
   * @throws EntityNotFoundException If the card does not exist
   */
  Card find(UUID id, UUID owner) throws IOException;

  /**
   * Update the name of a card with the given id.
   *
   * @param id          The id to look up
   * @param newQuestion The new question
   * @param newAnswer   The new answer
   * @param owner       UUID of deck to update card in
   * @return The updated card
   * @throws IOException             If underlying I/O error occurs
   * @throws EntityNotFoundException If the card does not exist
   */
  Card update(UUID id, String newQuestion, String newAnswer, UUID owner) throws IOException;

  /**
   * Delete a card with the given id.
   *
   * @param id    The id to look up
   * @param owner UUID of deck to delete card in
   * @throws IOException             If underlying I/O error occurs
   * @throws EntityNotFoundException If the card does not exist
   */
  void delete(UUID id, UUID owner) throws IOException;
}
