package Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Client.Controller.LoginController.ExitListener;
import Client.Controller.LoginController.LoginListener;
import Client.View.AdminFrame;
import Client.View.CatalogueFrame;
import Client.View.LoginFrame;

public class AdminController {
	private ObjectOutputStream socketOut;
    private ObjectInputStream socketIn;
    private AdminFrame myAdminFrame;

    /**
     * Create the main controller with given values.
     * @param socOut
     * @param socIn
     * @param theAdminFrame
     */
    public AdminController(ObjectOutputStream socOut, ObjectInputStream socIn,AdminFrame theAdminFrame)
    {
    	socketOut = socOut;
    	socketIn = socIn;
    	myAdminFrame = theAdminFrame;
    	
    	myAdminFrame.getBtnCatalogueCourses().addActionListener(new CatalogueCoursesListener());
        myAdminFrame.getBtnAddCourseToCatalogue().addActionListener(new AddNewCourse());
        myAdminFrame.getBtnLogOut().addActionListener(new LogOutListener());

        
    }
   /**
    * inner Class that listens to View Catalogue Courses button 
    * @author Abid,Ragya,Long
    *
    */
    class CatalogueCoursesListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String stringToSend = "1 ";
            try {
                socketOut.writeObject(stringToSend);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            CatalogueController myCatController = new CatalogueController(socketIn, new CatalogueFrame());

        }
    }
    /**
     * Inner Class that listens to Add Course to Catalogue button.
     * @author Abid,Ragya,Long
     *
     */
    class AddNewCourse implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	String stringToSend = myAdminFrame.getAddCourseDetail();
        	try {
				socketOut.writeObject(stringToSend);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }
    /**
     * Inner Class that listens to Log out button.
     * @author Abid,Ragya,Long
     *
     */
    class LogOutListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {
            	String stringToSend = "6 ";
                socketOut.writeObject(stringToSend);
                myAdminFrame.dispose();
                LoginController lgnController = new LoginController(socketOut, socketIn, new LoginFrame());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    
    
}
