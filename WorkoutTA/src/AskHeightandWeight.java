import javax.swing.*;

public class AskHeightandWeight extends JPanel {

    private navigationService nav;

    public AskHeightandWeight(navigationService nav) {   // FIXED
        this.nav = nav;

        setLayout(null);
        setPreferredSize(new java.awt.Dimension(360, 640));

        JLabel heightLabel = new JLabel("Height (in CM):");
        heightLabel.setBounds(50, 180, 120, 30);
        add(heightLabel);

        JTextField heightField = new JTextField();
        heightField.setBounds(180, 180, 120, 30);
        add(heightField);

        JLabel weightLabel = new JLabel("Weight (in KG):");
        weightLabel.setBounds(50, 230, 120, 30);
        add(weightLabel);

        JTextField weightField = new JTextField();
        weightField.setBounds(180, 230, 120, 30);
        add(weightField);

        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(50, 300, 250, 50);
        add(continueButton);

        continueButton.addActionListener(e -> {

            String height = heightField.getText();
            String weight = weightField.getText();

            if (height.isEmpty() || weight.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill in the necessary details.");
                return;
            }

            try {
                Integer.parseInt(height);
                Integer.parseInt(weight);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Height and weight must be integers.");
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
                    int thirdComma = lastLine.indexOf(",", secondComma + 1);
                    int fourthComma = lastLine.indexOf(",", thirdComma + 1);

                    if (secondComma != -1 && thirdComma != -1 && fourthComma != -1) {

                        String updatedLine =
                                lastLine.substring(0, secondComma + 2) +
                                        height +
                                        lastLine.substring(thirdComma, thirdComma + 1) +
                                        " " +
                                        weight +
                                        lastLine.substring(fourthComma);

                        raf.seek(lastPos - lastLine.length() - 1);
                        raf.writeBytes(updatedLine + "\n");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "No entry to update. Start from Welcome page.");
                }

                raf.close();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving height and weight: " + ex.getMessage());
                return;
            }

            // USE NAVIGATION SERVICE INSTEAD OF topFrame
            nav.goToFitnessGoal();
        });

        JButton loginButton = new JButton("Already have an account");
        loginButton.setBounds(50, 370, 250, 40);
        add(loginButton);

        loginButton.addActionListener(e -> {
            nav.goToLoginPage();
        });
    }
}
