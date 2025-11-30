import javax.swing.*;
import java.awt.Image;

public class AskGender extends JPanel {

    private navigationService nav;   // store nav reference

    public AskGender(navigationService nav) {   // FIXED constructor
        this.nav = nav;

        setLayout(null);
        setPreferredSize(new java.awt.Dimension(360, 640));

        ImageIcon askGenderIcon = new ImageIcon(getClass().getResource("/assets/AskGender.png"));
        Image img = askGenderIcon.getImage();
        Image scaledImg = img.getScaledInstance(360, 640, Image.SCALE_AREA_AVERAGING);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));
        imageLabel.setBounds(0, 0, 360, 640);
        add(imageLabel);

        JButton maleButton = new JButton("") {
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
                g2.dispose();
            }
        };
        maleButton.setBounds(50, 230, 100, 100);
        maleButton.setContentAreaFilled(false);
        maleButton.setBorderPainted(false);
        maleButton.setFocusPainted(false);
        maleButton.setOpaque(false);
        maleButton.setForeground(new java.awt.Color(0,0,0,0));
        add(maleButton);

        JButton femaleButton = new JButton("") {
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
                g2.dispose();
            }
        };
        femaleButton.setBounds(210, 230, 100, 100);
        femaleButton.setContentAreaFilled(false);
        femaleButton.setBorderPainted(false);
        femaleButton.setFocusPainted(false);
        femaleButton.setOpaque(false);
        femaleButton.setForeground(new java.awt.Color(0,0,0,0));
        add(femaleButton);

        final String[] selectedGender = {null};
        final JButton[] skipButtonRef = new JButton[1];

        maleButton.addActionListener(e -> {
            selectedGender[0] = "Male";
            maleButton.setBackground(java.awt.Color.CYAN);
            femaleButton.setBackground(null);
            if (skipButtonRef[0] != null) skipButtonRef[0].setBackground(null);
        });

        femaleButton.addActionListener(e -> {
            selectedGender[0] = "Female";
            femaleButton.setBackground(java.awt.Color.CYAN);
            maleButton.setBackground(null);
            if (skipButtonRef[0] != null) skipButtonRef[0].setBackground(null);
        });

        JButton skipButton = new JButton("") {
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
                g2.dispose();
            }
        };
        skipButtonRef[0] = skipButton;
        skipButton.setBounds(135, 330, 80, 80);
        skipButton.setContentAreaFilled(false);
        skipButton.setBorderPainted(false);
        skipButton.setFocusPainted(false);
        skipButton.setOpaque(false);
        skipButton.setForeground(new java.awt.Color(0,0,0,0));
        add(skipButton);

        skipButton.addActionListener(e -> {
            selectedGender[0] = "Skipped";
            maleButton.setBackground(null);
            femaleButton.setBackground(null);
            skipButton.setBackground(java.awt.Color.CYAN);
        });

        JButton continueButton = new JButton("");
        continueButton.setBounds(50, 505, 240, 45);
        continueButton.setContentAreaFilled(false);
        continueButton.setBorderPainted(false);
        continueButton.setFocusPainted(false);
        continueButton.setOpaque(false);
        continueButton.setForeground(new java.awt.Color(0,0,0,0));
        add(continueButton);

        continueButton.addActionListener(e -> {
            if (selectedGender[0] == null) {
                JOptionPane.showMessageDialog(this, "Select at least one option (Male, Female, or Skip)");
                return;
            }

            // FILE HANDLING (unchanged)
            try {
                java.io.RandomAccessFile raf = new java.io.RandomAccessFile("gwapsinfo", "rw");
                long lastPos = 0;
                String lastLine = null;
                String line;
                boolean foundEntry = false;

                while ((line = raf.readLine()) != null) {
                    lastPos = raf.getFilePointer();
                    lastLine = line;
                    if (lastLine.startsWith("[")) {
                        foundEntry = true;
                    }
                }

                if (foundEntry && lastLine != null && lastLine.startsWith("[")) {
                    int firstComma = lastLine.indexOf(",");
                    int secondComma = lastLine.indexOf(",", firstComma + 1);

                    if (firstComma != -1 && secondComma != -1) {
                        String genderValue = selectedGender[0].equals("Skipped") ? "N/A" : selectedGender[0];
                        String updatedLine =
                                lastLine.substring(0, firstComma + 2) +
                                genderValue +
                                lastLine.substring(secondComma);

                        raf.seek(lastPos - lastLine.length() - 1);
                        raf.writeBytes(updatedLine + "\n");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "No entry found. Start from Welcome page.");
                }

                raf.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving gender: " + ex.getMessage());
            }

            // USE NAVIGATION SERVICE
            nav.goToAskHeightAndWeight();
        });

        JButton backButton = new JButton("");
        backButton.setBounds(50, 550, 260, 40);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        backButton.setForeground(new java.awt.Color(0,0,0,0));
        add(backButton);

        backButton.addActionListener(e -> {
            nav.goToLoginPage();
        });

        setComponentZOrder(imageLabel, getComponentCount() - 1);
    }
}
