package no.ntnu.cardsnap.persistence;

import no.ntnu.cardsnap.domain.Counter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiskCounterStorageTest {
    @Test
    public void testStoreAndLoad() throws IOException {
        Counter counter = new Counter(100);
        DiskCounterStorage dsc = new DiskCounterStorage();
        dsc.store(counter);

        Counter loaded = dsc.load();
        assertEquals(100, loaded.getValue());
    }
}

