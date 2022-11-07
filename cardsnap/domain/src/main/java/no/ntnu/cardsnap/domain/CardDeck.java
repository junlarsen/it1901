package no.ntnu.cardsnap.domain;

import java.util.Objects;
import java.util.UUID;

/**
 * A deck or collection of {@link Card} entities.
 * <p>
 * Implemented using a Set to ensure there are no duplicate cards in the
 * deck.
 *
 * @author matsjla
 */
public class CardDeck {
    private final UUID id;
    private final String name;

    /**
     * No-args constructor for Gson
     */
    private CardDeck() {
        this(UUID.randomUUID(), "card_deck");
    }

    /**
     * Create a new Card Deck.
     *
     * @param id   Unique UUID for the card deck
     * @param name A textual name for the deck
     * @throws IllegalArgumentException If name is not a valid deck name
     * @throws NullPointerException     If id or name is null
     */
    public CardDeck(final UUID id, final String name) {
        this.id = id;
        this.name = name;
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        if (name.isBlank() || name.length() > 32 || name.length() < 3) {
            throw new IllegalArgumentException(
                "Deck name cannot be blank, shorter than 3, or longer than 32 characters"
            );
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof CardDeck deck) {
            return getId().equals(deck.getId())
                && getName().equals(deck.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
