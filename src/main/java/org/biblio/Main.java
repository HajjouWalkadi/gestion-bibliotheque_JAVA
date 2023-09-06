package org.biblio;

import org.biblio.model.Collection;
import org.biblio.model.Emprunteur;
import org.biblio.model.Livre;

import static org.biblio.console.Menu.showmenu;
import static org.biblio.database.Db.connect;

public class Main {
    public static void main(String[] args){
        connect();
        System.out.println(showmenu());

        Collection coll1 = new Collection(1,"Antigone","Jean Anouilh","ISBN 11223344",31);
        coll1.afficherInfosColl();

        Livre liv1 = new Livre(1,"A11233","disponible",coll1);
        liv1.afficherInfosLivre();

        Emprunteur Hajjou = new Emprunteur("Hajjou","hajjou@gmail.com","555-987-6543");
        Hajjou.afficherInfosEmprunteur();
    }
}
