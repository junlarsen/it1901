package no.ntnu.cardsnap;

import java.io.IOException;

/**
 * Abstract definition of something that can store a counter.
 *
 * Current implementation only allows for storage on user disk, but in the
 * future it might be useful to store this remotely on a server.
 */
public interface AbstractCounterStorage {
    void store(Counter counter) throws IOException;
    Counter load() throws IOException;
}
