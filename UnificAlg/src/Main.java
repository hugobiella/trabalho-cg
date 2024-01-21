import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
    private int startX, startY, endX, endY;
    private JComboBox<String> colorComboBox;
    private JComboBox<String> algorithmComboBox;
    private DrawingPanel drawingPanel;

    public Main() {
        setTitle("Paint 2");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] colors = {"Vermelho", "Verde", "Azul"};
        colorComboBox = new JComboBox<>(colors);

        String[] algorithms = { "Analítico - Linha", "DDA - Linha", "Bresenham - Linha",
                                "Varredura - Polígono", "BoundaryFill - Polígono", "AnáliseGeométrica - Polígono",
                                "Paramétrica - Círculo", "Incremental - Círculo", "Bresenham - Círculo"};

        algorithmComboBox = new JComboBox<>(algorithms);

        drawingPanel = new DrawingPanel();

        JPanel controlPanel = new JPanel();
        controlPanel.add(colorComboBox);
        controlPanel.add(algorithmComboBox);
        add(controlPanel, BorderLayout.NORTH);
        add(drawingPanel);

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (startX == 0 && startY == 0) {
                    startX = e.getX();
                    startY = e.getY();
                } else {
                    endX = e.getX();
                    endY = e.getY();
                    drawingPanel.drawLine(startX, startY, endX, endY, getSelectedColor(), getSelectedAlgorithm());
                    startX = 0;
                    startY = 0;
                }
            }
        });

        algorithmComboBox.addActionListener(e -> {
            drawingPanel.clearDrawing();
        });
    }

    private Color getSelectedColor() {
        String selectedColor = (String) colorComboBox.getSelectedItem();
        switch (selectedColor) {
            case "Vermelho":
                return Color.RED;
            case "Verde":
                return Color.GREEN;
            case "Azul":
                return Color.BLUE;
            default:
                return Color.BLACK;
        }
    }

    private String getSelectedAlgorithm() {
        return (String) algorithmComboBox.getSelectedItem();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
            app.setLocationRelativeTo(null);
        });
    }

    private class DrawingPanel extends JPanel {
        public DrawingPanel() {
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }

        private void drawLine(int x1, int y1, int x2, int y2, Color color, String algorithm) {
            Graphics g = getGraphics();
            g.setColor(color);
            switch (algorithm) {
                case "Analítico - Linha":
                    AlgLinhas.algAnalitic(x1, y1, x2, y2, g);
                    break;
                case "DDA - Linha":
                    AlgLinhas.algDDA(x1, y1, x2, y2, g);
                    break;
                case "Bresenham - Linha":
                    AlgLinhas.algBres(x1, y1, x2, y2, g);
                    break;
                case "Varredura - Polígono":
                    break;
                case "BoundaryFill - Polígono":
                    break;
                case "AnáliseGeométrica - Polígono":
                    break;
                case "Paramétrica - Círculo":
                    break;
                case "Incremental - Círculo":
                    break;
                case "Bresenham - Círculo":
                    break;
            }
        }

        private void clearDrawing() {
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }
}
