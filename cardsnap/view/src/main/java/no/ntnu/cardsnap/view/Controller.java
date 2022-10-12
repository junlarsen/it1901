package no.ntnu.cardsnap.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import no.ntnu.cardsnap.core.ProfileService;
import no.ntnu.cardsnap.domain.CardDeck;
import no.ntnu.cardsnap.domain.Profile;
import no.ntnu.cardsnap.persistence.DiskProfileStorage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    /**
     * {@link ProfileService} implementation for domain logic
     */
    private final ProfileService profileService = new ProfileService(
            new DiskProfileStorage(Path.of("storage")));

    /**
     * The currently loaded profile
     */
    private Profile profile;

    @FXML
    private GridPane deckList;

    @FXML
    private Button newDeck, prevPage, nextPage;

    @FXML
    private Text subtitle;

    @FXML
    private TextField inputCardDeckName, question, answer;

    private final Pagination<CardDeck> pagination = new Pagination<>(new ArrayList<>(), 4);

    @FXML
    private void initialize() {
        profile = profileService.load();
        updateDecklist();
        stylePageButtons();
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
        Button addCardDeckButton = new Button("Add");
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
        if (inputCardDeckName.getText().isBlank()) {
            displayAlert("Card deck name cannot be empty");
            return;
        }
        try {
            profileService.create(profile, inputCardDeckName.getText());
            inputCardDeckName.clear();
            updateDecklist();
        } catch (IllegalArgumentException e) {
            displayAlert(e.getLocalizedMessage());
        }
    }

    /**
     * Adds cards to cardDecksStartIndex if there is enough elements in list. Calls
     * on
     * updateDeckList() to update the content of the list
     */
    @FXML
    private void handleNextPage() {
        pagination.next();
        updateDecklist();
        stylePageButtons();
    }

    /**
     * Reduces cardDeckStartIndex by 4 if the index is greater than 4. Calls on
     * updateDeckList() to update the content of the list.
     */
    @FXML
    private void handlePrevPage() {
        pagination.previous();
        updateDecklist();
        stylePageButtons();
    }

    /**
     * Calcultes if next/page buttons should be enabled and sets disability on the
     * buttons.
     */
    private void stylePageButtons() {
        nextPage.setDisable(!pagination.hasNext());
        prevPage.setDisable(!pagination.hasPrevious());
    }

    /**
     * Updates list of carddecks. Calculates endIndex. Clears list if it has
     * content. Displays four decks at a time with data about each deck
     */
    private void updateDecklist() {
        pagination.setItems(profile.getDecks().stream().toList());
        clearDeckList();
        subtitle.setText("Your decks:");

        List<CardDeck> visible = pagination.getVisibleItems();
        for (int i = 0; i < visible.size(); i++) {
            CardDeck current = visible.get(i);
            deckList.add(new Text(current.getName()), 0, i);
            deckList.add(new Text(current.getCards().size() + " cards"), 1, i);
            deckList.add(createButton("Play", current), 2, i);
            deckList.add(createButton("+", current), 3, i);
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
     * @param deck CardDeck to get cards
     */
    private void addCard(CardDeck deck) {
        if (question.getText().isBlank() || answer.getText().isBlank()) {
            displayAlert("Card question or answer cannot be empty");
            return;
        }
        try {
            profileService.create(profile, deck, question.getText(), answer.getText());
            question.clear();
            answer.clear();
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
