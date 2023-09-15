package org.biblio.model;
import org.biblio.database.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Statistiques {
    private Connection connection;

    public Statistiques() {
        // Initialisez la connexion à la base de données
        String url = "jdbc:mysql://localhost/biblio_2";
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void genererRapport() {
        try {

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) AS nbLivresDisponibles FROM livre WHERE status = 'disponible'");
            resultSet.next();
            int livresDisponibles = resultSet.getInt("nbLivresDisponibles");


            resultSet = stmt.executeQuery("SELECT COUNT(*) AS nbLivresEmpruntes FROM emprunt WHERE retour = 0");
            resultSet.next();
            int livresEmpruntes = resultSet.getInt("nbLivresEmpruntes");


            resultSet = stmt.executeQuery("SELECT COUNT(*) AS nbLivresPerdus FROM livre WHERE status = 'perdu'");
            resultSet.next();
            int livresPerdus = resultSet.getInt("nbLivresPerdus");


            System.out.println("Statistiques de la bibliothèque :");
            System.out.println("Livres disponibles : " + livresDisponibles);
            System.out.println("Livres empruntés : " + livresEmpruntes);
            System.out.println("Livres perdus : " + livresPerdus);

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
