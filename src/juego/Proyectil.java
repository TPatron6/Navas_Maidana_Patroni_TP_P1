package juego;

import java.awt.Color;
import java.awt.Rectangle;

import entorno.Entorno;

public class Proyectil {

	private Rectangle area;

    private double vx;
    private double vy;

    private int velocidad;

    public Proyectil(int origenX,
                     int origenY,
                     int destinoX,
                     int destinoY) {

        this.area = new Rectangle(origenX, origenY, 10, 10);
        this.velocidad = 4;

        int dx = destinoX - origenX;
        int dy = destinoY - origenY;

        double distancia =
            Math.sqrt(dx * dx + dy * dy);

        this.vx = dx / distancia;
        this.vy = dy / distancia;
    }

    public void mover() {

        this.area.x += this.vx * this.velocidad;
        this.area.y += this.vy * this.velocidad;
    }

    public void dibujar(Entorno entorno, int camaraX) {
    	int xCentro = this.area.x + 5 - camaraX;
    	int yCentro = this.area.y + 5;

        entorno.dibujarRectangulo(
            xCentro,
            yCentro,
            10,
            10,
            0,
            Color.ORANGE
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
}