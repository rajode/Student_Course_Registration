package Client.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AdminFrame extends JFrame {

    private JButton btnCatalogueCourses;
    private JButton btnAddCourseToCatalogue;
    private JButton btnLogOut;
    private JTextArea textArea;

    /**
     * Create the admin frame.
     */
    public AdminFrame() {
        super("Admin Window");
        //listener = new MyListener (this);
        JPanel sideButtons = new JPanel();
        sideButtons.setBackground(Color.DARK_GRAY);
        FlowLayout f = new FlowLayout();
        sideButtons.setLayout(f);

        Container c = new Container();
        c = getContentPane();

        //creating buttons and adding it to panel
        btnCatalogueCourses = new JButton("View Catalgoue's Courses");
        btnAddCourseToCatalogue = new JButton("Add Course To Catalogue");
        btnLogOut = new JButton("Log out");
        sideButtons.add(btnCatalogueCourses);
        sideButtons.add(btnAddCourseToCatalogue);
        sideButtons.add(btnLogOut);

        //creating image label
        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon(getClass().getResource(("img.png"))));
        JPanel imgPanel = new JPanel();
        imgPanel.setBackground(Color.DARK_GRAY);
        imgPanel.add(imgLabel);


        //adding panels to Frame
        c.add(sideButtons, BorderLayout.EAST);
        c.add(imgPanel, BorderLayout.WEST);
        setVisible(true);
        pack();
    }

    public JButton getBtnCatalogueCourses() {
        return btnCatalogueCourses;
    }

    public JButton getBtnAddCourseToCatalogue() {
        return btnAddCourseToCatalogue;
    }

    public JButton getBtnLogOut() {
        return btnLogOut;
    }

    /**
     * This Function takes input from a dialog box and creates a string that will be sent to the server.
     *
     * @return The String to be sent to the server
     */
    public String getAddCourseDetail() {
        JTextField fCourseName = new JTextField();
        JTextField fCourseNum = new JTextField();
        JTextField fNumOfSec = new JTextField();
        JTextField fMaxStudent = new JTextField();

        Object[] o =
                {
                        "Enter course name:", fCourseName,
                        "Enter course ID:", fCourseNum,
                        "Enter the number of sections:", fNumOfSec,
                        "Enter the Maximum capacity of class:", fMaxStudent
                };

        int i = JOptionPane.showConfirmDialog(null, o, "Add Course to catalogue", JOptionPane.OK_CANCEL_OPTION);
        String s;
        if (i == JOptionPane.CANCEL_OPTION || i == JOptionPane.CLOSED_OPTION) {
            s = null;
        } else if (fCourseName.getText().isEmpty() || fCourseNum.getText().isEmpty() || fNumOfSec.getText().isEmpty() || fMaxStudent.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty field error", "ERROR", JOptionPane.ERROR_MESSAGE);
            s = null;
        } else {
            s = "7 ";
            s += fCourseName.getText().toUpperCase() + " ";
            s += fCourseNum.getText() + " ";
            s += fNumOfSec.getText() + " ";
            s += fMaxStudent.getText();
        }
        return s;
    }
}
