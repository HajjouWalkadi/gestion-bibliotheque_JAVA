package org.biblio.model;

import org.biblio.database.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Livre {
    private int id;
    private String numeroLivre;
    private String status;
    private Collection collection;

    public Livre(String numeroLivre, String status, Collection collection) {
        this.numeroLivre = numeroLivre;
        this.status = status;
        this.collection = collection;
    }
    public Livre(int id,String numeroLivre, String status, Collection collection) {
        this.id = id;
        this.numeroLivre = numeroLivre;
        this.status = status;
        this.collection = collection;
    }
    public Livre(){

    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public int getId() {
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


    public static void ajouterLivre(Livre livre) {
        Connection connection = Db.connect();
        if (connection == null) {
            System.err.println("La connexion à la base de données a échoué.");
            return;
        }

        String sql = "INSERT INTO livre (numeroLivre, status, collection_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, livre.getNumeroLivre());
            preparedStatement.setString(2, livre.getStatus());

            // Ensure the collection is not null
            Collection collection = livre.getCollection();
            if (collection != null) {
                preparedStatement.setInt(3, collection.getId());
            } else {
                preparedStatement.setNull(3, java.sql.Types.INTEGER); // Use java.sql.Types.INTEGER for nullable columns
            }

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livre ajouté avec succès !");
            } else {
                System.err.println("Échec de l'ajout du livre.");
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



    public static void supprimerLivre(int livreId) {
        Connection connection = Db.connect();
        if (connection == null) {
            System.err.println("La connexion à la base de données a échoué.");
            return;
        }

        String sql = "DELETE FROM livre WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, livreId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livre supprimé avec succès !");
            } else {
                System.err.println("Échec de la suppression du livre.");
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

 /*   public void displayBooks() {
        Connection connection = Db.connect();
        if (connection == null) {
            System.err.println("La connexion à la base de données a échoué.");
            return;
        }
        try {
            // Prepare a SQL query to retrieve all books
            String query = "SELECT * FROM livre";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Execute the query and retrieve the results
            ResultSet resultSet = preparedStatement.executeQuery();


            // Close resources
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


    public void update() {
        Connection connection = Db.connect();
        if (connection == null) {
            System.err.println("La connexion à la base de données a échoué.");
            return;
        }

        String sql = "UPDATE livre SET numeroLivre = ?, status = ?, collection_id = ? WHERE id = ?";
        System.out.println(collection.getId());
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, this.numeroLivre);
            preparedStatement.setString(2, this.status);
            preparedStatement.setInt(3, this.collection.getId());
            preparedStatement.setInt(4, this.id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livre modifié avec succès !");
            } else {
                System.err.println("Échec de la modification du livre.");
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


    public static Livre checkExisting(String numeroLivre){
        Livre livre = new Livre();
        String sql = "SELECT * FROM livre where numeroLivre = ?";
        try {
            PreparedStatement preparedStatement = Db.connect().prepareStatement(sql);
            preparedStatement.setString(1,numeroLivre );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                livre.id = resultSet.getInt("id");
                livre.numeroLivre = resultSet.getString("numeroLivre");
                livre.status = resultSet.getString("status");
                Collection collection= new Collection();
                collection.setId(resultSet.getInt("id"));
                livre.collection  = collection;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livre;
    }



    // Other methods and properties of the Livre class
}
