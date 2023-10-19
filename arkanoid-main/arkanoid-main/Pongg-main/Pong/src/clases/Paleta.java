package clases;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Paleta {
	private int x, y;
	static final int Ancho=100, Alto=20;
	
	public void resetpaleta(){
		x=350;
		y=415;
	}	
	
	public Paleta(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	public Rectangle2D getPaleta()
	{
		return new Rectangle2D.Double(x,y,Ancho,Alto);
	}
	public void mover_raqueta(Rectangle limites)
	{
	    if (Movimiento.d && x < limites.getMaxX() - Ancho) {
	        x++;
	    }
	    if (Movimiento.a && x > limites.getMinX()) {
	        x--;
	    }
	}

}
