import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class BasketballGame extends JPanel implements ActionListener {
    private int score = 0;
    private int basketX = 300;
    private int ballX = 150, ballY = 0;
    private Timer timer;
    private Random random;

    public BasketballGame() {
        random = new Random();
        timer = new Timer(20, this);
        timer.start();

        // Add mouse listener to move the basket
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Move basket to the clicked x position
                basketX = e.getX() - 50; // Center the basket on click
                if (basketX < 0) {
                    basketX = 0; // Prevent moving out of bounds
                } else if (basketX > getWidth() - 100) {
                    basketX = getWidth() - 100; // Prevent moving out of bounds
                }
                repaint(); // Trigger a repaint to update the basket position
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(basketX, 450, 100, 20); // Draw basket
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, 30, 30); // Draw ball
        g.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballY += 5; // Move ball down
        if (ballY >= 450 && ballX >= basketX && ballX <= basketX + 100) {
            score++;
            resetBall();
        }
        if (ballY > getHeight()) resetBall();
        repaint();
    }

    private void resetBall() {
        ballY = 0;
        ballX = random.nextInt(getWidth() - 30);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Basketball Game");
        BasketballGame game = new BasketballGame();
        frame.add(game);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}