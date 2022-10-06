package no.ntnu.cardsnap.persistence;

import no.ntnu.cardsnap.domain.Counter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Implementation of {@link AbstractCounterStorage} that stores the
 * counter state onto disk.
 */
public class DiskCounterStorage implements AbstractCounterStorage {
    /**
     * File path where the file will be read/written from
     */
    private final String path;

    /**
     * Gson instance used for serialization/deserialization
     */
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Create a DiskCounterStorage with a given path
     */
    public DiskCounterStorage(String path) {
        this.path = path;
    }

    /**
     * Create a DiskCounterStorage with path in user's home directory
     */
    public DiskCounterStorage() {
        this(System.getProperty("user.home"));
    }

    /**
     * Store the counter state to the configured path on disk.
     * <p>
     * This will create the file if it does not exist.
     *
     * @param counter state to store
     * @throws IOException if file could not be written
     */
    @Override
    public void store(Counter counter) throws IOException {
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
        
        writeCounterJson(counter);
    }

    @Override
    public Counter load() throws IOException {
        File file = getStoragePath().toFile();
        if (!file.exists() || file.length() == 0) {
            store(new Counter(0));
        }
        if (!file.isFile() || !file.canRead()) {
            throw new IOException("file is not a file, or is not readable");
        }

        return readCounterJson(); 
    }

    /**
     * Write the counter state to the configured path on disk
     * 
     * @param counter
     * @throws IOException
     */
    private void writeCounterJson(Counter counter) throws IOException { 
        Files.write(getStoragePath(), gson.toJson(counter).getBytes(StandardCharsets.UTF_8));
    }  
    
    /**
     * Read the counter state from the configured path on disk
     * 
     * @return
     * @throws IOException
     */
    private Counter readCounterJson() throws IOException {
        String jsonString = Files.readString(getStoragePath(), StandardCharsets.UTF_8);

        Counter counter = gson.fromJson(jsonString, Counter.class);  
        
        return counter;
    } 

    private Path getStoragePath() {
        return Paths.get(path, "cardsnap.json");
    }
}
