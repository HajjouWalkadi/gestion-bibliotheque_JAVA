package org.biblio.model;

public class Livre {
      private Integer id;
      private String numeroLivre;
      private String status;
      private  Collection collection;

      public Livre(Integer id, String numeroLivre, String status, Collection collection) {
            this.id = id;
            this.numeroLivre = numeroLivre;
            this.status = status;
            this.collection = collection;
      }

      public Collection getCollection() {
            return collection;
      }

      public void setCollection(Collection collection) {
            this.collection = collection;
      }

      public Integer getId() {
            return id;
      }

      public void setId(Integer id) {
            this.id = id;
      }

      public String getNumeroLivre() {
            return numeroLivre;
      }

      public void setNumeroLivre(String numeroLivre) {
            this.numeroLivre = numeroLivre;
      }

      public String getStatus() {
            return status;
      }

      public void setStatus(String status) {
            this.status = status;
      }

      public void afficherInfosLivre() {
            System.out.println("Information du livre :");
            System.out.println("ID : " + id);
            System.out.println("numero du livre : " + numeroLivre);
            System.out.println("status : " + status);
            System.out.println("collection : " + collection.getTitre());


      }

}
