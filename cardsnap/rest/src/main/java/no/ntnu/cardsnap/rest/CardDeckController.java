package no.ntnu.cardsnap.rest;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import no.ntnu.cardsnap.core.AbstractCardDeckService;
import no.ntnu.cardsnap.types.CardDeck;
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
 * REST API Controller for handling {@link CardDeck} objects.
 */
@CrossOrigin
@RestController()
@RequestMapping(path = "/api/decks")
public class CardDeckController {
  private final AbstractCardDeckService cardDeckService;

  private CardDeckController(AbstractCardDeckService cardDeckService) {
    this.cardDeckService = cardDeckService;
  }

  /**
   * List decks through pagination.
   *
   * @param page The page to view
   * @return The decks in the view
   * @throws IOException If underlying I/O error occurs
   */
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Found list of decks"),
      @ApiResponse(responseCode = "400",
          description = "Invalid id supplied",
          content = @Content),
      @ApiResponse(responseCode = "500",
          description = "Internal error",
          content = @Content),
  })
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CardDeck> findMany(
      @RequestParam(defaultValue = "0") Integer page) throws IOException {
    return cardDeckService.list(page);
  }

  /**
   * Get a single deck.
   *
   * @param id The deck to access
   * @return The deck
   * @throws IOException If underlying I/O error occurs
   */
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "The requested deck"),
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
  @GetMapping(value = "/{deck}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CardDeck find(@PathVariable("deck") UUID id) throws IOException {
    return cardDeckService.find(id);
  }

  /**
   * Create a new deck.
   *
   * @param body The Http body
   * @return The newly created deck
   * @throws IOException If underlying I/O error occurs
   */
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201",
          description = "Successfully created"),
      @ApiResponse(responseCode = "400",
          description = "Invalid id supplied",
          content = @Content),
      @ApiResponse(responseCode = "409",
          description = "Deck with given name already exists",
          content = @Content),
      @ApiResponse(responseCode = "500",
          description = "Internal error",
          content = @Content),
  })
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public CardDeck create(@RequestBody Map<String, String> body) throws IOException {
    String name = body.get("name");
    if (name == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return cardDeckService.create(name);
  }

  /**
   * Update an existing deck.
   *
   * @param id The deck to edit
   * @param body The Http body
   * @return The updated deck
   * @throws IOException If underlying I/O error occurs
   */
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Deck was updated"),
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
  @PatchMapping(value = "/{deck}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CardDeck update(
      @PathVariable("deck") UUID id,
      @RequestBody Map<String, String> body) throws IOException {
    String name = body.get("name");
    if (name == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return cardDeckService.update(id, name);
  }

  /**
   * Delete an existing deck.
   *
   * @param id The deck to delete
   * @throws IOException If underlying I/O error occurs
   */
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Deck was deleted"),
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
  @DeleteMapping(value = "/{deck}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@PathVariable("deck") UUID id) throws IOException {
    cardDeckService.delete(id);
  }
}
