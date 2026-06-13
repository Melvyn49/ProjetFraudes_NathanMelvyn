package fr.eseo.projet2.modele;

import java.util.Objects;

/**
 * @class Etudiant
 * @brief Représente un étudiant de l'établissement.
 * Un étudiant est identifié de manière unique par son numéro apprenant.
 */
public class Etudiant {
    private int numeroApprenant;
    private String nom;
    private String prenom;
    private Cursus cursus;

    /**
     * @brief Constructeur par défaut.
     */
    public Etudiant() {
    }

    /**
     * @brief Constructeur avec paramètres.
     * @param numeroApprenant Identifiant unique de l'étudiant.
     * @param nom Nom de famille.
     * @param prenom Prénom de l'étudiant.
     * @param cursus Année d'étude (E1, E2, etc.).
     */
    public Etudiant(int numeroApprenant, String nom, String prenom, Cursus cursus) {
        this.numeroApprenant = numeroApprenant;
        this.nom = nom;
        this.prenom = prenom;
        this.cursus = cursus;
    }

    public int getNumeroApprenant() {
        return numeroApprenant;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Cursus getCursus() {
        return cursus;
    }

    /**
     * @brief Redéfinition de equals basée sur le numéro apprenant
     */

    @Override
    public boolean equals(Object o) {
        //On compare l'objet avec lui même
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        //On lit les attributs de l'étudiant car on est sur que c'est bienn un etudiant.
        Etudiant etudiant = (Etudiant) o;

        // Maintenant qu'on a accès aux infos, on vérifie si le numéro apprenant de notre
        // étudiant courant (this) est le même que celui de l'étudiant qu'on nous a passé en paramètre.
        return numeroApprenant == etudiant.numeroApprenant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroApprenant);
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " (" + numeroApprenant + ") - " + cursus;
    }
}