package org.biblio.model;

import java.sql.*;

public class Search {
    public Search() {
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
    private Connection connection;
    public void searchByTitle(String title) {
        try {
            // Prepare a SQL query to search for books by title
            String query = "SELECT * FROM collection WHERE titre LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + title + "%");

            // Execute the query and retrieve the results
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean foundBooks = false;

            // Display the search results
            System.out.println("Books matching title '" + title + "':");
            while (resultSet.next()) {
                String bookTitle = resultSet.getString("titre");
                String author = resultSet.getString("auteur");
                //System.out.println("Title: " + bookTitle + ", Author: " + author);
                System.out.println("Title: " + bookTitle);
                System.out.println("Author: " + author);
                System.out.println();

                foundBooks = true;
            }

            // Close resources
            preparedStatement.close();
            if (!foundBooks) {
                System.out.println("No matching books found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByAuthor(String author) {
        try {
            String query = "SELECT * FROM collection WHERE auteur LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + author + "%");


            ResultSet resultSet = preparedStatement.executeQuery();

            boolean foundAuthor = false;

            System.out.println("Books by author '" + author + "':");
            while (resultSet.next()) {
                String bookTitle = resultSet.getString("titre");
                String bookAuthor = resultSet.getString("auteur");
                //System.out.println("Title: " + bookTitle + ", Author: " + bookAuthor);
                System.out.println("Title: " + bookTitle);
                System.out.println("Author: " + bookAuthor);
                System.out.println();

                foundAuthor = true;
            }


            preparedStatement.close();
            if (!foundAuthor){
                System.out.println("No matching author found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
