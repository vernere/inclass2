package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
    
    /**
     * Test that App class can be instantiated
     */
    @Test
    public void testAppInstantiation() {
        App app = new App();
        assertNotNull(app);
    }
}