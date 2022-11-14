package no.ntnu.cardsnap.rest.repositories;

import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.persistence.JsonDatabase;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Concrete implementation of something that can handle read/write operations on
 * a {@link Card} value.
 * <p>
 * Current implementation writes to disk, in the future it might be interesting
 * to store in a relational database.
 */
@Repository
public class CardRepository {
    private final JsonDatabase database;

    /**
     * Create a new CardRepository, backed by the given JsonModelStorage.
     *
     * @param jsonDatabase The database to use
     */
    public CardRepository(final JsonDatabase jsonDatabase) {
        this.database = jsonDatabase;
    }

    /**
     * Create a new card with the given name.
     *
     * @param id       The UUID to assign to the card
     * @param question The front of the card
     * @param answer   The back of the card
     * @param owner    The UUID of the deck it should belong to
     * @return The newly created card
     * @throws IOException              If an I/O error occurred during creation.
     * @throws IllegalArgumentException If the owner deck does not exist
     */
    public Card create(UUID id, String question, String answer, UUID owner) throws IOException {
        boolean parentExists = database.query((jsonModel) -> jsonModel.getDecks()
            .stream()
            .anyMatch((deck) -> deck.getId().equals(owner)));
        if (!parentExists) {
            throw new IllegalArgumentException("Owner with ID " + owner + "does not exist");
        }

        return database.mutation((jsonModel) -> {
            Card card = new Card(id, question, answer, owner);
            jsonModel.add(card);
            return card;
        });
    }

    /**
     * Update the question and answer of a card.
     *
     * @param id       The UUID of the card to update
     * @param question The new question
     * @param answer   The new answer
     * @param owner    UUID of deck to update card in
     * @return The updated card
     * @throws IOException If an I/O error occurred during creation.
     */
    public Optional<Card> update(UUID id, String question, String answer, UUID owner) throws IOException {
        Optional<Card> match = find(id, owner);
        if (match.isEmpty()) {
            return Optional.empty();
        }
        delete(id, owner);
        return Optional.of(create(id, question, answer, match.get().getOwner()));
    }

    /**
     * Get all the cards.
     *
     * @param owner UUID of deck to list cards for
     * @return All the cards
     * @throws IOException If an I/O error occurred during creation.
     */
    public Set<Card> all(UUID owner) throws IOException {
        return database.query(jsonModel -> jsonModel
            .getCards()
            .stream()
            .filter((card) -> card.getOwner().equals(owner))
            .collect(Collectors.toSet()));
    }

    /**
     * Find a card by a given id.
     *
     * @param owner UUID of deck to find cards in
     * @param id    The UUID of the card to find
     * @return The card, if it exists
     * @throws IOException If an I/O error occurred during creation.
     */
    public Optional<Card> find(UUID id, UUID owner) throws IOException {
        return database.query((jsonModel -> jsonModel
            .getCards()
            .stream()
            .filter((card) -> card.getId().equals(id) && card.getOwner().equals(owner))
            .findFirst()
        ));
    }

    /**
     * Delete a card with a given id.
     * <p>
     * If the card does not exist, nothing happens.
     *
     * @param owner UUID of deck to delete card in
     * @param id    The UUID of the card to delete
     * @throws IOException If an I/O error occurred during creation.
     */
    public void delete(UUID id, UUID owner) throws IOException {
        Optional<Card> card = find(id, owner);
        if (card.isEmpty()) {
            return;
        }
        database.mutation((jsonModel -> {
            jsonModel.remove(card.get());
            return null;
        }));
    }
}
