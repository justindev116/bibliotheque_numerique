package com.bibliotheque.patterns.facade;

import com.bibliotheque.dao.FavoriDAO;
import com.bibliotheque.dao.LivreDAO;
import com.bibliotheque.dao.TelechargementDAO;
import com.bibliotheque.dao.UtilisateurDAO;
import com.bibliotheque.entites.Livre;
import com.bibliotheque.entites.Utilisateur;
import com.bibliotheque.utils.HashUtils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class BibliothequeFacade {

    private final LivreDAO livreDAO = new LivreDAO();
    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private final FavoriDAO favoriDAO = new FavoriDAO();
    private final TelechargementDAO telechargementDAO = new TelechargementDAO();

    // ====== LIVRES ======
    public List<Livre> listerLivres() throws SQLException {
        return livreDAO.listAll();
    }

    public Livre trouverLivreParId(Long id) throws SQLException {
        return livreDAO.findById(id);
    }

    public Long ajouterLivre(String titre, String auteur, String description, String categorie, String fichierChemin) throws SQLException {
        Livre l = new Livre(null, titre, auteur, description, categorie, fichierChemin, LocalDateTime.now());
        return livreDAO.create(l);
    }

    // ====== UTILISATEURS (inscription / connexion) ======
    public Long inscription(String nom, String email, String motDePasseClair) throws SQLException {
        if (utilisateurDAO.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }
        String hash = HashUtils.sha256(motDePasseClair);
        Utilisateur u = new Utilisateur(null, nom, email, hash, LocalDateTime.now());
        return utilisateurDAO.create(u);
    }

    public Utilisateur connexion(String email, String motDePasseClair) throws SQLException {
        Utilisateur u = utilisateurDAO.findByEmail(email);
        if (u == null) return null;
        String hash = HashUtils.sha256(motDePasseClair);
        return hash.equals(u.getMotDePasseHash()) ? u : null;
    }

    // ====== FAVORIS ======
    public void ajouterFavori(Long utilisateurId, Long livreId) throws SQLException {
        favoriDAO.add(utilisateurId, livreId);
    }

    public boolean retirerFavori(Long utilisateurId, Long livreId) throws SQLException {
        return favoriDAO.remove(utilisateurId, livreId);
    }

    public List<Livre> favorisUtilisateur(Long utilisateurId) throws SQLException {
        return favoriDAO.listFavoris(utilisateurId);
    }

    // ====== TÉLÉCHARGEMENTS (log) ======
    public void enregistrerTelechargement(Long utilisateurId, Long livreId) throws SQLException {
        telechargementDAO.log(utilisateurId, livreId);
    }

    public int nombreTelechargements(Long livreId) throws SQLException {
        return telechargementDAO.countByLivre(livreId);
    }

}