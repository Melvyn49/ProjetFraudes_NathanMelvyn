package fr.eseo.projet2.ihm;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import fr.eseo.projet2.modele.Etudiant;
import fr.eseo.projet2.modele.Formulaire;
import fr.eseo.projet2.modele.Fraude;
import fr.eseo.projet2.stats.Statistiques;
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

        System.out.println(" BIENVENUE - GESTION DES FRAUDES ");

        while (continuer) {
            afficherMenu();
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    System.out.println("\n[Action] -> Création d'un formulaire (à venir)...");
                    break;
                case "2":
                    afficherStatistiques();
                    break;
                case "3":
                    afficherDetails();
                    break;
                case "4":
                    System.out.println("\n Fermeture du programme ");
                    continuer = false;
                    break;
                default:
                    System.out.println("\n Erreur : Choix invalide. Veuillez taper 1, 2, 3 ou 4.");
            }
        }
        scanner.close();
    }

    private void afficherMenu() {
        System.out.println("\nQue souhaitez-vous faire ?");
        System.out.println("1. Ajouter un nouveau formulaire");
        System.out.println("2. Consulter les statistiques");
        System.out.println("3. Afficher le détail des dossiers (Fraudes IA, etc.)");
        System.out.println("4. Quitter l'application");
        System.out.print("Votre choix : ");
    }

    private void afficherStatistiques() {
        System.out.println("\n STATISTIQUES DES FRAUDES ");
        Statistiques stats = new Statistiques(gestionnaire);
        System.out.println("Nombre total de fraudes : " + stats.calculerTotalFraudes());
        System.out.println("Moyenne de fraudes par formulaire : " + stats.calculerMoyenneFraudesParFormulaire());
    }

    /**
     * @brief Parcour tous les formulaires pour afficher les étudiants et les fraudes en détail.
     */
    private void afficherDetails() {
        System.out.println("\n--- DÉTAIL DES DOSSIERS DE FRAUDE ---");

        if (gestionnaire.getFormulaires().isEmpty()) {
            System.out.println("Aucun dossier enregistré pour le moment.");
            return; // On arrête la méthode ici si c'est vide
        }

        // On fait une boucle sur tous les formulaires (les dossiers)
        for (Formulaire f : gestionnaire.getFormulaires()) {
            System.out.println("\n" + f.toString());

            // On affiche tous les étudiants complices (Jonas, Alan, Line...)
            System.out.print("  Étudiants impliqués : ");
            for (Etudiant e : f.getEtudiants()) {
                System.out.print(e.getPrenom() + " " + e.getNom() + " | ");
            }
            System.out.println(); // Retour à la ligne

            // On affiche le détail des traces numériques ou physiques
            System.out.println("  Preuves de fraude :");
            for (Fraude fraude : f.getFraudes()) {
                System.out.println("    -> " + fraude.afficher());
            }
        }
    }
}