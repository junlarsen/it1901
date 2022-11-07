package no.ntnu.cardsnap.persistence;

import no.ntnu.cardsnap.domain.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonDatabaseTest {
    private final String TEMPDIR_PATH = System.getProperty("java.io.tmpdir");
    private final Path CARDSNAP_TEST_PATH = Paths.get(TEMPDIR_PATH, "cardsnap-test-directory" + UUID.randomUUID());

    @BeforeEach
    public void beforeEach() {
        CARDSNAP_TEST_PATH.toFile().deleteOnExit();
    }

    @Test
    @DisplayName("it stores the mutated state back, returning the derived state when refetched")
    public void testCanStoreMutations() throws IOException {
        JsonDatabase jdb = new JsonDatabase(new DiskJsonModelStorage(CARDSNAP_TEST_PATH));
        JsonModel baseModel = jdb.query(m -> m);
        assertTrue(baseModel.getCards().isEmpty());
        assertTrue(baseModel.getDecks().isEmpty());
        Card subject = new Card(UUID.randomUUID(), "Foo", "Bar", UUID.randomUUID());
        // Add a new card through a mutation
        jdb.mutation(model -> model.getCards().add(subject));
        JsonModel mutatedModel = jdb.query(m -> m);
        assertEquals(1, mutatedModel.getCards().size());
        assertTrue(mutatedModel.getCards().stream().anyMatch(s -> s.getId().equals(subject.getId())));
    }
}
