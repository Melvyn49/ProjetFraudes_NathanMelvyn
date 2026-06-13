package fr.eseo.projet2.modele;

import java.time.LocalDate;

/**
 * @class FraudeIAG
 * @brief Spécialisation d'une fraude impliquant un service d'Intelligence Artificielle Générative.
 */

public class FraudeIAG extends Fraude {
    private String nomService;

    /**
     * @brief Constructeur par défaut.
     */
    public FraudeIAG() {
        super();
    }

    /**
     * @brief Constructeur complet.
     * @param dateReleve Date du constat.
     * @param description Description de la triche.
     * @param contenu Prompt ou texte généré récupéré.
     * @param nomService Nom du modèle ou service utilisé (ex: ChatGPT, Claude).
     */
    public FraudeIAG(LocalDate dateReleve, String description, String contenu, String nomService) {
        super(dateReleve, description, contenu);
        this.nomService = nomService;
    }

    public String getNomService() {
        return nomService;
    }

    @Override
    public String afficher() {
        return toString() + " | IA utilisée : " + nomService + " | Contenu : " + contenu;
    }
}