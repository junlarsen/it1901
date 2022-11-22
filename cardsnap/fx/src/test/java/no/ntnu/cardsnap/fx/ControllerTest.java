package no.ntnu.cardsnap.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.cardsnap.types.Card;
import no.ntnu.cardsnap.types.CardDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerTest extends ApplicationTest {
  private Controller controller;

  @BeforeEach
  public void setup() throws IOException {
    // Delete all decks before starting test
    List<CardDeck> decks = controller.getCardDeckService().list(0);
    while (decks != null) {
      for (CardDeck deck : decks) {
        controller.getCardDeckService().delete(deck.getId());
      }
      List<CardDeck> refreshed = controller.getCardDeckService().list(0);
      if (refreshed.isEmpty()) {
        decks = null;
      } else {
        decks = refreshed;
      }
    }
  }

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(
        this.getClass().getResource("LandingPage.fxml")
    );
    Parent parent = fxmlLoader.load();
    controller = fxmlLoader.getController();
    stage.setScene(new Scene(parent));
    stage.setResizable(false);
    stage.setTitle("CardSnap");
    stage.show();
  }

  @Test
  @DisplayName("it can create a new deck with cards")
  public void testCreateDeck() throws IOException {
    assertNotNull(controller);
    // Create a new deck
    String name = "Algdat";
    clickOn("#newDeck");
    clickOn("#createDeckInput").write(name);
    clickOn("#createDeckAddButton");
    Optional<CardDeck> exists = controller.getCardDeckService().list(0).stream()
        .filter((deck) -> deck.getName().equals(name))
        .findFirst();
    assertTrue(exists.isPresent());
    assertEquals(name, exists.get().getName());
    // Add a card to the deck
    clickOn("#deckAddCards");
    clickOn("#inputQuestion").write("2 + 2");
    clickOn("#inputAnswer").write("4");
    clickOn("#deckAdd");
    // Add another
    clickOn("#inputQuestion").write("2 / 2");
    clickOn("#inputAnswer").write("1");
    clickOn("#deckAdd");
    Optional<Card> card = controller.getCardService().list(0, exists.get().getId())
        .stream()
        .filter((c) -> c.getQuestion().equals("2 + 2"))
        .findFirst();
    assertTrue(card.isPresent());
    assertEquals(card.get().getOwner(), exists.get().getId());
    // Go back home
    clickOn("#deckDone");
  }

  @Test
  @DisplayName("it cannot create decks with same name")
  public void testCannotDuplicateDeckName() {
    assertNotNull(controller);
    // Create a new deck
    String name = "ITP";
    clickOn("#newDeck");
    clickOn("#createDeckInput").write(name);
    clickOn("#createDeckAddButton");

    // Create it again and expect error
    clickOn("#newDeck");
    clickOn("#createDeckInput").write(name);
    clickOn("#createDeckAddButton");
    Node dialogPane = lookup(".dialog-pane").query();
    from(dialogPane).lookup((Text t) -> t.getText().startsWith("Deck with name"));
  }

  @Test
  @DisplayName("it wont accept too short deck names")
  public void testDeckNameValidation() {
    assertNotNull(controller);
    // The name is too short
    clickOn("#newDeck");
    clickOn("#createDeckInput").write("x");
    clickOn("#createDeckAddButton");
    Node dialogPane = lookup(".dialog-pane").query();
    from(dialogPane).lookup((Text t) -> t.getText().startsWith("Deck name cannot be blank, shorter"));
  }

  @Test
  @DisplayName("it wont accept too long deck names")
  public void testDeckNameValidationLength() {
    assertNotNull(controller);
    // The name is too short
    clickOn("#newDeck");
    clickOn("#createDeckInput").write("wed324ud73d4t7d3t74dt734dt734t7dt734dg734bjjd3bj4dbj34dghj34gjdghj34dghj34dghjx");
    clickOn("#createDeckAddButton");
    Node dialogPane = lookup(".dialog-pane").query();
    from(dialogPane).lookup((Text t) -> t.getText().startsWith("Deck name cannot be blank, shorter"));
  }

  @Test
  @DisplayName("it will allow pagination past 4 decks")
  public void testPagination() {
    for (int i = 0; i < 4; i++) {
      clickOn("#newDeck");
      clickOn("#createDeckInput").write("Deck" + i);
      clickOn("#createDeckAddButton");
    }

    Button next = lookup("#nextPage").query();
    Button prev = lookup("#prevPage").query();
    assertTrue(next.isDisabled());
    assertTrue(prev.isDisabled());

    clickOn("#newDeck");
    clickOn("#createDeckInput").write("Deck 5");
    clickOn("#createDeckAddButton");
    assertFalse(next.isDisabled());
    clickOn("#nextPage");
    assertFalse(prev.isDisabled());
    assertTrue(next.isDisabled());
  }
}
