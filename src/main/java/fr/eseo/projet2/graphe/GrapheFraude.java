package fr.eseo.projet2.graphe;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;

/**
 * @class GrapheFraude
 * @brief Modélise le réseau de liens (plagiats) entre les étudiants.
 */
public class GrapheFraude {
    private GestionnaireFormulaires gestionnaire;

    /**
     * @brief Constructeur par défaut.
     */
    public GrapheFraude() {
    }

    /**
     * @brief Constructeur initialisant le graphe avec les données du gestionnaire.
     * @param gestionnaire Le gestionnaire contenant les données source.
     */
    public GrapheFraude(GestionnaireFormulaires gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    /**
     * @brief Méthode simulant la génération de l'affichage du réseau.
     */
    public void genererGraphe() {
        System.out.println("Génération textuelle du réseau de plagiats...");
        // La logique d'affichage des nœuds et arêtes viendra ici
    }
}