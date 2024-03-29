import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;


public class AlgTransfTransl extends JFrame {

    AlgTransfTransl() {
        this.setTitle("Translação");
        this.setSize(200, 200);
        this.add("Center", new TransformaGeo());
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    private static class TransformaGeo extends JComponent {

        Polygon poly;
        double tx = 1, ty = 1, ang = 0.1;
        double mi[][];
        double mt[][];
        double mr[][];
        int li, ci, lt, ct;

        public TransformaGeo() {
            iniciaMatrizes();
            setFocusable(true);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                        transformaTranslada();
                        repaint();
                    } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                        transformaTransladaInversa();
                        repaint();
                    }
                }
            });
            requestFocusInWindow();
        }

        private void iniciaMatrizes() {
            poly = new Polygon();
            li = 4; // 4 vértices para um quadrado
            ci = 2; // coordenadas x e y
            mi = new double[li][ci];

            mi[0][0] = 50;   // x do primeiro vértice
            mi[0][1] = 50;   // y do primeiro vértice
            mi[1][0] = 150;  // x do segundo vértice
            mi[1][1] = 50;   // y do segundo vértice
            mi[2][0] = 150;  // x do terceiro vértice
            mi[2][1] = 150;  // y do terceiro vértice
        }


        public void transformaTranslada() {
            mr = new double[li][ci];

            mr[0][0] = (mi[0][0] + tx);
            mr[0][1] = (mi[0][1] + ty);

            mr[1][0] = (mi[1][0] + tx);
            mr[1][1] = (mi[1][1] + ty);

            mr[2][0] = (mi[2][0] + tx);
            mr[2][1] = (mi[2][1] + ty);

            pushMatrix();

        }

        public void transformaTransladaInversa() {
            mr = new double[li][ci];

            mr[0][0] = (mi[0][0] - tx); // subtraindo tx ao invés de somar
            mr[0][1] = (mi[0][1] - ty); // subtraindo ty ao invés de somar

            mr[1][0] = (mi[1][0] - tx); // subtraindo tx ao invés de somar
            mr[1][1] = (mi[1][1] - ty); // subtraindo ty ao invés de somar

            mr[2][0] = (mi[2][0] - tx); // subtraindo tx ao invés de somar
            mr[2][1] = (mi[2][1] - ty); // subtraindo ty ao invés de somar

            pushMatrix();
        }

        public void pushMatrix() {
            for (int i = 0; i < li; i++) {
                for (int j = 0; j < ci; j++) {
                    mi[i][j] = mr[i][j];
                }
            }
        }

        @Override
        public void paint(Graphics g) {
            poly = new Polygon();
            for (int i = 0; i < mi.length; i++) {
                poly.addPoint((int) mi[i][0], (int) mi[i][1]);
            }
            g.setColor(Color.RED);
            g.fillPolygon(poly);
        }
    }

}
