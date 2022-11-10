package no.ntnu.cardsnap.rest.services;

import no.ntnu.cardsnap.core.CardDeck;
import no.ntnu.cardsnap.rest.exceptions.EntityAlreadyExistsException;
import no.ntnu.cardsnap.rest.exceptions.EntityNotFoundException;
import no.ntnu.cardsnap.rest.repositories.CardDeckRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Concrete implementation of something that can handle business level logic
 * on the {@link CardDeck} entity.
 */
@Service
public class CardDeckService {
    private final CardDeckRepository repository;

    /**
     * Create a new CardDeckService, backed by a repository.
     *
     * @param repository The repository to use
     */
    public CardDeckService(final CardDeckRepository repository) {
        this.repository = repository;
    }

    /**
     * Create a new card deck.
     *
     * @param name Name for the deck
     * @return The newly created deck
     * @throws IOException                  If underlying I/O error occurs
     * @throws EntityAlreadyExistsException If deck with name already exists
     */
    public CardDeck create(String name) throws IOException, IllegalArgumentException {
        boolean exists = repository.all()
            .stream()
            .anyMatch((deck) -> deck.getName().equals(name));
        if (exists) {
            throw new EntityAlreadyExistsException("Deck with name " + name + "already exists");
        }
        return repository.create(UUID.randomUUID(), name);
    }

    /**
     * Get a view of all the stored card decks.
     *
     * @param page Offset to start the view at, cannot exceed size of list.
     * @return The requested card decks.
     * @throws IOException              If underlying I/O error occurs
     * @throws IllegalArgumentException If invalid offset or limit value
     */
    public List<CardDeck> list(int page) throws IOException, IllegalArgumentException {
        Set<CardDeck> all = repository.all();
        if ((page * 50) > all.size()) {
            throw new IllegalArgumentException("Specified offset goes past size");
        }
        return all.stream()
            .limit(50)
            .skip(page * 50L)
            .toList();
    }

    /**
     * Find a deck with the given id.
     *
     * @param id The id to look up
     * @return The deck if it exists
     * @throws IOException             If underlying I/O error occurs
     * @throws EntityNotFoundException If the deck does not exist
     */
    public CardDeck find(UUID id) throws IOException {
        Optional<CardDeck> deck = repository.find(id);
        if (deck.isEmpty()) {
            throw new EntityNotFoundException("Deck with ID " + id + " does not exist");
        }
        return deck.get();
    }

    /**
     * Update the name of a deck with the given id.
     *
     * @param id      The id to look up
     * @param newName The new name for the deck
     * @return The updated deck
     * @throws IOException             If underlying I/O error occurs
     * @throws EntityNotFoundException If the deck does not exist
     */
    public CardDeck update(UUID id, String newName) throws IOException {
        Optional<CardDeck> deck = repository.update(id, newName);
        if (deck.isEmpty()) {
            throw new EntityNotFoundException("Deck with ID " + id + " does not exist");
        }
        return deck.get();
    }

    /**
     * Delete a dekc with the given id.
     *
     * @param id The id to look up
     * @throws IOException             If underlying I/O error occurs
     * @throws EntityNotFoundException If the deck does not exist
     */
    public void delete(UUID id) throws IOException {
        // Ensure that the entity exists
        find(id);
        repository.delete(id);
    }
}
