package fr.eseo.projet2.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class FormulaireTest {

    @BeforeEach
    void reinitialiserCompteur() {
        Formulaire.resetCompteur();
    }

    @Test
    void testConstructeurParDefaut() {
        Formulaire f = new Formulaire();
        assertTrue(f.getId() > 0, "L'ID du formulaire doit être strictement positif.");
        assertNull(f.getEpreuve(), "L'épreuve doit être nulle par défaut à la création.");
        assertTrue(f.getEtudiants().isEmpty(), "La liste des étudiants doit être initialement vide.");
        assertTrue(f.getFraudes().isEmpty(), "La liste des fraudes doit être initialement vide.");
    }

    @Test
    void testConstructeurAvecEpreuve() {
        Epreuve ep = new Epreuve("INFO-101", LocalDate.now(), LocalTime.of(8, 0), 60, Modalite.EXAMEN_ECRIT);
        Formulaire f = new Formulaire(ep);
        assertEquals("INFO-101", f.getEpreuve().getCodeECUE(), "Le code de l'épreuve assignée au formulaire doit correspondre à celui passé au constructeur.");
    }

    @Test
    void testIdAutoIncremente() {
        Formulaire f1 = new Formulaire();
        Formulaire f2 = new Formulaire();
        assertEquals(f1.getId() + 1, f2.getId(), "L'ID doit s'incrémenter exactement de 1 à chaque nouvelle création de formulaire.");
    }

    @Test
    void testAjouterEtudiantNouveau() {
        Formulaire f = new Formulaire();
        Etudiant e = new Etudiant(101, "Esnault", "Melvyn", Cursus.E1);
        f.ajouterEtudiant(e);
        assertEquals(1, f.getEtudiants().size(), "La liste doit contenir exactement un étudiant après le premier ajout.");
    }

    @Test
    void testAjouterEtudiantDoublon() {
        Formulaire f = new Formulaire();
        Etudiant e = new Etudiant(101, "Caillaud", "Mathis", Cursus.E1);
        f.ajouterEtudiant(e);
        f.ajouterEtudiant(e);
        assertEquals(1, f.getEtudiants().size(), "La taille de la liste ne doit pas augmenter lors de l'ajout d'un étudiant déjà présent (doublon).");
    }

    @Test
    void testAjouterFraude() {
        Formulaire f = new Formulaire();
        Fraude fraude = new FraudePapier(LocalDate.now(), "Antisèche", "Contenu", "10x5", true);
        f.ajouterFraude(fraude);
        assertEquals(1, f.getFraudes().size(), "La liste doit contenir exactement une fraude après l'ajout réussi.");
    }

    @Test
    void testToStringAvecEpreuve() {
        Epreuve ep = new Epreuve("INFO-101", LocalDate.now(), LocalTime.of(8, 0), 60, Modalite.EXAMEN_ECRIT);
        Formulaire f = new Formulaire(ep);
        assertTrue(f.toString().contains("INFO-101"), "La méthode toString doit afficher le code de l'épreuve lorsque celle-ci est définie.");
    }

    @Test
    void testToStringSansEpreuve() {
        Formulaire f = new Formulaire();
        assertTrue(f.toString().contains("N/A"), "La méthode toString doit afficher 'N/A' lorsqu'aucune épreuve n'est associée au formulaire.");
    }
}