package no.ntnu.cardsnap.rest.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import no.ntnu.cardsnap.core.Card;
import no.ntnu.cardsnap.core.CardDeck;
import no.ntnu.cardsnap.rest.AppControllerAdvice;
import no.ntnu.cardsnap.rest.Application;
import no.ntnu.cardsnap.rest.TestConfiguration;
import no.ntnu.cardsnap.rest.repositories.CardDeckRepository;
import no.ntnu.cardsnap.rest.repositories.CardRepository;
import no.ntnu.cardsnap.rest.services.CardDeckService;
import no.ntnu.cardsnap.rest.services.CardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@Import({CardDeckController.class, CardController.class})
@ContextConfiguration(classes = {CardDeckRepository.class, CardDeckService.class, CardRepository.class, CardService.class, TestConfiguration.class, Application.class, AppControllerAdvice.class})
@WebMvcTest({CardDeckController.class, CardController.class})
public class CardIntegrationTest {
  @Autowired
  private MockMvc mvc;

  private final Gson gson = new Gson();

  @Test
  @DisplayName("integration test")
  public void testCardControllerIntegration() throws Exception {
    Type cardListType = new TypeToken<List<Card>>() {
    }.getType();
    CardDeck owner;
    // First we need to create a card deck
    {
      MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/decks/")
          .content(gson.toJson(Map.of("name", "foo")))
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
      ).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
      owner = gson.fromJson(result.getResponse().getContentAsString(), CardDeck.class);
    }
    // At the beginning, the deck should have no cards
    {
      MvcResult result = mvc.perform(
              MockMvcRequestBuilders.get("/api/decks/" + owner.getId() + "/cards/").accept(MediaType.APPLICATION_JSON)
          )
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andReturn();
      try {
        List<Card> decks = gson.fromJson(result.getResponse().getContentAsString(), cardListType);
        assertTrue(decks.isEmpty());
      } catch (JsonParseException ex) {
        fail(ex.getMessage());
      }
    }
    // Then we add a card to the deck
    Card created = null;
    {
      MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/decks/" + owner.getId() + "/cards/")
          .content(gson.toJson(Map.of("question", "Q", "answer", "A")))
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
      ).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
      try {
        created = gson.fromJson(result.getResponse().getContentAsString(), Card.class);
        assertEquals("Q", created.getQuestion());
        assertEquals("A", created.getAnswer());
        assertEquals(owner.getId(), created.getOwner());
      } catch (JsonParseException ex) {
        fail(ex.getMessage());
      }
    }
    // It should now be possible to find the card
    {
      MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/decks/" + owner.getId() + "/cards/" + created.getId()).accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andReturn();
      try {
        Card found = gson.fromJson(result.getResponse().getContentAsString(), Card.class);
        assertEquals("Q", found.getQuestion());
      } catch (JsonParseException ex) {
        fail(ex.getMessage());
      }
    }
    // Then we try to update the name of the deck
    {
      MvcResult result = mvc.perform(MockMvcRequestBuilders.patch("/api/decks/" + owner.getId() + "/cards/" + created.getId())
              .content(gson.toJson(Map.of("question", "newQ", "answer", "newA")))
              .accept(MediaType.APPLICATION_JSON)
              .contentType(MediaType.APPLICATION_JSON)
          ).andExpect(MockMvcResultMatchers.status().isOk())
          .andReturn();
      try {
        Card updated = gson.fromJson(result.getResponse().getContentAsString(), Card.class);
        assertEquals("newQ", updated.getQuestion());
        assertEquals("newA", updated.getAnswer());
        assertEquals(created.getId(), updated.getId());
      } catch (JsonParseException ex) {
        fail(ex.getMessage());
      }
    }
    // Finally we should be able to delete the deck
    {
      mvc.perform(MockMvcRequestBuilders.delete("/api/decks/" + owner.getId() + "/cards/" + created.getId()).accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andReturn();
      // The deck should no longer be findable
      mvc.perform(MockMvcRequestBuilders.get("/api/decks/" + owner.getId() + "/cards/" + created.getId()).accept(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isNotFound())
          .andReturn();
    }
  }
}
