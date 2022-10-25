package no.ntnu.cardsnap.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("it will test initializing a card with valid values")
    public void testValidCard() {
        Card c = new Card("fooQuestion", "fooAnswer");
        assertEquals("fooAnswer", c.getAnswer());
        assertEquals("fooQuestion", c.getQuestion());
    }

    @Test
    @DisplayName("it will test initializing a card with invalid values")
    public void testInvalidCard() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Card(null, "fooAnswer");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Card("", "fooAnswer");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Card("fooQuestion", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Card("fooQuestion", "");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Card(null, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Card("", "");
        });

    }

}
