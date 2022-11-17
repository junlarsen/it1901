package no.ntnu.cardsnap.rest.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.rest.services.AbstractCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
  private final AbstractCardService cardService;

  private CardController(AbstractCardService cardService) {
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
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Found list of cards"),
      @ApiResponse(responseCode = "400",
          description = "Invalid id supplied",
          content = @Content),
      @ApiResponse(responseCode = "404",
          description = "Deck not found",
          content = @Content),
      @ApiResponse(responseCode = "500",
          description = "Internal error",
          content = @Content),
  })
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Card> findMany(
      @PathVariable("deck") UUID deckId,
      @RequestParam(defaultValue = "0") Integer page) throws IOException {
    return cardService.list(page, deckId);
  }

  /**
   * Get a single card.
   *
   * @param deckId The deck to access
   * @param id The card to access
   * @return The card
   * @throws IOException If underlying I/O error occurs
   */
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The requested card"),
      @ApiResponse(responseCode = "400",
          description = "Invalid id supplied",
          content = @Content),
      @ApiResponse(responseCode = "404",
          description = "Card or deck not found",
          content = @Content),
      @ApiResponse(responseCode = "500",
          description = "Internal error",
          content = @Content),
  })
  @GetMapping(value = "/{card}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Card find(
      @PathVariable("deck") UUID deckId,
      @PathVariable("card") UUID id) throws IOException {
    return cardService.find(id, deckId);
  }

  /**
   * Create a new card.
   *
   * @param body The Http body
   * @param deckId The deck to create the card in
   * @return The newly created card
   * @throws IOException If underlying I/O error occurs
   */
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201",
          description = "Successfully created"),
      @ApiResponse(responseCode = "400",
          description = "Invalid id supplied",
          content = @Content),
      @ApiResponse(responseCode = "404",
          description = "Deck not found",
          content = @Content),
      @ApiResponse(responseCode = "500",
          description = "Internal error",
          content = @Content),
  })
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public Card create(
      @RequestBody Map<String, String> body,
      @PathVariable("deck") UUID deckId) throws IOException {
    String question = body.get("question");
    String answer = body.get("answer");
    if (question == null || answer == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return cardService.create(question, answer, deckId);
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
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Card was updated"),
      @ApiResponse(responseCode = "400",
          description = "Invalid id supplied",
          content = @Content),
      @ApiResponse(responseCode = "404",
          description = "Card or deck not found",
          content = @Content),
      @ApiResponse(responseCode = "500",
          description = "Internal error",
          content = @Content),
  })
  @PatchMapping(value = "/{card}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Card update(
      @PathVariable("deck") UUID deckId,
      @PathVariable("card") UUID id,
      @RequestBody Map<String, String> body) throws IOException {
    String question = body.get("question");
    String answer = body.get("answer");
    if (question == null || answer == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return cardService.update(id, question, answer, deckId);
  }

  /**
   * Delete an existing card.
   *
   * @param deckId The deck to access
   * @param id The card to delete
   * @throws IOException If underlying I/O error occurs
   */
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Card was deleted"),
      @ApiResponse(responseCode = "400",
          description = "Invalid id supplied",
          content = @Content),
      @ApiResponse(responseCode = "404",
          description = "Card or deck not found",
          content = @Content),
      @ApiResponse(responseCode = "500",
          description = "Internal error",
          content = @Content),
  })
  @DeleteMapping(value = "/{card}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void delete(
      @PathVariable("deck") UUID deckId,
      @PathVariable("card") UUID id) throws IOException {
    cardService.delete(id, deckId);
  }
}
