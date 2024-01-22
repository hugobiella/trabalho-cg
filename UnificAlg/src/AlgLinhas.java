import java.awt.*;

public class AlgLinhas {

    public static void DesenhaDDA(int x1, int y1, int x2, int y2, Graphics g) {
        System.out.println("DesenhaDDA: X:"+x1+", Y:"+y1+"; X:"+x2+", Y:"+y2);
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
        System.out.println("DesenhaAnalitico: X:"+x1+", Y:"+y1+"; X:"+x2+", Y:"+y2);
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
        System.out.println("DesenhaBresenham: X:"+x1+", Y:"+y1+"; X:"+x2+", Y:"+y2);
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

    public static void putPixel(Graphics g, int x, int y) {
        g.drawLine(x, y, x, y);
    }

    public static void algDDA(int x1, int y1, int x2, int y2, Graphics g) {
        System.out.println("algDDA_linha: X:"+x1+", Y:"+y1+"; X:"+x2+", Y:"+y2);
        int steps;
        float x=x1, y=y1, incX, incY;
        int dx = x2 - x1;
        int dy = y2 - y1;
        if(Math.abs(dx)>Math.abs(dy)) {
            steps = Math.abs(dx); incX = 1; incY= (float) dy /dx; }
        else {
            steps = Math.abs(dy); incY = 1; incX= (float) dx /dy; }
        for(int i=0; i<steps; i++) {
            x = x + incX;
            y = y + incY;
            putPixel(g,Math.round(x),Math.round(y)); }
    }

    public static void algAnalitic(int x1, int y1, int x2, int y2, Graphics g) {
        System.out.println("algAnalitic_linha: X:"+x1+", Y:"+y1+"; X:"+x2+", Y:"+y2);

        float m, b, dy, dx;
        dy = y2 - y1;
        dx = x2 - x1;
        m = (float) dy/dx;
        b = (float) (y1 - m * x1);
        for(int x = x1; x<=x2; x++) {
            int y = (int) (m * x + b);
            putPixel(g,x,y);
        }
    }

    public static void algBres(int x1, int y1, int x2, int y2, Graphics g) {
        System.out.println("algBres_linha: X:"+x1+", Y:"+y1+"; X:"+x2+", Y:"+y2);
        int x = x1, y = y1, d=0, dx = x2-x1, dy = y2-y1, c, m, incX=1, incY=1;
        if(dx < 0) {incX = -1; dx = -dx;}
        if(dy < 0) {incY = -1; dy = -dy;}
        if(dy <= dx) {
            c = 2 * dx; m = 2 * dy;
            for(;;) {
                putPixel(g,x,y);
                if (x == x2) break;
                x += incX;
                d += m;
                if(d >= dx) {y += incY; d -= c;}
            }
        } else {
            c = 2 * dy; m = 2 * dx;
            for(;;) {
                putPixel(g,x,y);
                if (y == y2) break;
                y += incY;
                d += m;
                if(d >= dy) {x += incX; d -= c;}
            }
        }
    }
}
