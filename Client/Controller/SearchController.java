package Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Client.Controller.MainController.SearchListener;
import Client.View.SearchResultFrame;

public class SearchController {
	private ObjectOutputStream socketOut;
    private ObjectInputStream socketIn;
    private String searchResult;
    private String theCourseNameAndNum;

    private SearchResultFrame mySRF;

    /**
     * Create the search controller.
     */
    public SearchController(ObjectInputStream socketIn, ObjectOutputStream socketOut,SearchResultFrame theSRF,String result,String CourseName)
    {
    	this.socketIn = socketIn;
    	this.socketOut = socketOut;
    	this.mySRF = theSRF;
    	this.searchResult = result;
    	this.theCourseNameAndNum = CourseName;
    	
    	
    	mySRF.setTextArea(searchResult);
    	mySRF.getAddCourse().addActionListener(new AddCoursesListener());
    	mySRF.getOkay().addActionListener(new OkayListener());
    }
 /**
  * Inner Class that listens to Add Course button.
  * @author Abid,Ragya,Long
  *
  */
    class AddCoursesListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String stringToSend = mySRF.getAddCourseDetail(theCourseNameAndNum);
            
            try {
                if (stringToSend == null) {
                    return;
                } else {
                    socketOut.writeObject(stringToSend);
                    System.out.println(stringToSend);
                    String result = socketIn.readObject().toString();
                    mySRF.ShowAddResults(result);
                }
            } catch (IOException | ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }
/**
 * Inner Class that listens to Close button.    
 * @author Abid,Ragya,Long
 *
 */
   class OkayListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent e) {
		   mySRF.setVisible(false);
		   mySRF.dispose();
	   }
   }
    

}
