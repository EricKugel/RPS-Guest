import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private JPanel main = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            draw(g);
        }
    };

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int ZOOMINESS = 1;
    public static final int threshold = 2;

    public static final int FPS = 64;

    private Cell[][] cells = new Cell[HEIGHT][WIDTH];

    public Main() {
        setTitle("RPS-Guest");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        initGUI();
        pack();
    }

    private void initGUI() {
        main.setPreferredSize(new Dimension(WIDTH * ZOOMINESS, HEIGHT * ZOOMINESS));
        setContentPane(main);
        main.setLayout(new GridLayout(HEIGHT, WIDTH));
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Cell cell = new Cell(row, col);
                cells[row][col] = cell;
            }
        }

        Timer timer = new Timer(1000 / FPS, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[][] newStates = new String[HEIGHT][WIDTH];
                for (int row = 0; row < HEIGHT; row++) {
                    for (int col = 0; col < WIDTH; col++) {
                        newStates[row][col] = cells[row][col].getNewState(cells);
                    }
                }

                for (int row = 0; row < HEIGHT; row++) {
                    for (int col = 0; col < WIDTH; col++) {
                        cells[row][col].setState(newStates[row][col]);
                    }
                }
                main.repaint();
            }
        });
        timer.start();
    }

    private void draw(Graphics g) {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < HEIGHT; col++) {
                g.setColor(cells[row][col].getColor());
                g.fillRect(col * ZOOMINESS, row * ZOOMINESS, ZOOMINESS, ZOOMINESS);
            }
        }
    }


    public static void main(String[] arg0) {
        new Main();
    }
}