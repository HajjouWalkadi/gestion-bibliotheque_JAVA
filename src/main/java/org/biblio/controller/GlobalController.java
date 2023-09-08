package org.biblio.controller;

import org.biblio.model.Collection;
import org.biblio.model.Livre;

import java.util.List;
import java.util.Scanner;

import static org.biblio.model.Collection.ajouterCollection;
import static org.biblio.model.Collection.getCollections;

public class GlobalController {
    static Scanner sc = new Scanner(System.in);
    public static void displayBooks(){
        List<Collection> collections = Collection.getCollections(null);
        for (Collection collection:collections){
            System.out.println("Id   :" +collection.getId());
            System.out.println("Title   :" +collection.getTitre());

            System.out.println("---------------------------------");
        }

    }
    public static void addBook(){

    }
    public static void deleteBook(){
        System.out.print("ID du livre à supprimer : ");
        int livreId = sc.nextInt();
        sc.nextLine(); // Pour consommer la nouvelle ligne après la saisie de l'ID

        // Appelez la méthode pour supprimer le livre
        Livre.supprimerLivre(livreId);

        System.out.println("Le livre a été supprimé avec succès !");
    }
    public static void updateBook(){
        System.out.print("numero du livre à modifié : ");
        String numeroLivre = sc.nextLine();
        Livre livre = Livre.checkExisting(numeroLivre);
        if (livre.getId() == 0){
            System.out.println("there is no books ith this number");
            updateBook();
        }else {
           livre =  getUpdatedBookInfo(livre);
           livre.update();
        }
    }

    public static Livre getUpdatedBookInfo(Livre livre){
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
        }while (choice !=1 && choice !=2 && choice !=3);
        if (choice == 1){
            return livre;
        }else{
            if (choice == 2){
                displayBooks();
                System.out.println("choose the id of the collection");
                Collection collection = new Collection();
                collection.setId(sc.nextInt());
                livre.setCollection(collection);
            }else{
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
                collection =  Collection.getCollections(nouvelleCollection.getTitre()).get(0);
                livre.setCollection(collection);
                System.out.println("La collection a été ajoutée avec succès !");
            }
        }
        return livre;
    }

}
