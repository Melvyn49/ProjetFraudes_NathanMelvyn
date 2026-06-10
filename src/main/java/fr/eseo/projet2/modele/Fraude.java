package fr.eseo.projet2.modele;

import java.time.LocalDate;

/**
 * @class Fraude
 * @brief Classe abstraite représentant une fraude générique.
 */
public abstract class Fraude {
    protected LocalDate dateReleve;
    protected String description;
    protected String contenu;

    /**
     * @brief Constructeur par défaut.
     */
    public Fraude() {
    }

    /**
     * @brief Constructeur avec paramètres.
     * @param dateReleve Date à laquelle la fraude a été constatée.
     * @param description Description contextuelle de la triche.
     * @param contenu Contenu spécifique (texte de l'antisèche, prompt, etc.).
     */
    public Fraude(LocalDate dateReleve, String description, String contenu) {
        this.dateReleve = dateReleve;
        this.description = description;
        this.contenu = contenu;
    }

    public LocalDate getDateReleve() {
        return dateReleve;
    }

    public String getDescription() {
        return description;
    }

    public String getContenu() {
        return contenu;
    }

    /**
     * @brief Méthode abstraite pour forcer l'affichage spécifique aux classes filles.
     * @return Une chaîne de caractères décrivant la fraude.
     */
    public abstract String afficher();

    @Override
    public String toString() {
        return "Fraude le " + dateReleve + " : " + description;
    }
}