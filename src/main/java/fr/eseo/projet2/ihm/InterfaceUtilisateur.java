package fr.eseo.projet2.ihm;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import fr.eseo.projet2.modele.FraudeIAGConnectee;
import fr.eseo.projet2.modele.Etudiant;
import fr.eseo.projet2.modele.Formulaire;
import fr.eseo.projet2.modele.Fraude;
import fr.eseo.projet2.modele.Epreuve;
import fr.eseo.projet2.modele.Cursus;
import fr.eseo.projet2.modele.FraudeIAG;
import fr.eseo.projet2.modele.FraudePapier;
import fr.eseo.projet2.modele.FraudeCalculatrice;
import fr.eseo.projet2.stats.Statistiques;
import fr.eseo.projet2.graphe.GrapheFraude;
import java.time.LocalDate;
import java.util.List;
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
                    afficherRechercheMenu();
                    break;
                case "6":
                    retirerFormulaireInteractif();
                    break;
                case "7":
                    System.out.println("\n Fermeture du programme ");
                    continuer = false;
                    break;
                default:
                    System.out.println("\n Erreur : Choix invalide. Veuillez taper un chiffre entre 1 et 7.");
            }
        }
    }

    private void afficherMenu() {
        System.out.println("\nQue souhaitez-vous faire ?");
        System.out.println("1. Ajouter un nouveau formulaire");
        System.out.println("2. Consulter les statistiques");
        System.out.println("3. Afficher le détail des dossiers");
        System.out.println("4. Afficher le réseau de plagiats (Graphe)");
        System.out.println("5. Rechercher");
        System.out.println("6. Retirer un formulaire existant");
        System.out.println("7. Quitter l'application");
        System.out.print("Votre choix : ");
    }

    private void creerFormulaireInteractif() {
        System.out.println("\nCRÉATION D'UN NOUVEAU DOSSIER :");

        System.out.print("Nom de l'étudiant suspecté : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom de l'étudiant : ");
        String prenom = scanner.nextLine();

        if (gestionnaire.getEpreuves().isEmpty()) {
            System.out.println("\nErreur : Aucune épreuve disponible dans le système ! Impossible de créer un dossier.");
            return;
        }

        System.out.println("\nVeuillez sélectionner l'épreuve concernée :");
        int index = 1;
        for (Epreuve ep : gestionnaire.getEpreuves()) {
            System.out.println(index + ". " + ep.getCodeECUE() + " (" + ep.getModalite() + " - " + ep.getDate() + ")");
            index++;
        }
        int choixEpreuve = -1;
        boolean choixValide = false;
        int nombreMaxEpreuves = gestionnaire.getEpreuves().size();

        while (!choixValide) {
            System.out.print("Votre choix (numéro) : ");
            String entreeUtilisateur = scanner.nextLine();

            try {
                choixEpreuve = Integer.parseInt(entreeUtilisateur);

                // On vérifie que le chiffre est bien entre 1 et le nombre total d'épreuves
                if (choixEpreuve >= 1 && choixEpreuve <= nombreMaxEpreuves) {
                    choixValide = true;
                } else {
                    System.out.println("Choix invalide. Veuillez taper un numéro entre 1 et " + nombreMaxEpreuves + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur de saisie. Veuillez taper un chiffre valide.");
            }
        }

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
                nouveauFormulaire.ajouterFraude(new FraudePapier(LocalDate.now(), "Antisèche papier", "Document caché", dimensions, estPlie));
                System.out.println("-> Fraude papier ajoutée au dossier.");
                break;

            case "2":
                System.out.print("Quel modèle génératif a été utilisé ? (ex: Gemini, ChatGPT) : ");
                String modele = scanner.nextLine();
                nouveauFormulaire.ajouterFraude(new FraudeIAG(LocalDate.now(), "Utilisation d'IA", "Contenu généré", modele));
                System.out.println("-> Fraude IA ajoutée au dossier.");
                break;

            case "3":
                System.out.print("Quelle est la marque de la calculatrice ? : ");
                String marque = scanner.nextLine();
                System.out.print("Quel est le nom du programme intégré dedans ? : ");
                String programme = scanner.nextLine();
                nouveauFormulaire.ajouterFraude(new FraudeCalculatrice(LocalDate.now(), "Antisèche calculatrice", "Formules masquées dans la mémoire", marque, programme));
                System.out.println("-> Fraude calculatrice ajoutée au dossier avec succès !");
                break;

            case "4":
                System.out.print("Quel service en ligne a été interrogé ? (ex: API OpenAI, Claude) : ");
                String service = scanner.nextLine();
                System.out.print("Quelle était l'adresse IP de la connexion illicite ? (ex: 192.168.1.45) : ");
                String ip = scanner.nextLine();
                nouveauFormulaire.ajouterFraude(new FraudeIAGConnectee(LocalDate.now(), "Connexion illicite à un serveur distant", "Requêtes envoyées via API externe", service, ip));
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
        System.out.println("Nombre de formulaires : " + stats.getNbFormulaires());
        System.out.println("Étudiants distincts : " + stats.getNbEtudiantsDistinct());
        System.out.println("Total fraudes : " + stats.calculerTotalFraudes());
        System.out.println("Moyenne fraudes/formulaire : " + stats.calculerMoyenneFraudesParFormulaire());
        System.out.println("Écart-type : " + stats.calculerEcartType());
    }

    /**
     * @brief Parcourt tous les formulaires pour afficher les étudiants et les fraudes en détail.
     */
    private void afficherDetails() {
        System.out.println("\n DÉTAIL DES DOSSIERS DE FRAUDE :");

        if (gestionnaire.getFormulaires().isEmpty()) {
            System.out.println("Aucun dossier enregistré pour le moment.");
            return;
        }

        for (Formulaire f : gestionnaire.getFormulaires()) {
            System.out.println("\n" + f.toString());

            System.out.print("  Étudiants impliqués : ");
            for (Etudiant e : f.getEtudiants()) {
                System.out.print(e.getPrenom() + " " + e.getNom() + " | ");
            }
            System.out.println();

            System.out.println("  Preuves de fraude :");
            for (Fraude fraude : f.getFraudes()) {
                System.out.println("  - " + fraude.afficher());
            }
        }
    }

    private void afficherGraphe() {
        GrapheFraude graphe = gestionnaire.construireGraphe();
        graphe.afficher();
    }

    private void afficherRechercheMenu() {
        System.out.println("\nRECHERCHE :");
        System.out.println("1. Formulaires d'un étudiant (par numéro apprenant)");
        System.out.println("2. Formulaires d'une épreuve (par code ECUE)");
        System.out.println("3. Étudiant par nom");
        System.out.println("4. Étudiant par prénom");
        System.out.println("5. Étudiant par numéro apprenant");
        System.out.print("Votre choix : ");
        String choix = scanner.nextLine();

        switch (choix) {
            case "1":
                System.out.print("Numéro apprenant : ");
                int numero = Integer.parseInt(scanner.nextLine());
                List<Formulaire> parEtudiant = gestionnaire.rechercherFormulaireParEtudiant(numero);
                if (parEtudiant.isEmpty()) {
                    System.out.println("Aucun formulaire trouvé pour ce numéro.");
                } else {
                    for (Formulaire f : parEtudiant) System.out.println("  " + f);
                }
                break;

            case "2":
                System.out.print("Code ECUE : ");
                String code = scanner.nextLine();
                List<Formulaire> parEpreuve = gestionnaire.rechercherParEpreuve(code);
                if (parEpreuve.isEmpty()) {
                    System.out.println("Aucun formulaire trouvé pour cette épreuve.");
                } else {
                    for (Formulaire f : parEpreuve) System.out.println("  " + f);
                }
                break;

            case "3":
                System.out.print("Nom à rechercher : ");
                String nom = scanner.nextLine();
                List<Etudiant> parNom = gestionnaire.rechercherEtudiantParNom(nom);
                if (parNom.isEmpty()) {
                    System.out.println("Aucun étudiant trouvé avec ce nom.");
                } else {
                    for (Etudiant e : parNom) System.out.println("  " + e);
                }
                break;

            case "4":
                System.out.print("Prénom à rechercher : ");
                String prenom = scanner.nextLine();
                List<Etudiant> parPrenom = gestionnaire.rechercherEtudiantParPrenom(prenom);
                if (parPrenom.isEmpty()) {
                    System.out.println("Aucun étudiant trouvé avec ce prénom.");
                } else {
                    for (Etudiant e : parPrenom) System.out.println("  " + e);
                }
                break;

            case "5":
                System.out.print("Numéro apprenant : ");
                int num = Integer.parseInt(scanner.nextLine());
                Etudiant etudiant = gestionnaire.rechercherEtudiantParNumeroApprenant(num);
                if (etudiant == null) {
                    System.out.println("Aucun étudiant trouvé avec ce numéro.");
                } else {
                    System.out.println("  " + etudiant);
                }
                break;

            default:
                System.out.println("Choix non reconnu.");
        }
    }

    private void retirerFormulaireInteractif() {
        System.out.println("\nRETRAIT D'UN DOSSIER :");
        if (gestionnaire.getFormulaires().isEmpty()) {
            System.out.println("Aucun dossier enregistré pour le moment.");
            return;
        }

        System.out.println("Liste des dossiers actuels :");
        for (Formulaire f : gestionnaire.getFormulaires()) {

            System.out.print("- ID: " + f.getId() + " | Épreuve: " + f.getEpreuve().getCodeECUE() + " | Étudiant(s) : ");

            List<Etudiant> suspects = f.getEtudiants();

            for (int i = 0; i < suspects.size(); i++) {
                Etudiant e = suspects.get(i);
                System.out.print(e.getPrenom() + " " + e.getNom());

                // Si c'est pas le dernier étudiant on ajoute une virgule pour lisibilité
                if (i < suspects.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
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
            System.out.println("Erreur : Veuillez saisir un nombre valide.");
        }
    }
}