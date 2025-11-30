import javax.swing.*;

public class LoginPage extends JPanel {

    private navigationService nav;

    public LoginPage(navigationService nav) {
        this.nav = nav;
        initUI();
    }

    private void initUI() {
        setLayout(null);
        setPreferredSize(new java.awt.Dimension(360, 640));

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(80, 300, 200, 50);
        add(signUpButton);

        // Use navigationService to go to AskName page
        signUpButton.addActionListener(e -> nav.goToAskName());
    }
}
