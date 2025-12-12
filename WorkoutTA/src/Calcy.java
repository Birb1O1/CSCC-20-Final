import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Calcy extends JPanel {

    private final Map<String, Double> MET_VALUES;
    private final Map<String, Boolean> USE_SETS_REPS;

    private JTextField weightField;
    private JPanel exercisesPanel;
    private JScrollPane scrollPane;
    private JButton addExerciseButton;
    private JButton calculateButton;
    private JButton backButton;

    private final java.util.List<ExerciseRow> exerciseRows = new ArrayList<>();
    private navigationService nav;

    public Calcy(navigationService nav) {
        this.nav = nav;

        MET_VALUES = new LinkedHashMap<>();
        USE_SETS_REPS = new HashMap<>();

        // Initialize MET values
        addExerciseMET("bench_press", 4.0, true);
        addExerciseMET("triceps_pushdowns", 4.0, true);
        addExerciseMET("deadlift", 6.0, true);
        addExerciseMET("lunge", 3.5, true);
        addExerciseMET("dip", 4.5, true);
        addExerciseMET("bicep_curl", 3.0, true);
        addExerciseMET("pull_ups", 8.0, false);
        addExerciseMET("squats", 5.0, false);
        addExerciseMET("push_ups", 5.0, false);
        addExerciseMET("swimming", 7.0, false);
        addExerciseMET("jogging", 8.0, false);
        addExerciseMET("inclined_walking", 6.0, false);

        createUI();
    }

    private void addExerciseMET(String name, double met, boolean useSetsReps) {
        MET_VALUES.put(name, met);
        USE_SETS_REPS.put(name, useSetsReps);
    }

    private void createUI() {
        setLayout(null);
        setPreferredSize(new Dimension(360, 640));
        setBackground(new Color(20, 20, 20));

        JLabel title = new JLabel("Calorie Burn Calculator");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBounds(20, 10, 320, 30);
        add(title);

        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setForeground(Color.WHITE);
        weightLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        weightLabel.setBounds(20, 50, 100, 25);
        add(weightLabel);

        weightField = new JTextField();
        weightField.setBounds(130, 50, 80, 25);
        add(weightField);

        exercisesPanel = new JPanel();
        exercisesPanel.setLayout(new BoxLayout(exercisesPanel, BoxLayout.Y_AXIS));
        exercisesPanel.setBackground(new Color(20, 20, 20));

        scrollPane = new JScrollPane(exercisesPanel);
        scrollPane.setBounds(20, 90, 320, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        addExerciseButton = new JButton("Add Exercise");
        addExerciseButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addExerciseButton.setBounds(20, 500, 320, 40);
        addExerciseButton.setBackground(new Color(50, 50, 50));
        addExerciseButton.setForeground(Color.WHITE);
        addExerciseButton.setFocusPainted(false);
        addExerciseButton.addActionListener(e -> addExerciseRow());
        add(addExerciseButton);

        calculateButton = new JButton("Calculate Calories");
        calculateButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        calculateButton.setBounds(20, 550, 320, 50);
        calculateButton.setBackground(new Color(0, 122, 255));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(e -> calculateTotalCalories());
        add(calculateButton);

        // Back Button to Home
        backButton = new JButton("Back to Home");
        backButton.setBounds(20, 610, 320, 40);
        backButton.setBackground(new Color(50, 50, 50));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> nav.goToHomepage());
        add(backButton);
    }

    private void addExerciseRow() {
        ExerciseRow row = new ExerciseRow();
        exerciseRows.add(row);
        exercisesPanel.add(row);
        exercisesPanel.revalidate();
        exercisesPanel.repaint();
    }

    private void removeExerciseRow(ExerciseRow row) {
        exercisesPanel.remove(row);
        exerciseRows.remove(row);
        exercisesPanel.revalidate();
        exercisesPanel.repaint();
    }

    private void calculateTotalCalories() {
        double weight;
        try {
            weight = Double.parseDouble(weightField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid weight.");
            return;
        }

        double total = 0;
        java.util.List<String> details = new ArrayList<>();
        for (ExerciseRow row : exerciseRows) {
            double calories = row.calculateCalories(weight);
            total += calories;
            String exerciseName = (String) row.exerciseCombo.getSelectedItem();
            details.add(exerciseName + ": " + calories + " kcal");
        }

        // Result Dialog
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Calories Burned", true);
        dialog.setSize(360, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(new Color(20, 20, 20));

        JLabel title = new JLabel("Estimated Calories Burned", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        dialog.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(20, 20, 20));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        for (String line : details) {
            JLabel label = new JLabel(line);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            label.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
            centerPanel.add(label);
        }

        JLabel totalLabel = new JLabel("Total: " + total + " kcal", SwingConstants.CENTER);
        totalLabel.setForeground(new Color(0, 200, 255));
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        centerPanel.add(totalLabel);

        JScrollPane resultScroll = new JScrollPane(centerPanel);
        resultScroll.setBorder(null);
        resultScroll.getVerticalScrollBar().setUnitIncrement(16);
        dialog.add(resultScroll, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        okButton.setBackground(new Color(0, 122, 255));
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.addActionListener(e -> dialog.dispose());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(20, 20, 20));
        southPanel.add(okButton);
        dialog.add(southPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private class ExerciseRow extends JPanel {
        private JComboBox<String> exerciseCombo;
        private JTextField setsField, repsField, durationField;
        private JLabel setsLabel, repsLabel, durationLabel;
        private JButton removeButton;

        public ExerciseRow() {
            setLayout(new GridBagLayout());
            setMaximumSize(new Dimension(320, 55));
            setBackground(new Color(30, 30, 30));
            setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            exerciseCombo = new JComboBox<>(MET_VALUES.keySet().toArray(new String[0]));
            exerciseCombo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            exerciseCombo.setForeground(Color.WHITE);
            exerciseCombo.setBackground(new Color(50, 50, 50));
            exerciseCombo.setPreferredSize(new Dimension(100, 25));
            gbc.gridx = 0;
            gbc.weightx = 0.15;
            add(exerciseCombo, gbc);

            setsLabel = new JLabel("Sets:");
            setsLabel.setForeground(Color.WHITE);
            setsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            gbc.gridx = 1;
            gbc.weightx = 0.1;
            add(setsLabel, gbc);

            setsField = new JTextField();
            setsField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            gbc.gridx = 2;
            gbc.weightx = 0.15;
            add(setsField, gbc);

            repsLabel = new JLabel("Reps:");
            repsLabel.setForeground(Color.WHITE);
            repsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            gbc.gridx = 3;
            gbc.weightx = 0.1;
            add(repsLabel, gbc);

            repsField = new JTextField();
            repsField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            gbc.gridx = 4;
            gbc.weightx = 0.15;
            add(repsField, gbc);

            durationLabel = new JLabel("Mins:");
            durationLabel.setForeground(Color.WHITE);
            durationLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            gbc.gridx = 1;
            add(durationLabel, gbc);

            durationField = new JTextField();
            durationField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            gbc.gridx = 2;
            gbc.gridwidth = 2;
            add(durationField, gbc);

            removeButton = new JButton("X");
            removeButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
            removeButton.setBackground(new Color(200, 0, 0));
            removeButton.setForeground(Color.WHITE);
            removeButton.setFocusPainted(false);
            removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            gbc.gridx = 5;
            gbc.weightx = 0;
            gbc.gridwidth = 1;
            add(removeButton, gbc);

            removeButton.addActionListener(e -> removeExerciseRow(this));
            exerciseCombo.addActionListener(e -> updateFieldsVisibility());
            updateFieldsVisibility();
        }

        private void updateFieldsVisibility() {
            if (exerciseCombo.getSelectedItem() == null) return;
            String key = ((String) exerciseCombo.getSelectedItem()).toLowerCase().replace(' ', '_');
            boolean isSetsReps = USE_SETS_REPS.getOrDefault(key, false);

            setsLabel.setVisible(isSetsReps);
            setsField.setVisible(isSetsReps);
            repsLabel.setVisible(isSetsReps);
            repsField.setVisible(isSetsReps);

            durationLabel.setVisible(!isSetsReps);
            durationField.setVisible(!isSetsReps);
        }

        public double calculateCalories(double weight) {
            String key = ((String) exerciseCombo.getSelectedItem()).toLowerCase().replace(' ', '_');
            double met = MET_VALUES.getOrDefault(key, 0.0);
            boolean isSetsReps = USE_SETS_REPS.getOrDefault(key, false);
            double durationMinutes = 0;

            try {
                if (isSetsReps) {
                    int sets = Integer.parseInt(setsField.getText());
                    int reps = Integer.parseInt(repsField.getText());
                    double totalReps = sets * reps;
                    double totalLiftingSeconds = totalReps * 4.0;
                    double totalRestSeconds = (sets - 1) * 90.0;
                    durationMinutes = (totalLiftingSeconds + totalRestSeconds) / 60.0;
                } else {
                    durationMinutes = Double.parseDouble(durationField.getText());
                }
            } catch (NumberFormatException ignored) {}

            return Math.round(met * weight * (durationMinutes / 60.0) * 100.0) / 100.0;
        }
    }
}
