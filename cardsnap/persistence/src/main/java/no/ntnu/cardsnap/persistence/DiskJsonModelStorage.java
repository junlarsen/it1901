package no.ntnu.cardsnap.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

/**
 * Implementation of {@link AbstractJsonModelStorage} that stores the
 * JsonModel state onto disk.
 */
public class DiskJsonModelStorage implements AbstractJsonModelStorage {
  /**
   * File path where the file will be read/written from.
   */
  private final String path;

  /**
   * Gson instance used for serialization/deserialization.
   */
  private final Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();

  /**
   * Create a {@link DiskJsonModelStorage} with a given path.
   *
   * @param rootPath The root path to use
   */
  public DiskJsonModelStorage(String rootPath) {
    path = rootPath;
    System.out.println(
        "info: initialized DiskJsonModelStroage in directory " + rootPath
    );
  }

  /**
   * Create a {@link DiskJsonModelStorage} from a {@link Path} as base.
   *
   * @param rootPath The root path to use
   */
  public DiskJsonModelStorage(Path rootPath) {
    this(rootPath.toAbsolutePath().toString());
  }

  /**
   * Create a DiskCounterStorage with path in user's home directory.
   */
  public DiskJsonModelStorage() {
    this(System.getProperty("user.home"));
  }

  /**
   * Store the {@link DiskJsonModelStorage} state to the configured path on disk.
   *
   * <p>This will create the file if it does not exist.
   *
   * @param model The JsonModel to store onto disk
   * @throws IOException If file was unable to be created or written to
   */
  @Override
  public void store(JsonModel model) throws IOException {
    File file = getStoragePath().toFile();
    File parent = file.getParentFile();
    if (!parent.exists()) {
      if (!parent.mkdirs()) {
        throw new IOException(
            "failed to create parent directories at path " + parent
        );
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

    writeModelJson(model);
  }

  /**
   * Load the {@link JsonModel} state from the configured path on disk.
   *
   * <p>This returns an empty JsonModel if the model did not exist.
   *
   * @return The stored JsonModel, or an empty JsonModel if not existent
   * @throws IOException If the file did exist but was not readable.
   */
  @Override
  public JsonModel load() throws IOException {
    File file = getStoragePath().toFile();
    if (!file.exists() || file.length() == 0) {
      JsonModel empty = new JsonModel(new HashSet<>(), new HashSet<>());
      store(empty);
    }
    if (!file.isFile() || !file.canRead()) {
      throw new IOException("file is not a file, or is not readable");
    }

    return readModelJson();
  }

  /**
   * Determine the full path of the CardSnap JsonModel storage file.
   *
   * @return The {@link Path} of the JsonModel file
   */
  public Path getStoragePath() {
    return Paths.get(path, "cardsnap-model.json");
  }

  /**
   * Write the JsonModel state to the configured path on disk.
   *
   * @param model The JsonModel to serialize
   * @throws IOException If there was an error writing to the file, though
   *                     this should not happen as this method is only called
   *                     by {@link DiskJsonModelStorage#store(JsonModel)}
   *                     which will perform IO checks and return more detailed
   *                     errors.
   */
  private void writeModelJson(JsonModel model) throws IOException {
    Files.writeString(getStoragePath(), gson.toJson(model));
  }

  /**
   * Read the model state from the configured path on disk.
   *
   * @return The deserialized JsonModel
   * @throws IOException If there was an error reading to the file, though
   *                     this should not happen as this method is only called
   *                     by {@link DiskJsonModelStorage#load()} which will
   *                     perform IO checks and return more detailed errors.
   */
  private JsonModel readModelJson() throws IOException {
    String jsonString = Files.readString(
        getStoragePath(),
        StandardCharsets.UTF_8
    );

    return gson.fromJson(jsonString, JsonModel.class);
  }
}
