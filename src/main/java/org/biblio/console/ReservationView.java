package org.biblio.console;

import org.biblio.helper.LogicHelper;
import org.biblio.helper.Printer;
import org.biblio.model.Collection;
import org.biblio.model.Emprunt;
import org.biblio.model.Emprunteur;
import org.biblio.model.Livre;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ReservationView {
    private static Scanner scanner = new Scanner(System.in);
    private static Collection collection = new Collection();
    private static Emprunteur emprunteur = new Emprunteur();
    private static Emprunt emprunt = new Emprunt();


    public static void reserve() {

        setCollectionByISBN();
        setEmprunteurByEmail();

        if (collection.getQuantity_disponible() == 0) {
            Printer.print("this book is not available");
            return;
        }

        emprunt.setCollection_id(collection.getId());
        System.out.println(collection.getId());

        emprunt.setEmprunteur_id(emprunteur.getId());
        List<Livre> livres = Collection.getCollectionLivres(collection.getId());
        if (livres.size() == 0){
            System.out.println("noo books");
            return;
        }
        for (Livre livre:livres){
            System.out.println("id :"+livre.getId());
        }
        emprunt.setLivre(livres.get(0));
        setReservationQuantity();
        setReservationReturnDate();

        emprunt.reserve();
        Printer.print("book was booked successfully");

    }

    private static void setEmprunteurByEmail() {
        while (true) {

            Printer.print("Enter the emprunteur email: ");
            emprunteur = emprunteur.getEmprunteurByEmail(LogicHelper.scan());
            if (emprunteur == null) {
                emprunteur = new Emprunteur();
                Printer.print("This email was not found.");
            } else {
                Printer.printModel(emprunteur);
                break;
            }

        }
    }


    private static void setCollectionByISBN() {
        while (true) {

            Printer.print("Enter the collection ISBN: ");
            collection = collection.getCollectionByISBN(LogicHelper.scan());
            if (collection == null) {
                collection = new Collection();
                Printer.print("This ISBN was not found.");
            } else {
                Printer.printModel(collection);
                break;
            }
        }
    }

    private static void setReservationReturnDate() {
        Printer.print("enter the return Date (yyyy-MM-dd)");
        emprunt.setDateFin(LogicHelper.stringToDate(LogicHelper.scan()));
    }

    private static void setReservationQuantity() {
        int quanity;
        while (true) {
            Printer.print("enter the quantity");
            quanity = Integer.parseInt(LogicHelper.scan());

            if (quanity > collection.getQuantity_disponible()) {
                Printer.print("this quantity is not available, try again");
                Printer.print("the quantity available is :" + collection.getQuantity_disponible());
            } else {
                emprunt.setQuantity(quanity);
                break;
            }
        }
    }

    public static void retournerBook() {
        setCollectionByISBN();
        setEmprunteurByEmail();
        emprunt.setCollection_id(collection.getId());
        emprunt.setEmprunteur_id(emprunteur.getId());
        int quantityReturned = setReturnQuantity();


        if (!Emprunt.existsEmprunt(collection.getId(), emprunteur.getId())) {
            Printer.print("No record of borrowing this book by the borrower.");
        } else {


            Emprunt.returnBook(collection.getId(), emprunteur.getId(), quantityReturned);
            Printer.print("Book with ISBN " + collection.getISBN() + " has been successfully returned.");

        }
    }

    public static int setReturnQuantity() {
        int quantityReturn;
        while (true) {
            Printer.print("enter the quantity to return ");
            quantityReturn = scanner.nextInt() ;
            int quantityBorrowed = Emprunt.getQuantityBorrowed(collection.getId(), emprunteur.getId());
            if (quantityReturn > quantityBorrowed) {
                Printer.print("You cannot return more books than you borrowed.");
                Printer.print("the quantity you borrowed is :" + quantityBorrowed);
            } else {

                break;
            }
        }
        return quantityReturn;
    }


}
