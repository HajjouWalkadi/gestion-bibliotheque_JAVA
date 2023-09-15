


package org.biblio;

import org.biblio.console.Menu;
import org.biblio.console.ReservationView;
import org.biblio.controller.GlobalController;
import org.biblio.model.Livre;
import org.biblio.model.Search;
import org.biblio.model.Statistiques;

import java.util.Scanner;

import static org.biblio.database.Db.connect;
import static org.biblio.helper.LogicHelper.scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        connect();
        while (true) {


            Menu.showmenu();
            Menu.Options();
            int optionChoosed = sc.nextInt();
            executeFunctionality(optionChoosed);
       }
    }


    private static void executeFunctionality(int optionChoosed) {
        switch (optionChoosed) {


            case 1:
                GlobalController.addBook();
                break;

            case 2:
                GlobalController.displayBook();
                break;
            case 3:
                GlobalController.updateBook();
                break;
            case 4:
                GlobalController.deleteBook();
                break;
            case 5:
                ReservationView.reserve();
                break;
            case 6:
                ReservationView.retournerBook();
                break;
            case 7:
                GlobalController.addCollection();
                break;
            case 8:
                GlobalController.displayCollection();
                break;
            case 9:
                GlobalController.updateCollection();
                break;
            case 10:
                GlobalController.deleteCollection();
                break;
            case 11:
                Statistiques rapport = new Statistiques();
                rapport.genererRapport();
            break;
            case 12:
                sc.nextLine();
                Search searchTitle  = new Search();
                System.out.println("Entrer le titre ");
                String title = sc.nextLine();

                searchTitle.searchByTitle(title);

                break;
            case 13:
                sc.nextLine();
                Search searchAuthor  = new Search();
                System.out.println("Enter le nom d'auteur' ");
                String author = sc.nextLine();

                searchAuthor.searchByAuthor(author);

                break;
            case 14:
            GlobalController.addEmprunteur();
                break;
            case 15:
                // Exit the program
                System.out.println("Exiting the library program. Goodbye!");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

}


