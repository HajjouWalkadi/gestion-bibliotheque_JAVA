package org.biblio.model;

public class Collection {
    private Integer id;
    private String titre;
    private String auteur;
    private String ISBN;
    private Integer quantity;

    public Collection(Integer id, String titre, String auteur, String ISBN, Integer quantity) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.ISBN = ISBN;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    // Méthode d'affichage
    public void afficherInfosColl() {
        System.out.println("Information de la collection :");
        System.out.println("ID : " + id);
        System.out.println("titre : " + titre);
        System.out.println("auteur : " + auteur);
        System.out.println("ISBN : " + ISBN);
        System.out.println("Quantity : " + quantity);
    }

}
