import java.awt.event.*;
import javax.swing.*;

public class FlappyBirdGame extends JFrame {

    public static final int WIDTH = 360;
    public static final int HEIGHT = 640;
    private Bird bird;
    private Timer gravityTimer;

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

        bird = new Bird();
        backgroundLabel.add(bird);

        gravityTimer = new Timer(30, e -> bird.fall());
        gravityTimer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    bird.fly();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FlappyBirdGame::new);
    }
}
