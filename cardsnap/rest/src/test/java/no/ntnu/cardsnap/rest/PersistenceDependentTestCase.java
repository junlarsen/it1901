package no.ntnu.cardsnap.rest;

import no.ntnu.cardsnap.persistence.DiskJsonModelStorage;
import no.ntnu.cardsnap.persistence.JsonDatabase;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Helper class to make it easier to create a new dummy card snap JsonDatabase
 * instance with a random temp directory (meaning clean database upon each test)
 */
public class PersistenceDependentTestCase {
    private final String TEMPDIR_PATH = System.getProperty("java.io.tmpdir");
    private final Path CARDSNAP_TEST_PATH = Paths.get(TEMPDIR_PATH, "cardsnap-test-directory" + UUID.randomUUID());
    protected final JsonDatabase jdb = new JsonDatabase(new DiskJsonModelStorage(CARDSNAP_TEST_PATH));

    @BeforeEach
    private void beforeEach() {
        CARDSNAP_TEST_PATH.toFile().delete();
    }
}
