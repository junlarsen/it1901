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
    /** The card decks that are stored on the profile. */
    private final Set<CardDeck> decks;

    /**
     * Create a new profile.
     *
     * @param initialDecks Initial set of card decks to store on the profile
     */
    public Profile(final Set<CardDeck> initialDecks) {
        decks = new HashSet<>(initialDecks);
    }

    /**
     * Add a deck to the profile.
     *
     * @param deck The deck to add
     * @throws IllegalArgumentException If the given card deck (or another deck
     *                                  with the same cards) already exists on
     *                                  this profile
     */
    public void add(final CardDeck deck) throws IllegalArgumentException {
        boolean unique = decks.add(deck);
        if (!unique) {
            throw new IllegalArgumentException(
                "Given card deck already exists on this profile"
            );
        }
    }

    /**
     * Get a copy of the decks.
     *
     * @return A clone of the decks in the profile
     */
    public Set<CardDeck> getDecks() {
        return new HashSet<>(decks);
    }
}
