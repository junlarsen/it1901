package no.ntnu.cardsnap.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A deck or collection of {@link Card} entities.
 * <p>
 * Implemented using a Set to ensure there are no duplicate cards in the
 * deck.
 *
 * @author matsjla
 */
public final class CardDeck {
    /**
     * The collection of cards that reside inside this deck.
     */
    private final Set<Card> cards;
    /**
     * The textual name the user has given this deck.
     */
    private String name;

    /**
     * The maximum of characters allowed in a deck name.
     */
    private int maxLengthName = 20;

    /**
     * The minimum of characters allowed in a deck name.
     */
    private int minLengthName = 3;

    /**
     * Create a new Card Deck.
     *
     * @param initialCards Initial set of cards to use in the deck
     * @param deckName     A textual name for the deck
     */
    public CardDeck(final Set<Card> initialCards, final String deckName) {
        this.cards = new HashSet<>(initialCards);
        setName(deckName);
    }

    /**
     * Create a new Card Deck.
     *
     * @param deckName A textual name for the deck
     */
    public CardDeck(final String deckName) {
        this(new HashSet<>(), deckName);
    }

    /**
     * Add a card to the deck.
     *
     * @param card The card to add
     * @throws IllegalArgumentException If the given card (or another card with
     *                                  the same question and answer) already
     *                                  exists inside this deck
     */
    public void add(final Card card) throws IllegalArgumentException {
        boolean unique = cards.add(card);
        if (!unique) {
            throw new IllegalArgumentException(
                    "Given card already exists in this deck");
        }
    }

    /**
     * Get a copy of the cards.
     *
     * @return A copy of the cards
     */
    public Set<Card> getCards() {
        return new HashSet<>(cards);
    }

    /**
     * Get the card deck name.
     *
     * @return The deck name
     */
    public String getName() {
        return name;
    }

    /**
     * Give the deck a new name.
     *
     * @param newName The new name
     * @throws IllegalArgumentException If the given name is illegal
     */
    public void setName(final String newName) throws IllegalArgumentException {
        if (newName == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (newName.equals(name)) {
            return;
        }
        if (newName.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (newName.length() > maxLengthName) {
            throw new IllegalArgumentException(
                    "Name cannot be longer than 20 characters");
        }
        if (newName.length() < minLengthName) {
            throw new IllegalArgumentException(
                    "Name must be longer than 2 characters");
        }
        this.name = newName;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CardDeck deck)) {
            return false;
        }
        return name.equals(deck.name)
                && getCards().containsAll(deck.getCards())
                && deck.getCards().containsAll(getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, name);
    }
}
