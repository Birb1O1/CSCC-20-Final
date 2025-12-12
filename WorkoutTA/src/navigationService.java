import javax.swing.*;
import java.awt.*;

public class navigationService extends JFrame
{
    private JFrame window;

    public navigationService(JFrame window) 
    {
        this.window = window;
    }

    private void showPage(JPanel panel) {
        window.getContentPane().removeAll();
        window.getContentPane().add(panel);
        window.revalidate();
        window.repaint();
    }

    public void goToWelcome() {
        showPage(new Welcome(this));
    }

    public void goToAskName() {
        showPage(new AskName(this));
    }

    public void goToAskGender() {
        showPage(new AskGender(this));
    }

    public void goToAskHeightAndWeight() {
        showPage(new AskHeightandWeight(this));
    }

    public void goToFitnessGoal() 
    {
        showPage(new FitnessGoalUI(this));
    }

    public void goToBuildMuscle()
    {
        showPage(new BuildMuscle(this));
    }

    public void goToCardio()
    {
        showPage(new Cardio(this));
    }

    public void goToBodyComp()
    {
        showPage(new BodyComposition(this));
    }

    public void goToLoseWeight()
    {
        showPage(new LoseWeight(this));
    }

    public void goToRegisterPage() {
        showPage(new RegisterUI(this));
    }

    public void goToLoginPage() {
        showPage(new LoginPage(this));
    }

    public void goToHomepage() {
        showPage(new HomePageDesign(this));
    }

    public void goToProfile() {
        showPage(new ProfilePage(this));
    }

    public void goToCalculator(){
        showPage(new Calcy(this));
    }
}