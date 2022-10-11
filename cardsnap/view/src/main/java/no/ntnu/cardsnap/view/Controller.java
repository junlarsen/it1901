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
    private Button newDeck, prevPage, nextPage;

    private int cardDeckStartIndex;

    private List<CardDeck> cardDecks;

    @FXML
    private void initialize() {
        loadCardDecks();
        updateDecklist();
        stylePageButtons();
    }

    /**
     * Adds card-decks to cardDecks
     * TODO load carddecks from storage and add all to cardDecks
     */
    private void loadCardDecks() {
        cardDecks = new ArrayList<>();

        // TODO delete all this testdata later

        Set<Card> cards = new HashSet<>();
        cards.add(new Card("Question1", "Answer1"));
        cards.add(new Card("Question2", "Answer2"));

        Set<Card> cards2 = new HashSet<>();
        cards2.add(new Card("Question1a", "Answer1a"));
        cards2.add(new Card("Question2a", "Answer2a"));

        Set<Card> cards3 = new HashSet<>();
        cards3.add(new Card("Question1b", "Answer1b"));
        cards3.add(new Card("Question2b", "Answer2b"));

        Set<Card> cards4 = new HashSet<>();
        cards4.add(new Card("Question1c", "Answer1c"));
        cards4.add(new Card("Question2c", "Answer2c"));

        Set<Card> cards5 = new HashSet<>();
        cards5.add(new Card("Question1d", "Answer1d"));
        cards5.add(new Card("Question2d", "Answer2d"));

        CardDeck tmp = new CardDeck(cards, "TestDeck");
        CardDeck tmp2 = new CardDeck(cards2, "TestDeck2");
        CardDeck tmp3 = new CardDeck(cards2, "TestDeck3");
        CardDeck tmp4 = new CardDeck(cards2, "TestDeck4");
        CardDeck tmp5 = new CardDeck(cards2, "TestDeck5");

        cardDecks.add(tmp);
        cardDecks.add(tmp2);
        cardDecks.add(tmp3);
        cardDecks.add(tmp4);
        cardDecks.add(tmp5);

    }

    @FXML
    private void createDeck() {
        // TODO change view to add deck
    }

    /**
     * Adds 4 to cardDecksStartIndex if there is enough elements in list. Calls on
     * updateDeckList() to update the content of the list
     */
    @FXML
    private void handleNextPage() {
        if (cardDeckStartIndex < cardDecks.size()) {
            cardDeckStartIndex += 4;
            updateDecklist();
        }
        stylePageButtons();
    }

    /**
     * Reduces cardDeckStartIndex by 4 if the index is greater than 4. Calls on
     * updateDeckList() to update the content of the list.
     */
    @FXML
    private void handlePrevPage() {
        if (cardDeckStartIndex >= 4) {
            cardDeckStartIndex -= 4;
            updateDecklist();
        }
        stylePageButtons();
    }

    /**
     * Calcultes if next/page buttons should be enabled and sets disability on the
     * buttons.
     */
    private void stylePageButtons() {
        boolean next = cardDeckStartIndex + 4 < cardDecks.size();
        boolean prev = cardDeckStartIndex >= 4;

        nextPage.setDisable(!next);
        prevPage.setDisable(!prev);

    }

    /**
     * Updates list of carddecks. Calculates endIndex. Clears list if it has
     * content. Displays four decks at a time with data about each deck
     */
    private void updateDecklist() {

        deckList.getChildren().clear();

        int endIndex = cardDeckStartIndex + 4;
        if (endIndex > cardDecks.size()) // Sets index to last element in list if size is less than index
            endIndex = cardDecks.size();

        for (int i = cardDeckStartIndex; i < endIndex; i++) {
            int rowI = i % 4;
            deckList.add(new Text(cardDecks.get(i).getName()), 0, rowI);
            deckList.add(new Text(cardDecks.get(i).getCards().size() + " cards"), 1, rowI);
            deckList.add(createButton("Play", cardDecks.get(i)), 2, rowI);
            deckList.add(createButton("+", cardDecks.get(i)), 3, rowI);
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
