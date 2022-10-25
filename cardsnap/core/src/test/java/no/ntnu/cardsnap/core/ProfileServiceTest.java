package no.ntnu.cardsnap.core;

import no.ntnu.cardsnap.domain.Card;
import no.ntnu.cardsnap.domain.CardDeck;
import no.ntnu.cardsnap.domain.Profile;
import no.ntnu.cardsnap.persistence.DiskProfileStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileServiceTest {
    private final DiskProfileStorage storage = new DiskProfileStorage("/tmp/cardsnap-test");
    private final ProfileService service = new ProfileService(storage);

    @AfterEach
    public void setup() {
        storage.getStoragePath().toFile().delete();
    }

    @Test
    @DisplayName("it can load an existing profile from storage")
    public void testLoadFromService() {
        Profile p = service.load();
        assertNotNull(p);

        // Cause circumstance under which storage.load will fail
        storage.getStoragePath().toFile().setReadable(false);
        assertThrows(IllegalStateException.class, service::load);
    }

    @Test
    @DisplayName("it can store existing profiles to storage")
    public void testStoreToStorage() {
        Profile p = service.load();
        service.store(p);

        // Cause circumstance under which storage.store will fail
        storage.getStoragePath().toFile().setWritable(false);
        assertThrows(IllegalStateException.class, () -> service.store(p));
    }

    @Test
    @DisplayName("it can create new card decks in a profile")
    public void testCreateDeckInProfile() {
        Profile p = service.load();
        CardDeck created = service.create(p, "New Deck");
        assertEquals("New Deck", created.getName());

        assertThrows(IllegalArgumentException.class, () -> service.create(p, "New Deck"));
    }

    @Test
    @DisplayName("it can create new cards in a deck")
    public void testCreateCardInDeckInProfile() {
        Profile p = service.load();
        CardDeck root = service.create(p, "root");
        Card created = service.create(p, root, "foo", "bar");

        assertEquals("foo", created.getQuestion());
        assertEquals("bar", created.getAnswer());

        assertDoesNotThrow(() -> service.create(p, root, "foo2", "bar2"));
        assertDoesNotThrow(() -> service.create(p, root, "foo", "bar2"));
        assertDoesNotThrow(() -> service.create(p, root, "foo2", "bar"));

        assertThrows(IllegalArgumentException.class, () -> service.create(p, root, "foo", "bar"));
    }

    @Test
    @DisplayName("it can change name of card in deck")
    public void testChangeCardName() {
        Profile p = service.load();
        CardDeck root = service.create(p, "root");

        service.setCardDeckName(p, root, "new name");
        assertEquals("new name", root.getName());
        assertEquals(root, service.setCardDeckName(p, root, "new name"));

        assertThrows(IllegalArgumentException.class,
                () -> service.setCardDeckName(p, new CardDeck("foo"), "new name2"));

    }

    @Test
    @DisplayName("it can delete a CardDeck from Profile")
    public void testRemoveDeckFromProfile() {
        Profile p = service.load();
        CardDeck created = service.create(p, "New Deck");

        service.removeDeck(p, created);
        assertFalse(p.getDecks().contains(created), "Profile shouldn't contain deck after deck is removed");

        assertThrows(IllegalArgumentException.class, () -> service.removeDeck(p, created),
                "Removal of deck that doesn't exists should throw exception");
    }

    @Test
    @DisplayName("it can change a card in a deck")
    public void testEditCard() {
        Profile p = service.load();
        CardDeck root = service.create(p, "root");
        Card created = service.create(p, root, "foo", "bar");

        service.editCard(p, root, created, "newFoo", "newBar");

        assertEquals("newFoo", created.getQuestion());
        assertEquals("newBar", created.getAnswer());

        assertThrows(IllegalArgumentException.class,
                () -> service.editCard(p, root, new Card("foo", "bar"), "newFoo", "newBar"),
                "Editing a card that doesn't exist should throw exception");
    }

    @Test
    @DisplayName("it can delete card from card deck")
    public void testDeleteCardFromDeckInProfile() {
        Profile p = service.load();
        CardDeck root = service.create(p, "root");
        Card created = service.create(p, root, "foo", "random");

        assertTrue(root.getCards().contains(created));

        assertTrue(service.deleteCardFromDeckInProfile(p, root, created));

    }
}
