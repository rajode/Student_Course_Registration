package Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Client.View.CatalogueFrame;
import Client.View.LoginFrame;
import Client.View.MainFrame;
import Client.View.SearchResultFrame;

public class MainController {
    private ObjectOutputStream socketOut;
    private ObjectInputStream socketIn;

    private MainFrame myMainFrame;

    /**
     * Create the main controller with given values.
     * @param socOut
     * @param socIn
     * @param mainFrame
     */
    public MainController(ObjectOutputStream socOut, ObjectInputStream socIn, MainFrame mainFrame) {
        socketOut = socOut;
        socketIn = socIn;
        myMainFrame = mainFrame;

        myMainFrame.getBtnSearchCourses().addActionListener(new SearchListener());
        myMainFrame.getBtnCatalogueCourses().addActionListener(new CatalogueCoursesListener());
        myMainFrame.getBtnRemoveCourses().addActionListener(new RemoveCoursesListener());
        myMainFrame.getBtnStudentCourses().addActionListener(new StudentCoursesListener());
        myMainFrame.getBtnLogOut().addActionListener(new LogOutListener());
    }
/**
 * Inner Class that listens to Search button.
 * @author Abid,Ragya,Long
 *
 */
    class SearchListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String stringToSend = myMainFrame.getSearchDetail();
            try {
                if (stringToSend == null) {
                    return;
                } else {
                    socketOut.writeObject("2 "+stringToSend);
                    String result = socketIn.readObject().toString();
                    if(!(result.contains("not found")))
                    {
                    	SearchController theSearchController = new SearchController(socketIn, socketOut, new SearchResultFrame(),result,stringToSend);
                    }
                    else {
                    	myMainFrame.ShowSearchCourse(result);
                    }
                }
            } catch (IOException | ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
/**
 * Inner Class that listens to Show Course Catalogue button.
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
 * Inner Class that listens to Remove Courses button.
 * @author Abid,Ragya,Long
 *
 */
    class RemoveCoursesListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String stringToSend = myMainFrame.getRemoveCourseDetail();
            try {
                if (stringToSend == null) {
                    return;
                } else {
                    socketOut.writeObject(stringToSend);
                    String result = socketIn.readObject().toString();
                    myMainFrame.ShowAddOrRemoveResults(result);
                }
            } catch (IOException | ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }

    }


/**
 * Inner Class that listens to Student Courses button.
 * @author Abid,Ragya,Long
 *
 */
    class StudentCoursesListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                String stringToSend = "5 ";
                socketOut.writeObject(stringToSend);
                myMainFrame.printToTextArea(socketIn.readObject().toString());
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
/**
 * Inner Class that listens to Log Out button.
 * @author Abid,Ragya,Long
 *
 */
    class LogOutListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {
                String stringToSend = "6 ";
                socketOut.writeObject(stringToSend);
                myMainFrame.dispose();
                LoginController lgnController = new LoginController(socketOut, socketIn, new LoginFrame());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}