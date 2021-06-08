package Client.View;

import java.awt.BorderLayout;
import javax.swing.*;

public class CatalogueFrame extends JFrame {

    private JScrollPane contentPane;
    private JTextArea textArea;


    /**
     * Create the frame.
     */
    public CatalogueFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 484, 388);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setBounds(83, 41, 311, 262);

        contentPane = new JScrollPane(textArea);
        contentPane.setBounds(377, 41, 17, 262);

        JLabel lblNewLabel = new JLabel("Below are all available courses in the Catalogue:");
        lblNewLabel.setBounds(83, 11, 267, 29);

        add("Center", contentPane);
        add("North", lblNewLabel);

        setVisible(true);
    }
    
    /**
     * function to print catalogue to textArea
     * @param toBePrinted
     */
    public void printCatalogue(String toBePrinted) {
        textArea.setText(toBePrinted);
    }
}
