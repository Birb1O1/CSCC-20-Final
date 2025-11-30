import javax.swing.*;

public class RegisterPage extends JPanel {

    private navigationService nav;

    public RegisterPage(navigationService nav) {
        this.nav = nav;
        initUI();
    }

    private void initUI() {
        setLayout(null);
        setPreferredSize(new java.awt.Dimension(360, 640));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 180, 120, 30);
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(180, 180, 120, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 230, 120, 30);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(180, 230, 120, 30);
        add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50, 300, 250, 50);
        add(registerButton);

        JButton loginButton = new JButton("Already have an account");
        loginButton.setBounds(50, 370, 250, 40);
        add(loginButton);

        // REGISTER BUTTON ACTION
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.");
                return;
            }
            try {
                java.io.RandomAccessFile raf = new java.io.RandomAccessFile("gwapsinfo", "rw");
                String line;
                boolean usernameExists = false;
                while ((line = raf.readLine()) != null) {
                    if (line.startsWith("[")) {
                        String[] parts = line.substring(1, line.length() - 1).split(",");
                        if (parts.length >= 5 && parts[4].trim().equalsIgnoreCase(username)) {
                            usernameExists = true;
                            break;
                        }
                    }
                }
                if (usernameExists) {
                    JOptionPane.showMessageDialog(this, "Username already exists. Please choose another.");
                    raf.close();
                    return;
                }

                // Find last entry
                raf.seek(0);
                long lastPos = 0;
                String lastLine = null;
                while ((line = raf.readLine()) != null) {
                    lastPos = raf.getFilePointer();
                    lastLine = line;
                }

                if (lastLine != null && lastLine.startsWith("[")) {
                    int firstComma = lastLine.indexOf(",");
                    int secondComma = lastLine.indexOf(",", firstComma + 1);
                    int thirdComma = lastLine.indexOf(",", secondComma + 1);
                    int fourthComma = lastLine.indexOf(",", thirdComma + 1);
                    int fifthComma = lastLine.indexOf(",", fourthComma + 1);
                    int sixthComma = lastLine.indexOf(",", fifthComma + 1);

                    if (fifthComma != -1 && sixthComma != -1) {
                        String updatedLine = lastLine.substring(0, fourthComma + 2) + username
                                + lastLine.substring(fifthComma, fifthComma + 1) + " " + password
                                + lastLine.substring(sixthComma);

                        raf.seek(lastPos - lastLine.length() - 1);
                        raf.writeBytes(updatedLine + "\n");
                        raf.close();

                        JOptionPane.showMessageDialog(this, "Registration successful!");

                        // GO TO HOMEPAGE VIA NAVIGATION
                        nav.goToHomepage();
                    } else {
                        JOptionPane.showMessageDialog(this, "Entry format error. Please start from Welcome page.");
                        raf.close();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No entry found to update. Please start from Welcome page.");
                    raf.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error during registration: " + ex.getMessage());
            }
        });

        // LOGIN BUTTON ACTION
        loginButton.addActionListener(e -> nav.goToLoginPage());
    }
}
