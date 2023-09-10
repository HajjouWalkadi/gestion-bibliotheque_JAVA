package org.biblio.console;

import java.util.Scanner;

public class Menu {
    public static String Rose = "\u001B[35m";
    public static String resetColor = "\u001B[0m";
    public static void showmenu(){
        System.out.println(Rose+"                                                    \n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "                                                    \n" +
                "                                                    ");
        System.out.println("┓ •┓           ┳┳┓                    ┏┓         \n" +
                "┃ ┓┣┓┏┓┏┓┏┓┓┏  ┃┃┃┏┓┏┓┏┓┏┓┏┓┏┳┓┏┓┏┓╋  ┗┓┓┏┏╋┏┓┏┳┓\n" +
                "┗┛┗┗┛┛ ┗┻┛ ┗┫  ┛ ┗┗┻┛┗┗┻┗┫┗ ┛┗┗┗ ┛┗┗  ┗┛┗┫┛┗┗ ┛┗┗\n" +
                "            ┛            ┛               ┛       ");
        System.out.println("                                                    \n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "                                                    \n" +
                "                                                    ");

        System.out.println(resetColor+"SELECT AN OPTION : \n");
        System.out.println(resetColor+"1) LOGIN : ");
        System.out.println(resetColor+"2) EXIT: \n");
        System.out.println(Rose+"                                                    \n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "                                                    \n" +
                "                                                    ");

    }

    public static void Options(){
        System.out.println("                                                    \n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "                                                    \n" +
                "                                                    ");

        System.out.println(resetColor+"Menu de gestion de bibliothèque :\n");
        System.out.println(resetColor+"1. Ajouter un livre");
        System.out.println(resetColor+"2. Afficher la liste des livres");
        System.out.println(resetColor+"3. Modifier un livre");
        System.out.println(resetColor+"4. Supprimer un livre");
        System.out.println(resetColor+"5. Emprunter un livre");
        System.out.println(resetColor+"6. Retourner un livre");
        System.out.println(resetColor+"7. Ajouter une collection");
        System.out.println(resetColor+"8. Afficher une collection");
        System.out.println(resetColor+"9. Modifier une collection");
        System.out.println(resetColor+"10. Supprimer une collection");
        System.out.println(resetColor+"11. Voir les statistiques");
        System.out.println(resetColor+"12. Quitter\n\n");
        System.out.print(resetColor+"Veuillez sélectionner une option : \n");
        System.out.println(Rose+"                                                    \n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "                                                    \n" +
                "                                                    ");

    }
    /*public static void showmenu(){
        System.out.println("Menu de gestion de bibliothèque :");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Afficher la liste des livres");
        System.out.println("3. Modifier un livre");
        System.out.println("4. Supprimer un livre");
        System.out.println("5. Emprunter un livre");
        System.out.println("6. Retourner un livre");
        System.out.println("7. Ajouter une collection");
        System.out.println("8. Afficher une collection");
        System.out.println("9. Modifier une collection");
        System.out.println("10. Supprimer une collection");
        System.out.println("11. Voir les statistiques");
        System.out.println("12. Quitter");
        System.out.print("Veuillez sélectionner une option : ");
    }*/


}
