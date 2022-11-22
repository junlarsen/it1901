package no.ntnu.cardsnap.persistence;

import java.util.HashSet;
import java.util.Set;
import no.ntnu.cardsnap.types.Card;
import no.ntnu.cardsnap.types.CardDeck;

/**
 * Model type which models something similar to database tables in a typical
 * relational database.
 *
 * <p>In the JsonModel, each {@link Set} represents a table in the database. The
 * type is populated by Gson in the {@link DiskJsonModelStorage} class.
 */
public class JsonModel {
  private final Set<CardDeck> decks;
  private final Set<Card> cards;

  /**
   * No-args constructor for Gson.
   */
  public JsonModel() {
    this(new HashSet<>(), new HashSet<>());
  }

  public JsonModel(Set<CardDeck> decks, Set<Card> cards) {
    this.decks = decks;
    this.cards = cards;
  }

  /**
   * Add a deck to the model.
   *
   * @param deck The deck to add
   */
  public void add(CardDeck deck) {
    decks.add(deck);
  }

  /**
   * Add a card to the model.
   *
   * @param card The card to add
   */
  public void add(Card card) {
    cards.add(card);
  }

  /**
   * Remove a deck from the model.
   *
   * @param deck The deck to remove
   */
  public void remove(CardDeck deck) {
    decks.remove(deck);
  }

  /**
   * Remove a card from the model.
   *
   * @param card The card to remove
   */
  public void remove(Card card) {
    cards.remove(card);
  }

  /**
   * Get a copy of the stored decks.
   *
   * @return The set of decks
   */
  public Set<CardDeck> getDecks() {
    return new HashSet<>(decks);
  }

  /**
   * Get a copy of the stored cards.
   *
   * @return The set of cards
   */
  public Set<Card> getCards() {
    return new HashSet<>(cards);
  }
}