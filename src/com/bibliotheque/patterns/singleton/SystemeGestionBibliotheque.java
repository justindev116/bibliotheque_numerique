package com.bibliotheque.patterns.singleton;
import java.io.*;
import java.util.*;

import com.bibliotheque.entites.Livre;
import com.bibliotheque.entites.Utilisateur;


public class SystemeGestionBibliotheque implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<Livre> catalogueLivres;
    private final List<Utilisateur> listeUtilisateurs;

    public SystemeGestionBibliotheque() {
        this.catalogueLivres = new ArrayList<>();
        this.listeUtilisateurs = new ArrayList<>();
    }

    /* ============================
       ========== LIVRES ==========
       ============================ */

    public void ajouterLivre(Livre livre) {
        if (livre == null) return;
        catalogueLivres.add(livre);
    }

    public boolean retirerLivreParId(int idLivre) {
        return catalogueLivres.removeIf(l -> l.getId() == idLivre);
    }

    public Livre rechercherLivreParId(Long id) {
        if (id == null) return null;
        for (Livre l : catalogueLivres) {
            if (id.equals(l.getId())) return l;
        }
        return null;
    }

    public boolean retirerLivreParId(Long id) {
        if (id == null) return false;
        return catalogueLivres.removeIf(l -> id.equals(l.getId()));
    }

    public List<Livre> rechercherLivresParTitre(String titrePartiel) {
        if (titrePartiel == null || titrePartiel.isBlank()) return Collections.emptyList();
        String needle = titrePartiel.toLowerCase(Locale.ROOT);
        List<Livre> res = new ArrayList<>();
        for (Livre l : catalogueLivres) {
            if (l.getTitre() != null && l.getTitre().toLowerCase(Locale.ROOT).contains(needle)) {
                res.add(l);
            }
        }
        return res;
    }

    /* ================================
       ========== UTILISATEURS =========
       ================================ */

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        if (utilisateur == null) return;
        listeUtilisateurs.add(utilisateur);
    }

    public Utilisateur rechercherUtilisateurParId(Long id) {
        if (id == null) return null;
        for (Utilisateur u : listeUtilisateurs) {
            // si Utilisateur a getId():
            if (id.equals(u.getId())) return u;
            // sinon si c'est getIdUtilisateur(), remplace par : if (id.equals(u.getIdUtilisateur())) ...
        }
        return null;
    }

    public boolean retirerUtilisateurParId(Long id) {
        if (id == null) return false;
        return listeUtilisateurs.removeIf(u -> {
            // si Utilisateur a getId():
            return id.equals(u.getId());
            // sinon : return id.equals(u.getIdUtilisateur());
        });
    }
    public List<Utilisateur> rechercherUtilisateursParNom(String nomPartiel) {
        if (nomPartiel == null || nomPartiel.isBlank()) return Collections.emptyList();
        String needle = nomPartiel.toLowerCase(Locale.ROOT);
        List<Utilisateur> res = new ArrayList<>();
        for (Utilisateur u : listeUtilisateurs) {
            if (u.getNom() != null && u.getNom().toLowerCase(Locale.ROOT).contains(needle)) {
                res.add(u);
            }
        }
        return res;
    }

    /* ========================================
       ========== SAUVEGARDE / CHARGEMENT ======
       ======================================== */

    public void sauvegarderSysteme(String cheminFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cheminFichier))) {
            oos.writeObject(new ArrayList<>(catalogueLivres));
            oos.writeObject(new ArrayList<>(listeUtilisateurs));
            System.out.println("Système sauvegardé dans " + cheminFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du système: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void chargerSysteme(String cheminFichier) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cheminFichier))) {
            List<Livre> livres = (List<Livre>) ois.readObject();
            List<Utilisateur> utilisateurs = (List<Utilisateur>) ois.readObject();

            // Remplissage "propre" des collections internes
            catalogueLivres.clear();
            catalogueLivres.addAll(livres != null ? livres : Collections.emptyList());

            listeUtilisateurs.clear();
            listeUtilisateurs.addAll(utilisateurs != null ? utilisateurs : Collections.emptyList());

            System.out.println("Système chargé depuis " + cheminFichier);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement du système: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* ====================================
       ========== ACCÈS LECTURE ONLY =======
       ==================================== */

    public List<Livre> getCatalogueLivres() {
        return Collections.unmodifiableList(catalogueLivres);
    }

    public List<Utilisateur> getListeUtilisateurs() {
        return Collections.unmodifiableList(listeUtilisateurs);
    }
}
