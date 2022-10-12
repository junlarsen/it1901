package no.ntnu.cardsnap.domain;

import java.util.HashSet;
import java.util.Objects;
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
        this.cards = new HashSet<>(cards);
        this.name = name;
    }

    /**
     * Create a new Card Deck
     *
     * @param name A textual name for the deck
     */
    public CardDeck(String name) {
        this(new HashSet<>(), name);
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
        return new HashSet<>(cards);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CardDeck deck)) {
            return false;
        }
        return name.equals(deck.name) &&
            getCards().containsAll(deck.getCards()) &&
            deck.getCards().containsAll(getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, name);
    }
}
