package fr.eseo.projet2.modele;

import java.time.LocalDate;

/**
 * @class FraudePapier
 * @brief Représente une fraude utilisant un document physique non autorisé.
 */
public class FraudePapier extends Fraude {
    private String dimensions;
    private boolean pile;

    public FraudePapier() {
        super();
    }

    public FraudePapier(LocalDate dateReleve, String description, String contenu, String dimensions, boolean pile) {
        super(dateReleve, description, contenu);
        this.dimensions = dimensions;
        this.pile = pile;
    }

    public String getDimensions() { return dimensions; }
    public boolean isPile() { return pile; }

    @Override
    public String afficher() {
        return toString() + " | Dimensions : " + dimensions + " | Plié : " + pile;
    }
}