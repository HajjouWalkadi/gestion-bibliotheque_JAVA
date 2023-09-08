/*
package org.biblio.model;

import org.biblio.database.Db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
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

    public static List<Collection> getCollections(){
        List<Collection> collections = null;
        String sqlQuery = "SELECT * FROM collection";
        try {
            PreparedStatement preparedStatement = Db.connect().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Collection collection = new Collection(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getString("isbn"),
                        resultSet.getInt("id")
                        );
                Collections.add(collection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return collections;
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
    */
/*public void afficherInfosColl() {
        System.out.println("Information de la collection :");
        System.out.println("ID : " + id);
        System.out.println("titre : " + titre);
        System.out.println("auteur : " + auteur);
        System.out.println("ISBN : " + ISBN);
        System.out.println("Quantity : " + quantity);
    }*//*


}
*/


/*
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

    public static List<Collection> getCollections() {
        List<Collection> collections = new ArrayList<>();
        String sqlQuery = "SELECT * FROM collection";
        try {
            PreparedStatement preparedStatement = Db.connect().prepareStatement(sqlQuery);
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

    public int getId() {
        return id;
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
*/

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


