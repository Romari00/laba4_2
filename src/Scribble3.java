import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Scribble3 extends JFrame {
    private int last_x, last_y;
    private boolean isDrawing = false;
    private Color currentColor = Color.black;
    private Random random = new Random();

    public Scribble3() {
        // Устанавливаем JFrame
        setTitle("Scribble");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем панель для рисования
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Рисуем на панели
            }
        };

        // Слушатель мыши для рисования и изменения цвета
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    isDrawing = true;
                    last_x = e.getX();
                    last_y = e.getY();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    // Изменение цвета при нажатии правой кнопки мыши
                    currentColor = getRandomColor();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    isDrawing = false;
                }
            }
        });

        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDrawing) {
                    Graphics g = drawingPanel.getGraphics();
                    g.setColor(currentColor);
                    int x = e.getX(), y = e.getY();
                    int size = 10; // Размер кружка
                    g.fillOval(x - size / 2, y - size / 2, size, size);
                }
            }
        });

        // Создаем кнопку для очистки
        JButton clearButton = new JButton("Очистить");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Очищаем рисунок
                Graphics g = drawingPanel.getGraphics();
                g.setColor(drawingPanel.getBackground());
                g.fillRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());
            }
        });

        // Настраиваем макет
        setLayout(new BorderLayout());
        add(drawingPanel, BorderLayout.CENTER);
        add(clearButton, BorderLayout.SOUTH);
    }

    private Color getRandomColor() {
        // Генерация случайного цвета
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Scribble3().setVisible(true);
            }
        });
    }
}
