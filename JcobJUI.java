import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.CardLayout;

public class JcobJUI {
    // replace App.buttonPressed, set it to your own to receive buttons' action codes
    void buttonPressed(String actionCode) {
        App.buttonPressed(actionCode);
    }

    final JFrame window = new JFrame();
    final CardLayout cardLayout = new CardLayout();
    final JPanel mainPanel = new JPanel(cardLayout);
    final JTextArea[] textAreas = new JTextArea[99];
    final String[] tableNames = new String[99];
    final int spaces = 30;

    JcobJUI(String windowTitle) {
        window.add(mainPanel);
        window.setVisible(true);
        window.setSize(400,600);
        window.setResizable(false);
        window.setTitle(windowTitle);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // options should only have 2 every row, 0 = button name, 1 = action code
    // initialize a card that only display options
    void addMenuCard(String cardName, String title, String[][] options) {
        // CARD PANEL
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // TITLE LABEL
        JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        
        panel.add(Box.createVerticalStrut(spaces));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(spaces));

        for (int i = 0; i < options.length; i++) {
            int index = i;

            // OPTIONS BUTTONS
            JButton button = new JButton(options[i][0]);
            button.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            button.addActionListener(e -> buttonPressed(options[index][1]));

            panel.add(button);
            panel.add(Box.createVerticalStrut(spaces));
        }
        mainPanel.add(panel, cardName);
    }

    // initialize a card only for displaying large text
    void addListCard(String cardName, String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> switchToPrevious());
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(spaces));
        panel.add(backButton);
        panel.add(Box.createVerticalStrut(spaces));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        mainPanel.add(panel, cardName);
        
        for (int i = 0; i < 99; i++) 
            if (tableNames[i] == null) {
                tableNames[i] = cardName;
                textAreas[i] = textArea;
                break;
            }
    }
    
    void updateListCard(String cardName, String newText) {
        for (int i = 0; i < 99; i++) 
            if (tableNames[i] != null && tableNames[i].equals(cardName)) {
                textAreas[i].setText(newText);
            }
    }

    String ask(String question) {
        return JOptionPane.showInputDialog(question);
    }

    void show(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    void switchTo(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }

    void switchToPrevious() {
        cardLayout.previous(mainPanel);
    }
}
