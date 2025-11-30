import java.awt.BorderLayout;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {

            AppFrame frame = new AppFrame();
            frame.setLayout(new BorderLayout());
            frame.setVisible(true);

            // Create navigation service and give it the main window
            navigationService nav = new navigationService(frame);

            // Start app at Welcome page
            nav.goToWelcome();
        });
    }
}
