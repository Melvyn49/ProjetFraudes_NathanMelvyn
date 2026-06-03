package fr.eseo.projet2.modele;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @class Epreuve
 * @brief Représente une épreuve d'évaluation avec sa date et ses modalités.
 */
public class Epreuve {
    private String codeECUE;
    private LocalDate date;
    private LocalTime heure;
    private int duree;
    private Modalite modalite;

    /**
     * @brief Constructeur par défaut.
     */
    public Epreuve() {
    }

    /**
     * @brief Constructeur initialisant l'ensemble des attributs.
     * @param codeECUE Code de l'épreuve.
     * @param date Date de passage.
     * @param heure Heure de début.
     * @param duree Durée en minutes.
     * @param modalite Type d'évaluation.
     */
    public Epreuve(String codeECUE, LocalDate date, LocalTime heure, int duree, Modalite modalite) {
        this.codeECUE = codeECUE;
        this.date = date;
        this.heure = heure;
        this.duree = duree;
        this.modalite = modalite;
    }

    public String getCodeECUE() { return codeECUE; }
    public Modalite getModalite() { return modalite; }

    @Override
    public String toString() {
        return codeECUE + " (" + modalite + ") le " + date;
    }
}
