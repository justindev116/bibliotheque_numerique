package com.bibliotheque.patterns.factory;

import com.bibliotheque.entites.Livre;
import com.bibliotheque.entites.CategorieLivre;


public class LivreFactory {

    public static Livre creerLivre(String type, int idLivre, String titre, String auteur, String isbn, CategorieLivre categorie) {
        System.out.println("LivreFactory: Tentative de création d'un livre de type '" + type + "'");

        if (type != null && !type.trim().isEmpty()) {

            Livre nouveauLivre = new Livre((long) idLivre, titre, auteur, isbn, categorie.getNomCategorie(), null, null);
            System.out.println("LivreFactory: Livre créé avec succès: " + nouveauLivre.getTitre());
            return nouveauLivre;
        } else {
            System.err.println("LivreFactory: Type de livre non spécifié ou invalide.");
            return null;
        }
    }

}

