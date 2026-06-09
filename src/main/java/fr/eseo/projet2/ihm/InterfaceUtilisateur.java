package fr.eseo.projet2.ihm;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import fr.eseo.projet2.modele.FraudeIAGConnectee;
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
import fr.eseo.projet2.graphe.GrapheFraude;

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
                    creerFormulaireInteractif();
                    break;
                case "2":
                    afficherStatistiques();
                    break;
                case "3":
                    afficherDetails();
                    break;
                case "4":
                    afficherGraphe();
                    break;
                case "5":
                    retirerFormulaireInteractif();
                    break;
                case "6":
                    System.out.println("\n Fermeture du programme ");
                    continuer = false;
                    break;
                default:
                    System.out.println("\n Erreur : Choix invalide. Veuillez taper 1, 2, 3, 4, 5 ou 6.");
            }
        }
    }

    private void afficherMenu() {
        System.out.println("\nQue souhaitez-vous faire ?");
        System.out.println("1. Ajouter un nouveau formulaire");
        System.out.println("2. Consulter les statistiques");
        System.out.println("3. Afficher le détail des dossiers (Fraudes IA, etc.)");
        System.out.println("4. Afficher le réseau de plagiats (Graphe)");
        System.out.println("5. Retirer un formulaire existant");
        System.out.println("6. Quitter l'application");
        System.out.print("Votre choix : ");
    }

    private void creerFormulaireInteractif() {
        System.out.println("\n--- CRÉATION D'UN NOUVEAU DOSSIER ---");

        System.out.print("Nom de l'étudiant suspecté : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom de l'étudiant : ");
        String prenom = scanner.nextLine();

        if (gestionnaire.getEpreuves().isEmpty()) {
            System.out.println("\nErreur : Aucune épreuve disponible dans le système ! Impossible de créer un dossier.");
            return; // On annule la création pour éviter les bugs
        }

        System.out.println("\nVeuillez sélectionner l'épreuve concernée :");
        int index = 1;
        for (Epreuve ep : gestionnaire.getEpreuves()) {
            System.out.println(index + ". " + ep.getCodeECUE() + " (" + ep.getModalite() + " - " + ep.getDate() + ")");
            index++;
        }
        System.out.print("Votre choix (numéro) : ");
        int choixEpreuve = Integer.parseInt(scanner.nextLine());

        // On récupère l'épreuve choisie par l'utilisateur (index - 1 car les listes commencent à 0)
        Epreuve epreuveSelectionnee = gestionnaire.getEpreuves().get(choixEpreuve - 1);

        Formulaire nouveauFormulaire = new Formulaire(epreuveSelectionnee);

        Etudiant nouvelEtudiant = new Etudiant(999, nom, prenom, Cursus.E3E);
        nouveauFormulaire.ajouterEtudiant(nouvelEtudiant);

        System.out.println("\nQuel type de fraude a été constaté ?");
        System.out.println("1. Fraude classique (Papier)");
        System.out.println("2. Fraude technologique (IA Générative hors-ligne)");
        System.out.println("3. Fraude technologique (Calculatrice)");
        System.out.println("4. Fraude technologique (IA Connectée au réseau)");
        System.out.print("Votre choix (1, 2, 3 ou 4) : ");
        String typeFraude = scanner.nextLine();

        switch (typeFraude) {
            case "1":
                System.out.print("Quelles sont les dimensions de l'antisèche ? (ex: 5x5 cm) : ");
                String dimensions = scanner.nextLine();
                System.out.print("Le papier était-il plié ? (oui/non) : ");
                boolean estPlie = scanner.nextLine().equalsIgnoreCase("oui");

                FraudePapier fraudePapier = new FraudePapier(LocalDate.now(), "Antisèche papier", "Document caché", dimensions, estPlie);
                nouveauFormulaire.ajouterFraude(fraudePapier);
                System.out.println("-> Fraude papier ajoutée au dossier.");
                break;

            case "2":
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

            case "4":
                System.out.print("Quel service en ligne a été interrogé ? (ex: API OpenAI, Claude) : ");
                String service = scanner.nextLine();

                System.out.print("Quelle était l'adresse IP de la connexion illicite ? (ex: 192.168.1.45) : ");
                String ip = scanner.nextLine();

                FraudeIAGConnectee fraudeReseau = new FraudeIAGConnectee(
                        LocalDate.now(),
                        "Connexion illicite à un serveur distant",
                        "Requêtes massives générant une forte empreinte carbone",
                        service,
                        ip
                );

                nouveauFormulaire.ajouterFraude(fraudeReseau);
                System.out.println("-> Fraude IA Connectée ajoutée au dossier avec succès !");
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
        System.out.println("Nombre de formulaires      : " + stats.getNbFormulaires());
        System.out.println("Étudiants distincts        : " + stats.getNbEtudiantsDistinct());
        System.out.println("Total fraudes              : " + stats.calculerTotalFraudes());
        System.out.println("Moyenne fraudes/formulaire : " + stats.calculerMoyenneFraudesParFormulaire());
        System.out.println("Écart-type                 : " + stats.calculerEcartType());
    }

    /**
     * @brief Parcourt tous les formulaires pour afficher les étudiants et les fraudes en détail
     */
    private void afficherDetails() {
        System.out.println("\n DÉTAIL DES DOSSIERS DE FRAUDE :");

        if (gestionnaire.getFormulaires().isEmpty()) {
            System.out.println("Aucun dossier enregistré pour le moment.");
            return;
        }

        for (Formulaire f : gestionnaire.getFormulaires()) {
            System.out.println("\n" + f.toString());       //  mettre héritage !!!!

            // On affiche tout les étudiants complices
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

    private void afficherGraphe() {
        GrapheFraude graphe = gestionnaire.construireGraphe();
        graphe.afficher();
    }

    private void retirerFormulaireInteractif() {
        System.out.println("\nRETRAIT D'UN DOSSIER :");
        if (gestionnaire.getFormulaires().isEmpty()) {
            System.out.println("Aucun dossier enregistré pour le moment.");
            return;
        }

        System.out.println("Liste des dossiers actuels :");
        for (Formulaire f : gestionnaire.getFormulaires()) {
            System.out.println("- ID: " + f.getId() + " | Épreuve: " + f.getEpreuve().getCodeECUE());
        }

        System.out.print("\nSaisissez l'ID du formulaire à retirer : ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean succes = gestionnaire.retirerFormulaire(id);
            if (succes) {
                System.out.println("-> Le formulaire " + id + " a été retiré avec succès.");
            } else {
                System.out.println("-> Erreur : Aucun formulaire trouvé avec cet identifiant.");
            }
        } catch (NumberFormatException e) {
            System.out.println("-> Erreur : Veuillez saisir un nombre valide.");
        }
    }

}