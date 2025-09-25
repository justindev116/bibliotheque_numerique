package com.bibliotheque.entites;


import java.time.LocalDateTime;

public class Livre {
    private Long id;
    private String titre;
    private String auteur;
    private String description;
    private String categorie;
    private String fichierChemin; // chemin local ou URL vers le fichier
    private LocalDateTime datePublication;

    public Livre(){}

    public Livre(Long id, String titre, String auteur, String description, String categorie, String fichierChemin, LocalDateTime datePublication) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.description = description;
        this.categorie = categorie;
        this.fichierChemin = fichierChemin;
        this.datePublication = datePublication;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public String getFichierChemin() { return fichierChemin; }
    public void setFichierChemin(String fichierChemin) { this.fichierChemin = fichierChemin; }
    public LocalDateTime getDatePublication() { return datePublication; }
    public void setDatePublication(LocalDateTime datePublication) { this.datePublication = datePublication; }


}
