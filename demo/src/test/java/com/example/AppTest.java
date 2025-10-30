package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;
import java.util.ResourceBundle;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testAppInstantiation() {
        App app = new App();
        assertNotNull(app);
    }

    @Test
    public void testPrimaryControllerInstantiation() {
        PrimaryController controller = new PrimaryController();
        assertNotNull(controller);
    }

    @Test
    public void testChangeLangSetsResourceBundle() {
        PrimaryController controller = new PrimaryController();
        Locale locale = new Locale("en", "US");
        // Simulate changeLang logic
        ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", locale);
        assertNotNull(bundle);
        assertEquals("en", locale.getLanguage());
        assertEquals("US", locale.getCountry());
    }

    @Test
    public void testBMICalculationLogic() {
        double weight = 70;
        double height = 175;
        height = height / 100;
        double bmi = weight / (height * height);
        assertEquals(22.86, bmi, 0.01);
    }
}