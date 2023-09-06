package org.biblio.console;

import java.util.Scanner;

public class Menu {

    public static String showmenu(){
        Scanner sc  = new Scanner(System.in);
        System.out.println("Enter username");
        String name = sc.next();
        System.out.println("Menu de gestion de bibliothèque :");
        System.out.println("1. Afficher la liste des livres");
        System.out.println("2. Emprunter un livre");
        System.out.println("3. Retourner un livre");
        System.out.println("4. Ajouter un livre");
        System.out.println("5. Quitter");
        System.out.print("Veuillez sélectionner une option : ");

        return name ;
    }


}
