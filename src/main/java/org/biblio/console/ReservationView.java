package org.biblio.console;

import org.biblio.helper.LogicHelper;
import org.biblio.helper.Printer;
import org.biblio.model.Collection;
import org.biblio.model.Emprunt;
import org.biblio.model.Emprunteur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationView {

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
        emprunt.setEmprunteur_id(emprunteur.getId());


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
                Printer.print("This ISBN was not found.");
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
            quantityReturn = Integer.parseInt(LogicHelper.scan());
            int quantityBorrowed = Emprunt.getQuantityBorrowed(collection.getId(), emprunteur.getId());
            if (quantityReturn > quantityBorrowed) {
                Printer.print("You cannot return more books than you borrowed.");
                Printer.print("this quantity you borrowed is :" + quantityBorrowed);
            } else {

                break;
            }
        }
        return quantityReturn;
    }


}
