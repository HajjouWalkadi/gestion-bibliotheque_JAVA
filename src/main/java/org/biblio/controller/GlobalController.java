package org.biblio.controller;

import org.biblio.console.Menu;
import org.biblio.model.Collection;
import org.biblio.model.Livre;

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

    /*   public static void displayBooks(){
           List<Collection> collections = Collection.getCollections(null);
           for (Collection collection:collections){
               System.out.println("Id   :" +collection.getId());
               System.out.println("Title   :" +collection.getTitre());
               System.out.println("Auteur   :" +collection.getAuteur());
               System.out.println("ISBN   :" +collection.getISBN());
               System.out.println("Quantity   :" +collection.getQuantity());

               System.out.println("---------------------------------");
           }

       }*/
    public static void addBook() {
        // Ajouter un livre
        System.out.print("ID du livre : ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Numéro de livre : ");
        String numeroLivre = sc.nextLine();
        System.out.print("Statut du livre : ");
        String status = sc.nextLine();
        System.out.print("ID de la collection : ");
        int collectionId = sc.nextInt();

        sc.nextLine();

        // Créez un objet Collection avec l'ID de la collection
        Collection collection = new Collection(collectionId, null, null, null, 0);

        // Créez un objet Livre avec les données saisies
        Livre livre = new Livre(id, numeroLivre, status, collection);

        // Appelez la méthode pour ajouter le livre
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
        // Ajouter une collection
        System.out.print("Titre de la collection : ");
        String titre = sc.nextLine();
        System.out.print("Auteur de la collection : ");
        String auteur = sc.nextLine();
        System.out.print("ISBN de la collection : ");
        String isbn = sc.nextLine();
        System.out.print("Quantité total de la collection : ");
        int quantity = sc.nextInt();
        sc.nextLine(); // Pour consommer la nouvelle ligne

        // Créez un objet Collection avec les données saisies
        Collection nouvelleCollection = new Collection(0, titre, auteur, isbn, quantity);

        // Appelez la méthode pour ajouter la collection
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
        // Prompt for updated information
        System.out.print("ID de la collection à modifier : ");
        int collectionId = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        System.out.print("Nouveau titre (ou appuyez sur Entrée pour conserver le titre actuel) : ");
        String newTitre = sc.nextLine();
        System.out.print("Nouvel auteur (ou appuyez sur Entrée pour conserver l'auteur actuel) : ");
        String newAuteur = sc.nextLine();
        System.out.print("Nouvel ISBN (ou appuyez sur Entrée pour conserver l'ISBN actuel) : ");
        String newIsbn = sc.nextLine();
        System.out.print("Nouvelle quantité : ");
        int newQuantity = sc.nextInt();
        sc.nextLine(); // Consume the newline character

// Update the collection
        Collection.updateCollection(collectionId, newTitre.isEmpty() ? null : newTitre,
                newAuteur.isEmpty() ? null : newAuteur,
                newIsbn.isEmpty() ? null : newIsbn,
                newQuantity);

        System.out.println("Collection mise à jour avec succès !");

    }



}
