package no.ntnu.cardsnap.rest.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.rest.exceptions.EntityNotFoundException;
import no.ntnu.cardsnap.rest.repositories.AbstractCardRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link AbstractCardService}.
 */
@Service
public class CardService implements AbstractCardService {
  private final AbstractCardRepository repository;

  /**
   * Create a new CardService, backed by a repository.
   *
   * @param repository The repository to use
   */
  public CardService(AbstractCardRepository repository) {
    this.repository = repository;
  }

  @Override
  public Card create(String question, String answer, UUID owner) throws IOException {
    try {
      return repository.create(UUID.randomUUID(), question, answer, owner);
    } catch (IllegalArgumentException ex) {
      throw new EntityNotFoundException(
          "Could not create card because deck with ID " + owner + " does not exist"
      );
    }
  }

  @Override
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

  @Override
  public Card find(UUID id, UUID owner) throws IOException {
    Optional<Card> deck = repository.find(id, owner);
    if (deck.isEmpty()) {
      throw new EntityNotFoundException("Card with ID " + id + " does not exist");
    }
    return deck.get();
  }

  @Override
  public Card update(UUID id, String newQuestion, String newAnswer, UUID owner) throws IOException {
    Optional<Card> deck = repository.update(id, newQuestion, newAnswer, owner);
    if (deck.isEmpty()) {
      throw new EntityNotFoundException("Card with ID " + id + " does not exist");
    }
    return deck.get();
  }

  @Override
  public void delete(UUID id, UUID owner) throws IOException {
    // Ensure that the entity exists
    find(id, owner);
    repository.delete(id, owner);
  }
}
