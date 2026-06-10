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
        assertTrue(f.getId() > 0);
        assertNull(f.getEpreuve());
        assertTrue(f.getEtudiants().isEmpty());
        assertTrue(f.getFraudes().isEmpty());
    }

    @Test
    void testConstructeurAvecEpreuve() {
        Epreuve ep = new Epreuve("INFO-101", LocalDate.now(), LocalTime.of(8, 0), 60, Modalite.EXAMEN_ECRIT);
        Formulaire f = new Formulaire(ep);
        assertEquals("INFO-101", f.getEpreuve().getCodeECUE());
    }

    @Test
    void testIdAutoIncremente() {
        Formulaire f1 = new Formulaire();
        Formulaire f2 = new Formulaire();
        assertEquals(f1.getId() + 1, f2.getId());
    }

    @Test
    void testAjouterEtudiantNouveau() {
        Formulaire f = new Formulaire();
        Etudiant e = new Etudiant(101, "Esnault", "Melvyn", Cursus.E1);
        f.ajouterEtudiant(e);
        assertEquals(1, f.getEtudiants().size());
    }

    @Test
    void testAjouterEtudiantDoublon() {
        Formulaire f = new Formulaire();
        Etudiant e = new Etudiant(101, "Caillaud", "Mathis", Cursus.E1);
        f.ajouterEtudiant(e);
        f.ajouterEtudiant(e);
        assertEquals(1, f.getEtudiants().size());
    }

    @Test
    void testAjouterFraude() {
        Formulaire f = new Formulaire();
        Fraude fraude = new FraudePapier(LocalDate.now(), "Antisèche", "Contenu", "10x5", true);
        f.ajouterFraude(fraude);
        assertEquals(1, f.getFraudes().size());
    }

    @Test
    void testToStringAvecEpreuve() {
        Epreuve ep = new Epreuve("INFO-101", LocalDate.now(), LocalTime.of(8, 0), 60, Modalite.EXAMEN_ECRIT);
        Formulaire f = new Formulaire(ep);
        assertTrue(f.toString().contains("INFO-101"));
    }

    @Test
    void testToStringSansEpreuve() {
        Formulaire f = new Formulaire();
        assertTrue(f.toString().contains("N/A"));
    }
}