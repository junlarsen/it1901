package no.ntnu.cardsnap.core;

import no.ntnu.cardsnap.types.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardDeckRepositoryTest extends PersistenceDependentTestCase {
  private final CardDeckRepository repository = new CardDeckRepository(jdb);

  @Test
  @DisplayName("it can create and find new decks")
  public void testCreateCardDeck() throws IOException {
    assertTrue(repository.all().isEmpty());
    CardDeck created = repository.create(UUID.randomUUID(), "Foobar");
    assertEquals(1, repository.all().size());
    Optional<CardDeck> found = repository.find(created.getId());
    assertTrue(found.isPresent());
    assertEquals(created, found.get());
  }

  @Test
  @DisplayName("it can update decks")
  public void testUpdateDeck() throws IOException {
    CardDeck deck = repository.create(UUID.randomUUID(), "poop");
    assertEquals(1, repository.all().size());
    Optional<CardDeck> updated = repository.update(deck.getId(), "newname");
    assertEquals(1, repository.all().size());
    assertTrue(updated.isPresent());
    assertEquals(updated.get().getName(), "newname");
    // It cannot update things that dont exist
    Optional<CardDeck> missing = repository.update(UUID.randomUUID(), "ba");
    assertFalse(missing.isPresent());
  }

  @Test
  @DisplayName("it can delete decks")
  public void testDeleteDeck() throws IOException {
    CardDeck created = repository.create(UUID.randomUUID(), "foo");
    assertEquals(1, repository.all().size());
    Optional<CardDeck> found = repository.find(created.getId());
    assertTrue(found.isPresent());
    repository.delete(created.getId(), true);
    Optional<CardDeck> deleted = repository.find(created.getId());
    assertFalse(deleted.isPresent());
  }
}