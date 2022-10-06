package no.ntnu.cardsnap.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import no.ntnu.cardsnap.domain.Card;
import no.ntnu.cardsnap.domain.CardDeck;

public class Controller {

    @FXML
    private GridPane deckList;

    @FXML
    private Button newDeck;

    private int cardDeckStartIndex;

    private List<CardDeck> cardDecks;

    @FXML
    private void initialize() {
        loadCardDecks();
        updateList();
    }

    /**
     * Adds card-decks to cardDecks
     * TODO load carddecks from storage and add all to cardDecks
     */
    private void loadCardDecks() {
        cardDecks = new ArrayList<>();

        Set<Card> cards = new HashSet<>();
        cards.add(new Card("Question1", "Answer1"));
        cards.add(new Card("Question2", "Answer2"));

        Set<Card> cards2 = new HashSet<>();
        cards2.add(new Card("Question1a", "Answer1a"));
        cards2.add(new Card("Question2a", "Answer2a"));

        CardDeck tmp = new CardDeck(cards, "TestDeck");
        CardDeck tmp2 = new CardDeck(cards2, "TestDeck2");

        cardDecks.add(tmp);
        cardDecks.add(tmp2);

    }

    @FXML
    private void createDeck() {
        // TODO change view to add deck
    }

    /**
     * Updates list of carddecks. Display four decks at a time with data about each
     * deck
     */
    private void updateList() {

        int endIndex = cardDeckStartIndex + 4;

        if (endIndex > cardDecks.size()) // Sets index to last element in list if size is less than index
            endIndex = cardDecks.size();

        for (int i = cardDeckStartIndex; i < endIndex; i++) {
            deckList.add(new Text(cardDecks.get(i).getName()), 0, i);
            deckList.add(new Text(cardDecks.get(i).getCards().size() + " cards"), 1, i);
            deckList.add(createButton("Play", cardDecks.get(i)), 2, i);

            deckList.add(createButton("+", cardDecks.get(i)), 3, i);
        }

    }

    /**
     * Creates a button with styleclasses, text and eventListener on click
     * 
     * @return Button with style and text
     */
    private Button createButton(String text, CardDeck cardDeck) {
        Button res = new Button(text);
        if (text.equals("Play")) {
            res.getStyleClass().add("playButton");
            res.setOnAction((event) -> handlePlayDeck(cardDeck));

        } else if (text.equals("+")) {
            res.getStyleClass().add("addCardButton");
            res.setOnAction((event) -> handleAddCard(cardDeck));
        }
        res.getStyleClass().add("pressButton");
        return res;
    }

    /**
     * Method to display view to add a new card to deck
     */
    private void handleAddCard(CardDeck cardDeck) {
        // TODO change view to display add card
    }

    /**
     * Method to display view to add a new deck of cards
     */
    private void handlePlayDeck(CardDeck cardDeck) {
        // TODO change view to display create deck
    }

}
