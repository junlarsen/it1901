package no.ntnu.cardsnap.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardDeckTest {
    @Test
    @DisplayName("it will reject cards that are equal")
    public void testCardAddition() {
        CardDeck deck = new CardDeck(new HashSet<>(), "Foo");
        deck.add(new Card("Hello", "world"));
        assertThrows(IllegalArgumentException.class, () -> {
            // Card uniqueness is defined by question * answer. This should fail
            // because the equals() of the card should match the first one.
            deck.add(new Card("Hello", "world"));
        });
    }

    @Test
    @DisplayName("it can determine if decks are equal")
    public void testDeckEquality() {
        CardDeck first = new CardDeck("foo");
        CardDeck sameName = new CardDeck("foo");
        assertEquals(first, sameName);

        CardDeck twoCards = new CardDeck(Set.of(new Card("a", "b"), new Card("c", "d")), "foo");
        assertNotEquals(first, twoCards);

        CardDeck subsetOfCards = new CardDeck(Set.of(new Card("a", "b")), "foo");
        assertNotEquals(first, subsetOfCards);

        CardDeck twoSameCards = new CardDeck(Set.of(new Card("a", "b"), new Card("c", "d")), "foo");
        assertEquals(twoCards, twoSameCards);
    }

    @Test
    @DisplayName("it can determine if deckname is valid")
    public void testIllegalNames() {
        CardDeck foo = new CardDeck("foo");
        foo.setName("foo");
        assertThrows(IllegalArgumentException.class, () -> {
            foo.setName("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            foo.setName(" ");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            foo.setName(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            foo.setName("This is a very long name that should be invalid when new name is sat");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            foo.setName("0");
        });

    }

    @Test
    @DisplayName("it will test editting cards")
    public void testEditCard() {
        CardDeck deck = new CardDeck("foo");
        Card card = new Card("Hello", "world");
        deck.add(card);

        deck.editCard(card, "greatQuestion", "greatAnswer");
        assertEquals("greatQuestion", card.getQuestion());
        assertEquals("greatAnswer", card.getAnswer());

        assertThrows(IllegalArgumentException.class, () -> {
            deck.editCard(new Card("greatQuestion2", "greatAnswer2"), "fooQuestion", "fooAnswer");
        });

    }

    @Test
    @DisplayName("it can delete card from decks")
    public void testCardDeleteFromDeck() {
        CardDeck deck = new CardDeck("foo");
        Card c = new Card("Q", "A");
        deck.add(c);

        assertTrue(deck.deleteCard(c));
        assertTrue(deck.getCards().isEmpty());

        assertThrows(IllegalArgumentException.class, () -> deck.deleteCard(c));

    }
}
