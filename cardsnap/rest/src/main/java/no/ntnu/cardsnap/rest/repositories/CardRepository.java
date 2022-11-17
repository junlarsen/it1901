package no.ntnu.cardsnap.rest.repositories;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.persistence.JsonDatabase;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Implementation of {@link AbstractCardRepository} backed by a
 * {@link JsonDatabase}.
 */
@Primary
@Repository
public class CardRepository implements AbstractCardRepository {
  private final JsonDatabase database;

  /**
   * Create a new CardRepository, backed by the given JsonModelStorage.
   *
   * @param jsonDatabase The database to use
   */
  public CardRepository(JsonDatabase jsonDatabase) {
    this.database = jsonDatabase;
  }

  @Override
  public Card create(UUID id, String question, String answer, UUID owner) throws IOException {
    boolean parentExists = database.query((jsonModel) -> jsonModel.getDecks()
        .stream()
        .anyMatch((deck) -> deck.getId().equals(owner)));
    if (!parentExists) {
      throw new IllegalArgumentException("Owner with ID " + owner + "does not exist");
    }

    return database.mutation((jsonModel) -> {
      Card card = new Card(id, question, answer, owner);
      jsonModel.add(card);
      return card;
    });
  }

  @Override
  public Optional<Card> update(
      UUID id,
      String question,
      String answer,
      UUID owner) throws IOException {
    Optional<Card> match = find(id, owner);
    if (match.isEmpty()) {
      return Optional.empty();
    }
    delete(id, owner);
    return Optional.of(create(id, question, answer, match.get().getOwner()));
  }

  @Override
  public Set<Card> all(UUID owner) throws IOException {
    return database.query(jsonModel -> jsonModel
        .getCards()
        .stream()
        .filter((card) -> card.getOwner().equals(owner))
        .collect(Collectors.toSet()));
  }

  @Override
  public Optional<Card> find(UUID id, UUID owner) throws IOException {
    return database.query((jsonModel -> jsonModel
        .getCards()
        .stream()
        .filter((card) -> card.getId().equals(id) && card.getOwner().equals(owner))
        .findFirst()
    ));
  }

  @Override
  public void delete(UUID id, UUID owner) throws IOException {
    Optional<Card> card = find(id, owner);
    if (card.isEmpty()) {
      return;
    }
    database.mutation((jsonModel -> {
      jsonModel.remove(card.get());
      return null;
    }));
  }
}
