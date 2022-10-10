package no.ntnu.cardsnap.persistence;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import no.ntnu.cardsnap.domain.Profile;

/**
 * Implementation of {@link AbstractProfileStorage} that stores the
 * counter state onto disk.
 */
public class DiskProfileStorage implements AbstractProfileStorage {
    /**
     * File path where the file will be read/written from
     */
    private final String path;

    /**
     * Gson instance used for serialization/deserialization
     */
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Create a {@link DiskProfileStorage} with a given path
     *
     * @param path The root path to use
     */
    public DiskProfileStorage(String path) {
        this.path = path;
    }

    /**
     * Create a {@link DiskProfileStorage} from a {@link Path} as base
     *
     * @param path The root path to use
     */
    public DiskProfileStorage(Path path) {
        this(path.toString());
    }

    /**
     * Create a DiskCounterStorage with path in user's home directory
     */
    public DiskProfileStorage() {
        this(System.getProperty("user.home"));
    }

    /**
     * Store the {@link Profile} state to the configured path on disk.
     * <p>
     * This will create the file if it does not exist.
     *
     * @param profile The profile to store onto disk
     * @throws IOException If file was unable to be created or written to
     */
    @Override
    public void store(Profile profile) throws IOException {
        File file = getStoragePath().toFile();
        File parent = file.getParentFile();
        if (!parent.exists()) {
            if (!parent.mkdirs()) {
                throw new IOException("failed to create parent directories at path " + parent);
            }
        }

        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("failed to create file at path " + file);
            }
        }

        if (!file.isFile() || !file.canWrite()) {
            throw new IOException("path is not a file, or it is not writable");
        }

        writeProfileJson(profile);
    }

    /**
     * Load the {@link Profile} state from the configured path on disk.
     * <p>
     * This returns an empty profile if the profile did not exist.
     *
     * @return The stored profile, or an empty profile if not existent
     * @throws IOException If the file did exist but was not readable.
     */
    @Override
    public Profile load() throws IOException {
        File file = getStoragePath().toFile();
        if (!file.exists() || file.length() == 0) {
            Profile empty = new Profile(new HashSet<>());
            store(empty);
        }
        if (!file.isFile() || !file.canRead()) {
            throw new IOException("file is not a file, or is not readable");
        }

        return readProfileJson();
    }

    /**
     * Determine the full path of the CardSnap profile Json storage file
     *
     * @return The {@link Path} of the profile file
     */
    public Path getStoragePath() {
        return Paths.get(path, "cardsnap-profile.json");
    }

    /**
     * Write the profile state to the configured path on disk
     *
     * @param profile The profile to serialize
     * @throws IOException If there was an error writing to the file, though
     *                     this should not happen as this method is only called
     *                     by {@link DiskProfileStorage#store(Profile)} which
     *                     will perform IO checks and return more detailed
     *                     errors.
     */
    private void writeProfileJson(Profile profile) throws IOException {
        Files.writeString(getStoragePath(), gson.toJson(profile));
    }

    /**
     * Read the profile state from the configured path on disk
     *
     * @return The deserialized profile
     * @throws IOException If there was an error reading to the file, though
     *                     this should not happen as this method is only called
     *                     by {@link DiskProfileStorage#load()} which will
     *                     perform IO checks and return more detailed errors.
     */
    private Profile readProfileJson() throws IOException {
        String jsonString = Files.readString(getStoragePath(), StandardCharsets.UTF_8);

        return gson.fromJson(jsonString, Profile.class);
    }
}
