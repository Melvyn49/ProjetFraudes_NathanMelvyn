package fr.eseo.projet2.ihm;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import java.util.Scanner;

public class InterfaceUtilisateur {
    private GestionnaireFormulaires gestionnaire;
    private Scanner scanner;

    public InterfaceUtilisateur(GestionnaireFormulaires gestionnaire) {
        this.gestionnaire = gestionnaire;
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {
        boolean continuer = true;

        System.out.println("  BIENVENUE - GESTION DES FRAUDES   ");

        while (continuer) {
            afficherMenu();
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    System.out.println("\n[Action] -> Création d'un formulaire...");
                    break;
                case "2":
                    System.out.println("\n[Action] -> Affichage des statistiques...");
                    break;
                case "3":
                    System.out.println("\nFermeture du programme. Au revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("\nErreur : Choix invalide. Veuillez taper 1, 2 ou 3.");
            }
        }
        scanner.close();
    }

    private void afficherMenu() {
        System.out.println("\nQue souhaitez-vous faire ?");
        System.out.println("1. Ajouter un nouveau formulaire");
        System.out.println("2. Consulter les statistiques");
        System.out.println("3. Quitter l'application");
        System.out.print("Votre choix : ");
    }
}