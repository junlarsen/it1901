package no.ntnu.cardsnap.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaginationTest {
    @Test
    @DisplayName("it is able to paginate through a set of elements")
    public void testWorks() {
        Pagination<Integer> pagination = new Pagination<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 4);
        List<Integer> view = pagination.getVisibleItems();
        assertEquals(List.of(1, 2, 3, 4), view);
        assertTrue(pagination.hasNext());
        assertFalse(pagination.hasPrevious());

        pagination.next();
        List<Integer> increment = pagination.getVisibleItems();
        assertEquals(List.of(5, 6, 7, 8), increment);
        assertTrue(pagination.hasPrevious());
        assertTrue(pagination.hasNext());

        pagination.next();
        List<Integer> remainder = pagination.getVisibleItems();
        assertEquals(List.of(9), remainder);
        assertFalse(pagination.hasNext());

        pagination.previous();
        List<Integer> middleAgain = pagination.getVisibleItems();
        assertEquals(List.of(5, 6, 7, 8), middleAgain);
    }

    @Test
    @DisplayName("it will drop the page number when new set is smaller")
    public void testResetsPageNumber() {
        Pagination<Integer> pagination = new Pagination<>(List.of(1, 2, 3, 4, 5), 4);
        pagination.next();

        assertEquals(1, pagination.getPage());
        pagination.setItems(List.of(1, 2, 3));

        assertEquals(0, pagination.getPage());
    }
}
