package no.ntnu.cardsnap.persistence;

import no.ntnu.cardsnap.domain.Profile;

import java.io.IOException;

/**
 * Abstract definition of something that can store a counter.
 * <p>
 * Current implementation only allows for storage on user disk, but in the
 * future it might be useful to store this remotely on a server.
 */
public interface AbstractProfileStorage {
    /**
     * Store the in-memory {@link Profile} object to the target destination.
     *
     * @param profile The profile to store
     * @throws IOException If there was some I/O error that occurred while
     *                     attempting to store the profile
     */
    void store(Profile profile) throws IOException;

    /**
     * Load the {@link Profile} from the target destination into memory.
     *
     * @return The profile of the given user
     * @throws IOException If there was some I/O error that occurred while
     *                     attempting to retrieve the profile
     */
    Profile load() throws IOException;
}
