package no.ntnu.cardsnap.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * A profile of an application user.
 * <p>
 * A profile owns a set of {@link CardDeck} objects, each containing a set of
 * {@link CardDeck} objects.
 */
public class Profile {
    private final Set<CardDeck> decks;

    /**
     * Create a new profile
     *
     * @param decks Initial set of card decks to store on the profile
     */
    public Profile(Set<CardDeck> decks) {
        this.decks = new HashSet<>(decks);
    }

    /**
     * Add a deck to the profile
     *
     * @param deck The deck to add
     * @throws IllegalArgumentException If the given card deck (or another deck
     *                                  with the same cards) already exists on
     *                                  this profile
     */
    public void add(CardDeck deck) throws IllegalArgumentException {
        boolean unique = decks.add(deck);
        if (!unique) {
            throw new IllegalArgumentException("Given card deck already exists on this profile");
        }
    }

    public Set<CardDeck> getDecks() {
        return new HashSet<>(decks);
    }
}
