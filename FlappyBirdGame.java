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
    }

    public static void main(String[] args) {
        FlappyBirdGame app = new FlappyBirdGame();
        app.setVisible(true);
    }
}
