package fr.eseo.projet2.stats;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import fr.eseo.projet2.modele.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class StatistiquesTest {

    private GestionnaireFormulaires gestionnaire;
    private Statistiques stats;

    @BeforeEach
    public void preparation() {
        Formulaire.resetCompteur();
        gestionnaire = new GestionnaireFormulaires();
        stats = new Statistiques(gestionnaire);
    }


    @Test
    public void testTotalFraudesGestionnaireVide() {
        assertEquals(0, stats.calculerTotalFraudes());
    }


    @Test
    public void testTotalFraudesAvecUneFraude() {
        Formulaire f = creerFormulaireAvecFraudes(1);
        gestionnaire.ajouterFormulaire(f);
        assertEquals(1, stats.calculerTotalFraudes());
    }


    @Test
    public void testTotalFraudesAvecPlusieursFormulaires() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(2));
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(3));

        assertEquals(5, stats.calculerTotalFraudes());
    }


    @Test
    public void testNbFormulairesVide() {
        assertEquals(0, stats.getNbFormulaires());
    }


    @Test
    public void testNbFormulairesApresAjout() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(1));
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(1));

        assertEquals(2, stats.getNbFormulaires());
    }


    @Test
    public void testNbEtudiantsDistinctsVide() {
        assertEquals(0, stats.getNbEtudiantsDistinct());
    }


    @Test
    public void testNbEtudiantsDistinctsSansDoublon() {
        Epreuve ep = new Epreuve("TEST", LocalDate.now(), LocalTime.of(8, 0), 60, Modalite.EXAMEN_ECRIT);
        Formulaire f1 = new Formulaire(ep);
        Formulaire f2 = new Formulaire(ep);
        f1.ajouterEtudiant(new Etudiant(1, "A", "A", Cursus.E1));
        f2.ajouterEtudiant(new Etudiant(2, "B", "B", Cursus.E2));
        gestionnaire.ajouterFormulaire(f1);
        gestionnaire.ajouterFormulaire(f2);

        assertEquals(2, stats.getNbEtudiantsDistinct());
    }


    @Test
    public void testNbEtudiantsDistinctsAvecDoublon() {
        Epreuve ep = new Epreuve("TEST", LocalDate.now(), LocalTime.of(8, 0), 60, Modalite.EXAMEN_ECRIT);
        Etudiant e = new Etudiant(1, "A", "A", Cursus.E1);
        Formulaire f1 = new Formulaire(ep);
        Formulaire f2 = new Formulaire(ep);
        f1.ajouterEtudiant(e);
        f2.ajouterEtudiant(e);
        gestionnaire.ajouterFormulaire(f1);
        gestionnaire.ajouterFormulaire(f2);
        assertEquals(1, stats.getNbEtudiantsDistinct());
    }


    @Test
    public void testMoyenneGestionnaireVide() {
        assertEquals(0.0, stats.calculerMoyenneFraudesParFormulaire());
    }


    @Test
    public void testMoyenneUnFormulaire() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(2));

        assertEquals(2.0, stats.calculerMoyenneFraudesParFormulaire());
    }


    @Test
    public void testMoyennePlusieursFormulaires() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(1));
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(3));

        assertEquals(2.0, stats.calculerMoyenneFraudesParFormulaire());
    }


    @Test
    public void testEcartTypeVide() {
        assertEquals(0.0, stats.calculerEcartType());
    }


    @Test
    public void testEcartTypeFormulairesIdentiques() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(2));
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(2));

        assertEquals(0.0, stats.calculerEcartType());
    }


    @Test
    public void testEcartTypeFormulairesDifferents() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(1));
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(3));

        assertEquals(1.0, stats.calculerEcartType(), 0.0001);
    }


    private Formulaire creerFormulaireAvecFraudes(int nbFraudes) {
        Epreuve ep = new Epreuve("TEST", LocalDate.now(), LocalTime.of(8, 0), 60, Modalite.EXAMEN_ECRIT);
        Formulaire f = new Formulaire(ep);
        for (int i = 0; i < nbFraudes; i++) {
            f.ajouterFraude(new FraudePapier(LocalDate.now(), "Triche", "Preuve", "10x10", false));
        }
        return f;
    }
}