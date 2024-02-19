import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class AlgCurvaBezier extends JFrame {

    AlgCurvaBezier(int valor) {
        this.setTitle("Curvas - Bézier");
        this.setSize(500, 300);
        this.add("Center", new Bezier(valor));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }
}

class Bezier extends JComponent {

    P2D[] pts;
    int value;
    int np = 0, centerX, centerY;
    float rWidth = 10.0F, rHeight = 10.0F, pixelSize;

    Bezier(int valor) {

        value = valor;
        pts = new P2D[value];
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                float x = fx(evt.getX()), y = fy(evt.getY());
                if (np == valor) {
                    np = 0;
                }
                pts[np++] = new P2D(x, y);
                repaint();
            }
        });
    }

    void initValues() {
        Dimension d = getSize();
        int maxX = d.width - 1, maxY = d.height - 1;
        pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
        centerX = maxX / 2;
        centerY = maxY / 2;
    }

    public float fx(int x) {
        return (x - centerX) * pixelSize;
    }

    public float fy(int y) {
        return (centerY - y) * pixelSize;
    }

    int iX(float x) {
        return Math.round(centerX + x / pixelSize);
    }

    int iY(float y) {
        return Math.round(centerY - y / pixelSize);
    }

    P2D medio(P2D p1, P2D p2) {
        return new P2D((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    void geraCurva(Graphics g, P2D p0, P2D p1, P2D p2, P2D p3) {
        int x0 = iX(p0.x), y0 = iY(p0.y), x3 = iX(p3.x), y3 = iY(p3.y);

        if (Math.abs(x0 - x3) <= 1 && Math.abs(y0 - y3) <= 1) {
            g.drawLine(x0, y0, x3, y3);
        } else {
            P2D a = medio(p0, p1), b = medio(p3, p2), c = medio(p1, p2), a1 = medio(a, c), b1 = medio(b, c), c1 = medio(a1, b1);
            geraCurva(g, p0, a, a1, c1);
            geraCurva(g, c1, b1, b, p3);
        }
    }

    @Override
    public void paint(Graphics g) {
        initValues();
        int left = iX(-rWidth / 2), right = iX(rWidth / 2), bottom = iY(-rHeight / 2), top = iY(rHeight / 2);
        g.drawRect(left, top, right - left, bottom - top);
        for (int i = 0; i < np; i++) {
            g.drawRect(iX(pts[i].x) - 2, iY(pts[i].y) - 2, 4, 4);
            if (i > 0) {
                g.drawLine(iX(pts[i - 1].x), iY(pts[i - 1].y), iX(pts[i].x), iY(pts[i].y));
            }
        }
        if (np == value) {
            geraCurva(g, pts[0], pts[(pts.length / 2) - 1], pts[pts.length / 2], pts[pts.length - 1]);
        }
    }
}

class Ponto2D {

    float x, y;

    Ponto2D(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
