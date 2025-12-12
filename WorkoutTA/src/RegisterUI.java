import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class RegisterUI extends JPanel {

    private navigationService nav;

    public RegisterUI(navigationService nav) {
        this.nav = nav;

        setLayout(null);
        setPreferredSize(new Dimension(350, 750));

        Color bg = new Color(25, 30, 30);
        Color redBar = new Color(230, 55, 60);
        setBackground(bg);

        JPanel topRed = new JPanel();
        topRed.setBackground(redBar);
        topRed.setBounds(0, 60, 350, 4);
        add(topRed);

        JLabel title = new JLabel("Sign Up", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBounds(0, 70, 350, 40);
        add(title);

        JLabel subtitle = new JLabel("<html><center>Please complete your<br>biodata correctly</center></html>",
                SwingConstants.CENTER);
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setBounds(0, 110, 350, 40);
        add(subtitle);

        int fieldW = 260, fieldH = 45, X = 45;
        int Y = 165;

        JTextField name = createField("Your Name");
        name.setBounds(X, Y, fieldW, fieldH);
        add(name);

        JTextField email = createField("Your E-mail");
        email.setBounds(X, Y + 55, fieldW, fieldH);
        add(email);

        JPasswordField pass = createPassword("Create your Password");
        pass.setBounds(X, Y + 110, fieldW, fieldH);
        add(pass);

        JPasswordField pass2 = createPassword("Re-type your Password");
        pass2.setBounds(X, Y + 165, fieldW, fieldH);
        add(pass2);

        JComboBox<String> gender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        styleCombo(gender);
        gender.setBounds(X, Y + 230, 120, fieldH);
        add(gender);

        JTextField birth = createField("Birth");
        birth.setBounds(X + 140, Y + 230, 120, fieldH);
        add(birth);

        JTextField height = createField("Height (cm)");
        height.setBounds(X, Y + 290, fieldW / 2 - 10, fieldH);
        add(height);

        JTextField weight = createField("Weight (kg)");
        weight.setBounds(X + fieldW / 2 + 10, Y + 290, fieldW / 2 - 10, fieldH);
        add(weight);

        JLabel terms = new JLabel("I agree to the Terms and Conditions", SwingConstants.CENTER);
        terms.setForeground(new Color(200, 200, 200));
        terms.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        terms.setBounds(0, Y + 340, 350, 25);
        add(terms);

        JButton create = new JButton("Create your Account");
        create.setFont(new Font("Segoe UI", Font.BOLD, 16));
        create.setForeground(Color.WHITE);
        create.setBackground(new Color(80, 80, 80));
        create.setBorder(new EmptyBorder(5, 5, 5, 5));
        create.setFocusPainted(false);
        create.setBounds(X, Y + 375, fieldW, 55);
        add(create);

        create.addActionListener(e -> {
            nav.goToLoginPage();
        });
    }

    class PaddedTextField extends JTextField {
        private Insets insets = new Insets(10, 10, 10, 10);
        public PaddedTextField(String text) { super(text); }
        public Insets getInsets() { return insets; }
    }

    class PaddedPasswordField extends JPasswordField {
        private Insets insets = new Insets(10, 10, 10, 10);
        public PaddedPasswordField(String text) { super(text); }
        public Insets getInsets() { return insets; }
    }

    private JTextField createField(String placeholder) {
        JTextField field = new PaddedTextField(placeholder);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setForeground(Color.GRAY);
        field.setBorder(new RoundedBorder(20));

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        return field;
    }

    private JPasswordField createPassword(String placeholder) {
        JPasswordField field = new PaddedPasswordField(placeholder);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setForeground(Color.GRAY);
        field.setBorder(new RoundedBorder(20));
        field.setEchoChar((char) 0);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                    field.setEchoChar('•');
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setEchoChar((char) 0);
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        return field;
    }

    private void styleCombo(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBorder(new RoundedBorder(20));
    }

    class RoundedBorder implements Border {
        private int radius;
        RoundedBorder(int r) { radius = r; }
        public Insets getBorderInsets(Component c) { return new Insets(radius, radius, radius, radius); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawRoundRect(x, y, w - 1, h - 1, radius, radius);
        }
    }
}
