package no.ntnu.cardsnap.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    @DisplayName("it will test edditing existing cards")
    public void testEditCard() {
        Profile p = new Profile(new HashSet<>());
        CardDeck cd = new CardDeck("foo");
        Card c = new Card("greatQuestion", "greatAnswer");

        p.add(cd);
        cd.add(c);

        p.editCard(cd, c, "greatQuestion2", "greatAnswer2");
        assertEquals("greatQuestion2", c.getQuestion());
        assertEquals("greatAnswer2", c.getAnswer());

        assertThrows(IllegalArgumentException.class, () -> {
            p.editCard(new CardDeck("newDeck"), new Card("foo", "bar"), "greatQuestion", "greatAnswer");
        }, "Editing a card in a cardDeck profile doesn't have should throw exception");

    }

    @Test
    @DisplayName("deck delete working")
    public void testDeckDelete() {
        Profile p = new Profile(new HashSet<>());
        CardDeck d = new CardDeck("foo");
        Card c = new Card("Q", "A");
        d.add(c);

        p.add(d);

        CardDeck d2 = new CardDeck("bar");
        d2.add(c);

        assertTrue(p.deleteCardFromDeck(d, c));
        assertThrows(IllegalArgumentException.class, () -> p.deleteCardFromDeck(d, c));
        assertThrows(IllegalArgumentException.class, () -> p.deleteCardFromDeck(d2, c));

    }
}
