package no.ntnu.cardsnap.rest.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.rest.exceptions.EntityNotFoundException;
import no.ntnu.cardsnap.rest.repositories.CardRepository;
import org.springframework.stereotype.Service;

/**
 * Concrete implementation of something that can handle business level logic
 * on the {@link Card} entity.
 */
@Service
public class CardService {
  private final CardRepository repository;

  /**
   * Create a new CardService, backed by a repository.
   *
   * @param repository The repository to use
   */
  public CardService(CardRepository repository) {
    this.repository = repository;
  }

  /**
   * Create a new card.
   *
   * @param question Question for front of card
   * @param answer   Answer for back of card
   * @param owner    UUID of card this should belong to
   * @return The newly created card
   * @throws IOException If underlying I/O error occurs
   */
  public Card create(String question, String answer, UUID owner) throws IOException {
    try {
      return repository.create(UUID.randomUUID(), question, answer, owner);
    } catch (IllegalArgumentException ex) {
      throw new EntityNotFoundException(
          "Could not create card because deck with ID " + owner + " does not exist"
      );
    }
  }

  /**
   * Get a view of all the stored cards.
   *
   * @param page  Offset to start the view at, cannot exceed size of list.
   * @param owner UUID of deck to list cards for
   * @return The requested cards.
   * @throws IOException              If underlying I/O error occurs
   * @throws IllegalArgumentException If invalid offset value
   */
  public List<Card> list(int page, UUID owner) throws IOException, IllegalArgumentException {
    Set<Card> all = repository.all(owner);
    if ((page * 50) > all.size()) {
      throw new IllegalArgumentException("Specified offset goes past size");
    }
    return all.stream()
        .limit(50)
        .skip(page * 50L)
        .toList();
  }

  /**
   * Find a card with the given id.
   *
   * @param id    The id to look up
   * @param owner UUID of deck to find cards in
   * @return The card if it exists
   * @throws IOException             If underlying I/O error occurs
   * @throws EntityNotFoundException If the card does not exist
   */
  public Card find(UUID id, UUID owner) throws IOException {
    Optional<Card> deck = repository.find(id, owner);
    if (deck.isEmpty()) {
      throw new EntityNotFoundException("Card with ID " + id + " does not exist");
    }
    return deck.get();
  }

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
  public Card update(UUID id, String newQuestion, String newAnswer, UUID owner) throws IOException {
    Optional<Card> deck = repository.update(id, newQuestion, newAnswer, owner);
    if (deck.isEmpty()) {
      throw new EntityNotFoundException("Card with ID " + id + " does not exist");
    }
    return deck.get();
  }

  /**
   * Delete a card with the given id.
   *
   * @param id    The id to look up
   * @param owner UUID of deck to delete card in
   * @throws IOException             If underlying I/O error occurs
   * @throws EntityNotFoundException If the card does not exist
   */
  public void delete(UUID id, UUID owner) throws IOException {
    // Ensure that the entity exists
    find(id, owner);
    repository.delete(id, owner);
  }
}
