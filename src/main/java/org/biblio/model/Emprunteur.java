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
        Connection con = Db.connect();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Emprunteur emprunteur = null;

        try {
            String query = "SELECT * FROM emprunteur WHERE email = ?";
            statement = con.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.mapData(resultSet);
                return  this;
            } else {
                return null;
            }

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

        return emprunteur;
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
