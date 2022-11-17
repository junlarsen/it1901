package no.ntnu.cardsnap.rest.repositories;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.core.CardDeck;
import no.ntnu.cardsnap.persistence.JsonDatabase;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Implementation of {@link AbstractCardDeckRepository} backed by a
 * {@link JsonDatabase}.
 */
@Primary
@Repository
public class CardDeckRepository implements AbstractCardDeckRepository {
  private final JsonDatabase database;

  /**
   * Create a new CardDeckRepository, backed by the given JsonModelStorage.
   *
   * @param jsonDatabase The database to use
   */
  public CardDeckRepository(JsonDatabase jsonDatabase) {
    this.database = jsonDatabase;
  }

  @Override
  public CardDeck create(UUID id, String name) throws IOException {
    return database.mutation((jsonModel -> {
      CardDeck deck = new CardDeck(id, name);
      jsonModel.add(deck);
      return deck;
    }));
  }

  @Override
  public Optional<CardDeck> update(UUID id, String newName) throws IOException {
    Optional<CardDeck> match = find(id);
    if (match.isEmpty()) {
      return Optional.empty();
    }
    delete(id, false);
    return Optional.of(create(id, newName));
  }

  @Override
  public Set<CardDeck> all() throws IOException {
    return database.query((jsonModel -> new HashSet<>(jsonModel.getDecks())));
  }

  @Override
  public Optional<CardDeck> find(UUID id) throws IOException {
    return database.query((jsonModel -> jsonModel
        .getDecks()
        .stream()
        .filter((deck) -> deck.getId().equals(id))
        .findFirst()
    ));
  }

  @Override
  public void delete(UUID id, boolean cascade) throws IOException {
    Optional<CardDeck> deck = find(id);
    if (deck.isEmpty()) {
      return;
    }
    database.mutation((jsonModel) -> {
      jsonModel.remove(deck.get());
      return null;
    });
    // Cascade deletion on the cards
    if (!cascade) {
      return;
    }
    Set<Card> related = database.query((jsonModel) -> jsonModel.getCards()
            .stream().filter((card) -> card.getOwner().equals(id)))
        .collect(Collectors.toSet());
    database.mutation((jsonModel) -> {
      for (Card card : related) {
        jsonModel.remove(card);
      }
      return null;
    });
  }
}
