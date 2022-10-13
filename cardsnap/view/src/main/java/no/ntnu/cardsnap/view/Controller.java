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

public final class Controller {
    /**
     * {@link ProfileService} implementation for domain logic.
     */
    private final ProfileService profileService = new ProfileService(
        new DiskProfileStorage(Path.of("storage")));

    /**
     * The currently loaded profile.
     */
    private Profile profile;

    /**
     * The grid pane that has the list of card decks.
     */
    @FXML
    private GridPane deckList;

    /**
     * Button to create new deck.
     */
    @FXML
    private Button newDeck;

    /**
     * Button to navigate to previous deck page.
     */
    @FXML
    private Button prevPage;

    /**
     * Button to navigate to next deck page.
     */
    @FXML
    private Button nextPage;

    /**
     * Subtitle on page.
     */
    @FXML
    private Text subtitle;

    /**
     * Input text field for creating new card deck name.
     */
    @FXML
    private TextField inputCardDeckName;

    /**
     * Input text field for new card question.
     */
    @FXML
    private TextField question;

    /**
     * Input text field for new card answer.
     */
    @FXML
    private TextField answer;

    /**
     * Pagination controller to control pagination of decks.
     */
    private final Pagination<CardDeck> pagination = new Pagination<>(
        new ArrayList<>(),
        4
    );

    /**
     * Index in grid where the deck name lies.
     */
    private static final int GRID_COLUMN_NAME = 0;
    /**
     * Index in grid where the deck card count lies.
     */
    private static final int GRID_COLUMN_COUNT = 1;
    /**
     * Index in grid where the play button lies.
     */
    private static final int GRID_COLUMN_PLAY = 2;
    /**
     * Index in grid where the edit button lies.
     */
    private static final int GRID_COLUMN_EDIT = 3;

    /**
     * Row index in grid where finished button lies.
     */
    private static final int GRID_ROW_FINISHED_BUTTON = 3;

    /**
     * Initialize the JavaFX Controller.
     */
    @FXML
    private void initialize() {
        profile = profileService.load();
        updateDecklist();
        stylePageButtons();
    }

    /**
     * Method for "Create new deck" button.
     * <p>
     * Creates new view with input field and button for new card deck,
     * validation, and adds card deck to all card decks.
     */
    @FXML
    private void createDeck() {
        clearDeckList();
        addNewCardDeckfields();
        addCardDeckAddButton();
        subtitle.setText("Create new deck:");
    }

    /**
     * Creates input field for new card deck name.
     */
    private void addNewCardDeckfields() {
        deckList.add(new Text("Name"), GRID_COLUMN_NAME, 0);
        inputCardDeckName = new TextField("");
        deckList.add(inputCardDeckName, GRID_COLUMN_COUNT, 0);
    }

