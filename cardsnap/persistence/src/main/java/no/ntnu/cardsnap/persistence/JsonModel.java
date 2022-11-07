package no.ntnu.cardsnap.persistence;

import no.ntnu.cardsnap.domain.Card;
import no.ntnu.cardsnap.domain.CardDeck;

import java.util.HashSet;
import java.util.Set;

/**
 * Model type which models something similar to database tables in a typical
 * relational database.
 * <p>
 * In the JsonModel, each {@link Set} represents a table in the database. The
 * type is populated by Gson in the {@link DiskJsonModelStorage} class.
 */
public class JsonModel {
    private final Set<CardDeck> decks;
    private final Set<Card> cards;

    /**
     * No-args constructor for Gson
     */
    public JsonModel() {
        this(new HashSet<>(), new HashSet<>());
    }

    public JsonModel(final Set<CardDeck> decks, final Set<Card> cards) {
        this.decks = decks;
        this.cards = cards;
    }

    public Set<CardDeck> getDecks() {
        return decks;
    }

    public Set<Card> getCards() {
        return cards;
    }
}