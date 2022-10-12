package no.ntnu.cardsnap.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import no.ntnu.cardsnap.domain.Card;
import no.ntnu.cardsnap.domain.CardDeck;

public class Controller {

    @FXML
    private GridPane deckList;

    @FXML
    private Button newDeck, prevPage, nextPage;

    @FXML
    private Text subtitle, cardDeckName;

    private int cardDeckStartIndex;

    private List<CardDeck> cardDecks;

    private TextField inputCardDeckName, question, answer;

    private Button addCardDeckButton;

    private int deckListRowCount = 4;

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

    /**
     * Method for "Create new deck" button. Creates new view with input field and
     * button
     * for new card deck, validation, and adds card deck to all carddecks.
     */
    @FXML
    private void createDeck() {
        clearDeckList();
        addNewCardDeckfields();
        addCardDeckAddButton();
        subtitle.setText("Create new deck:");
    }

    /**
     * Creates input field for new card deck name
     */
    private void addNewCardDeckfields() {
        deckList.add(new Text("Name"), 0, 0);
        inputCardDeckName = new TextField("");
        deckList.add(inputCardDeckName, 1, 0);
    }

    /**
     * Creates button for adding the new card deck.
     */
    private void addCardDeckAddButton() {
        addCardDeckButton = new Button("Add");
        addCardDeckButton.getStyleClass().add("myButton");
        addCardDeckButton.getStyleClass().add("pressButton");
        addCardDeckButton.setOnAction((event) -> handleAddDeck());
        deckList.add(addCardDeckButton, 1, 1);
    }

    /**
     * Validation for input in name of the new carddeck.
     * If valid, adds the new card deck to the other carddecks, and
     * updates the view including the new card deck
     */
    private void handleAddDeck() {
        // TODO this validation should be moved into domain
        List<String> cardDeckNames = cardDecks.stream().map(deck -> deck.getName()).collect(Collectors.toList());
        if (cardDeckNames.contains(inputCardDeckName.getText())) {
            displayAlert("Card deck already exists");
        } else if (inputCardDeckName.getText().isBlank()) {
            displayAlert("Card deck name is empty");
        } else {
            cardDecks.add(new CardDeck(inputCardDeckName.getText()));
            updateDecklist();
        }
    }

    /**
     * Adds cards to cardDecksStartIndex if there is enough elements in list. Calls
     * on
     * updateDeckList() to update the content of the list
     */
    @FXML
    private void handleNextPage() {
        if (cardDeckStartIndex < cardDecks.size()) {
            cardDeckStartIndex += deckListRowCount;
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
        if (cardDeckStartIndex >= deckListRowCount) {
            cardDeckStartIndex -= deckListRowCount;
            updateDecklist();
        }
        stylePageButtons();
    }

    /**
     * Calcultes if next/page buttons should be enabled and sets disability on the
     * buttons.
     */
    private void stylePageButtons() {
        boolean next = cardDeckStartIndex + deckListRowCount < cardDecks.size();
        boolean prev = cardDeckStartIndex >= deckListRowCount;

        nextPage.setDisable(!next);
        prevPage.setDisable(!prev);
    }

    /**
     * Updates list of carddecks. Calculates endIndex. Clears list if it has
     * content. Displays four decks at a time with data about each deck
     */
    private void updateDecklist() {

        clearDeckList();
        subtitle.setText("Your decks:");

        int endIndex = cardDeckStartIndex + deckListRowCount;
        if (endIndex > cardDecks.size()) // Sets index to last element in list if size is less than index
            endIndex = cardDecks.size();

        for (int i = cardDeckStartIndex; i < endIndex; i++) {
            int rowI = i % deckListRowCount;
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
        clearDeckList();
        createAddCardLabels();
        createAddCardFields();
        createAddCardButton(cardDeck);
        createFinishedButton();
        setButtonVisibility(false);
        subtitle.setText("Add card to deck:");
    }

    /**
     * Clears all nodes in deckList
     */
    private void clearDeckList() {
        deckList.getChildren().remove(0, deckList.getChildren().size());
    }

    /**
     * Creates two text-labels to belong to inputfields
     */
    private void createAddCardLabels() {
        deckList.add(new Text("Question:"), 0, 0);
        deckList.add(new Text("Answer:"), 0, 1);
    }

    /**
     * Creates two text-inputs for user input. Adds styleclass for css-usage
     */
    private void createAddCardFields() {
        question = new TextField();
        question.getStyleClass().add("inputTextField");
        deckList.add(question, 1, 0);

        answer = new TextField();
        question.getStyleClass().add("inputTextField");
        deckList.add(answer, 1, 1);
    }

    /**
     * Creates a button that later is used to add a card to the correct carddeck.
     * 
     * @param cardDeck CardDeck with 0 or many cards
     */
    private void createAddCardButton(CardDeck cardDeck) {
        Button b = new Button("Add question");
        b.getStyleClass().add("createCardButtons");
        b.getStyleClass().add("pressButton");
        b.setOnAction((e) -> addCard(cardDeck));
        deckList.add(b, 1, 2);
    }

    /**
     * Creates a button that takes user back to landing page
     */
    private void createFinishedButton() {
        Button b = new Button("Finished");
        b.getStyleClass().add("createCardButtons");
        b.getStyleClass().add("pressButton");
        b.setOnAction((e) -> handleAddCardFinished());
        deckList.add(b, 1, 3);
    }

    /**
     * Clears grid and sets visibility on footer-buttons
     */
    private void handleAddCardFinished() {
        updateDecklist();
        setButtonVisibility(true);
    }

    /**
     * Adds a card to the given carddeck, or displays warning popup if vanlues is
     * invalid
     * 
     * @param cardDeck CardDeck to get cards
     */
    private void addCard(CardDeck cardDeck) {
        try {
            cardDeck.add(new Card(question.getText(), answer.getText()));
            question.setText(null);
            answer.setText(null);
        } catch (IllegalArgumentException e) {
            displayAlert(e.getLocalizedMessage());
        }
    }

    /**
     * Creates and sets message for a warning pop-up
     * 
     * @param messsage String message
     */
    private void displayAlert(String messsage) {
        Alert a = new Alert(AlertType.NONE);
        a.setContentText(messsage);
        a.setAlertType(AlertType.WARNING);
        a.show();
    }

    /**
     * Sets visibility on buttons depending on given boolean
     * 
     * @param visible boolean, true = visible buttons
     */
    private void setButtonVisibility(boolean visible) {
        newDeck.setVisible(visible);
        nextPage.setVisible(visible);
        prevPage.setVisible(visible);
    }

    /**
     * Method to display view to add a new deck of cards
     */
    private void handlePlayDeck(CardDeck cardDeck) {
        displayAlert("This functionality is not been implemented yet");
    }

}
