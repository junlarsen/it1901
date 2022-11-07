package no.ntnu.cardsnap.rest.repositories;

import no.ntnu.cardsnap.domain.CardDeck;
import no.ntnu.cardsnap.persistence.JsonDatabase;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Concrete implementation of something that can handle read/write operations on
 * a {@link CardDeck} value.
 * <p>
 * Current implementation writes to disk, in the future it might be interesting
 * to store in a relational database.
 */
@Repository
public class CardDeckRepository {
    private final JsonDatabase database;

    /**
     * Create a new CardDeckRepository, backed by the given JsonModelStorage
     *
     * @param jsonDatabase The database to use
     */
    public CardDeckRepository(final JsonDatabase jsonDatabase) {
        this.database = jsonDatabase;
    }

    /**
     * Create a new card deck with the given name.
     *
     * @param name The name to give
     * @return The newly created card deck
     * @throws IOException If an I/O error occurred during creation.
     */
    public CardDeck create(UUID id, String name) throws IOException {
        return database.mutation((jsonModel -> {
            CardDeck deck = new CardDeck(id, name);
            jsonModel.getDecks().add(deck);
            return deck;
        }));
    }

    /**
     * Update the name of a card deck.
     *
     * @param id      The UUID of the card deck to update
     * @param newName The new name for the card deck
     * @return The updated card deck
     * @throws IOException If an I/O error occurred during creation.
     */
    public Optional<CardDeck> update(UUID id, String newName) throws IOException {
        Optional<CardDeck> match = find(id);
        if (match.isEmpty()) {
            return Optional.empty();
        }
        delete(id);
        return Optional.of(create(id, newName));
    }

    /**
     * Get all the card decks.
     *
     * @return All the decks
     * @throws IOException If an I/O error occurred during creation.
     */
    public Set<CardDeck> all() throws IOException {
        return database.query((jsonModel -> new HashSet<>(jsonModel.getDecks())));
    }

    /**
     * Find a card deck by a given id.
     *
     * @param id The UUID of the card deck to find
     * @return The card deck, if it exists
     * @throws IOException If an I/O error occurred during creation.
     */
    public Optional<CardDeck> find(UUID id) throws IOException {
        return database.query((jsonModel -> jsonModel
            .getDecks()
            .stream()
            .filter((deck) -> deck.getId().equals(id))
            .findFirst()
        ));
    }

    /**
     * Delete a card deck with a given id.
     * <p>
     * If the deck does not exist, nothing happens.
     *
     * @param id The UUID of the card deck to delete
     * @throws IOException If an I/O error occurred during creation.
     */
    public void delete(UUID id) throws IOException {
        database.mutation((jsonModel -> jsonModel
            .getDecks()
            .removeIf((deck) -> deck.getId().equals(id))
        ));
        // Cascade deletion on the cards
        database.mutation((jsonModel -> jsonModel
            .getCards()
            .removeIf((card) -> card.getOwner().equals(id))
        ));
    }
}
