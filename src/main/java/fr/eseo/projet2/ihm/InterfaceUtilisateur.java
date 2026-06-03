package fr.eseo.projet2.ihm;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import java.util.Scanner;

/**
 * @class InterfaceUtilisateur
 * @brief Gère les interactions en ligne de commande avec l'utilisateur.
 */
public class InterfaceUtilisateur {
    private GestionnaireFormulaires gestionnaire;
    private Scanner scanner;

    /**
     * @brief Constructeur par défaut.
     */
    public InterfaceUtilisateur() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * @brief Constructeur avec injection du gestionnaire principal.
     * @param gestionnaire Le gestionnaire manipulant les données de l'application.
     */
    public InterfaceUtilisateur(GestionnaireFormulaires gestionnaire) {
        this.gestionnaire = gestionnaire;
        this.scanner = new Scanner(System.in);
    }

    /**
     * @brief Affiche le menu principal dans la console.
     */
    public void afficherMenu() {
        System.out.println("=== GESTION DES FRAUDES ===");
        System.out.println("1. Ajouter un nouveau formulaire");
        System.out.println("2. Consulter les statistiques");
        System.out.println("3. Quitter l'application");
        System.out.print("Votre choix : ");
    }
}