import javax.swing.*;
import java.awt.*;

public class HomePageDesign extends JPanel {

    private navigationService nav;

    public HomePageDesign(navigationService nav) {

        this.nav = nav;

        setBackground(new Color(20, 20, 20));
        setLayout(null);

        // Title
        JLabel title = new JLabel("Home", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setBounds(0, 90, 420, 50);
        add(title);

        // Choose Workout Button
        JButton chooseBtn = new JButton("Choose a Workout");
        chooseBtn.setBounds(60, 230, 300, 60);
        chooseBtn.setBackground(new Color(50, 50, 50));
        chooseBtn.setForeground(Color.WHITE);
        chooseBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(chooseBtn);

        chooseBtn.addActionListener(e -> nav.goToFitnessGoal());

        // Profile Button
        JButton profileBtn = new JButton("Profile");
        profileBtn.setBounds(60, 310, 300, 60); // shifted down
        profileBtn.setBackground(new Color(50, 50, 50));
        profileBtn.setForeground(Color.WHITE);
        profileBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(profileBtn);

        profileBtn.addActionListener(e -> nav.goToProfile());

        // Calorie Calculator Button
        JButton calorieBtn = new JButton("Calorie Calculator");
        calorieBtn.setBounds(60, 390, 300, 60);
        calorieBtn.setBackground(new Color(50, 50, 50));
        calorieBtn.setForeground(Color.WHITE);
        calorieBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(calorieBtn);

        calorieBtn.addActionListener(e -> nav.goToCalculator());

        // Quit Button
        JButton quitBtn = new JButton("Quit");
        quitBtn.setBounds(60, 470, 300, 60); // shifted down
        quitBtn.setBackground(new Color(100, 0, 0));
        quitBtn.setForeground(Color.WHITE);
        quitBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(quitBtn);

        quitBtn.addActionListener(e -> System.exit(0));
    }
}
