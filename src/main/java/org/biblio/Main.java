/*
package org.biblio;

import org.biblio.console.Menu;
import org.biblio.controller.GlobalController;
import org.biblio.model.Collection;
import org.biblio.model.Emprunteur;
import org.biblio.model.Livre;

import java.util.Scanner;

import static org.biblio.database.Db.connect;

public class Main {
    static Scanner sc  = new Scanner(System.in);
    public static void main(String[] args){
        connect();
        Menu.showmenu();
        int optionChoosed = sc.nextInt();
        executeFunctionality(optionChoosed);
*/
/*        Collection coll1 = new Collection(1,"Antigone","Jean Anouilh","ISBN 11223344",31);
        coll1.afficherInfosColl();

        Livre liv1 = new Livre(1,"A11233","disponible",coll1);
        liv1.afficherInfosLivre();

        Emprunteur Hajjou = new Emprunteur("Hajjou","hajjou@gmail.com","555-987-6543");
        Hajjou.afficherInfosEmprunteur();*//*

    }

    private static void executeFunctionality(int optionChoosed){
        switch (optionChoosed){
            case 1:
                GlobalController.displayBooks();
                break;
            default:
                System.out.println("not yet");
                break;
        }

    }

}
*/


package org.biblio;

import org.biblio.console.Menu;
import org.biblio.controller.GlobalController;
import org.biblio.model.Collection;
import org.biblio.model.Emprunteur;
import org.biblio.model.Livre;

import java.util.List;
import java.util.Scanner;

import static org.biblio.database.Db.connect;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        connect();
        Menu.showmenu();
        int optionChoosed = sc.nextInt();
        executeFunctionality(optionChoosed);
    }

    /*private static void executeFunctionality(int optionChoosed) {
        switch (optionChoosed) {
            case 1:
                GlobalController.displayBooks();

                // Create a Collection object (replace these values with actual data)
                Collection collection = new Collection(6, "DJDC", "NAME Author", "IS12345", 10);

                // Create a Livre object
                Livre livre = new Livre(5, "NO123", "perdu", collection);

                // Call the ajouterLivre method to insert the Livre object into the database
                Livre.ajouterLivre(livre);

                break;
            default:
                *//*System.out.println("not yet");*//*
                break;

        }*/

    private static void executeFunctionality(int optionChoosed) {
        switch (optionChoosed) {
          /*  case 1:
                GlobalController.displayBooks();
                break;*/

            case 1:
                // Ajouter un livre
                System.out.print("ID du livre : ");
                int id = sc.nextInt();
                sc.nextLine(); // Pour consommer la nouvelle ligne après la saisie de l'ID
                System.out.print("Numéro de livre : ");
                String numeroLivre = sc.nextLine();
                System.out.print("Statut du livre : ");
                String status = sc.nextLine();
                System.out.print("ID de la collection : ");
                int collectionId = sc.nextInt();
                sc.nextLine(); // Pour consommer la nouvelle ligne après la saisie de l'ID de la collection

                // Créez un objet Collection avec l'ID de la collection
                Collection collection = new Collection(collectionId, null, null, null, 0);

                // Créez un objet Livre avec les données saisies
                Livre livre = new Livre(id, numeroLivre, status, collection);

                // Appelez la méthode pour ajouter le livre
                Livre.ajouterLivre(livre);

                System.out.println("Le livre a été ajouté avec succès !");
                break;
            default:
                /*System.out.println("not yet");*/
                break;

            case 2: // Assuming option 2 is for displaying books
                List<Livre> livresList = Livre.afficherLivres();
                for (Livre livreItem : livresList) {
                    System.out.println("ID: " + livreItem.getId());
                    System.out.println("NumeroLivre: " + livreItem.getNumeroLivre());
                    System.out.println("Status: " + livreItem.getStatus());
                    // Display other properties as needed
                }


           /* case 2: // Assuming option 2 is for updating a book
                System.out.print("ID du livre à mettre à jour : ");
                int livreIdToUpdate = sc.nextInt();
                sc.nextLine(); // Consume the newline

                // Fetch the existing Livre from the database based on its ID
                Livre existingLivre = retrieveLivreById(livreIdToUpdate);

                if (existingLivre == null) {
                    System.out.println("Aucun livre trouvé avec cet ID.");
                } else {
                    // Display the existing Livre's information
                    System.out.println("Information actuelle du livre :");
                    System.out.println("ID : " + existingLivre.getId());
                    System.out.println("Numéro de livre : " + existingLivre.getNumeroLivre());
                    System.out.println("Statut : " + existingLivre.getStatus());

                    // Update the Livre's information
                    System.out.print("Nouveau numéro de livre : ");
                    String newNumeroLivre = sc.nextLine();
                    System.out.print("Nouveau statut : ");
                    String newStatus = sc.nextLine();

                    // Create a new Livre object with the updated information
                    Livre updatedLivre = new Livre(
                            existingLivre.getId(),
                            newNumeroLivre,
                            newStatus,
                            existingLivre.getCollection()
                    );

                    // Call the method to update the Livre
                    Livre.modifierLivre(updatedLivre);

                    System.out.println("Le livre a été mis à jour avec succès !");
                }
                break;*/



            case 4:
                // Supprimer un livre
                System.out.print("ID du livre à supprimer : ");
                int livreId = sc.nextInt();
                sc.nextLine(); // Pour consommer la nouvelle ligne après la saisie de l'ID

                // Appelez la méthode pour supprimer le livre
                Livre.supprimerLivre(livreId);

                System.out.println("Le livre a été supprimé avec succès !");
                break;
        }
    }

}


