package no.ntnu.cardsnap.rest.services;

import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.core.CardDeck;
import no.ntnu.cardsnap.rest.PersistenceDependentTestCase;
import no.ntnu.cardsnap.rest.exceptions.EntityNotFoundException;
import no.ntnu.cardsnap.rest.repositories.CardDeckRepository;
import no.ntnu.cardsnap.rest.repositories.CardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardServiceTest extends PersistenceDependentTestCase {
    private final CardRepository repository = new CardRepository(jdb);
    private final CardService service = new CardService(repository);
    private final CardDeckRepository cardDeckRepository = new CardDeckRepository(jdb);
    private final CardDeckService cardDeckService = new CardDeckService(cardDeckRepository);

    @Test
    @DisplayName("it can create and find new cards")
    public void testCreateNewCard() throws IOException {
        CardDeck owner = cardDeckService.create("Foo");
        assertTrue(service.list(0, owner.getId()).isEmpty());
        Card created = service.create("Q", "A", owner.getId());
        Card found = service.find(created.getId(), owner.getId());
        assertEquals(created, found);
        // It will fail with non existing owner id
        assertThrows(EntityNotFoundException.class, () -> {
            service.create("Q", "A", UUID.randomUUID());
        });
    }

    @Test
    @DisplayName("it can list stored cards")
    public void testListCards() throws IOException {
        CardDeck owner = cardDeckService.create("Foo");
        List<Card> none = service.list(0, owner.getId());
        assertTrue(none.isEmpty());
        service.create("Q", "A", owner.getId());
        assertFalse(service.list(0, owner.getId()).isEmpty());
        // Cannot paginate larger than size
        assertThrows(IllegalArgumentException.class, () -> {
            service.list(1, owner.getId());
        });
    }

    @Test
    @DisplayName("it can update cards")
    public void testUpdateCard() throws IOException {
        CardDeck owner = cardDeckService.create("Foo");
        Card created = service.create("Q", "A", owner.getId());
        Card updated = service.update(created.getId(), "newQ", "newA", owner.getId());
        assertEquals("newQ", updated.getQuestion());
        assertEquals("newA", updated.getAnswer());
        // Cannot update what does not exist
        assertThrows(EntityNotFoundException.class, () -> {
            service.update(UUID.randomUUID(), "zzww", "wejd", owner.getId());
        });
    }

    @Test
    @DisplayName("it can delete cards")
    public void testDeleteCard() throws IOException {
        CardDeck owner = cardDeckService.create("Foo");
        Card created = service.create("AAAA", "BBBB", owner.getId());
        assertEquals(1, service.list(0, owner.getId()).size());
        service.delete(created.getId(), owner.getId());
        assertTrue(service.list(0, owner.getId()).isEmpty());
        assertThrows(EntityNotFoundException.class, () -> {
            service.delete(created.getId(), owner.getId());
        });
    }
}
