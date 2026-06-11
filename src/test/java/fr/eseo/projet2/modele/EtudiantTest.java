package fr.eseo.projet2.modele;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EtudiantTest {

    @Test
    void testConstructeurParDefaut() {
        Etudiant e = new Etudiant();
        assertEquals(0, e.getNumeroApprenant(), "Le numéro apprenant doit être 0 par défaut.");
        assertNull(e.getNom(), "Le nom doit être null par défaut.");
        assertNull(e.getPrenom(), "Le prénom doit être null par défaut.");
        assertNull(e.getCursus(), "Le cursus doit être null par défaut.");
    }

    @Test
    void testConstructeurAvecParametres() {
        Etudiant e = new Etudiant(101, "Caillaud", "Mathis", Cursus.E1);
        assertEquals(101, e.getNumeroApprenant(), "Le numéro apprenant doit correspondre à l'identifiant passé en paramètre.");
        assertEquals("Caillaud", e.getNom(), "Le nom doit correspondre à celui passé en paramètre.");
        assertEquals("Mathis", e.getPrenom(), "Le prénom doit correspondre à celui passé en paramètre.");
        assertEquals(Cursus.E1, e.getCursus(), "Le cursus doit correspondre à celui défini à la création.");
    }

    @Test
    void testEqualsMemeReference() {
        Etudiant e = new Etudiant(101, "Caillaud", "Mathis", Cursus.E1);
        assertEquals(e, e, "L'étudiant doit être égal à lui-même en mémoire.");
    }

    @Test
    void testEqualsAvecNull() {
        Etudiant e = new Etudiant(101, "Caillaud", "Mathis", Cursus.E1);
        assertNotEquals(null, e, "L'étudiant ne doit pas être considéré comme égal à une référence nulle.");
    }

    @Test
    void testEqualsAvecAutreType() {
        Etudiant e = new Etudiant(101, "Caillaud", "Mathis", Cursus.E1);
        assertNotEquals("uneChaine", e, "L'étudiant ne doit pas être égal à un objet d'une classe différente (ex: String).");
    }

    @Test
    void testEqualsMemeNumeroApprenant() {
        Etudiant e1 = new Etudiant(101, "Caillaud", "Mathis", Cursus.E1);
        Etudiant e2 = new Etudiant(101, "Esnault", "Melvyn", Cursus.E2);
        assertEquals(e1, e2, "Deux étudiants ayant le même numéro apprenant doivent être considérés comme identiques (gestion des doublons).");
    }

    @Test
    void testEqualsNumerosDifferents() {
        Etudiant e1 = new Etudiant(101, "Caillaud", "Mathis", Cursus.E1);
        Etudiant e2 = new Etudiant(202, "Caillaud", "Mathis", Cursus.E1);
        assertNotEquals(e1, e2, "Deux étudiants avec des numéros apprenants distincts ne doivent pas être considérés comme égaux.");
    }

    @Test
    void testHashCodeCoherentAvecEquals() {
        Etudiant e1 = new Etudiant(101, "Caillaud", "Mathis", Cursus.E1);
        Etudiant e2 = new Etudiant(101, "Esnault", "Melvyn", Cursus.E2);
        assertEquals(e1.hashCode(), e2.hashCode(), "Le hashCode doit être identique pour deux étudiants jugés égaux par la méthode equals().");
    }

    @Test
    void testToString() {
        Etudiant e = new Etudiant(101, "Caillaud", "Mathis", Cursus.E1);
        String s = e.toString();
        assertTrue(s.contains("Mathis"), "La chaîne générée par toString doit contenir le prénom de l'étudiant.");
        assertTrue(s.contains("Caillaud"), "La chaîne générée par toString doit contenir le nom de l'étudiant.");
        assertTrue(s.contains("101"), "La chaîne générée par toString doit contenir le numéro apprenant de l'étudiant.");
    }
}
