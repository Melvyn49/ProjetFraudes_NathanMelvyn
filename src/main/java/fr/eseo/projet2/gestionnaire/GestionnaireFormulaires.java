package fr.eseo.projet2.gestionnaire;

import fr.eseo.projet2.modele.Etudiant;
import fr.eseo.projet2.modele.Formulaire;
import java.util.ArrayList;
import java.util.List;
/*test*/
/**
 * @class GestionnaireFormulaires
 * @brief Gère la liste centrale des formulaires de fraude.
 */
public class GestionnaireFormulaires {
    private List<Formulaire> formulaires;

    /**
     * @brief Constructeur par défaut.
     */
    public GestionnaireFormulaires() {
        this.formulaires = new ArrayList<>();
    }

    /**
     * @brief Constructeur avec liste existante.
     * @param formulaires Une liste pré-existante de formulaires.
     */
    public GestionnaireFormulaires(List<Formulaire> formulaires) {
        this.formulaires = formulaires;
    }

    /**
     * @brief Ajoute un nouveau formulaire à la liste.
     * @param f Le formulaire à ajouter.
     */
    public void ajouterFormulaire(Formulaire f) {
        this.formulaires.add(f);
    }

    /**
     * @brief Retire un formulaire via son identifiant unique.
     * @param id L'identifiant du formulaire à supprimer
     * @return true si supprimé avec succès, false sinon
     */
    public boolean retirerFormulaire(int id) {
        return this.formulaires.removeIf(f -> f.getId() == id);
    }

    /**
     * @brief Recherche un étudiant impliqué dans les fraudes par son numéro.
     * @param numero Le numéro apprenant de l'étudiant.
     * @return L'objet Etudiant s'il est trouvé, null sinon.
     */
    public Etudiant rechercherParNumero(int numero) {
        for (Formulaire f : formulaires) {
            for (Etudiant e : f.getEtudiants()) {
                if (e.getNumeroApprenant() == numero) {
                    return e;
                }
            }
        }
        return null;
    }

    public List<Formulaire> getFormulaires() {
        return formulaires;
    }
}