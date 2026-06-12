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
        // 1. OPTIMISATION : Est-ce qu'on compare l'objet avec lui-même ?
        // Si c'est exactement la même "boîte" en mémoire, on gagne du temps, c'est forcément vrai.
        if (this == o) return true;

        // 2. SÉCURITÉ : L'objet est-il vide ou d'une famille différente ?
        // Si on compare avec du vide (null) ou avec une autre classe (ex: une Fraude),
        // c'est impossible qu'ils soient égaux, donc on renvoie faux direct.
        if (o == null || getClass() != o.getClass()) return false;

        // 3. CASTING : On met l'étiquette "Etudiant"
        // À ce stade, on est sûr que l'objet 'o' est bien un Etudiant, mais Java le voit
        // toujours comme un objet générique. On le "transforme" (cast) pour pouvoir lire ses attributs.
        Etudiant etudiant = (Etudiant) o;

        // 4. LA VRAIE COMPARAISON
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