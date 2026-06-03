package fr.eseo.projet2.stats;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import fr.eseo.projet2.modele.Formulaire;

/**
 * @class Statistiques
 * @brief Fournit des méthodes de calcul statistique sur les données de fraude.
 */
public class Statistiques {
    private GestionnaireFormulaires gestionnaire;

    /**
     * @brief Constructeur par défaut.
     */
    public Statistiques() {
    }

    /**
     * @brief Constructeur liant les statistiques au gestionnaire de données.
     * @param gestionnaire Le gestionnaire contenant les formulaires à analyser.
     */
    public Statistiques(GestionnaireFormulaires gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    /**
     * @brief Calcule le nombre total de fraudes enregistrées.
     * @return Le total des fraudes.
     */
    public int calculerTotalFraudes() {
        if (gestionnaire == null) return 0;
        int total = 0;
        for (Formulaire f : gestionnaire.getFormulaires()) {
            total += f.getFraudes().size();
        }
        return total;
    }

    /**
     * @brief Calcule la moyenne du nombre de fraudes par formulaire.
     * @return La moyenne sous forme de nombre décimal.
     */
    public double calculerMoyenneFraudesParFormulaire() {
        if (gestionnaire == null || gestionnaire.getFormulaires().isEmpty()) return 0.0;
        return (double) calculerTotalFraudes() / gestionnaire.getFormulaires().size();
    }
}