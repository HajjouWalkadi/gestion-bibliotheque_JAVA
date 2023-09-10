package getdata;

import junit.framework.TestCase;
import org.biblio.helper.Printer;
import org.biblio.model.Emprunteur;

public class EmprunteurTest extends TestCase {

    public  void  testGetEmprunteurByEmail(){

        Emprunteur emprunteur = new Emprunteur();
        emprunteur = emprunteur.getEmprunteurByEmail("john.doe@example.com");
        Printer.printModel(emprunteur);
    }

}
