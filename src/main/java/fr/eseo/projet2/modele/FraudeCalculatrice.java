package fr.eseo.projet2.modele;

import java.time.LocalDate;

/**
 * @class FraudeCalculatrice
 * @brief Représente une fraude utilisant un programme stocké sur calculatrice.
 */
public class FraudeCalculatrice extends Fraude {
    private String marque;
    private String programme;

    public FraudeCalculatrice() {
        super();
    }

    public FraudeCalculatrice(LocalDate dateReleve, String description, String contenu, String marque, String programme) {
        super(dateReleve, description, contenu);
        this.marque = marque;
        this.programme = programme;
    }

    public String getMarque() { return marque; }
    public String getProgramme() { return programme; }

    @Override
    public String afficher() {
        return toString() + " | Marque : " + marque + " | Programme : " + programme;
    }
}