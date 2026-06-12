package fr.eseo.projet2.modele;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @class Formulaire
 * @brief Document principal reliant une épreuve, des étudiants et des fraudes.
 */
public class Formulaire {  //génère un identifiant unique et automatique pour chaque nouveau dossier
    private static int compteurId = 1;

    private int id;
    private LocalDate dateCreation;
    private LocalDate dateModification;

    private Epreuve epreuve;
    private List<Etudiant> etudiants;
    private List<Fraude> fraudes;

    /**
     * @brief Constructeur par défaut.
     */
    public Formulaire() {
        this.id = compteurId++;
        this.dateCreation = LocalDate.now();
        this.dateModification = this.dateCreation;
        this.etudiants = new ArrayList<>();
        this.fraudes = new ArrayList<>();
    }

    /**
     * @brief Constructeur avec une épreuve.
     * @param epreuve L'épreuve concernée par la fraude.
     */
    public Formulaire(Epreuve epreuve) {
        this(); // Appelle le constructeur par défaut pour initialiser l'ID et les listes
        this.epreuve = epreuve;
    }

    /**
     * @brief Ajoute un étudiant au formulaire et met à jour la date de modification.
     * @param etudiant L'étudiant impliqué.
     */
    public void ajouterEtudiant(Etudiant etudiant) {
        if (!etudiants.contains(etudiant)) {
            etudiants.add(etudiant);
            this.dateModification = LocalDate.now();
        }
    }

    /**
     * @brief Ajoute une fraude au formulaire et met à jour la date de modification.
     * @param fraude La fraude constatée.
     */
    public void ajouterFraude(Fraude fraude) {
        fraudes.add(fraude);
        this.dateModification = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public List<Fraude> getFraudes() {
        return fraudes;
    }

    /**
     * @brief Réinitialise le compteur d'identifiant
     */

    public static void resetCompteur() {
        compteurId = 1;
    }

    @Override
    public String toString() {
        return "Formulaire n°" + id + " | Épreuve : " + (epreuve != null ? epreuve.getCodeECUE() : "N/A") + " | Étudiants : " + etudiants.size() + " | Fraudes : " + fraudes.size();
    }
}