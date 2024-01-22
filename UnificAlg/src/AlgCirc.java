import java.awt.Graphics;
import javax.swing.JComponent;

public class AlgCirc {

    public static void simetria(Graphics g, int x, int y, int centerX, int centerY) {
        putPixel(g,x,y,centerX,centerY);
        putPixel(g,x,-y,centerX,centerY);
        putPixel(g,-x,y,centerX,centerY);
        putPixel(g,-x,-y,centerX,centerY);
        putPixel(g,y,x,centerX,centerY);
        putPixel(g,y,-x,centerX,centerY);
        putPixel(g,-y,x,centerX,centerY);
        putPixel(g,-y,-x,centerX,centerY);
    }

    public static void putPixel(Graphics g, int x, int y, int centerX, int centerY) {
        g.drawLine(x+centerX, y+centerY, x+centerX, y+centerY);
    }

    public static void algBres(Graphics g, int raio, int centerX, int centerY) {
        System.out.println("algBres_circ:"+raio);
        int x=0, y=raio, u=1, v=2 * raio-1, e=0;
        while(x<=y) {
            simetria(g,x,y,centerX,centerY);
            x++;
            e = e + u;
            u = u + 2;
            if (v<(2*e)) {y--; e=e-v; v=v-2;}
        }
    }

}
