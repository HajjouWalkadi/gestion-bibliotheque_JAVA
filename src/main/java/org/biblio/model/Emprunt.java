package org.biblio.model;

import org.biblio.database.Db;
import org.biblio.helper.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class Emprunt {
    private Integer id;
    private Date dateDebut;
    private Date dateFin;

    private Integer emprunteur_id;
    private Integer collection_id ;
    private  Integer quantity;

    private Boolean retour;


    public Emprunt() {
        this.retour = false;
        this.dateDebut = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Boolean getRetour() {
        return retour;
    }

    public void setRetour(Boolean retour) {
        this.retour = retour;
    }

    public Integer getEmprunteur_id() {
        return emprunteur_id;
    }

    public void setEmprunteur_id(Integer emprunteur_id) {
        this.emprunteur_id = emprunteur_id;
    }

    public Integer getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(Integer collection_id) {
        this.collection_id = collection_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public  void  reserve(){

        Connection con = Db.connect(); // Assuming you have a Db class for database connection

        try {
            String query = "INSERT INTO emprunt (dateDebut, dateFin, retour, emprunteur_id, collection_id, quantity) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            // Set values for the PreparedStatement
            preparedStatement.setDate(1, new java.sql.Date(dateDebut.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(dateFin.getTime()));
            preparedStatement.setBoolean(3, retour);
            preparedStatement.setInt(4, emprunteur_id);
            preparedStatement.setInt(5, collection_id);
            preparedStatement.setInt(6, quantity);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                Printer.print("Reservation successfully added to the database.");
            } else {
                Printer.print("Failed to add the reservation to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
