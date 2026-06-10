package fr.eseo.projet2.modele;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FraudeTest {


    @Test
    void testCreationFraudeIAG() {
        LocalDate date = LocalDate.of(2026, 5, 18);
        FraudeIAG fraude = new FraudeIAG(date, "Génération de code Java", "public class Main { ... }", "Gemini");

        assertEquals(date, fraude.getDateReleve());
        assertEquals("Gemini", fraude.getNomService());
        assertEquals("Génération de code Java", fraude.getDescription());
        assertTrue(fraude.afficher().contains("Gemini"));
    }


    @Test
    void testAfficherFraudeIAGContientContenu() {
        FraudeIAG fraude = new FraudeIAG(LocalDate.now(), "desc", "contenu_test", "ChatGPT");

        assertTrue(fraude.afficher().contains("contenu_test"));
    }


    @Test
    void testCreationFraudePapier() {
        LocalDate date = LocalDate.of(2026, 5, 18);
        FraudePapier fraude = new FraudePapier(date, "Antisèche", "Contenu", "10x5 cm", true);

        assertEquals(date, fraude.getDateReleve());
        assertEquals("10x5 cm", fraude.getDimensions());
        assertTrue(fraude.isPile());
        assertTrue(fraude.afficher().contains("10x5 cm"));
    }


    @Test
    void testFraudePapierNonPliee() {
        FraudePapier fraude = new FraudePapier(LocalDate.now(), "desc", "contenu", "5x5", false);

        assertFalse(fraude.isPile());
    }


    @Test
    void testCreationFraudeCalculatrice() {
        FraudeCalculatrice fraude = new FraudeCalculatrice(LocalDate.now(), "Programme", "Formules", "Casio", "ResolEquation.exe");

        assertEquals("Casio", fraude.getMarque());
        assertEquals("ResolEquation.exe", fraude.getProgramme());
        assertTrue(fraude.afficher().contains("Casio"));
        assertTrue(fraude.afficher().contains("ResolEquation.exe"));
    }


    @Test
    void testCreationFraudeIAGConnectee() {
        FraudeIAGConnectee fraude = new FraudeIAGConnectee(LocalDate.now(), "Connexion", "Requêtes", "Claude", "192.168.1.77");

        assertEquals("Claude", fraude.getNomService());
        assertEquals("192.168.1.77", fraude.getAdresseIP());
        assertTrue(fraude.afficher().contains("192.168.1.77"));
        assertTrue(fraude.afficher().contains("Claude"));
    }


    @Test
    void testFraudeIAGConnecteeHeriteDeIAG() {
        FraudeIAGConnectee fraude = new FraudeIAGConnectee(
                LocalDate.now(), "desc", "contenu", "GPT-4", "10.0.0.1");
        // Vérifie que l'héritage fonctionne
        assertInstanceOf(FraudeIAG.class, fraude);
        assertInstanceOf(Fraude.class, fraude);
    }


    @Test
    void testConstructeursParDefaut() {
        FraudeIAG iag = new FraudeIAG();
        FraudePapier papier = new FraudePapier();
        FraudeCalculatrice calc = new FraudeCalculatrice();
        FraudeIAGConnectee connectee = new FraudeIAGConnectee();

        assertNull(iag.getDateReleve());
        assertNull(papier.getDimensions());
        assertNull(calc.getMarque());
        assertNull(connectee.getAdresseIP());
    }
}