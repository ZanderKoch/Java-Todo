package sequencers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdSequencerImplementationTest {
    private PersonIdSequencer sequencer;

    @BeforeEach
    void setUp() {
        sequencer = PersonIdSequencer.getInstance();
        sequencer.setCurrentId(0); // Ensure tests start with a clean state
    }

    @Test
    void testNextIdIncrements() {
        assertEquals(1, sequencer.nextId(), "First ID should be 1");
        assertEquals(2, sequencer.nextId(), "Second ID should be 2");
        assertEquals(3, sequencer.nextId(), "Third ID should be 3");
    }

    @Test
    void testGetCurrentId() {
        sequencer.nextId(); // Increment to 1
        assertEquals(1, sequencer.getCurrentId(), "Current ID should be 1");
        sequencer.nextId(); // Increment to 2
        assertEquals(2, sequencer.getCurrentId(), "Current ID should be 2");
    }
}
