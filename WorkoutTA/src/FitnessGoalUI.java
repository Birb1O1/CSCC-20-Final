import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class FitnessGoalUI extends JPanel {

    private navigationService nav;

    public FitnessGoalUI(navigationService nav) {

        this.nav = nav; // store navigation reference
        setLayout(null);

        Color bg = new Color(25, 30, 30);
        Color grayBtn = new Color(60, 60, 60);
        Color redBtn = new Color(230, 60, 60);

        setBackground(bg);

        JLabel title = new JLabel("What is Your Goal?", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBounds(0, 30, 300, 40);
        add(title);

        JLabel subtitle = new JLabel("What would you like to achieve?", SwingConstants.CENTER);
        subtitle.setForeground(new Color(200, 200, 200));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setBounds(0, 65, 300, 20);
        add(subtitle);

        int X = 35, W = 230, H = 50;
        int Y = 110;

        JToggleButton lose = createGoalButton("Lose Weight", grayBtn);
        lose.setBounds(X, Y, W, H);
        add(lose);

        JToggleButton gain = createGoalButton("Gain Weight", grayBtn);
        gain.setBounds(X, Y + 60, W, H);
        add(gain);

        JToggleButton build = createGoalButton("Build Muscle", grayBtn);
        build.setBounds(X, Y + 120, W, H);
        add(build);

        JToggleButton end = createGoalButton("Endurance", grayBtn);
        end.setBounds(X, Y + 180, W, H);
        add(end);

        ButtonGroup group = new ButtonGroup();
        group.add(lose);
        group.add(gain);
        group.add(build);
        group.add(end);

        JButton confirm = new JButton("CONFIRM");
        confirm.setFont(new Font("Segoe UI", Font.BOLD, 14));
        confirm.setForeground(Color.WHITE);
        confirm.setBackground(redBtn);
        confirm.setBorder(new EmptyBorder(5, 5, 5, 5));
        confirm.setFocusPainted(false);
        confirm.setBounds(X, Y + 260, W, 50);
        add(confirm);

        confirm.addActionListener(e -> 
        {
            if (lose.isSelected()) {
                nav.goToLoseWeight();
            } else if (gain.isSelected()) {
                nav.goToGainWeight();
            } else if (build.isSelected()) {
                nav.goToBuildMuscle();
            } else if (end.isSelected()) {
                nav.goToEndurance();
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Please select a goal.");
            }
        });
    }

    private JToggleButton createGoalButton(String text, Color bgColor) {
        JToggleButton btn = new JToggleButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(20));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        return btn;
    }

    class RoundedBorder implements Border {
        private int radius;
        RoundedBorder(int r) { radius = r; }
        public Insets getBorderInsets(Component c) { return new Insets(radius, radius, radius, radius); }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            g.setColor(Color.BLACK);
            g.drawRoundRect(x, y, w - 1, h - 1, radius, radius);
        }
    }
}
