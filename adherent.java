 // IntelliJ API Decompiler stub source generated from a class file
  // Implementation of methods is not available

package com.bibliotheque.entites;

public class Adherent implements com.bibliotheque.patterns.observer.Observateur, java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int idAdherent;
    private java.lang.String nom;
    private java.lang.String prenom;
    private java.lang.String coordonnees;
    private java.util.List<com.bibliotheque.entites.Emprunt> livresEmpruntes;
    private java.util.List<com.bibliotheque.entites.Reservation> reservations;

    public Adherent(int idAdherent, java.lang.String nom, java.lang.String prenom, java.lang.String coordonnees) { /* compiled code */ }

    public int getIdAdherent() { /* compiled code */ }

    public java.lang.String getNom() { /* compiled code */ }

    public java.lang.String getPrenom() { /* compiled code */ }

    public java.lang.String getCoordonnees() { /* compiled code */ }

    public java.util.List<com.bibliotheque.entites.Emprunt> getLivresEmpruntes() { /* compiled code */ }

    public java.util.List<com.bibliotheque.entites.Reservation> getReservations() { /* compiled code */ }

    public void setNom(java.lang.String nom) { /* compiled code */ }

    public void setPrenom(java.lang.String prenom) { /* compiled code */ }

    public void setCoordonnees(java.lang.String coordonnees) { /* compiled code */ }

    public void ajouterEmprunt(com.bibliotheque.entites.Emprunt emprunt) { /* compiled code */ }

    public void retirerEmprunt(com.bibliotheque.entites.Emprunt emprunt) { /* compiled code */ }

    public void ajouterReservation(com.bibliotheque.entites.Reservation reservation) { /* compiled code */ }

    public void retirerReservation(com.bibliotheque.entites.Reservation reservation) { /* compiled code */ }

    public void actualiser(com.bibliotheque.patterns.observer.Sujet sujet, java.lang.Object arg) { /* compiled code */ }

    public java.lang.String toString() { /* compiled code */ }
}