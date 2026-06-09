package fr.eseo.projet2.gestionnaire;

import fr.eseo.projet2.modele.Etudiant;
import fr.eseo.projet2.modele.Formulaire;
import fr.eseo.projet2.graphe.GrapheFraude;
import fr.eseo.projet2.modele.Epreuve;
import fr.eseo.projet2.stats.Statistiques;
import java.util.ArrayList;
import java.util.List;

/*test*/
/**
 * @class GestionnaireFormulaires
 * @brief Gère la liste centrale des formulaires de fraude.
 */
public class GestionnaireFormulaires {
    private List<Formulaire> formulaires;

    // NOUVEAU : La liste des épreuves exigée par l'UML
    private List<Epreuve> epreuves;

    /** @brief Constructeur par défaut. */
    public GestionnaireFormulaires() {
        this.formulaires = new ArrayList<>();
        this.epreuves = new ArrayList<>(); // NOUVEAU
    }

    /**
     * @brief Constructeur avec liste existante.
     * @param formulaires Une liste pré-existante de formulaires.
     */
    public GestionnaireFormulaires(List<Formulaire> formulaires) {
        this.formulaires = formulaires;
        this.epreuves = new ArrayList<>();
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
     * @param id L'identifiant du formulaire à supprimer.
     * @return true si supprimé avec succès, false sinon.
     */
    public boolean retirerFormulaire(int id) {
        return this.formulaires.removeIf(f -> f.getId() == id);
    }

    /**
     * @brief Recherche un étudiant par son numéro apprenant.
     * @param numero Le numéro apprenant de l'étudiant.
     * @return L'objet Etudiant s'il est trouvé, null sinon.
     */
    public Etudiant rechercherParNumeroApprenant(int numero) {
        for (Formulaire f : formulaires) {
            for (Etudiant e : f.getEtudiants()) {
                if (e.getNumeroApprenant() == numero) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * @brief Retourne tous les formulaires impliquant un étudiant donné.
     * @param numeroApprenant Le numéro apprenant de l'étudiant recherché.
     * @return La liste des formulaires correspondants.
     */
    public List<Formulaire> rechercherParEtudiant(int numeroApprenant) {
        List<Formulaire> resultat = new ArrayList<>();
        for (Formulaire f : formulaires) {
            for (Etudiant e : f.getEtudiants()) {
                if (e.getNumeroApprenant() == numeroApprenant) {
                    resultat.add(f);
                    break;
                }
            }
        }
        return resultat;
    }

    /**
     * @brief Retourne tous les formulaires concernant une épreuve donnée.
     * @param codeECUE Le code de l'épreuve recherchée.
     * @return La liste des formulaires correspondants.
     */
    public List<Formulaire> rechercherParEpreuve(String codeECUE) {
        List<Formulaire> resultat = new ArrayList<>();
        for (Formulaire f : formulaires) {
            if (f.getEpreuve() != null && f.getEpreuve().getCodeECUE().equals(codeECUE)) {
                resultat.add(f);
            }
        }
        return resultat;
    }

    /**
     * @brief Recherche des étudiants par nom (insensible à la casse).
     * @param nom Le nom à rechercher.
     * @return La liste des étudiants correspondants.
     */
    public List<Etudiant> rechercherParNom(String nom) {
        List<Etudiant> resultat = new ArrayList<>();
        for (Formulaire f : formulaires) {
            for (Etudiant e : f.getEtudiants()) {
                if (e.getNom().equalsIgnoreCase(nom) && !resultat.contains(e)) {
                    resultat.add(e);
                }
            }
        }
        return resultat;
    }

    /**
     * @brief Recherche des étudiants par prénom (insensible à la casse).
     * @param prenom Le prénom à rechercher.
     * @return La liste des étudiants correspondants.
     */
    public List<Etudiant> rechercherParPrenom(String prenom) {
        List<Etudiant> resultat = new ArrayList<>();
        for (Formulaire f : formulaires) {
            for (Etudiant e : f.getEtudiants()) {
                if (e.getPrenom().equalsIgnoreCase(prenom) && !resultat.contains(e)) {
                    resultat.add(e);
                }
            }
        }
        return resultat;
    }

    /**
     * @brief Retourne la liste de tous les formulaires.
     * @return La liste des formulaires.
     */
    public List<Formulaire> getFormulaires() {
        return formulaires;
    }

    public void ajouterEpreuve(Epreuve epreuve) {
        this.epreuves.add(epreuve);
    }

    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    public Statistiques calculerStatistiques() {
        return new Statistiques(this);
    }

    /**
     * @brief Construit et retourne le graphe des fraudes.
     * @return Le graphe généré.
     */
    public GrapheFraude construireGraphe() {
        GrapheFraude graphe = new GrapheFraude();
        graphe.construire(this.formulaires);
        return graphe;
    }
}