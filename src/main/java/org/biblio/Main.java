


package org.biblio;

import org.biblio.console.Menu;
import org.biblio.console.ReservationView;
import org.biblio.controller.GlobalController;
import org.biblio.model.Collection;
import org.biblio.model.Emprunteur;
import org.biblio.model.Livre;

import java.util.List;
import java.util.Scanner;

import static org.biblio.database.Db.connect;
import static org.biblio.model.Collection.ajouterCollection;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        connect();
        Menu.showmenu();
        Menu.Options();
        int optionChoosed = sc.nextInt();
        executeFunctionality(optionChoosed);
    }


    private static void executeFunctionality(int optionChoosed) {
        switch (optionChoosed) {
          /*  case 1:
                GlobalController.displayBooks();
                break;*/

            case 1:
                GlobalController.addBook();
                break;

            case 2:

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
            default:
                System.out.println("not yet");
                break;
        }
    }

}


