import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;


public class AlgTransfEscala extends JFrame {

    AlgTransfEscala() {
        this.setTitle("Escala");
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
                        aumentaEscala();
                        repaint();
                    } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                        diminuiEscala();
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

        public void aumentaEscala() {
            lt = 2;
            ct = 2;
            mt = new double[lt][ct];
            mt[0][0] = (double) 1.5;
            mt[0][1] = (double) 0;
            mt[1][0] = (double) 0;
            mt[1][1] = (double) 1.5;

            mr = new double[li][ct];

            mr[0][0] = (mi[0][0] * mt[0][0]) + (mi[0][1] * mt[0][1]);
            mr[0][1] = (mi[0][0] * mt[0][1]) + (mi[0][1] * mt[1][1]);

            mr[1][0] = (mi[1][0] * mt[0][0]) + (mi[1][1] * mt[0][1]);
            mr[1][1] = (mi[1][0] * mt[0][1]) + (mi[1][1] * mt[1][1]);

            mr[2][0] = (mi[2][0] * mt[0][0]) + (mi[2][1] * mt[0][1]);
            mr[2][1] = (mi[2][0] * mt[0][1]) + (mi[2][1] * mt[1][1]);

            pushMatrix();

        }

        public void diminuiEscala() {
            
            lt = 2;
            ct = 2;
            mt = new double[lt][ct];
            mt[0][0] = 0.5;
            mt[0][1] = 0;
            mt[1][0] = 0;
            mt[1][1] = 0.5;

            mr = new double[li][ct];

            mr[0][0] = (mi[0][0] * mt[0][0]) + (mi[0][1] * mt[0][1]);
            mr[0][1] = (mi[0][0] * mt[0][1]) + (mi[0][1] * mt[1][1]);

            mr[1][0] = (mi[1][0] * mt[0][0]) + (mi[1][1] * mt[0][1]);
            mr[1][1] = (mi[1][0] * mt[0][1]) + (mi[1][1] * mt[1][1]);

            mr[2][0] = (mi[2][0] * mt[0][0]) + (mi[2][1] * mt[0][1]);
            mr[2][1] = (mi[2][0] * mt[0][1]) + (mi[2][1] * mt[1][1]);

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
            g.setColor(Color.GREEN);
            g.fillPolygon(poly);
        }
    }
}
