package no.ntnu.cardsnap.rest.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.core.CardDeck;
import no.ntnu.cardsnap.rest.exceptions.EntityAlreadyExistsException;
import no.ntnu.cardsnap.rest.exceptions.EntityNotFoundException;

/**
 * Abstract implementation of something that can handle business level logic
 * on the {@link Card} entity.
 */
public interface AbstractCardDeckService {
  /**
   * Create a new card deck.
   *
   * @param name Name for the deck
   * @return The newly created deck
   * @throws IOException                  If underlying I/O error occurs
   * @throws EntityAlreadyExistsException If deck with name already exists
   */
  CardDeck create(String name) throws IOException, IllegalArgumentException;

  /**
   * Get a view of all the stored card decks.
   *
   * @param page Offset to start the view at, cannot exceed size of list.
   * @return The requested card decks.
   * @throws IOException              If underlying I/O error occurs
   * @throws IllegalArgumentException If invalid offset value
   */
  List<CardDeck> list(int page) throws IOException, IllegalArgumentException;

  /**
   * Find a deck with the given id.
   *
   * @param id The id to look up
   * @return The deck if it exists
   * @throws IOException             If underlying I/O error occurs
   * @throws EntityNotFoundException If the deck does not exist
   */
  CardDeck find(UUID id) throws IOException;

  /**
   * Update the name of a deck with the given id.
   *
   * @param id      The id to look up
   * @param newName The new name for the deck
   * @return The updated deck
   * @throws IOException             If underlying I/O error occurs
   * @throws EntityNotFoundException If the deck does not exist
   */
  CardDeck update(UUID id, String newName) throws IOException;

  /**
   * Delete a deck with the given id.
   *
   * @param id The id to look up
   * @throws IOException             If underlying I/O error occurs
   * @throws EntityNotFoundException If the deck does not exist
   */
  void delete(UUID id) throws IOException;
}
