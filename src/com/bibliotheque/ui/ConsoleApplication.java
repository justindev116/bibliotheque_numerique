package com.bibliotheque.ui;


import com.bibliotheque.patterns.facade.BibliothequeFacade;
import com.bibliotheque.entites.Livre;
import com.bibliotheque.entites.Utilisateur;

import java.util.Collection;
import java.util.List;

public class ConsoleApplication {
    public static void main(String[] args) {
        try {
            BibliothequeFacade app = new BibliothequeFacade();

            // Inscription (idempotent si déjà créé)
            try {
                Long uid = app.inscription("Alice", "ismail@gmail.com", "secret123");
                System.out.println("Inscrite avec id=" + uid);
            } catch (IllegalArgumentException e) {
                System.out.println("Inscription: " + e.getMessage());
            }

            // Connexion
            Utilisateur u = app.connexion("ismail@gmail.com", "secret123");
            if (u == null) {
                System.out.println("Connexion échouée");
                return;
            }
            System.out.println("Connectée: " + u.getEmail());

            // Ajouter un livre de test (si tu en as pas déjà)
            Long livreId = app.ajouterLivre(
                    "Les Misérables", "Victor Hugo", "Classique français",
                    "Roman", "/files/les_miserables.pdf"
            );
            System.out.println("Livre ajouté id=" + livreId);

            // Lister les livres
            List<Livre> livres = app.listerLivres();
            System.out.println("Livres disponibles:");
            for (Livre l : livres) {
                System.out.println("- [" + l.getId() + "] " + l.getTitre() + " — " + l.getAuteur());
            }

            // Favoris
            app.ajouterFavori(u.getId(), livreId);
            System.out.println("Ajouté aux favoris.");
            System.out.println("Favoris de " + u.getNom() + " : " + app.favorisUtilisateur(u.getId()).size());

            // “Télécharger” = on log l’événement
            app.enregistrerTelechargement(u.getId(), livreId);
            System.out.println("Téléchargements de ce livre: " + app.nombreTelechargements(livreId));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
