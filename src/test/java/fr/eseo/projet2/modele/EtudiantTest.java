package fr.eseo.projet2.modele;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EtudiantTest {

    @Test
    void testConstructeurParDefaut() {
        Etudiant e = new Etudiant();
        assertEquals(0, e.getNumeroApprenant());
        assertNull(e.getNom());
        assertNull(e.getPrenom());
        assertNull(e.getCursus());
    }

    @Test
    void testConstructeurAvecParametres() {
        Etudiant e = new Etudiant(101, "Dupont", "Alice", Cursus.E1);
        assertEquals(101, e.getNumeroApprenant());
        assertEquals("Dupont", e.getNom());
        assertEquals("Alice", e.getPrenom());
        assertEquals(Cursus.E1, e.getCursus());
    }

    @Test
    void testEqualsMemeReference() {
        Etudiant e = new Etudiant(101, "Dupont", "Alice", Cursus.E1);
        assertEquals(e, e);
    }

    @Test
    void testEqualsAvecNull() {
        Etudiant e = new Etudiant(101, "Dupont", "Alice", Cursus.E1);
        assertNotEquals(null, e);
    }

    @Test
    void testEqualsAvecAutreType() {
        Etudiant e = new Etudiant(101, "Dupont", "Alice", Cursus.E1);
        assertNotEquals("uneChaine", e);
    }

    @Test
    void testEqualsMemeNumeroApprenant() {
        Etudiant e1 = new Etudiant(101, "Dupont", "Alice", Cursus.E1);
        Etudiant e2 = new Etudiant(101, "Martin", "Bob", Cursus.E2);
        assertEquals(e1, e2);
    }

    @Test
    void testEqualsNumerosDifferents() {
        Etudiant e1 = new Etudiant(101, "Dupont", "Alice", Cursus.E1);
        Etudiant e2 = new Etudiant(202, "Dupont", "Alice", Cursus.E1);
        assertNotEquals(e1, e2);
    }

    @Test
    void testHashCodeCoherentAvecEquals() {
        Etudiant e1 = new Etudiant(101, "Dupont", "Alice", Cursus.E1);
        Etudiant e2 = new Etudiant(101, "Martin", "Bob", Cursus.E2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void testToString() {
        Etudiant e = new Etudiant(101, "Dupont", "Alice", Cursus.E1);
        String s = e.toString();
        assertTrue(s.contains("Alice"));
        assertTrue(s.contains("Dupont"));
        assertTrue(s.contains("101"));
    }
}
