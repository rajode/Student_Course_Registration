package Client.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class SearchResultFrame extends JFrame {

	private JScrollPane contentPane;
	 private JTextArea textArea;
	 private JButton btnAddCourse;
	 private JButton btnOkay;

	 
    /**
     * Creating the Search Result frame.
     */
    public SearchResultFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 484, 388);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setBounds(83, 41, 311, 262);

        contentPane = new JScrollPane(textArea);
        contentPane.setBounds(377, 41, 17, 262);

        JLabel lblNewLabel = new JLabel("Search Result");
        lblNewLabel.setBounds(83, 11, 267, 29);
        
        btnAddCourse = new JButton("Add Course");
		btnAddCourse.setBounds(74, 215, 122, 25);
		contentPane.add(btnAddCourse);
		
		btnOkay = new JButton("Close");
		btnOkay.setBounds(225, 215, 116, 25);
		contentPane.add(btnOkay);
       
		JPanel bottomButtons = new JPanel();
		bottomButtons.add(btnAddCourse);
		bottomButtons.add(btnOkay);
		
        add("Center", contentPane);
        add("North", lblNewLabel);
        add("South",bottomButtons);
       

        setVisible(true);
    }

    public void setTextArea(String toBePrinted) {
        textArea.setText(toBePrinted);
    }
	
/**
 * This function displays a dialog box and takes the user input then add them in a string that is sent to the server
 * @param theCourseNameAndNum The course Name and Number from the search history
 * @return The string that is to be sent
 */
	public String getAddCourseDetail(String theCourseNameAndNum) {

        JTextField fSec = new JTextField();
        Object[] o =
                {

                        "Enter section num:", fSec
                };

        int i = JOptionPane.showConfirmDialog(null, o, "Add Courses", JOptionPane.OK_CANCEL_OPTION);
        String s;
        if (i == JOptionPane.CANCEL_OPTION || i == JOptionPane.CLOSED_OPTION) {
            s = null;
        } else if (fSec.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty field error", "ERROR", JOptionPane.ERROR_MESSAGE);
            s = null;
        } else {
            s = "3 ";
            s += theCourseNameAndNum + " ";
            s += fSec.getText();
        }
        return s;
    }
	
	
	/**
	 * pops open a message box with Result
	 * @param toBeDisplayed
	 */
	public void ShowAddResults(String toBeDisplayed)
	{
		JOptionPane.showMessageDialog(this, toBeDisplayed, "Result", JOptionPane.PLAIN_MESSAGE);
	}
	
	
	/**
	 * basic getter functions
	 */
	public JButton getAddCourse() {return btnAddCourse;}
	
	public JButton getOkay() {return btnOkay;}
	
	
}
