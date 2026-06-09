package fr.eseo.projet2.stats;

import fr.eseo.projet2.gestionnaire.GestionnaireFormulaires;
import fr.eseo.projet2.modele.Etudiant;
import fr.eseo.projet2.modele.Formulaire;
import java.util.HashSet;
import java.util.Set;

/**
 * @class Statistiques
 * @brief Fournit des méthodes de calcul statistique sur les données de fraude.
 */
public class Statistiques {
    private GestionnaireFormulaires gestionnaire;

    /** @brief Constructeur par défaut. */
    public Statistiques() {}

    /**
     * @brief Constructeur liant les statistiques au gestionnaire de données.
     * @param gestionnaire Le gestionnaire contenant les formulaires à analyser.
     */
    public Statistiques(GestionnaireFormulaires gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    /**
     * @brief Retourne le nombre total de formulaires enregistrés.
     * @return Le nombre de formulaires.
     */
    public int getNbFormulaires() {
        if (gestionnaire == null) return 0;
        return gestionnaire.getFormulaires().size();
    }

    /**
     * @brief Retourne le nombre d'étudiants distincts impliqués dans au moins un formulaire.
     * @return Le nombre d'étudiants uniques.
     */
    public int getNbEtudiantsDistinct() {
        if (gestionnaire == null) return 0;
        Set<Etudiant> distincts = new HashSet<>();
        for (Formulaire f : gestionnaire.getFormulaires()) {
            distincts.addAll(f.getEtudiants());
        }
        return distincts.size();
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

    /**
     * @brief Calcule l'écart-type du nombre de fraudes par formulaire.
     * @return L'écart-type sous forme de nombre décimal.
     */
    public double calculerEcartType() {
        if (gestionnaire == null || gestionnaire.getFormulaires().isEmpty()) return 0.0;
        double moyenne = calculerMoyenneFraudesParFormulaire();
        double sommeCarre = 0.0;
        for (Formulaire f : gestionnaire.getFormulaires()) {
            double diff = f.getFraudes().size() - moyenne;
            sommeCarre += diff * diff;
        }
        return Math.sqrt(sommeCarre / gestionnaire.getFormulaires().size());
    }
}