package clases;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Balon {
    private int x;
    private int y;
    private final int Ancho = 15;
    private final int Alto = 15;
    private double dx = 1;
    private double dy = 1;

    public Balon(int anchoTablero) {
        x = anchoTablero / 2 - Ancho / 2;
        y = 0;
    }

public Rectangle2D getBalon() {
    return new Rectangle2D.Double(x, y, Ancho, Alto);
}
public void reset(){
	dx=1;
	y=0;
	x=0;
	dy=1;
}


public void mover(Rectangle limites, boolean colisionp1) {
    x += dx;
    y += dy;
    
    if(colisionp1)
    {
    	dy=-dy;
    	y=400;
    }
    if (x > limites.getMaxX()) {
        dx = -dx;
    }
    if (y > limites.getMaxY()) {
        dy = -dy;
    }
    if (x < 0) {
        dx = -dx;
    }
    if (y < 0) {
        dy = -dy;
    }
    
    
}
public void invertirDireccionVertical() {
    dy = -dy;
}

public void invertirDireccionHorizontal() {
    dx = -dx;
}

public void aumentarVelocidad() {
    dx = 1.1;
    dy = 1.1;
}
public void dibujar(Graphics2D g) 
{
    g.fillOval(x, y, Ancho, Alto);
}
}
