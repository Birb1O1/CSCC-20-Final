import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class Cardio extends JPanel {

    private navigationService nav;

    public Cardio(navigationService nav) {

        this.nav = nav;
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 20));

        JLabel title = new JLabel("Workout Guide", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        String[] columns = { "Exercise", "Duration", "Estimated Calories Burned" };

        Object[][] data = {
                {"Treadmill / Running", "30–40 min or 20–25 min HIIT", "300–450 kcal"},
                {"Elliptical Trainer", "30–40 min", "250–400 kcal"},
                {"Rowing Machine", "20–30 min", "200–350 kcal"},
                {"Stationary Bike / Cycling", "30–45 min", "250–400 kcal"},
                {"Jump Rope", "10–15 min", "150–250 kcal"},
                {"Stair Climber / Step Machine", "20–30 min", "200–300 kcal"},
                {"HIIT", "15–25 min (30s work / 30s rest)", "250–400 kcal"}
        };

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

        JButton chooseBtn = new JButton("Choose a Workout");
        chooseBtn.setFont(new Font("Arial", Font.BOLD, 20));
        chooseBtn.setBackground(new Color(50, 50, 50));
        chooseBtn.setForeground(Color.WHITE);
        chooseBtn.setFocusPainted(false);
        chooseBtn.setPreferredSize(new Dimension(240, 50));

        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 20));
        backBtn.setBackground(new Color(150, 0, 0));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setPreferredSize(new Dimension(240, 50));

        backBtn.addActionListener(e -> nav.goToHomepage());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(20, 20, 20));
        bottomPanel.setLayout(new GridLayout(2, 1, 0, 15));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        bottomPanel.add(chooseBtn);
        bottomPanel.add(backBtn);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
