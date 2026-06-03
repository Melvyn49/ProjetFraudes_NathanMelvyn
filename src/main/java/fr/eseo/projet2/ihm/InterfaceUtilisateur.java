package fr.eseo.projet2.ihm;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import fr.eseo.projet2.modele.Etudiant;
import fr.eseo.projet2.modele.Formulaire;
import fr.eseo.projet2.modele.Fraude;
import fr.eseo.projet2.modele.Epreuve;
import fr.eseo.projet2.modele.Cursus;
import fr.eseo.projet2.modele.Modalite;
import fr.eseo.projet2.modele.FraudeIAG;
import fr.eseo.projet2.modele.FraudePapier;
import fr.eseo.projet2.stats.Statistiques;
import fr.eseo.projet2.modele.FraudeCalculatrice;
import java.time.LocalDate;
import java.time.LocalTime;
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
                    // MODIFICATION : On appelle la méthode au lieu du texte statique
                    creerFormulaireInteractif();
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
    }

    private void afficherMenu() {
        System.out.println("\nQue souhaitez-vous faire ?");
        System.out.println("1. Ajouter un nouveau formulaire");
        System.out.println("2. Consulter les statistiques");
        System.out.println("3. Afficher le détail des dossiers (Fraudes IA, etc.)");
        System.out.println("4. Quitter l'application");
        System.out.print("Votre choix : ");
    }

    // NOUVEAU : La méthode complète avec un sous-menu pour choisir n'importe quel type de fraude
    private void creerFormulaireInteractif() {
        System.out.println("\n--- CRÉATION D'UN NOUVEAU DOSSIER ---");

        System.out.print("Nom de l'étudiant suspecté : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom de l'étudiant : ");
        String prenom = scanner.nextLine();

        Epreuve epreuveAujourdhui = new Epreuve("Examen", LocalDate.now(), LocalTime.of(14, 0), 120, Modalite.EXAMEN_ECRIT);
        Formulaire nouveauFormulaire = new Formulaire(epreuveAujourdhui);

        Etudiant nouvelEtudiant = new Etudiant(999, nom, prenom, Cursus.E3E);
        nouveauFormulaire.ajouterEtudiant(nouvelEtudiant);

        System.out.println("\nQuel type de fraude a été constaté ?");
        System.out.println("1. Fraude classique (Papier)");
        System.out.println("2. Fraude technologique (IA Générative)");
        System.out.println("3. Fraude technologique (Calculatrice)");
        System.out.print("Votre choix (1, 2 ou 3) : ");
        String typeFraude = scanner.nextLine();

        switch (typeFraude) {
            case "1":
                // Saisie sur mesure pour le PAPIER
                System.out.print("Quelles sont les dimensions de l'antisèche ? (ex: 5x5 cm) : ");
                String dimensions = scanner.nextLine();
                System.out.print("Le papier était-il plié ? (oui/non) : ");
                boolean estPlie = scanner.nextLine().equalsIgnoreCase("oui");

                FraudePapier fraudePapier = new FraudePapier(LocalDate.now(), "Antisèche papier", "Document caché", dimensions, estPlie);
                nouveauFormulaire.ajouterFraude(fraudePapier);
                System.out.println("-> Fraude papier ajoutée au dossier.");
                break;

            case "2":
                // Saisie sur mesure pour l'IA
                System.out.print("Quel modèle génératif a été utilisé ? (ex: Gemini, ChatGPT) : ");
                String modele = scanner.nextLine();

                FraudeIAG fraudeIA = new FraudeIAG(LocalDate.now(), "Utilisation d'IA", "Contenu généré", modele);
                nouveauFormulaire.ajouterFraude(fraudeIA);
                System.out.println("-> Fraude IA ajoutée au dossier.");
                break;

            case "3":
                System.out.print("Quelle est la marque de la calculatrice ? : ");
                String marque = scanner.nextLine();

                System.out.print("Quel est le nom du programme intégrer dedans ? : ");
                String programme = scanner.nextLine();

                FraudeCalculatrice fraudeCalc = new FraudeCalculatrice(LocalDate.now(), "Anti-sèche calculatrice", "Formules masquées dans la mémoire", marque, programme);

                nouveauFormulaire.ajouterFraude(fraudeCalc);
                System.out.println("-> Fraude calculatrice ajoutée au dossier avec succès !");
                break;

            default:
                System.out.println("Choix non reconnu. Le dossier a été créé sans fraude associée.");
                break;
        }

        gestionnaire.ajouterFormulaire(nouveauFormulaire);
        System.out.println(" Dossier complet créé et enregistré pour " + prenom + " " + nom + " !");
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
        System.out.println("\n DÉTAIL DES DOSSIERS DE FRAUDE :");

        if (gestionnaire.getFormulaires().isEmpty()) {
            System.out.println("Aucun dossier enregistré pour le moment.");
            return;
        }

        for (Formulaire f : gestionnaire.getFormulaires()) {
            System.out.println("\n" + f.toString());     //  mettre héritage !!!!

            // On affiche tous les étudiants complices
            System.out.print("  Étudiants impliqués : ");
            for (Etudiant e : f.getEtudiants()) {
                System.out.print(e.getPrenom() + " " + e.getNom() + " | ");
            }
            System.out.println();

            System.out.println("  Preuves de fraude :");
            for (Fraude fraude : f.getFraudes()) {
                System.out.println("    - " + fraude.afficher());
            }
        }
    }
}