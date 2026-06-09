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

        // 2 epreuves de modalités différentes
        Epreuve epreuveJava = new Epreuve("JAVA-101", LocalDate.of(2026, 5, 18),
                LocalTime.of(8, 0), 120, Modalite.SUR_ORDINATEUR);
        Epreuve epreuveMaths = new Epreuve("MATH-202", LocalDate.of(2026, 5, 20),
                LocalTime.of(14, 0), 90, Modalite.EXAMEN_ECRIT);

        // 4 étudiants de cursus différents
        Etudiant e1 = new Etudiant(101, "Mézière",  "Jonas",   Cursus.E3E);
        Etudiant e2 = new Etudiant(102, "Robin",    "Line",    Cursus.E3A);
        Etudiant e3 = new Etudiant(103, "Caillaud",   "Mathis",    Cursus.E2);
        Etudiant e4 = new Etudiant(104, "Monfils",  "Gael", Cursus.E1);

        // Formulaire 1 : FraudeIAG + FraudePapier — lien de plagiat e1 <-> e2
        Formulaire f1 = new Formulaire(epreuveJava);
        f1.ajouterEtudiant(e1);
        f1.ajouterEtudiant(e2);
        f1.ajouterFraude(new FraudeIAG(
                LocalDate.of(2026, 5, 18),
                "Copie conforme d'un code généré par IA",
                "public class Robot { ... }",
                "ChatGPT"
        ));
        f1.ajouterFraude(new FraudePapier(
                LocalDate.of(2026, 5, 18),
                "Antisèche papier découverte sous la table",
                "Formules d'algorithmique",
                "10x5 cm",
                true
        ));

        // Formulaire 2 : FraudeCalculatrice e3 seul
        Formulaire f2 = new Formulaire(epreuveMaths);
        f2.ajouterEtudiant(e3);
        f2.ajouterFraude(new FraudeCalculatrice(
                LocalDate.of(2026, 5, 20),
                "Programme de résolution d'équations stocké en mémoire",
                "Formules masquées dans la mémoire de la calculatrice",
                "Casio",
                "ResolEquation.exe"
        ));

        // Formulaire 3 : FraudeIAGConnectee — lien de plagiat e3 <-> e4
        Formulaire f3 = new Formulaire(epreuveMaths);
        f3.ajouterEtudiant(e3);
        f3.ajouterEtudiant(e4);
        f3.ajouterFraude(new FraudeIAGConnectee(
                LocalDate.of(2026, 5, 20),
                "Connexion illicite à un service IA distant pendant l'épreuve",
                "Réponses générées via API externe",
                "Claude",
                "192.168.1.77"
        ));

        gestionnaire.ajouterFormulaire(f1);
        gestionnaire.ajouterFormulaire(f2);
        gestionnaire.ajouterFormulaire(f3);

        gestionnaire.ajouterEpreuve(epreuveJava);
        gestionnaire.ajouterEpreuve(epreuveMaths);

        System.out.println("[Système] Jeu de données de test chargé avec succès !");
        System.out.println("  -> 2 épreuves, 4 étudiants, 3 formulaires, 4 types de fraude couverts.");
    }
}