package org.biblio.model;

import org.biblio.database.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public Collection(){}

    // Getter methods for attributes
    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getISBN() {
        return ISBN;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public static List<Collection> getCollections(String titre) {
        List<Collection> collections = new ArrayList<Collection>();
        String sqlQuery = "SELECT * FROM collection";
        if (titre != null){
            sqlQuery += " WHERE titre = ?";
        }
        try {
            PreparedStatement preparedStatement = Db.connect().prepareStatement(sqlQuery);
            if (titre != null){
                preparedStatement.setString(1,titre);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Collection collection = new Collection(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getString("ISBN"),
                        resultSet.getInt("quantity")
                );
                collections.add(collection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return collections;
    }

    public static void ajouterCollection(Collection collection) {
        Connection connection = Db.connect();
        if (connection == null) {
            System.err.println("La connexion à la base de données a échoué.");
            return;
        }

        String sql = "INSERT INTO collection (titre, auteur, ISBN, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, collection.getTitre());
            preparedStatement.setString(2, collection.getAuteur());
            preparedStatement.setString(3, collection.getISBN());
            preparedStatement.setInt(4, collection.getQuantity());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Collection ajoutée avec succès !");
            } else {
                System.err.println("Échec de l'ajout de la collection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


