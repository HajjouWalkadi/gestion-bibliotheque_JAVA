package org.biblio.model;

import org.biblio.database.Db;
import org.biblio.helper.Printer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Emprunt {
    private Livre livre;
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

    public void setLivre(Livre livre) {
        this.livre = livre;
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

        Connection con = Db.connect();

        try {
            String query = "INSERT INTO emprunt (dateDebut, dateFin, retour, emprunteur_id, livre_id, quantity) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setDate(1, new java.sql.Date(this.dateDebut.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(this.dateFin.getTime()));
            preparedStatement.setBoolean(3, this.retour);
            preparedStatement.setInt(4, this.emprunteur_id);
            preparedStatement.setInt(5, this.livre.getId());
            preparedStatement.setInt(6, this.quantity);

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

    public static boolean existsEmprunt(int collectionId, int emprunteurId) {
        try {

            String sql = "SELECT COUNT(*) FROM emprunt JOIN livre ON livre.id = emprunt.livre_id WHERE livre.collection_id = ? AND emprunteur_id = ?";
            PreparedStatement statement = Db.connect().prepareStatement(sql);
            statement.setInt(1, collectionId);
            statement.setInt(2, emprunteurId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // If count is greater than 0, a matching record exists
            }

            return false; // No matching record found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getQuantityBorrowed(int collectionId, int emprunteurId) {
        int totalQuantityBorrowed = 0;

        try {
            String sql = "SELECT SUM(quantity) FROM emprunt " +
                    "JOIN livre ON livre.id = emprunt.livre_id " +
                    "WHERE livre.collection_id = ? AND emprunt.emprunteur_id = ?";

            PreparedStatement statement = Db.connect().prepareStatement(sql);
            statement.setInt(1, collectionId);
            statement.setInt(2, emprunteurId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalQuantityBorrowed = resultSet.getInt(1);
                System.out.println(totalQuantityBorrowed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalQuantityBorrowed;
    }


    public static void returnBook(int collectionId, int emprunteurId, int quantityReturned) {
        Connection con = Db.connect();

        try {

            String query = "SELECT * FROM emprunt JOIN livre ON livre.id = emprunt.livre_id WHERE livre.collection_id = ? AND emprunteur_id = ? AND quantity >= ? AND retour = 0";
            PreparedStatement checkStatement = con.prepareStatement(query);
            checkStatement.setInt(1, collectionId);
            checkStatement.setInt(2, emprunteurId);
            checkStatement.setInt(3, quantityReturned);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {

                int empruntId = resultSet.getInt("id");



                String updateQuery = "UPDATE emprunt SET retour = 1 WHERE id = ? AND  quantity = ? ";
                PreparedStatement updateStatement = con.prepareStatement(updateQuery);
                updateStatement.setInt(1, empruntId);
                updateStatement.setInt(2, quantityReturned);
                int rowsUpdated = updateStatement.executeUpdate();


                if (rowsUpdated > 0) {
                    returnCollectionQuantity(collectionId, quantityReturned);
                    Printer.print("Book returned successfully.");
                } else {
                    Printer.print("Failed to update the reservation status.");
                }



                String updateQueryQuantity = "UPDATE emprunt SET quantity = quantity-? WHERE id = ?";
                PreparedStatement updateStatementQuantity = con.prepareStatement(updateQueryQuantity);
                updateStatementQuantity.setInt(1, quantityReturned);
                updateStatementQuantity.setInt(2, empruntId);

                int rowsUpdatedQuantity = updateStatementQuantity.executeUpdate();


                if (rowsUpdatedQuantity > 0) {
                    returnCollectionQuantity(collectionId, quantityReturned);
                    Printer.print("Book returned successfully.");
                } else {
                    Printer.print("Failed to update the reservation status (quantity).");
                }
            } else {
                Printer.print("No matching reservation found for return.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void updateEmpruntReturn(int empruntId) {
    }

    public static void returnCollectionQuantity(int collectionId, int quantityReturned) {
        Connection con = Db.connect();

        try {
            String query = "UPDATE collection SET quantity_disponible = quantity_disponible + ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, quantityReturned);
            preparedStatement.setInt(2, collectionId);

            //System.out.println(preparedStatement);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                Printer.print("Collection quantity updated successfully.");
            } else {
                Printer.print("Failed to update collection quantity.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
