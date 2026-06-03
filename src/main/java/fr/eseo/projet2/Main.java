package fr.eseo.projet2;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import fr.eseo.projet2.ihm.InterfaceUtilisateur;

public class Main {
    public static void main(String[] args) {
        // 1. On crée le cerveau de l'application
        GestionnaireFormulaires gestionnaire = new GestionnaireFormulaires();

        // 2. On crée l'interface en lui connectant le cerveau
        InterfaceUtilisateur ihm = new InterfaceUtilisateur(gestionnaire);

        // 3. On lance le menu principal
        ihm.demarrer();
    }
}
