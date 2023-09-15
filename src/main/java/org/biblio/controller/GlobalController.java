package org.biblio.controller;

import org.biblio.console.Menu;
import org.biblio.database.Db;
import org.biblio.model.Collection;
import org.biblio.model.Emprunteur;
import org.biblio.model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.biblio.database.Db.connect;
import static org.biblio.model.Collection.ajouterCollection;
import static org.biblio.model.Collection.getCollections;

public class GlobalController {
    static Scanner sc = new Scanner(System.in);

    public static void displayCollection() {
        List<Collection> collections = Collection.getCollections(null);
        for (Collection collection : collections) {
            System.out.println("Id   :" + collection.getId());
            System.out.println("Title   :" + collection.getTitre());
            System.out.println("Auteur   :" + collection.getAuteur());
            System.out.println("ISBN   :" + collection.getISBN());
            System.out.println("Quantity totale  :" + collection.getQuantity_total());
            System.out.println("Quantity disponible  :" + collection.getQuantity_disponible());

            System.out.println("---------------------------------");
        }

    }

    public static void addBook() {

        System.out.print("Numéro de livre : ");
        String numeroLivre = sc.nextLine();
        System.out.print("Statut du livre : ");
        String status = sc.nextLine();
        System.out.print("ID de la collection : ");
        int collectionId = sc.nextInt();

        sc.nextLine();

        Collection collection = new Collection(collectionId, null, null, null, 0);

        Livre livre = new Livre(numeroLivre, status, collection);

        Livre.ajouterLivre(livre);

        System.out.println("Le livre a été ajouté avec succès !");

    }


    public static void deleteBook() {
        System.out.print("ID du livre à supprimer : ");
        int livreId = sc.nextInt();
        sc.nextLine(); // Pour consommer la nouvelle ligne après la saisie de l'ID

        // Appelez la méthode pour supprimer le livre
        Livre.supprimerLivre(livreId);

        System.out.println("Le livre a été supprimé avec succès !");
    }

    public static void updateBook() {
        System.out.print("numero du livre à modifié : ");
        String numeroLivre = sc.nextLine();
        Livre livre = Livre.checkExisting(numeroLivre);
        if (livre.getId() == 0) {
            System.out.println("there is no books with this number");
            updateBook();
        } else {
            livre = getUpdatedBookInfo(livre);
            livre.update();
        }
    }

   public static void displayBook() {
       try (Connection connection = Db.connect()) {
           if (connection == null) {
               System.err.println("La connexion à la base de données a échoué.");
               return;
           }


           String query = "SELECT livre.*, collection.titre, collection.auteur " +
                   "FROM livre " +
                   "INNER JOIN collection ON livre.collection_id = collection.id";

           try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

               // Display the list of books
               System.out.println("List of Books:");
               while (resultSet.next()) {
                   int bookId = resultSet.getInt("livre.id");
                   String bookTitle = resultSet.getString("livre.numeroLivre");
                   String bookStatus = resultSet.getString("livre.status");
                   String collectionTitle = resultSet.getString("collection.titre");
                   String collectionAuthor = resultSet.getString("collection.auteur");


                   System.out.println("ID du livre: " + bookId);
                   System.out.println("Numéro du livre: " + bookTitle);
                   System.out.println("Status: " + bookStatus);
                   System.out.println("Titre de la collection: " + collectionTitle);
                   System.out.println("Auteur de la collection: " + collectionAuthor);
                   System.out.println();
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }


    public static Livre getUpdatedBookInfo(Livre livre) {
        System.out.println("new book number");
        livre.setNumeroLivre(sc.nextLine());
        System.out.println("new book status");
        livre.setStatus(sc.nextLine());
        System.out.println("do you want to update the collection:");
        System.out.println("1-No i do not");
        System.out.println("2-Yes, To an existing collection");
        System.out.println("3-Yes, To a collection that doesn't exist");

        int choice = 0;
        do {
            choice = sc.nextInt();
        } while (choice != 1 && choice != 2 && choice != 3);
        if (choice == 1) {
            return livre;
        } else {
            if (choice == 2) {
                displayCollection();
                System.out.println("choose the id of the collection");
                Collection collection = new Collection();
                collection.setId(sc.nextInt());
                livre.setCollection(collection);
            } else {
                sc.nextLine();
                Collection collection = new Collection();
                System.out.print("Titre de la collection : ");
                String titre = sc.nextLine();
                System.out.print("Auteur de la collection : ");
                String auteur = sc.nextLine();
                System.out.print("ISBN de la collection : ");
                String isbn = sc.nextLine();
                System.out.print("Quantité de la collection : ");
                int quantity = sc.nextInt();
                sc.nextLine();
                Collection nouvelleCollection = new Collection(0, titre, auteur, isbn, quantity);

                ajouterCollection(nouvelleCollection);
                collection = Collection.getCollections(nouvelleCollection.getTitre()).get(0);
                livre.setCollection(collection);
                System.out.println("La collection a été ajoutée avec succès !");
            }
        }
        return livre;
    }

    public static void addCollection() {
        System.out.print("Titre de la collection : ");
        String titre = sc.nextLine();
        System.out.print("Auteur de la collection : ");
        String auteur = sc.nextLine();
        System.out.print("ISBN de la collection : ");
        String isbn = sc.nextLine();
        System.out.print("Quantité total de la collection : ");
        int quantity = sc.nextInt();
        sc.nextLine();

        Collection nouvelleCollection = new Collection(0, titre, auteur, isbn, quantity);

        ajouterCollection(nouvelleCollection);

        System.out.println("La collection a été ajoutée avec succès !");

    }

    public static void deleteCollection() {
        System.out.print("ID de la collection à supprimer : ");
        int collectionId = sc.nextInt();
        sc.nextLine();

        // Appelez la méthode pour supprimer le livre
        Collection.deleteCollection(collectionId);

        System.out.println("La collection a été supprimée avec succès !");
    }

    public static void updateCollection() {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID de la collection à modifier : ");
        int collectionId = sc.nextInt();
        sc.nextLine();

        System.out.print("Nouveau titre (ou appuyez sur Entrée pour conserver le titre actuel) : ");
        String newTitre = sc.nextLine();
        System.out.print("Nouvel auteur (ou appuyez sur Entrée pour conserver l'auteur actuel) : ");
        String newAuteur = sc.nextLine();
        System.out.print("Nouvel ISBN (ou appuyez sur Entrée pour conserver l'ISBN actuel) : ");
        String newIsbn = sc.nextLine();
        System.out.print("Nouvelle quantité : ");
        int newQuantity = sc.nextInt();
        sc.nextLine();


        Collection.updateCollection(collectionId, newTitre.isEmpty() ? null : newTitre,
                newAuteur.isEmpty() ? null : newAuteur,
                newIsbn.isEmpty() ? null : newIsbn,
                newQuantity);

        System.out.println("Collection mise à jour avec succès !");

    }

    public static void addEmprunteur() {

        System.out.print("Entrer le nom de l'emprunteur : ");
        String name = sc.nextLine();
        System.out.print("Entrer l'email' de l'emprunteur : ");
        String email = sc.nextLine();
        System.out.print("Entrer le numéro de téléphone de l'emprunteur : ");
        String phone = sc.nextLine();


        Emprunteur emprunteur = new Emprunteur();
        emprunteur.setName(name);
        emprunteur.setEmail(email);
        emprunteur.setPhone(phone);

        emprunteur.ajouterEmprunteur(emprunteur);
        System.out.println("L'emprunteur a été ajouté avec succès !");
    }

}
