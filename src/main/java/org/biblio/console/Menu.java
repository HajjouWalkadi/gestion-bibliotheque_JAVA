package org.biblio.console;

import org.biblio.Main;
import org.biblio.controller.GlobalController;

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

        //System.out.println(resetColor+"SELECT AN OPTION : \n");
        //System.out.println(resetColor+"1) LOGIN : ");
        //System.out.println(resetColor+"2) EXIT: \n");
        //System.out.println(Rose+"                                                    \n" +
          //      "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
          //      "                                                    \n" +
            //    "                                                    ");

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
        System.out.println(resetColor+"12. Rechercher par le titre");
        System.out.println(resetColor+"13. Rechercher par le l'auteur");
        System.out.println(resetColor+"14. Quitter\n\n");
        System.out.print(resetColor+"Veuillez sélectionner une option : \n");
        System.out.println(Rose+"                                                    \n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "                                                    \n" +
                "                                                    ");

    }



    /*public static void Returnopt(){
        System.out.println("CHoose a correct one ");
        Scanner scan =  new Scanner(System.in);
        String choose = scan.nextLine();
        switch (choose){
            case "1":
                //

                break;
            case "2":
                //
                System.exit(0);
                break;
            default:
                System.out.println("rak ghlati ");
                Returnopt();
                break;
        }
    }*/
  }
