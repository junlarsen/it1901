package no.ntnu.cardsnap.domain;

import java.util.Set;

/**
 * A deck or collection of {@link Card} entities
 * <p>
 * Implemented using a Set to ensure there are no duplicate cards in the
 * deck.
 *
 * @author matsjla
 */
public class CardDeck {
    /**
     * The collection of cards that reside inside this deck
     */
    private final Set<Card> cards;
    /**
     * The textual name the user has given this deck
     */
    private String name;

    /**
     * Create a new Card Deck
     *
     * @param cards Initial set of cards to use in the deck
     * @param name  A textual name for the deck
     */
    public CardDeck(Set<Card> cards, String name) {
        this.cards = cards;
        this.name = name;
    }

    /**
     * Add a card to the deck
     *
     * @param card The card to add
     * @throws IllegalArgumentException If the given card (or another card with
     *                                  the same question and answer) already
     *                                  exists inside this deck
     */
    public void add(Card card) throws IllegalArgumentException {
        boolean unique = cards.add(card);
        if (!unique) {
            throw new IllegalArgumentException("Given card already exists in this deck");
        }
    }

    public Set<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
