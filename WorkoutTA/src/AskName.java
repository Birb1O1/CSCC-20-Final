import javax.swing.*;
import java.awt.Image;
import java.awt.Color;

public class AskName extends JPanel 
{
   private navigationService nav;  // store nav service

    public AskName(navigationService nav)              // FIXED constructor
    {   
        this.nav = nav;

        setLayout(null);
        setPreferredSize(new java.awt.Dimension(360, 640));

        // Background Image
        ImageIcon askNameIcon = new ImageIcon(getClass().getResource("/assets/AskName.png"));
        Image img = askNameIcon.getImage();
        Image scaledImg = img.getScaledInstance(360, 640, Image.SCALE_AREA_AVERAGING);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));
        imageLabel.setBounds(0, 0, 360, 640);
        add(imageLabel);

        // Chatbox 
        JTextField nameField = new JTextField();
        nameField.setBounds(50, 300, 230, 40);
        nameField.setOpaque(false);
        nameField.setBorder(null);
        nameField.setForeground(Color.BLACK);
        add(nameField);

        // Continue Button
        JButton continueButton = new JButton("");
        continueButton.setBounds(80, 363, 200, 50);
        continueButton.setContentAreaFilled(false);
        continueButton.setBorderPainted(false);
        continueButton.setFocusPainted(false);
        continueButton.setOpaque(false);
        add(continueButton);

        // Already have an account Button
        JButton loginButton = new JButton("");
        loginButton.setBounds(80, 420, 200, 40);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setOpaque(false);
        add(loginButton);

        // FILE HANDLING
        continueButton.addActionListener(e -> {
            String name = nameField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name.");
                return;
            }

            try (java.io.FileWriter writer = new java.io.FileWriter("gwapsinfo", true)) {
                writer.write("[" + name + ", , , , , , , , ]\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving name.");
            }

            // GO TO ASK GENDER USING NAVIGATION SERVICE
            nav.goToAskGender();
        });

        // LOGIN BUTTON NAVIGATION
        loginButton.addActionListener(e -> nav.goToLoginPage());

        setComponentZOrder(imageLabel, getComponentCount() - 1);
    }
}
