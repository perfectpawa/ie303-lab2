import java.awt.Image;
import javax.swing.*;

public class FlappyBirdGame extends JFrame {

    public static final int WIDTH = 360;
    public static final int HEIGHT = 640;

    public FlappyBirdGame() {
        setTitle("Flappy Bird");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon background = new ImageIcon("./flappybirdbg.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, WIDTH, HEIGHT);
        add(backgroundLabel);

        ImageIcon birdIcon = new ImageIcon("./flappybird.png");
        Image scaledBird = birdIcon.getImage().getScaledInstance(34, 24, Image.SCALE_SMOOTH);
        ImageIcon resizedBirdIcon = new ImageIcon(scaledBird);

        JLabel birdLabel = new JLabel(resizedBirdIcon);
        birdLabel.setBounds(50, HEIGHT / 2, resizedBirdIcon.getIconWidth(), resizedBirdIcon.getIconHeight());
        backgroundLabel.add(birdLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FlappyBirdGame::new);
    }
}
