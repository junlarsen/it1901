package no.ntnu.cardsnap.rest.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.rest.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST API Controller for handling {@link no.ntnu.cardsnap.core.CardDeck}
 * objects.
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/decks/{deck}/cards")
public class CardController {
  private final CardService cardService;

  private CardController(CardService cardService) {
    this.cardService = cardService;
  }

  /**
   * List cards in a deck through pagination.
   *
   * @param deckId The deck to access
   * @param page The page to view
   * @return The cards in the view
   * @throws IOException If underlying I/O error occurs
   */
  @GetMapping(value = "/")
  public List<Card> findMany(
      @PathVariable("deck") String deckId,
      @RequestParam(defaultValue = "0") Integer page) throws IOException {
    return cardService.list(page, UUID.fromString(deckId));
  }

  /**
   * Get a single card.
   *
   * @param deckId The deck to access
   * @param id The card to access
   * @return The card
   * @throws IOException If underlying I/O error occurs
   */
  @GetMapping(value = "/{card}")
  public Card find(
      @PathVariable("deck") String deckId,
      @PathVariable("card") String id) throws IOException {
    return cardService.find(UUID.fromString(id), UUID.fromString(deckId));
  }

  /**
   * Create a new card.
   *
   * @param body The Http body
   * @param deckId The deck to create the card in
   * @return The newly created card
   * @throws IOException If underlying I/O error occurs
   */
  @PostMapping(value = "/")
  @ResponseStatus(HttpStatus.CREATED)
  public Card create(
      @RequestBody Map<String, String> body,
      @PathVariable("deck") String deckId) throws IOException {
    String question = body.get("question");
    String answer = body.get("answer");
    if (question == null || answer == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return cardService.create(question, answer, UUID.fromString(deckId));
  }

  /**
   * Update an existing card.
   *
   * @param deckId The deck to access
   * @param id The card to edit
   * @param body The Http body
   * @return The updated card
   * @throws IOException If underlying I/O error occurs
   */
  @PatchMapping(value = "/{card}")
  public Card update(
      @PathVariable("deck") String deckId,
      @PathVariable("card") String id,
      @RequestBody Map<String, String> body) throws IOException {
    UUID uuid = UUID.fromString(id);
    String question = body.get("question");
    String answer = body.get("answer");
    if (question == null || answer == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return cardService.update(uuid, question, answer, UUID.fromString(deckId));
  }

  /**
   * Delete an existing card.
   *
   * @param deckId The deck to access
   * @param id The card to delete
   * @throws IOException If underlying I/O error occurs
   */
  @DeleteMapping(value = "/{card}")
  public void delete(
      @PathVariable("deck") String deckId,
      @PathVariable("card") String id) throws IOException {
    cardService.delete(UUID.fromString(id), UUID.fromString(deckId));
  }
}
