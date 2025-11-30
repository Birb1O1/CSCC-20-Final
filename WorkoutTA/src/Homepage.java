import javax.swing.*;

public class Homepage extends JPanel {
    public Homepage() {
        setLayout(null);
        setPreferredSize(new java.awt.Dimension(360, 640));

        JLabel welcomeLabel = new JLabel("Welcome to the Homepage!");
        welcomeLabel.setBounds(80, 100, 200, 40);
        add(welcomeLabel);

       
    }
}
