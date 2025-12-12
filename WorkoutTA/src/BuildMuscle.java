import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class BuildMuscle extends JPanel {

    private navigationService nav;

    public BuildMuscle(navigationService nav) {
        this.nav = nav;

        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 20));

        // Title
        JLabel title = new JLabel("Muscle Gain Guide", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        // Table columns
        String[] columns = { "Exercise", "Sets × Reps", "Estimated Calories Burned" };

        Object[][] data = {
                {"Squats", "4 × 8–12", "90–120 kcal"},
                {"Deadlifts", "4 × 6–10", "100–140 kcal"},
                {"Bench Press", "4 × 8–12", "70–100 kcal"},
                {"Pull-Ups / Chin-Ups", "3 × 6–12", "50–80 kcal"},
                {"Overhead Press", "4 × 8–12", "60–90 kcal"},
                {"Barbell / Dumbbell Rows", "4 × 8–12", "70–100 kcal"},
                {"Leg Press", "4 × 10–15", "80–110 kcal"},
                {"Bicep Curls & Tricep Extensions", "3 × 12–15", "40–60 kcal"}
        };

        // Create table
        JTable table = new JTable(data, columns);
        table.setBackground(new Color(40, 40, 40));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(60, 60, 60));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setPreferredSize(new Dimension(50, 35));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        scrollPane.getViewport().setBackground(new Color(20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JButton chooseBtn = new JButton("Choose a Workout");
        chooseBtn.setFont(new Font("Arial", Font.BOLD, 20));
        chooseBtn.setBackground(new Color(50, 50, 50));
        chooseBtn.setForeground(Color.WHITE);
        chooseBtn.setFocusPainted(false);
        chooseBtn.setPreferredSize(new Dimension(240, 50));

        // Example navigation to Cardio page (change as needed)
        chooseBtn.addActionListener(e -> {
            nav.goToHomepage();
        });

        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 20));
        backBtn.setBackground(new Color(100, 0, 0));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setPreferredSize(new Dimension(240, 50));

        backBtn.addActionListener(e -> nav.goToHomepage());

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(20, 20, 20));
        bottomPanel.setLayout(new GridLayout(2, 1, 0, 15));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        bottomPanel.add(chooseBtn);
        bottomPanel.add(backBtn);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
