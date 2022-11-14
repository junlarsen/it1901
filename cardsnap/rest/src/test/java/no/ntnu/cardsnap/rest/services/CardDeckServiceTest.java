package no.ntnu.cardsnap.rest.services;

import no.ntnu.cardsnap.core.CardDeck;
import no.ntnu.cardsnap.rest.PersistenceDependentTestCase;
import no.ntnu.cardsnap.rest.exceptions.EntityAlreadyExistsException;
import no.ntnu.cardsnap.rest.exceptions.EntityNotFoundException;
import no.ntnu.cardsnap.rest.repositories.CardDeckRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardDeckServiceTest extends PersistenceDependentTestCase {
  private final CardDeckRepository repository = new CardDeckRepository(jdb);
  private final CardDeckService service = new CardDeckService(repository);

  @Test
  @DisplayName("it can create and find new decks")
  public void testCreateNewDeck() throws IOException {
    assertTrue(service.list(0).isEmpty());
    CardDeck created = service.create("foobar");
    CardDeck found = service.find(created.getId());
    assertEquals(created, found);
    // It must not be able to create two with the same name
    assertThrows(EntityAlreadyExistsException.class, () -> {
      service.create("foobar");
    });
  }

  @Test
  @DisplayName("it can list stored card decks")
  public void testListDecks() throws IOException {
    List<CardDeck> none = service.list(0);
    assertTrue(none.isEmpty());
    service.create("baerd");
    assertFalse(service.list(0).isEmpty());
    // Cannot paginate larger than size
    assertThrows(IllegalArgumentException.class, () -> {
      service.list(1);
    });
  }

  @Test
  @DisplayName("it can update decks")
  public void testUpdateDeck() throws IOException {
    CardDeck created = service.create("foo");
    CardDeck updated = service.update(created.getId(), "new");
    assertEquals("new", updated.getName());
    // Cannot update what does not exist
    assertThrows(EntityNotFoundException.class, () -> {
      service.update(UUID.randomUUID(), "zzww");
    });
  }

  @Test
  @DisplayName("it can delete decks")
  public void testDeleteDeck() throws IOException {
    CardDeck created = service.create("zzz");
    assertEquals(1, service.list(0).size());
    service.delete(created.getId());
    assertTrue(service.list(0).isEmpty());
    assertThrows(EntityNotFoundException.class, () -> {
      service.delete(created.getId());
    });
  }
}
