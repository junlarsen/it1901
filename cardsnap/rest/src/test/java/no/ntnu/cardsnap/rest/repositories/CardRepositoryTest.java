package no.ntnu.cardsnap.rest.repositories;

import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.core.CardDeck;
import no.ntnu.cardsnap.rest.PersistenceDependentTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardRepositoryTest extends PersistenceDependentTestCase {
  private final CardRepository repository = new CardRepository(jdb);
  private final CardDeckRepository cardDeckRepository = new CardDeckRepository(jdb);

  @Test
  @DisplayName("it can create and find new cards")
  public void testCreateCard() throws IOException {
    // First, we need to make a card deck for the cards
    CardDeck owner = cardDeckRepository.create(UUID.randomUUID(), "Foo");
    // Then we can start adding cards
    assertTrue(repository.all(owner.getId()).isEmpty());
    Card created = repository.create(UUID.randomUUID(), "Q", "A", owner.getId());
    assertEquals(1, repository.all(owner.getId()).size());
    Optional<Card> found = repository.find(created.getId(), owner.getId());
    assertTrue(found.isPresent());
    assertEquals(created, found.get());
    // Ensure it cannot create one without an existing owner
    assertThrows(IllegalArgumentException.class, () -> {
      repository.create(UUID.randomUUID(), "Q", "A", UUID.randomUUID());
    });
  }

  @Test
  @DisplayName("it can update decks")
  public void testUpdateCard() throws IOException {
    CardDeck owner = cardDeckRepository.create(UUID.randomUUID(), "Foo");
    Card card = repository.create(UUID.randomUUID(), "Q", "A", owner.getId());
    assertEquals(1, repository.all(owner.getId()).size());
    Optional<Card> updated = repository.update(card.getId(), "newQ", "newA", owner.getId());
    assertEquals(1, repository.all(owner.getId()).size());
    assertTrue(updated.isPresent());
    assertEquals(updated.get().getAnswer(), "newA");
    assertEquals(updated.get().getQuestion(), "newQ");
    // It cannot update things that dont exist
    Optional<Card> missing = repository.update(UUID.randomUUID(), "A", "B", owner.getId());
    assertFalse(missing.isPresent());
  }

  @Test
  @DisplayName("it can delete cards")
  public void testDeleteCard() throws IOException {
    CardDeck owner = cardDeckRepository.create(UUID.randomUUID(), "Foo");
    Card card = repository.create(UUID.randomUUID(), "Q", "A", owner.getId());
    assertEquals(1, repository.all(owner.getId()).size());
    Optional<Card> found = repository.find(card.getId(), owner.getId());
    assertTrue(found.isPresent());
    repository.delete(card.getId(), owner.getId());
    Optional<Card> deleted = repository.find(card.getId(), owner.getId());
    assertFalse(deleted.isPresent());
  }
}
