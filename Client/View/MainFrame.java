package Client.View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;


public class MainFrame extends JFrame {

    private JButton btnSearchCourses;
    private JButton btnRemoveCourses;
    private JButton btnCatalogueCourses;
    private JButton btnStudentCourses;
    private JButton btnLogOut;
    private JTextArea textArea;

    /**
     * basic constructor where the main frame is created
     */
    public MainFrame() {
        super("Main Window");
        JPanel bottomButtons = new JPanel();
        bottomButtons.setBackground(Color.DARK_GRAY);
        FlowLayout f = new FlowLayout();
        bottomButtons.setLayout(f);

        Container c = new Container();
        c = getContentPane();

        btnSearchCourses = new JButton("Search Courses");
        btnRemoveCourses = new JButton("Remove Course");
        btnCatalogueCourses = new JButton("View Catalgoue's Courses");
        btnStudentCourses = new JButton("View Student's Courses");
        btnLogOut = new JButton("Log out");
        bottomButtons.add(btnSearchCourses);
        bottomButtons.add(btnRemoveCourses);
        bottomButtons.add(btnCatalogueCourses);
        bottomButtons.add(btnStudentCourses);
        bottomButtons.add(btnLogOut);

        //creating center JText area
        textArea = new JTextArea(30, 40);
        textArea.setEditable(false);
        JScrollPane vScrollPane = new JScrollPane(textArea);
        JPanel centreTextArea = new JPanel();
        vScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        vScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        centreTextArea.add(vScrollPane);

        //creating top label area
        JLabel topLabel = new JLabel("                                        My Courses:", SwingConstants.RIGHT);
        topLabel.setForeground(Color.WHITE);
        topLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setForeground(Color.WHITE);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        topPanel.add(topLabel);
        
        //creating image label
        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon(getClass().getResource(("img.png"))));
        JPanel imgPanel = new JPanel();
        imgPanel.setBackground(Color.DARK_GRAY);
        imgPanel.add(imgLabel);
        
        

        //adding panels to Frame
        c.add(bottomButtons, BorderLayout.SOUTH);
        c.add(centreTextArea, BorderLayout.EAST);
        c.add(topPanel, BorderLayout.NORTH);
        c.add(imgPanel,BorderLayout.WEST);
        setVisible(true);
        pack();


    }

    public JButton getBtnSearchCourses() {
        return btnSearchCourses;
    }

    public JButton getBtnRemoveCourses() {
        return btnRemoveCourses;
    }

    public JButton getBtnLogOut() {
        return btnLogOut;
    }

    public JButton getBtnCatalogueCourses() {
        return btnCatalogueCourses;
    }

    public JButton getBtnStudentCourses() {
        return btnStudentCourses;
    }
    
    /**
     * function for obtaining the search result for the name and ID of course. A dialog box is used to take input.
     * @return String with both the name and ID of course
     */
    public String getSearchDetail() {

        JTextField fCourseName = new JTextField();
        JTextField fCourseId = new JTextField();


        Object[] o =
                {
                        "Enter course name:", fCourseName,
                        "Enter course ID:", fCourseId
                };
        int i = JOptionPane.showConfirmDialog(null, o, "Search Courses", JOptionPane.OK_CANCEL_OPTION);
        String s;
        if (i == JOptionPane.CANCEL_OPTION || i == JOptionPane.CLOSED_OPTION) {
            s = null;
        } else if (fCourseName.getText().isEmpty() || fCourseId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty field error", "ERROR", JOptionPane.ERROR_MESSAGE);
            s = null;
        } else {
            s = "";
            s += fCourseName.getText().toUpperCase() + " ";
            s += fCourseId.getText();
        }
        return s;
    }
    
    /**
     * this function helps with printing to text area
     * @param toBePrinted
     */
    public void printToTextArea(String toBePrinted) {
        textArea.setText(toBePrinted);
    }

    /**
	 * function for obtaining the search result for the name and ID of course
	 * which is to be REMOVED
     * @return String with both the name and ID of course
     * @return
     */
    public String getRemoveCourseDetail() {
        JTextField fCourseName = new JTextField();
        JTextField fCourseNum = new JTextField();

        Object[] o =
                {
                        "Enter course name:", fCourseName,
                        "Enter course ID:", fCourseNum
                };
        int i = JOptionPane.showConfirmDialog(null, o, "Remove Courses", JOptionPane.OK_CANCEL_OPTION);
        String s;
        if (i == JOptionPane.CANCEL_OPTION || i == JOptionPane.CLOSED_OPTION) {
            s = null;
        } else if (fCourseName.getText().isEmpty() || fCourseNum.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty field error", "ERROR", JOptionPane.ERROR_MESSAGE);
            s = null;
        } else {
            s = "4 ";
            s += fCourseName.getText().toUpperCase() + " ";
            s += fCourseNum.getText();
        }
        return s;
    }

    /**
     * pops open a Message Dialog with the Search result
     * @param toBeDisplayed
     */
    public void ShowSearchCourse(String toBeDisplayed) {
        JOptionPane.showMessageDialog(this, toBeDisplayed, "Search Result", JOptionPane.PLAIN_MESSAGE);

    }
    
    /**
     * pops open a Message Dialog with the Search result
     * @param toBeDisplayed
     */
    public void ShowAddOrRemoveResults(String toBeDisplayed) {
        JOptionPane.showMessageDialog(this, toBeDisplayed, "Result", JOptionPane.PLAIN_MESSAGE);
    }
}



    