package Client.Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Client.View.CatalogueFrame;


public class CatalogueController {
    private ObjectInputStream socketIn;
    private CatalogueFrame myCatalogueFrame;

    /**
     * Create the catalogue controller with given values.
     * @param socIn
     * @param catalogueFrame
     */
    public CatalogueController(ObjectInputStream socIn, CatalogueFrame catalogueFrame) {
        socketIn = socIn;
        myCatalogueFrame = catalogueFrame;

        String result = "ERROR!";
        try {
            result = socketIn.readObject().toString();
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        myCatalogueFrame.printCatalogue(result);
    }


}
