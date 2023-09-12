package org.biblio.model;

import org.biblio.database.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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




}
