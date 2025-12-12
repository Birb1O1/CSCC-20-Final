import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JPanel {

    private navigationService nav;

    public ProfilePage(navigationService nav) {
        this.nav = nav;

        setBackground(new Color(20, 20, 20));
        setLayout(null);

        // Page Title
        JLabel title = new JLabel("Profile", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setBounds(0, 50, 420, 50);
        add(title);

        // User Avatar (placeholder circle)
        JLabel avatar = new JLabel();
        avatar.setBounds(160, 120, 100, 100);
        avatar.setOpaque(true);
        avatar.setBackground(new Color(50, 50, 50));
        avatar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        avatar.setHorizontalAlignment(SwingConstants.CENTER);
        avatar.setText("U");
        avatar.setFont(new Font("Segoe UI", Font.BOLD, 48));
        avatar.setForeground(Color.WHITE);
        add(avatar);

        // Username
        JLabel nameLabel = new JLabel("User", SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        nameLabel.setBounds(0, 240, 420, 40);
        add(nameLabel);

        // Email placeholder
        JLabel emailLabel = new JLabel("user@example.com", SwingConstants.CENTER);
        emailLabel.setForeground(new Color(180, 180, 180));
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setBounds(0, 280, 420, 30);
        add(emailLabel);

        // Edit Profile Button
        JButton editBtn = new JButton("Edit Profile");
        editBtn.setBounds(60, 350, 300, 50);
        editBtn.setBackground(new Color(50, 50, 50));
        editBtn.setForeground(Color.WHITE);
        editBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(editBtn);

        editBtn.addActionListener(e -> {
            // You can implement edit profile logic or navigate to an edit panel
            JOptionPane.showMessageDialog(this, "Edit profile clicked!");
        });

        // Back Button
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(60, 430, 300, 50);
        backBtn.setBackground(new Color(50, 50, 50));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(backBtn);

        backBtn.addActionListener(e -> {
            // Navigate back to Home Page
            nav.goToHomepage();
        });
    }
}
