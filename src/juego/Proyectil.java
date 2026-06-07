package juego;

import java.awt.Color;
import java.awt.Rectangle;

import entorno.Entorno;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Proyectil {

	private Rectangle area;

    private double vx;
    private double vy;

    private int velocidad;
    
    private Image sprite;

    public Proyectil(int origenX,
                     int origenY,
                     int destinoX,
                     int destinoY) {

        this.area = new Rectangle(origenX, origenY, 10, 10);
        this.velocidad = 8;

        int dx = destinoX - origenX;
        int dy = destinoY - origenY;

        double distancia =
            Math.sqrt(dx * dx + dy * dy);

        this.vx = dx / distancia;
        this.vy = dy / distancia;
        
        sprite = new ImageIcon("src/proyectil.gif").getImage();
    }

    public void mover() {

        this.area.x += this.vx * this.velocidad;
        this.area.y += this.vy * this.velocidad;
    }

    public void dibujar(Entorno entorno, int camaraX) {
    		int xCentro =
    	        this.area.x + this.area.width / 2 - camaraX;

    	    int yCentro =
    	        this.area.y + this.area.height / 2;

    	    entorno.dibujarImagen(
    	        sprite,
    	        xCentro,
    	        yCentro,
    	        0
    	    );
    	}

    public boolean fueraDePantalla(int camaraX) {

        double pantallaX = this.area.x - camaraX;

        return pantallaX < -20
            || pantallaX > 820
            || this.area.y < -20
            || this.area.y > 620;
    }
    
    public Rectangle getArea() {
    	return this.area;
    }
    
    public boolean colisionoConBloque(Bloques[][] bloques) {
    	for(Bloques[] nivel : bloques) {
	    	for(Bloques bloque : nivel) {
	    		if( bloque != null && this.getArea().intersects(bloque.getArea())) {
		    			return true;
	    		}
	    	}
    	}
    	return false;
    }
}