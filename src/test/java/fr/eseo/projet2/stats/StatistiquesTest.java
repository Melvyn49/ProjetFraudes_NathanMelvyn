package fr.eseo.projet2.stats;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import fr.eseo.projet2.modele.Cursus;
import fr.eseo.projet2.modele.Epreuve;
import fr.eseo.projet2.modele.Etudiant;
import fr.eseo.projet2.modele.Formulaire;
import fr.eseo.projet2.modele.FraudePapier;
import fr.eseo.projet2.modele.Modalite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

public class StatistiquesTest {

    private GestionnaireFormulaires gestionnaire;
    private Statistiques stats;

    @BeforeEach
    public void preparation() {
        gestionnaire = new GestionnaireFormulaires();
        stats = new Statistiques(gestionnaire);
    }

    @Test
    public void testZeroFraudeAuDemarrage() {
        // Au début, le gestionnaire est vide, donc le total doit être 0
        assertEquals(0, stats.calculerTotalFraudes(), "Le total des fraudes devrait être 0 au démarrage.");
    }

    @Test
    public void testCalculerTotalFraudesAvecUneFraude() {
        // 1. Préparation d'un faux dossier
        Epreuve epreuve = new Epreuve("Exam Test", LocalDate.now(), LocalTime.of(8, 0), 120, Modalite.EXAMEN_ECRIT);
        Formulaire formulaire = new Formulaire(epreuve);

        Etudiant etudiant = new Etudiant(1, "Test", "User", Cursus.E3E);
        formulaire.ajouterEtudiant(etudiant);

        FraudePapier fraude = new FraudePapier(LocalDate.now(), "Triche", "Preuve", "10x10", false);
        formulaire.ajouterFraude(fraude);

        gestionnaire.ajouterFormulaire(formulaire);

        assertEquals(1, stats.calculerTotalFraudes(), "Le total des fraudes devrait être de 1.");
    }

    @Test
    public void testCalculerMoyenneFraudes() {
        Epreuve epreuve = new Epreuve("Exam Test", LocalDate.now(), LocalTime.of(8, 0), 120, Modalite.EXAMEN_ECRIT);
        Formulaire formulaire = new Formulaire(epreuve);
        FraudePapier fraude = new FraudePapier(LocalDate.now(), "Triche", "Preuve", "10x10", false);

        formulaire.ajouterFraude(fraude);
        gestionnaire.ajouterFormulaire(formulaire);

        // MOyenne vraiment utile ??
        assertEquals(1.0, stats.calculerMoyenneFraudesParFormulaire(), "La moyenne devrait être de 1.0.");
    }
}