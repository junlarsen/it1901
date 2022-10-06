package no.ntnu.cardsnap.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardDeckTest {
    @Test
    public void testCardAddition() {
        CardDeck deck = new CardDeck(new HashSet<>(), "Foo");
        deck.add(new Card("Hello", "world"));
        assertThrows(IllegalArgumentException.class, () -> {
            // Card uniqueness is defined by question * answer. This should fail
            // because the equals() of the card should match the first one.
            deck.add(new Card("Hello", "world"));
        });
    }
}
