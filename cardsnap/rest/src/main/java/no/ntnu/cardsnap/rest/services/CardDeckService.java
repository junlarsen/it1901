package no.ntnu.cardsnap.rest.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import no.ntnu.cardsnap.core.CardDeck;
import no.ntnu.cardsnap.rest.exceptions.EntityAlreadyExistsException;
import no.ntnu.cardsnap.rest.exceptions.EntityNotFoundException;
import no.ntnu.cardsnap.rest.repositories.AbstractCardDeckRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link AbstractCardDeckService}.
 */
@Primary
@Service
public class CardDeckService implements AbstractCardDeckService {
  private final AbstractCardDeckRepository repository;

  /**
   * Create a new CardDeckService, backed by a repository.
   *
   * @param repository The repository to use
   */
  public CardDeckService(AbstractCardDeckRepository repository) {
    this.repository = repository;
  }

  @Override
  public CardDeck create(String name) throws IOException, IllegalArgumentException {
    boolean exists = repository.all()
        .stream()
        .anyMatch((deck) -> deck.getName().equals(name));
    if (exists) {
      throw new EntityAlreadyExistsException("Deck with name " + name + "already exists");
    }
    return repository.create(UUID.randomUUID(), name);
  }

  @Override
  public List<CardDeck> list(int page) throws IOException, IllegalArgumentException {
    Set<CardDeck> all = repository.all();
    if ((page * 50) > all.size()) {
      throw new IllegalArgumentException("Specified offset goes past size");
    }
    return all.stream()
        .limit(50)
        .skip(page * 50L)
        .toList();
  }

  @Override
  public CardDeck find(UUID id) throws IOException {
    Optional<CardDeck> deck = repository.find(id);
    if (deck.isEmpty()) {
      throw new EntityNotFoundException("Deck with ID " + id + " does not exist");
    }
    return deck.get();
  }

  @Override
  public CardDeck update(UUID id, String newName) throws IOException {
    Optional<CardDeck> deck = repository.update(id, newName);
    if (deck.isEmpty()) {
      throw new EntityNotFoundException("Deck with ID " + id + " does not exist");
    }
    return deck.get();
  }

  @Override
  public void delete(UUID id) throws IOException {
    // Ensure that the entity exists
    find(id);
    repository.delete(id, true);
  }
}
