package no.ntnu.cardsnap.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

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
}
