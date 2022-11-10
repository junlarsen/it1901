package no.ntnu.cardsnap.core;

import java.util.Objects;
import java.util.UUID;

/**
 * A card deck, which holds onto a collection of {@link Card} entities.
 * <p>
 * While it might seem obvious to have a {@link java.util.List} or a
 * {@link java.util.Set} holding onto some {@link Card} items on this class,
 * we've decided to model the relation the other way around. That is why there
 * exists a {@link Card#getOwner()} property which will return the corresponding
 * {@link CardDeck#getId()} the card belongs to.
 */
public final class CardDeck {
    private final UUID id;
    private final String name;

    /**
     * The maximum length a card deck name may have.
     */
    private static final int MAX_NAME_LENGTH = 32;

    /**
     * The minimal length a card deck name may have.
     */
    private static final int MIN_NAME_LENGTH = 3;

    /**
     * No-args constructor for Gson.
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
        if (name.isBlank()
            || name.length() > MAX_NAME_LENGTH
            || name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(
                "Deck name cannot be blank, shorter than 3"
                    + ", or longer than 32 characters"
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
