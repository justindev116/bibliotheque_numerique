package com.bibliotheque.entites;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class CategorieLivre implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomCategorie;
    private int dureeEmpruntMaxJour;
    private double facteurAmende;


    public static final CategorieLivre ROMAN = new CategorieLivre("ROMAN", 21, 1.0);
    public static final CategorieLivre SCIENCE_FICTION = new CategorieLivre("SCIENCE_FICTION", 14, 1.2);
    public static final CategorieLivre MAGAZINE = new CategorieLivre("MAGAZINE", 7, 0.8);
    public static final CategorieLivre AUTRE = new CategorieLivre("AUTRE", 14, 1.0);

    private static final Map<String, CategorieLivre> categoriesParNom = new HashMap<>();

    static {

        categoriesParNom.put("ROMAN", ROMAN);
        categoriesParNom.put("SCIENCE_FICTION", SCIENCE_FICTION);
        categoriesParNom.put("MAGAZINE", MAGAZINE);
        categoriesParNom.put("AUTRE", AUTRE);
    }


    public CategorieLivre(String nomCategorie, int dureeEmpruntMaxJour, double facteurAmende) {
        this.nomCategorie = nomCategorie;
        this.dureeEmpruntMaxJour = dureeEmpruntMaxJour;
        this.facteurAmende = facteurAmende;
        System.out.println("Catégorie de livre créée: " + nomCategorie);
    }

    public static CategorieLivre valueOf(String nom) {
        if (nom == null) {
            return AUTRE;
        }

        CategorieLivre categorie = categoriesParNom.get(nom.toUpperCase());
        return categorie != null ? categorie : AUTRE;
    }


    public String getNomCategorie() {
        return nomCategorie;
    }

    public int getDureeEmpruntMaxJour() {
        return dureeEmpruntMaxJour;
    }

    public double getFacteurAmende() {
        return facteurAmende;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public void setDureeEmpruntMaxJour(int dureeEmpruntMaxJour) {
        this.dureeEmpruntMaxJour = dureeEmpruntMaxJour;
    }

    public void setFacteurAmende(double facteurAmende) {
        this.facteurAmende = facteurAmende;
    }

    @Override
    public String toString() {
        return nomCategorie;
    }
}
