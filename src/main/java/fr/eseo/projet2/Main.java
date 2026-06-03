package fr.eseo.projet2;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import fr.eseo.projet2.ihm.InterfaceUtilisateur;
import fr.eseo.projet2.modele.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        GestionnaireFormulaires gestionnaire = new GestionnaireFormulaires();

        genererDonneesDeTest(gestionnaire);

        InterfaceUtilisateur ihm = new InterfaceUtilisateur(gestionnaire);
        ihm.demarrer();
    }

    private static void genererDonneesDeTest(GestionnaireFormulaires gestionnaire) {
        Epreuve epreuveJava = new Epreuve("JAVA-101", LocalDate.now(), LocalTime.of(8, 0), 120, Modalite.SUR_ORDINATEUR);
        Formulaire f1 = new Formulaire(epreuveJava);

        Etudiant e1 = new Etudiant(123, "Mézière", "Jonas", Cursus.E3E);
        Etudiant e2 = new Etudiant(789, "Robin", "Line", Cursus.E3E);
        f1.ajouterEtudiant(e1);
        f1.ajouterEtudiant(e2);

        FraudeIAG fraude = new FraudeIAG(LocalDate.now(), "Copie conforme d'un code généré par ia", "public class Robot { ... }", "GEMINI");
        f1.ajouterFraude(fraude);

        gestionnaire.ajouterFormulaire(f1);
        System.out.println(" [Système] Jeu de données de test chargé avec succès !");
    }
}