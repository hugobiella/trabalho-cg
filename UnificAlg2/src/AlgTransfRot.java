import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;

public class AlgTransfRot extends JFrame {

    AlgTransfRot() {
        this.setTitle("Rotação");
        this.setSize(200, 200);
        this.add("Center", new TransformaGeo());
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    private static class TransformaGeo extends JComponent {

        Polygon poly;
        double ang = 0.1;
        double centerX, centerY;
        int[] xPoints, yPoints;
        int li;

        public TransformaGeo() {
            iniciaPoligono();
            setFocusable(true);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                        sentidoHorario();
                        repaint();
                    } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                        sentidoAntiHorario();
                        repaint();
                    }
                }
            });
            requestFocusInWindow();
        }

        private void iniciaPoligono() {
            poly = new Polygon();
            li = 3;

            poly.addPoint(50, 50);
            poly.addPoint(150, 50);
            poly.addPoint(100, 150);

            centerX = 0;
            centerY = 0;
            for (int i = 0; i < li; i++) {
                centerX += poly.xpoints[i];
                centerY += poly.ypoints[i];
            }
            centerX /= li;
            centerY /= li;
        }

        public void sentidoHorario() {
            double cos = Math.cos(ang);
            double sen = Math.sin(ang);

            for (int i = 0; i < li; i++) {
                double newX = (poly.xpoints[i] - centerX) * cos - (poly.ypoints[i] - centerY) * sen + centerX;
                double newY = (poly.xpoints[i] - centerX) * sen + (poly.ypoints[i] - centerY) * cos + centerY;
                poly.xpoints[i] = (int) newX;
                poly.ypoints[i] = (int) newY;
            }
        }

        public void sentidoAntiHorario() {
            double cos = Math.cos(-ang);
            double sen = Math.sin(-ang);

            for (int i = 0; i < li; i++) {
                double newX = (poly.xpoints[i] - centerX) * cos - (poly.ypoints[i] - centerY) * sen + centerX;
                double newY = (poly.xpoints[i] - centerX) * sen + (poly.ypoints[i] - centerY) * cos + centerY;
                poly.xpoints[i] = (int) newX;
                poly.ypoints[i] = (int) newY;
            }
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(Color.BLUE);
            g.fillPolygon(poly);
        }
    }

    public static void main(String[] args) {
        new AlgTransfRot();
    }
}
