package getdata;

import junit.framework.TestCase;
import org.biblio.helper.Printer;
import org.biblio.model.Collection;

public class CollectionTest extends TestCase {


    public  void testGetCollection(){
        Collection collection = new Collection();

        collection = collection.getCollectionByISBN("978-1400079983");


        Printer.printModel(collection);

    }



}
