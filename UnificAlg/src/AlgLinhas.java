import java.awt.*;

public class AlgLinhas {
    public static void DesenhaDDA(int x1, int y1, int x2, int y2, Graphics g) {
        System.out.println("Chamando DesenhaDDA: x "+x1+"; y "+y1+"; x "+x2+"; y "+y2);
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        float xIncrement = dx / (float) steps;
        float yIncrement = dy / (float) steps;
        float x = x1;
        float y = y1;
        for (int i = 0; i <= steps; i++) {
            g.drawLine(Math.round(x), Math.round(y), Math.round(x), Math.round(y));
            x += xIncrement;
            y += yIncrement;
        }
    }

    public static void DesenhaAnalitico(int x1, int y1, int x2, int y2, Graphics g) {
        System.out.println("Chamando DesenhaAnalitico: x "+x1+"; y "+y1+"; x "+x2+"; y "+y2);
        int dx = x2 - x1;
        int dy = y2 - y1;
        float m = (float) dy / dx;
        if (Math.abs(m) <= 1) {
            int x = x1;
            while (x <= x2) {
                int y = Math.round(y1 + m * (x - x1));
                g.drawLine(x, y, x, y);
                x++;
            }
        } else {
            int y = y1;
            while (y <= y2) {
                int x = Math.round(x1 + (y - y1) / m);
                g.drawLine(x, y, x, y);
                y++;
            }
        }
    }

    public static void DesenhaBresenham(int x1, int y1, int x2, int y2, Graphics g) {
        System.out.println("Chamando DesenhaBresenham: x "+x1+"; y "+y1+"; x "+x2+"; y "+y2);
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        int err = dx - dy;
        int x = x1, y = y1;
        while (true) {
            g.drawLine(x, y, x, y);
            if (x == x2 && y == y2) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }
}
