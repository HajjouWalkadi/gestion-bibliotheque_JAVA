package org.biblio.model;

import org.biblio.database.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Collection {
    private static Integer id;
    private String titre;
    private String auteur;
    private String ISBN;
    private Integer quantity_total;
    private Integer quantity_disponible;

    public Collection(int id, String titre, String auteur, String isbn, int quantity_total, int quantity_disponible) {
        this.titre = titre;
        this.auteur = auteur;
        this.ISBN = ISBN;
        this.quantity_total = quantity_total;
        this.quantity_disponible = quantity_disponible;
    }


    public static Collection getCollectionById(int collectionId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Collection collection = null;

        try {
            connection = Db.connect(); // Establish your database connection here

            // Prepare the SQL query to retrieve the collection by id
            String sql = "SELECT * FROM collection WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            // Execute the query and retrieve the result set
            resultSet = preparedStatement.executeQuery();

            // Check if a collection was found
            if (resultSet.next()) {
                // Create a Collection object and populate it with the retrieved data
                collection = new Collection();
                collection.setId(resultSet.getInt("id"));
                collection.setTitre(resultSet.getString("titre"));
                collection.setAuteur(resultSet.getString("auteur"));
                collection.setISBN(resultSet.getString("isbn"));
                collection.setQuantity_total(resultSet.getInt("quantity_total"));
                collection.setQuantity_disponible(resultSet.getInt("quantity_disponible"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return collection;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }


    public Collection(Integer id, String titre, String auteur, String ISBN, Integer quantityTotal) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.ISBN = ISBN;
        this.quantity_total = quantityTotal;
        this.quantity_disponible = quantityTotal;
    }

    public Collection() {
    }

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


    public static List<Collection> getCollections(String titre) {
        List<Collection> collections = new ArrayList<Collection>();
        String sqlQuery = "SELECT * FROM collection";
        if (titre != null) {
            sqlQuery += " WHERE titre = ?";
        }
        try {
            PreparedStatement preparedStatement = Db.connect().prepareStatement(sqlQuery);
            if (titre != null) {
                preparedStatement.setString(1, titre);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Collection collection = new Collection(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getString("ISBN"),
                        resultSet.getInt("quantity_total"),
                        resultSet.getInt("quantity_disponible")
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

        String sql = "INSERT INTO collection (titre, auteur, ISBN, quantity_total, quantity_disponible) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, collection.getTitre());
            preparedStatement.setString(2, collection.getAuteur());
            preparedStatement.setString(3, collection.getISBN());
            preparedStatement.setInt(4, collection.getQuantity_total());
            preparedStatement.setInt(5, collection.getQuantity_disponible());

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

    public static void deleteCollection(int collectionId) {
        Connection connection = Db.connect();
        if (connection == null) {
            System.err.println("La connexion à la base de données a échoué.");
            return;
        }

        try {
            // First, update livre.collection_id to NULL
            String updateSql = "UPDATE livre SET collection_id = NULL WHERE collection_id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                updateStatement.setInt(1, collectionId);
                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("La collection du livre est mise à NULL avec succès !");
                } else {
                    System.err.println("Échec de la mise à jour de La collection du livre.");
                }
            }

            // Then, delete the collection
            String deleteSql = "DELETE FROM collection WHERE id = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
                deleteStatement.setInt(1, collectionId);
                int rowsDeleted = deleteStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Collection supprimée avec succès !");
                } else {
                    System.err.println("Échec de la suppression de la collection.");
                }
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


    public static void updateCollection(int id, String titre, String auteur, String isbn, int quantity) {
        Connection connection = Db.connect();
        if (connection == null) {
            System.err.println("La connexion à la base de données a échoué.");
            return;
        }

        // Initialize the SQL query
        StringBuilder sql = new StringBuilder("UPDATE collection SET");

        // Create an ArrayList to store the parameters that need to be updated
        List<Object> params = new ArrayList<>();

        // Check which fields are updated and add them to the SQL query
        if (titre != null) {
            sql.append(" titre = ?,");
            params.add(titre);
        }
        if (auteur != null) {
            sql.append(" auteur = ?,");
            params.add(auteur);
        }
        if (isbn != null) {
            sql.append(" isbn = ?,");
            params.add(isbn);
        }
        sql.append(" quantity = ? WHERE id = ?");
        params.add(quantity);
        params.add(id);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
            // Set the parameters based on the updates
            int parameterIndex = 1;
            for (Object param : params) {
                preparedStatement.setObject(parameterIndex++, param);
            }

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Collection modifiée avec succès !");
            } else {
                System.err.println("Échec de la modification de la collection.");
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


    public void setQuantity_total(Integer quantity_total) {
        this.quantity_total = quantity_total;
    }

    public void setQuantity_disponible(Integer quantity_disponible) {
        this.quantity_disponible = quantity_disponible;
    }

    public Integer getQuantity_total() {
        return quantity_total;
    }

    public Integer getQuantity_disponible() {
        return quantity_disponible;
    }


    public Collection getCollectionByISBN(String ISBN) {
        Connection con = Db.connect();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Collection collection = null;

        try {
            String query = "SELECT * FROM collection WHERE ISBN = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, ISBN);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.mapData(resultSet);
                return this;
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return collection;
    }

    public Collection mapData(ResultSet resultSet) {


        try {
            this.id = resultSet.getInt("id");
            this.titre = resultSet.getString("titre");
            this.auteur = resultSet.getString("auteur");
            this.ISBN = resultSet.getString("ISBN");
            this.quantity_total = resultSet.getInt("quantity_total");
            this.quantity_disponible = resultSet.getInt("quantity_disponible");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

}



