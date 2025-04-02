import java.awt.Image;
import javax.swing.*;

public class Pipe extends JPanel {
    private int xPosition;

    private JLabel topPipeLabel;
    private JLabel bottomPipeLabel;

    private static final int MIN_GAP = 64;
    private static final int MAX_GAP = 128;

    private static final int MAX_HEIGHT = 288;

    private static final int PIPE_SPEED = 5;
    private static final int PIPE_WIDTH = 64;

    public Pipe(int xPosition) {
        this.xPosition = xPosition;

        ImageIcon topPipeIcon = new ImageIcon("./toppipe.png");
        Image scaledtopPipeIcon = topPipeIcon.getImage().getScaledInstance(64, 512, Image.SCALE_SMOOTH);
        ImageIcon resizetopPipeIcon = new ImageIcon(scaledtopPipeIcon);

        ImageIcon bottomPipeIcon = new ImageIcon("./bottompipe.png");
        Image scaledbottomPipeIcon = bottomPipeIcon.getImage().getScaledInstance(64, 512, Image.SCALE_SMOOTH);
        ImageIcon resizebottomPipeIcon = new ImageIcon(scaledbottomPipeIcon);

        topPipeLabel = new JLabel(resizetopPipeIcon);
        bottomPipeLabel = new JLabel(resizebottomPipeIcon);

        int gap = (int) (Math.random() * (MAX_GAP - MIN_GAP + 1)) + MIN_GAP;
        int height = (int) (Math.random() * (MAX_HEIGHT + 1));

        topPipeLabel.setBounds(0, - gap - height, 64, 512);
        bottomPipeLabel.setBounds(0, 512 - height, 64, 512);

        setLayout(null);
        setBounds(xPosition, 0, 64, 640);
        add(topPipeLabel);
        add(bottomPipeLabel);

        setOpaque(false);
    }

    public void move() {
        xPosition -= PIPE_SPEED;
        setLocation(xPosition, 0);
    }

    public boolean isOutOfBounds() {
        return xPosition + PIPE_WIDTH < 0;
    }
}
