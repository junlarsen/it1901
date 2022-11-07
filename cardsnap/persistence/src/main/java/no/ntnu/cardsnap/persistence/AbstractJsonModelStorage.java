package no.ntnu.cardsnap.persistence;

import java.io.IOException;

/**
 * Abstract definition of something that can store a {@link JsonModel}.
 * <p>
 * Current implementation only allows for storage on user disk, but in the
 * future it might be useful to store this remotely on a server.
 */
public interface AbstractJsonModelStorage {
    /**
     * Store the in-memory {@link JsonModel} object to the target destination.
     *
     * @param model The JsonModel to store
     * @throws IOException If there was some I/O error that occurred while
     *                     attempting to store the model
     */
    void store(JsonModel model) throws IOException;

    /**
     * Load the {@link JsonModel} from the target destination into memory.
     *
     * @return The stored JsonModel
     * @throws IOException If there was some I/O error that occurred while
     *                     attempting to retrieve the model
     */
    JsonModel load() throws IOException;
}
