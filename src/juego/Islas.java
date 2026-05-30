package juego;

import java.awt.*;

import entorno.Entorno;

public class Islas {
	
	private Rectangle area;
   

    public Islas(int x, int y, int ancho, int alto) {
    	this.area = new Rectangle(x, y, ancho, alto);
    }

    public void dibujar(Entorno entorno, int camaraX) {

        entorno.dibujarRectangulo(
                area.x - camaraX,
                area.y,
                area.width,
                area.height,
                0,
                Color.GREEN);
    }

    public int getX() {
        return area.x;
    }

    public int getY() {
        return area.y;
    }

    public int getAncho() {
        return area.width;
    }

    public int getAlto() {
        return area.height;
    }
    
    public Rectangle getArea() {

        return this.area;
    }
}
