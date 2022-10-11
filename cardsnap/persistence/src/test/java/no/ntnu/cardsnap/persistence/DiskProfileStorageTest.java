package no.ntnu.cardsnap.persistence;

import no.ntnu.cardsnap.domain.Card;
import no.ntnu.cardsnap.domain.CardDeck;
import no.ntnu.cardsnap.domain.Profile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiskProfileStorageTest {
    private final String TEMPDIR_PATH = System.getProperty("java.io.tmpdir");
    private final Path CARDSNAP_TEST_PATH = Paths.get(TEMPDIR_PATH, "cardsnap-test-directory");

    @Test
    @DisplayName("it will return an empty profile if file does not exist")
    public void testWillFindEmpty() throws IOException {
        DiskProfileStorage dps = new DiskProfileStorage(CARDSNAP_TEST_PATH);
        File expected = dps.getStoragePath().toFile();
        if (expected.exists()) {
            expected.delete();
        }
        assertFalse(expected.exists());
        Profile empty = dps.load();
        assertTrue(empty.getDecks().isEmpty());
        expected.deleteOnExit();
    }

    @Test
    @DisplayName("it will store then load profiles to disk")
    public void testWillStoreAndLoadProfile() throws IOException {
        DiskProfileStorage dps = new DiskProfileStorage(CARDSNAP_TEST_PATH);

        Profile profile = new Profile(new HashSet<>());
        CardDeck deck = new CardDeck(new HashSet<>(), "My test set");
        Card card = new Card("What is love?", "Baby don't hurt me");
        deck.add(card);
        profile.add(deck);

        dps.store(profile);
        Profile imported = dps.load();
        assertEquals(profile.getDecks().size(), imported.getDecks().size());
    }
}

