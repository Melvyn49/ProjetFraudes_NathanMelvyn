package fr.eseo.projet2.graphe;

import fr.eseo.projet2.modele.Etudiant;
import fr.eseo.projet2.modele.Formulaire;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @class GrapheFraude
 * @brief Modélise le réseau de liens (plagiats) entre les étudiants.
 */
public class GrapheFraude {

    private Map<Etudiant, Set<Etudiant>> adjacence;

    public GrapheFraude() {
        this.adjacence = new HashMap<>();
    }

    public void construire(List<Formulaire> formulaires) {
        adjacence.clear();

        for (Formulaire f : formulaires) {
            List<Etudiant> etudiantsImpliques = f.getEtudiants();

            for (Etudiant e : etudiantsImpliques) {
                ajouterSommet(e);
            }

            for (int i = 0; i < etudiantsImpliques.size(); i++) {
                for (int j = i + 1; j < etudiantsImpliques.size(); j++) {
                    ajouterArete(etudiantsImpliques.get(i), etudiantsImpliques.get(j));
                }
            }
        }
    }

    public void ajouterSommet(Etudiant etudiant) {
        if (!adjacence.containsKey(etudiant)) {
            adjacence.put(etudiant, new HashSet<>());
        }
    }

    public void ajouterArete(Etudiant a, Etudiant b) {
        ajouterSommet(a);
        ajouterSommet(b);

        if (!a.equals(b)) {
            adjacence.get(a).add(b);
            adjacence.get(b).add(a);
        }
    }

    public void afficher() {
        System.out.println("\n--- RÉSEAU DE PLAGIAT POTENTIEL ---");

        if (adjacence.isEmpty()) {
            System.out.println("Aucun étudiant suspecté dans la base.");
            return;
        }

        for (Map.Entry<Etudiant, Set<Etudiant>> entree : adjacence.entrySet()) {
            Etudiant etudiantSuspect = entree.getKey();
            Set<Etudiant> sesComplices = entree.getValue();

            System.out.print(" -> " + etudiantSuspect.getPrenom() + " " + etudiantSuspect.getNom() + " est lié(e) à : ");

            if (sesComplices.isEmpty()) {
                System.out.println("Personne (Fraude isolée)");
            } else {
                for (Etudiant complice : sesComplices) {
                    System.out.print(complice.getPrenom() + " " + complice.getNom() + " | ");
                }
                System.out.println();
            }
        }
    }

    public Map<Etudiant, Set<Etudiant>> getAdjacence() {
        return adjacence;
    }
}