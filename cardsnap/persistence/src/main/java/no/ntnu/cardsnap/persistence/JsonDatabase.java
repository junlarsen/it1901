package no.ntnu.cardsnap.persistence;

import java.io.IOException;
import java.util.function.Function;

/**
 * A thin wrapper on top of {@link AbstractJsonModelStorage} to expose an API
 * of mutations and queries against the Json database.
 */
public class JsonDatabase {
    private final AbstractJsonModelStorage jsonModelStorage;

    public JsonDatabase(final AbstractJsonModelStorage storage) {
        this.jsonModelStorage = storage;
    }

    /**
     * Perform a "database mutation", a transaction that changes some of the
     * {@link JsonModel}.
     *
     * @param consumer The transaction consumer
     * @param <T>      Type of transaction result
     * @return The result from the transaction
     * @throws IOException If underlying I/O error occurred when the storage was
     *                     accessed
     */
    public <T> T mutation(Function<JsonModel, T> consumer) throws IOException {
        JsonModel model = jsonModelStorage.load();
        T result = consumer.apply(model);
        jsonModelStorage.store(model);
        return result;
    }

    /**
     * Perform a "database query", a transaction that does not change mutate
     * the {@link JsonModel}, but instead only reads values from it.
     *
     * @param consumer The transaction consumer
     * @param <T>      Type of transaction result
     * @return The result from the transaction
     * @throws IOException If underlying I/O error occurred when the storage was
     *                     accessed
     */
    public <T> T query(Function<JsonModel, T> consumer) throws IOException {
        JsonModel model = jsonModelStorage.load();
        return consumer.apply(model);
    }
}
