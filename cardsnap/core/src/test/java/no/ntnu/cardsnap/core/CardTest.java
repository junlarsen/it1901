package no.ntnu.cardsnap.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardTest {
    @Test
    @DisplayName("it will reject null uuids, questions, and answers")
    public void testRejectNullParameters() {
        assertThrows(NullPointerException.class, () -> new Card(null, "Front", "Back", UUID.randomUUID()));
        assertThrows(NullPointerException.class, () -> new Card(UUID.randomUUID(), null, "Back", UUID.randomUUID()));
        assertThrows(NullPointerException.class, () -> new Card(UUID.randomUUID(), "Front", null, UUID.randomUUID()));
        assertThrows(NullPointerException.class, () -> new Card(UUID.randomUUID(), "Front", "Back", null));
    }

    @Test
    @DisplayName("it will reject empty questions and answers")
    public void testRejectEmptyQuestionAndAnswer() {
        assertThrows(IllegalArgumentException.class, () -> new Card(UUID.randomUUID(), "", "Back", UUID.randomUUID()));
        assertThrows(IllegalArgumentException.class, () -> new Card(UUID.randomUUID(), "Front", "", UUID.randomUUID()));
        assertThrows(IllegalArgumentException.class, () -> new Card(UUID.randomUUID(), " ", "", UUID.randomUUID()));
        assertThrows(IllegalArgumentException.class, () -> new Card(UUID.randomUUID(), "\n", "", UUID.randomUUID()));
        assertThrows(IllegalArgumentException.class, () -> new Card(UUID.randomUUID(), "\t", "", UUID.randomUUID()));
        assertThrows(IllegalArgumentException.class, () -> new Card(UUID.randomUUID(), "\r", "", UUID.randomUUID()));
    }

    @Test
    @DisplayName("it will ensure cards are equal")
    public void testEquality() {
        UUID firstId = UUID.randomUUID();
        UUID secondID = UUID.randomUUID();
        Card base = new Card(firstId, "Front", "Back", firstId);
        Card duplicate = new Card(firstId, "Front", "Back", firstId);
        assertEquals(base, duplicate);
        Card differentId = new Card(secondID, "Front", "Back", firstId);
        assertNotEquals(base, differentId);
        Card differentFrontBack = new Card(firstId, "Foo", "Bar", firstId);
        assertNotEquals(base, differentFrontBack);
    }
}
