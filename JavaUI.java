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

public class JavaUI 
{
    void buttonPressed(String actionCode) 
    {
        // buttons handling goes here
    }

    final int OBJECTSPACE = 30;
    final JFrame window = new JFrame();
    final CardLayout cardLayout = new CardLayout();
    final JPanel mainPanel = new JPanel(cardLayout);
    final JTextArea[] textAreas = new JTextArea[99];
    final String[] tableNames = new String[99];

    public JavaUI(String windowTitle) 
    {
        window.add(mainPanel);
        window.setSize(400,600);
        window.setResizable(false);
        window.setTitle(windowTitle);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        // to avoid having no display when there is only one card added
        addMenuCard("shit", "shit", new String[][]{{"shit","shit"}});
    }

    // every button press will send its own actionCode to buttonPressed()
    // in options array {buttonName, actionCode}
    // ex: {{"Log In", "goLogIn"}, {"Sign Up", "goSignUp"}}
    public void addMenuCard(String cardName, String title, String[][] options) 
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        
        panel.add(Box.createVerticalStrut(OBJECTSPACE));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(OBJECTSPACE));

        for (int i = 0; i < options.length; i++) 
        {
            int index = i;

            JButton button = new JButton(options[index][0]);
            button.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            button.addActionListener(e -> buttonPressed(options[index][1]));

            panel.add(button);
            panel.add(Box.createVerticalStrut(OBJECTSPACE));
        }

        mainPanel.add(panel, cardName);
    }

    public void addListCard(String cardName, String title) 
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> switchToPrevious());
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(OBJECTSPACE));
        panel.add(backButton);
        panel.add(Box.createVerticalStrut(OBJECTSPACE));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        panel.add(scrollPane);

        mainPanel.add(panel, cardName);
        
        for (int i = 0; i < 99; i++) 
        {
            if (tableNames[i] == null) 
            {
                tableNames[i] = cardName;
                textAreas[i] = textArea;
                break;
            }
        }
    }
    
    public void updateListCard(String cardName, String newText) 
    {
        for (int i = 0; i < 99; i++) 
        {
            if (tableNames[i] != null && tableNames[i].equals(cardName)) 
            {
                textAreas[i].setText(newText);
            }
        }
    }

    public String ask(String question) 
    {
        return JOptionPane.showInputDialog(question);
    }

    public void show(String message) 
    {
        JOptionPane.showMessageDialog(null, message);
    }

    public void switchTo(String cardName) 
    {
        cardLayout.show(mainPanel, cardName);
    }

    public void switchToPrevious() 
    {
        cardLayout.previous(mainPanel);
    }
}
