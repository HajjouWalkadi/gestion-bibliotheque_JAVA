package org.biblio.model;

import org.biblio.database.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Livre {
    private Integer id;
    private String numeroLivre;
    private String status;
    private Collection collection;

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

    /*public static void ajouterLivre(Livre livre) {
        Connection connection = Db.connect();
        if (connection == null) {
            System.err.println("La connexion à la base de données a échoué.");
            return;
        }

        String sql = "INSERT INTO livres (numero_livre, status, collection_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, livre.getNumeroLivre());
            preparedStatement.setString(2, livre.getStatus());
            preparedStatement.setInt(3, livre.getCollection().getId());

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
    }*/

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


    public static List<Livre> afficherLivres() {
        List<Livre> livres = new ArrayList<>();
        Connection connection = Db.connect();
        if (connection == null) {
            System.err.println("La connexion à la base de données a échoué.");
            return livres;
        }

        String sql = "SELECT * FROM livre";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String numeroLivre = resultSet.getString("numeroLivre");
                String status = resultSet.getString("status");

                // You should retrieve the collection_id and create a Collection object here
                // Example: int collectionId = resultSet.getInt("collection_id");
                // Then create a Collection object using the collectionId

                // Assuming you have a Collection object, you can create a Livre object
                // with the retrieved data and the Collection object
                // Livre livre = new Livre(id, numeroLivre, status, collection);
                // livres.add(livre);
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

        return livres;
    }

    public static void modifierLivre(Livre livre) {
        Connection connection = Db.connect();
        if (connection == null) {
            System.err.println("La connexion à la base de données a échoué.");
            return;
        }

        String sql = "UPDATE livre SET numeroLivre = ?, status = ?, collection_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, livre.getNumeroLivre());
            preparedStatement.setString(2, livre.getStatus());
            preparedStatement.setInt(3, livre.getCollection().getId());
            preparedStatement.setInt(4, livre.getId());

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





    // Other methods and properties of the Livre class
}
