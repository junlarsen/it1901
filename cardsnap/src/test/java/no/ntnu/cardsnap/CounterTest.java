package no.ntnu.cardsnap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterTest {
    @Test
    public void testIncrement() {
        Counter counter = new Counter(100);
        assertEquals(100, counter.getValue());
        counter.increment();
        assertEquals(101, counter.getValue());
    }
}
