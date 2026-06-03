package fr.eseo.projet2.modele;
import java.time.LocalDate;

/**
 * @class FraudeIAGConnectee
 * @brief Spécialisation d'une fraude IAG impliquant un accès réseau.
 */
public class FraudeIAGConnectee extends FraudeIAG {
    private String adresseIP;

    public FraudeIAGConnectee() {
        super();
    }

    public FraudeIAGConnectee(LocalDate dateReleve, String description, String contenu, String nomService, String adresseIP) {
        super(dateReleve, description, contenu, nomService);
        this.adresseIP = adresseIP;
    }

    public String getAdresseIP() { return adresseIP; }

    @Override
    public String afficher() {
        return super.afficher() + " | IP : " + adresseIP;
    }
}