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
                    "Given card deck already exists on this profile");
        }
    }

    /**
     * Removes deck from Set of decks.
     * 
     * @param deck CardDeck to be deleted
     * @param name String name of the new name
     * @throws IllegalArgumentException If deck isn't an element in list
     * @return true if deck was deleted
     */
    public CardDeck setCardDeckName(
            final CardDeck deck,
            final String name)
            throws IllegalArgumentException {

        CardDeck optDeck = decks.stream()
                .filter(cardDeck -> cardDeck.getName().equals(deck.getName()))
                .findAny()
                .orElse(null);

        if (optDeck != null) {
            optDeck.setName(name);
            return optDeck;
        } else {
            throw new IllegalArgumentException(
                    "Card deck does not exist in profile");
        }
    }

    /**
     * Removes deck from Set of decks.
     * 
     * @param deck CardDeck to be deleted
     * @throws IllegalArgumentException If deck isn't an element in list
     */
    public boolean removeDeck(final CardDeck deck) throws IllegalArgumentException {
        if (!decks.contains(deck))
            throw new IllegalArgumentException("Given deck doesn't exist in decks");
        return decks.remove(deck);
    }

    /**
     * Get a copy of the decks.
     *
     * @return A clone of the decks in the profile
     */
    public Set<CardDeck> getDecks() {
        return new HashSet<>(decks);
    }

    /**
     * Method to edit a card if carddeck exists in profile.
     * 
     * @param deck     CardDeck to edit card in
     * @param card     Card to edit
     * @param question New question
     * @param answer   New answer
     */
    public void editCard(CardDeck deck, Card card, String question, String answer) {
        CardDeck optDeck = decks.stream()
                .filter(cardDeck -> cardDeck.getName().equals(deck.getName()))
                .findAny()
                .orElse(null);

        if (optDeck != null) {
            optDeck.editCard(card, question, answer);
        } else {
            throw new IllegalArgumentException("Card deck does not exist in profile");
        }
    }

    /**
     * Method to edit a carddeck if carddeck exists in profile.
     * 
     * @param deck CardDeck to lose card
     * @param card Card to be deleted
     * 
     * @return
     */
    public boolean deleteCardFromDeck(CardDeck deck, Card card) {

        if (!decks.contains(deck)) {
            throw new IllegalArgumentException("Profile doesn't contains this deck");
        }
        try {
            return deck.deleteCard(card);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }
}
