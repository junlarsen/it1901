package no.ntnu.cardsnap.core;

import no.ntnu.cardsnap.domain.Card;
import no.ntnu.cardsnap.domain.CardDeck;

import java.io.IOException;

import no.ntnu.cardsnap.domain.Profile;
import no.ntnu.cardsnap.persistence.AbstractProfileStorage;

/**
 * Service that is capable of manipulating {@link CardDeck} instances stored in
 * a {@link Profile}
 *
 * @author matsjla
 */
public class ProfileService {
    /**
     * Storage implementation that is capable of reading and writing
     * {@link Profile} objects to state.
     */
    private final AbstractProfileStorage storage;

    /**
     * Create a new {@link ProfileService}
     *
     * @param storage The storage implementation to use
     */
    public ProfileService(AbstractProfileStorage storage) {
        this.storage = storage;
    }

    /**
     * Load the current profile from storage
     *
     * @return The profile that was stored on the users computer
     * @throws IllegalArgumentException When I/O error occurs during load
     */
    public Profile load() {
        try {
            return storage.load();
        } catch (IOException e) {
            throw new IllegalStateException(
                "IO error occurred during state load",
                e
            );
        }
    }

    /**
     * Store a given profile to storage, overwriting the existing one
     *
     * @param profile The profile to store
     * @throws IllegalArgumentException When I/O error occurs during load
     */
    public void store(Profile profile) {
        try {
            storage.store(profile);
        } catch (IOException e) {
            throw new IllegalStateException(
                "IO error occurred during state load",
                e
            );
        }
    }

    /**
     * Create a new card in a given deck with a given question and answer
     *
     * @param profile  The profile that owns the deck to add to
     * @param deck     The deck to add a card to
     * @param question The wanted question
     * @param answer   The wanted answer
     * @return The newly created card
     * @throws IllegalArgumentException If a card with the given question and
     *                                  answer already exists
     */
    public Card create(Profile profile, CardDeck deck, String question, String answer) {
        boolean exists = profile.getDecks().contains(deck) && deck.getCards()
            .stream()
            .anyMatch(card -> card.getAnswer().equals(answer) && card.getQuestion().equals(question));
        if (exists) {
            throw new IllegalArgumentException(
                "Card with given question and answer already exists in the deck"
            );
        }
        Card card = new Card(question, answer);
        deck.add(card);
        store(profile);
        return card;
    }

    /**
     * Create a new card deck with a given name
     *
     * @param profile The profile to add a deck to
     * @param name    The name for the deck
     * @return The newly created card deck
     * @throws IllegalArgumentException If a deck with the given name exists
     */
    public CardDeck create(Profile profile, String name) {
        boolean exists = profile.getDecks()
            .stream()
            .anyMatch(deck -> deck.getName().equals(name));
        if (exists) {
            throw new IllegalArgumentException(
                "Card deck with given name already exists"
            );
        }
        CardDeck deck = new CardDeck(name);
        profile.add(deck);
        store(profile);
        return deck;
    }
}
