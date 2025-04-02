import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class FlappyBirdGame extends JFrame {

    public static final int WIDTH = 360;
    public static final int HEIGHT = 640;
    private Bird bird;
    private Timer gravityTimer;

    private ArrayList<Pipe> pipes;
    private static final int MIN_SPAWN_RATE = 500;
    private static final int MAX_SPAWN_RATE = 1500;
    private Timer gameLoopTimer;

    JLabel backgroundLabel;

    JLabel scoreLabel;
    int score;

    public FlappyBirdGame() {
        setTitle("Flappy Bird");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon background = new ImageIcon("./flappybirdbg.png");
        backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, WIDTH, HEIGHT);
        add(backgroundLabel);

        bird = new Bird();
        backgroundLabel.add(bird);

        gravityTimer = new Timer(30, e -> bird.fall());
        gravityTimer.start();

        pipes = new ArrayList<>();

        gameLoopTimer = new Timer(30, e -> gameLoop());
        gameLoopTimer.start();

        // Spawn pipes at random intervals
        Timer spawnPipeTimer = new Timer((int) (Math.random() * (MAX_SPAWN_RATE - MIN_SPAWN_RATE + 1)) + MIN_SPAWN_RATE, e -> {
            spawnPipe();
            ((Timer) e.getSource()).setDelay((int) (Math.random() * (MAX_SPAWN_RATE - MIN_SPAWN_RATE + 1)) + MIN_SPAWN_RATE);
        });
        spawnPipeTimer.start();

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBounds(10, 10, 100, 30);
        scoreLabel.setForeground(java.awt.Color.WHITE);
        backgroundLabel.add(scoreLabel);

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

    private void gameLoop() {
        for (Pipe pipe : pipes) {
            pipe.move();

            if (pipe.collidesWith(bird)) {
                //print collision message
                System.out.println("Collision detected!");
            
                gameOver();
                return;
            }

            if (pipe.isPassed(bird)) {
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }

        //if pipe is outbound, remove from the list and backgroundLabel
        for (int i = pipes.size() - 1; i >= 0; i--) {
            Pipe pipe = pipes.get(i);
            if (pipe.isOutOfBounds()) {
                pipes.remove(pipe);
                backgroundLabel.remove(pipe);
            }
        }

        // Remove pipes that have moved off screen
        for (int i = pipes.size() - 1; i >= 0; i--) {
            Pipe pipe = pipes.get(i);
            if (pipe.isOutOfBounds()) {
                pipes.remove(pipe);
                backgroundLabel.remove(pipe);
            }
        }

        //check if bird is out of bounds
        if (bird.getY() > HEIGHT || bird.getY() < 0) {
            System.out.println("bird down");
            gameOver();
        }
    }

    private void gameOver() {
        // Set the gameOver flag and display the message
        JLabel gameOverLabel = new JLabel("Game Over! Press Space or Enter to Restart");
        gameOverLabel.setBounds(50, HEIGHT / 2, 300, 30);
        gameOverLabel.setForeground(java.awt.Color.WHITE);
        backgroundLabel.add(gameOverLabel);
        backgroundLabel.repaint();
        // Stop any game loops, timers, or animations
        gravityTimer.stop();
        gameLoopTimer.stop();
    } 

    private void spawnPipe() {
        int xPosition = WIDTH;
        Pipe pipe = new Pipe(xPosition);
        pipes.add(pipe);
        backgroundLabel.add(pipe);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FlappyBirdGame::new);
    }
}
