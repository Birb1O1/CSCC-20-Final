import javax.swing.*;
import java.awt.*;

public class HomePageDesign extends JPanel {

    private navigationService nav;

    public HomePageDesign(navigationService nav) {
        this.nav = nav;
        setLayout(null);
        setPreferredSize(new Dimension(360, 640));
        setBackground(new Color(20, 20, 20)); // dark background

        // Header panel
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 360, 60);
        header.setBackground(new Color(15, 15, 15));

        JLabel appTitle = new JLabel("Fitness");
        appTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        appTitle.setForeground(Color.WHITE);
        appTitle.setBounds(20, 10, 200, 40);

        JSeparator redLine = new JSeparator();
        redLine.setForeground(Color.RED);
        redLine.setBounds(0, 58, 360, 2);

        header.add(appTitle);
        header.add(redLine);
        add(header);

        // Page title
        JLabel pageTitle = new JLabel("Home", SwingConstants.CENTER);
        pageTitle.setForeground(Color.WHITE);
        pageTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        pageTitle.setBounds(0, 90, 360, 50);
        add(pageTitle);

        JLabel subtitle = new JLabel("Please select an option", SwingConstants.CENTER);
        subtitle.setForeground(new Color(200, 200, 200));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setBounds(0, 135, 360, 40);
        add(subtitle);

        // Choose a Workout button
        JButton chooseBtn = new JButton("Choose a Workout");
        chooseBtn.setBounds(40, 230, 280, 60);
        chooseBtn.setBackground(new Color(50, 50, 50));
        chooseBtn.setForeground(Color.WHITE);
        chooseBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        chooseBtn.setFocusPainted(false);
        chooseBtn.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        chooseBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(chooseBtn);

        chooseBtn.addActionListener(e -> nav.goToFitnessGoal()); // Navigate to Fitness Goal UI

        // Quit button
        JButton quitBtn = new JButton("Quit");
        quitBtn.setBounds(40, 320, 280, 60);
        quitBtn.setBackground(new Color(100, 0, 0));
        quitBtn.setForeground(Color.WHITE);
        quitBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        quitBtn.setFocusPainted(false);
        quitBtn.setBorder(BorderFactory.createLineBorder(new Color(140, 0, 0)));
        quitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(quitBtn);

        quitBtn.addActionListener(e -> System.exit(0));
    }
}
