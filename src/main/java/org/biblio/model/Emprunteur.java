package org.biblio.model;

import org.biblio.database.Db;

import java.sql.*;

public class Emprunteur {
    private Integer id;
    private String name;
    private String email;
    private String phone;

    public Emprunteur(String name, String email, String phone) {

        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Emprunteur(Integer id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Emprunteur() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void afficherInfosEmprunteur() {
        System.out.println("Information d'emprunteur' :");
        System.out.println("name : " + name);
        System.out.println("email : " + email);
        System.out.println("phone : " + phone);
    }


    public  Emprunteur getEmprunteurByEmail(String email) {
        //Connection con = Db.connect();
        //PreparedStatement statement = null;
        //ResultSet resultSet = null;
        //Emprunteur emprunteur = null;


            String query = "SELECT * FROM emprunteur WHERE email = ?";
            try(PreparedStatement statement = Db.connect().prepareStatement(query)){
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                 return this.mapData(resultSet);
            }


        } catch (SQLException e) {
           // e.printStackTrace();
                throw new RuntimeException(e);
        }


    }

    public  Emprunteur mapData(ResultSet resultSet) {
        Emprunteur emprunteur = null;

        try {
            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
                this.name = resultSet.getString("name");
                this.email = resultSet.getString("email");
                this.phone = resultSet.getString("phone");

                emprunteur = this;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprunteur;
    }



        // Méthode pour ajouter un emprunteur
        public void ajouterEmprunteur(Emprunteur emprunteur) {
            Connection connection = Db.connect();
            if (connection == null) {
                System.err.println("La connexion à la base de données a échoué.");
                return;
            }
            String sql = "INSERT INTO emprunteur (name, email, phone) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, emprunteur.getName());
                preparedStatement.setString(2, emprunteur.getEmail());
                preparedStatement.setString(3, emprunteur.getPhone());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Méthode pour supprimer un emprunteur par ID
      /*  public void supprimerEmprunteur(int id) {
            Connection connection = Db.connect();
            if (connection == null) {
                System.err.println("La connexion à la base de données a échoué.");
                return;
            }
            String sql = "DELETE FROM emprunteurs WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/






        }



