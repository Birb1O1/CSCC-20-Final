import javax.swing.*;
import java.awt.*;

public class Welcome extends JPanel {

    private navigationService nav;

    public Welcome(navigationService nav)
    {
        this.nav = nav;
        initUI();  
    }

    private void initUI()
    {
        setLayout(null);
        setPreferredSize(new Dimension(360, 640));

        ImageIcon welcomeIcon = new ImageIcon(getClass().getResource("/assets/Welcome.png"));
        Image img = welcomeIcon.getImage();
        Image scaledImg = img.getScaledInstance(360, 640, Image.SCALE_AREA_AVERAGING);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));
        imageLabel.setBounds(0, 0, 360, 640);
        add(imageLabel);

        JButton startButton = new JButton("");
        startButton.setBounds(85, 480, 200, 50);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);
        add(startButton);

        JButton loginButton = new JButton("");
        loginButton.setBounds(135, 550, 100, 25);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setOpaque(false);
        add(loginButton);

        setComponentZOrder(imageLabel, getComponentCount() - 1);

        // BUTTON ACTIONS — NOW CORRECT
        startButton.addActionListener(e -> nav.goToAskName());
        loginButton.addActionListener(e -> nav.goToLoginPage());
    }
}
