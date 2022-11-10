package no.ntnu.cardsnap.persistence;

import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.core.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiskJsonModelStorageTest {
    private final String TEMPDIR_PATH = System.getProperty("java.io.tmpdir");
    private final Path CARDSNAP_TEST_PATH = Paths.get(TEMPDIR_PATH, "cardsnap-test-directory");

    @Test
    @DisplayName("it will return an empty model if file does not exist")
    public void testWillFindEmpty() throws IOException {
        DiskJsonModelStorage dps = new DiskJsonModelStorage(CARDSNAP_TEST_PATH);
        File expected = dps.getStoragePath().toFile();
        if (expected.exists()) {
            expected.delete();
        }
        assertFalse(expected.exists());
        JsonModel empty = dps.load();
        assertTrue(empty.getDecks().isEmpty());
        expected.deleteOnExit();
    }

    @Test
    @DisplayName("it will store then load models to disk")
    public void testWillStoreAndLoadModel() throws IOException {
        DiskJsonModelStorage dps = new DiskJsonModelStorage(CARDSNAP_TEST_PATH);

        JsonModel model = new JsonModel(
            Set.of(new CardDeck(UUID.randomUUID(), "My little pony")),
            Set.of(new Card(UUID.randomUUID(), "Foo", "Bar", UUID.randomUUID()))
        );

        dps.store(model);
        JsonModel imported = dps.load();
        assertEquals(model.getDecks().size(), imported.getDecks().size());
        assertEquals(model.getCards().size(), imported.getCards().size());
    }
}