    /**
     * Creates button for adding the new card deck.
     */
    private void addCardDeckAddButton() {
        Button addCardDeckButton = new Button("Add");
        addCardDeckButton.getStyleClass().add("myButton");
        addCardDeckButton.getStyleClass().add("pressButton");
        addCardDeckButton.setOnAction((event) -> handleAddDeck());
        deckList.add(addCardDeckButton, GRID_COLUMN_COUNT, 1);
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
     * Adds cards to cardDecksStartIndex if there is enough elements in list.
     * <p>
     * Calls on updateDeckList() to update the content of the list
     */
    @FXML
    private void handleNextPage() {
        pagination.next();
        updateDecklist();
        stylePageButtons();
    }

    /**
     * Reduces cardDeckStartIndex by 4 if the index is greater than 4.
     * <p>
     * Calls on updateDeckList() to update the content of the list.
     */
    @FXML
    private void handlePrevPage() {
        pagination.previous();
        updateDecklist();
        stylePageButtons();
    }

    /**
     * Calculates if next/page buttons should be enabled and sets disability on
     * the buttons.
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
            deckList.add(new Text(current.getName()), GRID_COLUMN_NAME, i);
            deckList.add(
                new Text(current.getCards().size() + " cards"),
                GRID_COLUMN_COUNT,
                i
            );
            deckList.add(
                createButton("Play", current),
                GRID_COLUMN_PLAY,
                i
            );
            deckList.add(createButton("+", current), GRID_COLUMN_EDIT, i);
        }
    }

    /**
     * Creates a button with styleclasses, text and eventListener on click.
     *
     * @param text     Button text
     * @param cardDeck Deck to add a button for
     * @return Button with style and text
     */
    private Button createButton(final String text, final CardDeck cardDeck) {
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
     * Method to display view to add a new card to deck.
     *
     * @param cardDeck Deck to add a new card to
     */
    private void handleAddCard(final CardDeck cardDeck) {
        clearDeckList();
        createAddCardLabels();
        createAddCardFields();
        createAddCardButton(cardDeck);
        createFinishedButton();
        setButtonVisibility(false);
        subtitle.setText("Add card to deck:");
    }

    /**
     * Clears all nodes in deckList.
     */
    private void clearDeckList() {
        deckList.getChildren().remove(0, deckList.getChildren().size());
    }

    /**
     * Creates two text-labels to belong to inputfields.
     */
    private void createAddCardLabels() {
        deckList.add(new Text("Question:"), GRID_COLUMN_NAME, 0);
        deckList.add(new Text("Answer:"), GRID_COLUMN_NAME, 1);
    }

    /**
     * Creates two text-inputs for user input. Adds styleclass for css-usage.
     */
    private void createAddCardFields() {
        question = new TextField();
        question.getStyleClass().add("inputTextField");
        deckList.add(question, GRID_COLUMN_COUNT, 0);

        answer = new TextField();
        question.getStyleClass().add("inputTextField");
        deckList.add(answer, GRID_COLUMN_COUNT, 1);
    }

    /**
     * Creates a button that later is used to add a card to the correct
     * card deck.
     *
     * @param cardDeck CardDeck with 0 or many cards
     */
    private void createAddCardButton(final CardDeck cardDeck) {
        Button b = new Button("Add question");
        b.getStyleClass().add("createCardButtons");
        b.getStyleClass().add("pressButton");
        b.setOnAction((e) -> addCard(cardDeck));
        deckList.add(b, GRID_COLUMN_COUNT, 2);
    }

    /**
     * Creates a button that takes user back to landing page.
     */
    private void createFinishedButton() {
        Button b = new Button("Finished");
        b.getStyleClass().add("createCardButtons");
        b.getStyleClass().add("pressButton");
        b.setOnAction((e) -> handleAddCardFinished());
        deckList.add(b, GRID_COLUMN_COUNT, GRID_ROW_FINISHED_BUTTON);
    }

    /**
     * Clears grid and sets visibility on footer-buttons.
     */
    private void handleAddCardFinished() {
        updateDecklist();
        setButtonVisibility(true);
    }

    /**
     * Adds a card to the given carddeck, or displays warning popup if vanlues
     * is invalid.
     *
     * @param deck CardDeck to get cards
     */
    private void addCard(final CardDeck deck) {
        if (question.getText().isBlank() || answer.getText().isBlank()) {
            displayAlert("Card question or answer cannot be empty");
            return;
        }
        try {
            profileService.create(
                profile,
                deck,
                question.getText(),
                answer.getText()
            );
            question.clear();
            answer.clear();
        } catch (IllegalArgumentException e) {
            displayAlert(e.getLocalizedMessage());
        }
    }

    /**
     * Creates and sets message for a warning pop-up.
     *
     * @param message String message
     */
    private void displayAlert(final String message) {
        Alert a = new Alert(AlertType.NONE);
        a.setContentText(message);
        a.setAlertType(AlertType.WARNING);
        a.show();
    }

    /**
     * Sets visibility on buttons depending on given boolean.
     *
     * @param visible boolean, true = visible buttons
     */
    private void setButtonVisibility(final boolean visible) {
        newDeck.setVisible(visible);
        nextPage.setVisible(visible);
        prevPage.setVisible(visible);
    }

    /**
     * Method to display view to add a new deck of cards.
     *
     * @param cardDeck Card deck to play
     */
    private void handlePlayDeck(final CardDeck cardDeck) {
        displayAlert("This functionality is not been implemented yet");
    }

}
