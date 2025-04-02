import java.awt.*;
import javax.swing.*;

public class Bird extends JLabel {
    private int velocity = 0;
    private static final int GRAVITY = 1;
    private static final int FLAP_STRENGTH = -10;

    public Bird() {
        ImageIcon birdIcon = new ImageIcon("./flappybird.png");
        Image scaledBird = birdIcon.getImage().getScaledInstance(34, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedBirdIcon = new ImageIcon(scaledBird);

        setIcon(resizedBirdIcon);

        setBounds(50, 320, 34, 24);
    }

    public void fall() {
        velocity += GRAVITY;
        setLocation(getX(), getY() + velocity);
    }

    public void fly() {
        velocity = FLAP_STRENGTH;
    }
}
