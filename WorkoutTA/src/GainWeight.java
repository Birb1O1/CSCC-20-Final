import javax.swing.*;
import java.awt.*;

public class GainWeight extends JPanel {

    private navigationService nav;

    public GainWeight(navigationService nav) {
        this.nav = nav;
        createUI();
    }

    private void createUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(15, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Gain Weight Workouts");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(title);
        add(Box.createVerticalStrut(10));

        // Workout cards
        add(createWorkoutCard("Lunge", "Intense", "/Lunge.png"));
        add(Box.createVerticalStrut(15));
        add(createWorkoutCard("Dip", "Moderate", "/Dip.png"));
        add(Box.createVerticalStrut(15));
        add(createWorkoutCard("Bicep Curl", "Beginner", "/Bicep.png"));
        add(Box.createVerticalGlue());

        // Choose Workout button
        JButton unlockButton = new JButton("Choose Workout");
        unlockButton.setPreferredSize(new Dimension(300, 50));
        unlockButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        unlockButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        unlockButton.setBackground(new Color(0, 122, 255));
        unlockButton.setForeground(Color.WHITE);
        unlockButton.setFont(new Font("Arial", Font.BOLD, 18));
        add(unlockButton);

        unlockButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Gain Weight workout selected!");
            // nav.showPage(new NextPage(nav)); // replace with next page if needed
            nav.goToHomepage();
        });
    }

    private JPanel createWorkoutCard(String title, String subtitle, String imagePath) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(320, 90));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        card.setBackground(new Color(40, 40, 40));
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Text panel
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setForeground(new Color(180, 180, 180));
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(40, 40, 40));
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);

        card.add(textPanel, BorderLayout.CENTER);

        // Image
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image scaled = icon.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaled));
            card.add(imgLabel, BorderLayout.EAST);
        } catch (Exception e) {
            System.out.println("Image not found: " + imagePath);
        }

        return card;
    }
}
