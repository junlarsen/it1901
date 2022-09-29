package no.ntnu.cardsnap.domain;

/**
 * Simple integer counter
 */
public class Counter {
    private int value;

    public Counter(int value) {
        this.value = value;
    }

    public void increment() {
        this.value += 1;
    }

    public int getValue() {
        return this.value;
    }
}
