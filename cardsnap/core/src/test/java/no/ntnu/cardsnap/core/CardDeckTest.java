package no.ntnu.cardsnap.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardDeckTest {
    @Test
    @DisplayName("it will reject null uuids and names")
    public void testRejectNullParameters() {
        assertThrows(NullPointerException.class, () -> new CardDeck(null, "Foo"));
        assertThrows(NullPointerException.class, () -> new CardDeck(UUID.randomUUID(), null));
    }

    @Test
    @DisplayName("It will reject empty, too long, or too short names")
    public void testValidateName() {
        assertThrows(IllegalArgumentException.class, () -> new CardDeck(UUID.randomUUID(), ""));
        assertThrows(IllegalArgumentException.class, () -> new CardDeck(UUID.randomUUID(), "aa"));
        assertThrows(IllegalArgumentException.class, () -> new CardDeck(UUID.randomUUID(), "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertThrows(IllegalArgumentException.class, () -> new CardDeck(UUID.randomUUID(), " "));
        assertThrows(IllegalArgumentException.class, () -> new CardDeck(UUID.randomUUID(), "\t"));
        assertThrows(IllegalArgumentException.class, () -> new CardDeck(UUID.randomUUID(), "\n"));
        assertThrows(IllegalArgumentException.class, () -> new CardDeck(UUID.randomUUID(), "\r"));
    }

    @Test
    @DisplayName("it will ensure card decks are equal")
    public void testEquality() {
        UUID firstId = UUID.randomUUID();
        UUID secondID = UUID.randomUUID();
        CardDeck base = new CardDeck(firstId, "Foo");
        CardDeck duplicate = new CardDeck(firstId, "Foo");
        assertEquals(base, duplicate);
        CardDeck differentId = new CardDeck(secondID, "Foo");
        assertNotEquals(base, differentId);
        CardDeck differentName = new CardDeck(firstId, "Bar");
        assertNotEquals(base, differentName);
    }
}
