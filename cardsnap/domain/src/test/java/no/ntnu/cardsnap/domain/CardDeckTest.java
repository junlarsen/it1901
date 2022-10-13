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
}
