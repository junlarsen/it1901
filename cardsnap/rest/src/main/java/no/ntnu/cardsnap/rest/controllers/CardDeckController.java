package no.ntnu.cardsnap.rest.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import no.ntnu.cardsnap.core.CardDeck;
import no.ntnu.cardsnap.rest.services.CardDeckService;
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
 * REST API Controller for handling {@link no.ntnu.cardsnap.core.Card} objects.
 */
@CrossOrigin
@RestController()
@RequestMapping(path = "/api/decks")
public class CardDeckController {
  private final CardDeckService cardDeckService;

  private CardDeckController(CardDeckService cardDeckService) {
    this.cardDeckService = cardDeckService;
  }

  /**
   * List decks through pagination.
   *
   * @param page The page to view
   * @return The decks in the view
   * @throws IOException If underlying I/O error occurs
   */
  @GetMapping(value = "/")
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
  @GetMapping(value = "/{deck}")
  public CardDeck find(@PathVariable("deck") String id) throws IOException {
    return cardDeckService.find(UUID.fromString(id));
  }

  /**
   * Create a new deck.
   *
   * @param body The Http body
   * @return The newly created deck
   * @throws IOException If underlying I/O error occurs
   */
  @PostMapping(value = "/")
  @ResponseStatus(HttpStatus.CREATED)
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
  @PatchMapping(value = "/{deck}")
  public CardDeck update(
      @PathVariable("deck") String id,
      @RequestBody Map<String, String> body) throws IOException {
    UUID uuid = UUID.fromString(id);
    String name = body.get("name");
    if (name == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return cardDeckService.update(uuid, name);
  }

  /**
   * Delete an existing deck.
   *
   * @param id The deck to delete
   * @throws IOException If underlying I/O error occurs
   */
  @DeleteMapping(value = "/{deck}")
  public void delete(@PathVariable("deck") String id) throws IOException {
    cardDeckService.delete(UUID.fromString(id));
  }
}
