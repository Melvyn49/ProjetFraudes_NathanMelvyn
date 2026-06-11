package fr.eseo.projet2.modele;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FraudeTest {

    @Test
    void testCreationFraudeIAG() {
        LocalDate date = LocalDate.of(2026, 5, 18);
        FraudeIAG fraude = new FraudeIAG(date, "Génération de code Java", "public class Main { ... }", "Gemini");

        assertEquals(date, fraude.getDateReleve(), "La date de la fraude doit correspondre à la date définie à la création.");
        assertEquals("Gemini", fraude.getNomService(), "Le nom du service IA doit correspondre à celui passé au constructeur.");
        assertEquals("Génération de code Java", fraude.getDescription(), "La description de la fraude doit être correctement stockée.");
        assertTrue(fraude.afficher().contains("Gemini"), "L'affichage généré par la méthode afficher() doit inclure le nom du service IA.");
    }

    @Test
    void testAfficherFraudeIAGContientContenu() {
        FraudeIAG fraude = new FraudeIAG(LocalDate.now(), "desc", "contenu_test", "ChatGPT");

        assertTrue(fraude.afficher().contains("contenu_test"), "L'affichage généré doit inclure le contenu de la fraude (contenu_test).");
    }

    @Test
    void testCreationFraudePapier() {
        LocalDate date = LocalDate.of(2026, 5, 18);
        FraudePapier fraude = new FraudePapier(date, "Antisèche", "Contenu", "10x5 cm", true);

        assertEquals(date, fraude.getDateReleve(), "La date de la fraude papier doit être correctement stockée.");
        assertEquals("10x5 cm", fraude.getDimensions(), "Les dimensions de l'antisèche doivent être correctement renseignées.");
        assertTrue(fraude.isPile(), "Le statut plié (isPile) de l'antisèche doit être vrai.");
        assertTrue(fraude.afficher().contains("10x5 cm"), "L'affichage doit mentionner les dimensions de l'antisèche.");
    }

    @Test
    void testFraudePapierNonPliee() {
        FraudePapier fraude = new FraudePapier(LocalDate.now(), "desc", "contenu", "5x5", false);

        assertFalse(fraude.isPile(), "Le statut plié de l'antisèche doit être faux si elle n'est pas pliée.");
    }

    @Test
    void testCreationFraudeCalculatrice() {
        FraudeCalculatrice fraude = new FraudeCalculatrice(LocalDate.now(), "Programme", "Formules", "Casio", "ResolEquation.exe");

        assertEquals("Casio", fraude.getMarque(), "La marque de la calculatrice doit être correctement enregistrée.");
        assertEquals("ResolEquation.exe", fraude.getProgramme(), "Le nom du programme frauduleux doit correspondre à la saisie.");
        assertTrue(fraude.afficher().contains("Casio"), "L'affichage doit faire apparaître la marque de la calculatrice.");
        assertTrue(fraude.afficher().contains("ResolEquation.exe"), "L'affichage doit faire apparaître le nom du programme.");
    }

    @Test
    void testCreationFraudeIAGConnectee() {
        FraudeIAGConnectee fraude = new FraudeIAGConnectee(LocalDate.now(), "Connexion", "Requêtes", "Claude", "192.168.1.77");

        assertEquals("Claude", fraude.getNomService(), "Le service interrogé à distance doit être correctement stocké.");
        assertEquals("192.168.1.77", fraude.getAdresseIP(), "L'adresse IP de la connexion illicite doit être correctement stockée.");
        assertTrue(fraude.afficher().contains("192.168.1.77"), "L'affichage doit inclure l'adresse IP de la fraude.");
        assertTrue(fraude.afficher().contains("Claude"), "L'affichage doit inclure le nom du service IA interrogé.");
    }

    @Test
    void testFraudeIAGConnecteeHeriteDeIAG() {
        FraudeIAGConnectee fraude = new FraudeIAGConnectee(
                LocalDate.now(), "desc", "contenu", "GPT-4", "10.0.0.1");

        // Vérifie que l'héritage fonctionne
        assertInstanceOf(FraudeIAG.class, fraude, "L'objet FraudeIAGConnectee doit bien hériter de la classe FraudeIAG.");
        assertInstanceOf(Fraude.class, fraude, "L'objet FraudeIAGConnectee doit ultimement hériter de la classe abstraite parent Fraude.");
    }

    @Test
    void testConstructeursParDefaut() {
        FraudeIAG iag = new FraudeIAG();
        FraudePapier papier = new FraudePapier();
        FraudeCalculatrice calc = new FraudeCalculatrice();
        FraudeIAGConnectee connectee = new FraudeIAGConnectee();

        assertNull(iag.getDateReleve(), "La date doit être nulle par défaut pour une IAG non initialisée.");
        assertNull(papier.getDimensions(), "Les dimensions doivent être nulles par défaut pour une instance Papier.");
        assertNull(calc.getMarque(), "La marque doit être nulle par défaut pour une instance Calculatrice.");
        assertNull(connectee.getAdresseIP(), "L'adresse IP doit être nulle par défaut pour une instance Connectée.");
    }
}