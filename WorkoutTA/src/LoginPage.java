import javax.swing.*;
import java.awt.*;

public class LoginPage extends JPanel {

    private navigationService nav;

    public LoginPage(navigationService nav) {

        this.nav = nav;

        setBackground(new Color(26, 30, 31));
        setLayout(null);

        // Title
        JLabel lblTitle = new JLabel("Log in");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Poppins", Font.BOLD, 26));
        lblTitle.setBounds(160, 40, 350, 40);
        add(lblTitle);

        // Email
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Poppins", Font.BOLD, 16));
        lblEmail.setBounds(50, 130, 200, 20);
        add(lblEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setBackground(new Color(26, 30, 31));
        txtEmail.setForeground(Color.WHITE);
        txtEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        txtEmail.setBounds(50, 160, 300, 30);
        add(txtEmail);

        // Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Poppins", Font.BOLD, 16));
        lblPassword.setBounds(50, 210, 200, 20);
        add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBackground(new Color(26, 30, 31));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        txtPassword.setBounds(50, 240, 300, 30);
        add(txtPassword);

        // Login Button
        JButton btnLogin = new JButton("Log In");
        btnLogin.setBackground(new Color(45, 45, 45));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Poppins", Font.BOLD, 18));
        btnLogin.setBounds(50, 340, 300, 50);
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            // Navigate to home
            nav.goToHomepage();;
        });

        // Register Button
        JButton btnRegister = new JButton("Register");
        btnRegister.setBackground(Color.WHITE);
        btnRegister.setForeground(Color.BLACK);
        btnRegister.setFont(new Font("Poppins", Font.BOLD, 16));
        btnRegister.setBounds(50, 410, 300, 50);
        add(btnRegister);

        btnRegister.addActionListener(e -> {
            nav.goToRegisterPage();
        });
    }
}
