package no.ntnu.cardsnap.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfileTest {
    @Test
    @DisplayName("it will reject decks that have same name")
    public void testDeckAddition() {
        Profile p = new Profile(new HashSet<>());
        p.add(new CardDeck("foo"));

        assertThrows(IllegalArgumentException.class, () -> {
            p.add(new CardDeck("foo"));
        });
    }

    @Test
    @DisplayName("it will change name of card deck in profile")
    public void testSetCardDeckName() {
        Profile p = new Profile(new HashSet<>());
        CardDeck deck = new CardDeck("foo");

        p.add(deck);
        assertEquals("foo", deck.getName());

        p.setCardDeckName(deck, "new name");
        assertEquals("new name", deck.getName());
        assertEquals(deck, p.setCardDeckName(deck, "new name"));

        assertThrows(IllegalArgumentException.class, () -> {
            p.setCardDeckName(new CardDeck("test"), "foo");
        });

    }

    @Test
    @DisplayName("it will reject deletion of non-existing decks")
    public void testDeckRemoval() {
        Profile p = new Profile(new HashSet<>());
        CardDeck cd = new CardDeck("foo");

        assertThrows(IllegalArgumentException.class, () -> {
            p.removeDeck(cd);
        }, "Deletion of a card that doesn't exist should throw exception");

        p.add(cd);
        p.removeDeck(cd);

        assertFalse(p.getDecks().contains(cd), "CardDeck shouldn't contain the removed deck");
    }
}
