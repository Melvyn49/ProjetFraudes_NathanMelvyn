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
        assertEquals(0, stats.calculerTotalFraudes(), "Le total des fraudes doit être 0 lorsque le gestionnaire est vide.");
    }

    @Test
    public void testTotalFraudesAvecUneFraude() {
        Formulaire f = creerFormulaireAvecFraudes(1);
        gestionnaire.ajouterFormulaire(f);
        assertEquals(1, stats.calculerTotalFraudes(), "Le total des fraudes doit être 1 après l'ajout d'un formulaire contenant exactement une fraude.");
    }

    @Test
    public void testTotalFraudesAvecPlusieursFormulaires() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(2));
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(3));

        assertEquals(5, stats.calculerTotalFraudes(), "Le total doit correspondre à la somme exacte des fraudes de tous les formulaires (2 + 3 = 5).");
    }

    @Test
    public void testNbFormulairesVide() {
        assertEquals(0, stats.getNbFormulaires(), "Le nombre de formulaires doit être 0 à l'initialisation du gestionnaire.");
    }

    @Test
    public void testNbFormulairesApresAjout() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(1));
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(1));

        assertEquals(2, stats.getNbFormulaires(), "Le nombre de formulaires doit être 2 après l'ajout de deux dossiers distincts.");
    }

    @Test
    public void testNbEtudiantsDistinctsVide() {
        assertEquals(0, stats.getNbEtudiantsDistinct(), "Le nombre d'étudiants distincts doit être 0 quand aucun formulaire n'est enregistré.");
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

        assertEquals(2, stats.getNbEtudiantsDistinct(), "Le compteur doit trouver 2 étudiants distincts lorsqu'ils ont des numéros apprenants différents.");
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

        assertEquals(1, stats.getNbEtudiantsDistinct(), "Le compteur doit trouver 1 seul étudiant distinct si le même étudiant est impliqué dans plusieurs formulaires.");
    }

    @Test
    public void testMoyenneGestionnaireVide() {
        assertEquals(0.0, stats.calculerMoyenneFraudesParFormulaire(), "La moyenne doit être 0.0 (et éviter une division par zéro) lorsque le gestionnaire est vide.");
    }

    @Test
    public void testMoyenneUnFormulaire() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(2));

        assertEquals(2.0, stats.calculerMoyenneFraudesParFormulaire(), "La moyenne doit être égale au nombre de fraudes du seul formulaire présent (2 / 1 = 2.0).");
    }

    @Test
    public void testMoyennePlusieursFormulaires() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(1));
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(3));

        assertEquals(2.0, stats.calculerMoyenneFraudesParFormulaire(), "La moyenne doit être calculée correctement sur l'ensemble des formulaires ((1 + 3) / 2 = 2.0).");
    }

    @Test
    public void testEcartTypeVide() {
        assertEquals(0.0, stats.calculerEcartType(), "L'écart-type doit être 0.0 si aucun formulaire n'est présent.");
    }

    @Test
    public void testEcartTypeFormulairesIdentiques() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(2));
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(2));

        assertEquals(0.0, stats.calculerEcartType(), "L'écart-type doit être nul si tous les formulaires ont exactement le même nombre de fraudes (aucune dispersion).");
    }

    @Test
    public void testEcartTypeFormulairesDifferents() {
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(1));
        gestionnaire.ajouterFormulaire(creerFormulaireAvecFraudes(3));

        assertEquals(1.0, stats.calculerEcartType(), 0.0001, "L'écart-type doit refléter la dispersion correcte autour de la moyenne.");
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