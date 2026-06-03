package fr.eseo.projet2.modele;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FraudeTest {

    @Test
    void testCreationFraudeIAG() {
        // Arrange
        LocalDate date = LocalDate.of(2026, 5, 18);
        String desc = "Génération de code Java";
        String contenu = "public class Main { ... }";
        String ia = "Gemini";

        // Act
        FraudeIAG fraude = new FraudeIAG(date, desc, contenu, ia);

        // Assert
        assertEquals(date, fraude.getDateReleve(), "La date doit être initialisée via le constructeur parent.");
        assertEquals("Gemini", fraude.getNomService(), "Le nom du service IA doit être correctement stocké.");
        assertTrue(fraude.afficher().contains("Gemini"), "La méthode afficher() doit inclure le nom du service.");
    }
}