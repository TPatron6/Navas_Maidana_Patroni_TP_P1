package juego;

import java.awt.*;

import entorno.Entorno;

public class Islas {
	
	private Rectangle area;
	private boolean esRompible;
   

    public Islas(int x, int y, int ancho, int alto, boolean esRompible) {
    	this.area = new Rectangle(x, y, ancho, alto);
    	this.esRompible = esRompible;
    }

    public boolean isEsRompible() {
		return esRompible;
	}

	public void dibujar(Entorno entorno, int x) {
    	Color color = Color.GREEN;
    	if(this.esRompible) {
    		color = Color.ORANGE;
    	}
        entorno.dibujarRectangulo(
                x,
                area.y,
                area.width,
                area.height,
                0,
                color);
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



