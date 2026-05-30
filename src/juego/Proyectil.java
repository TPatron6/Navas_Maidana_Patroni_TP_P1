package juego;

import java.awt.Color;
import entorno.Entorno;

public class Proyectil {

    private double x;
    private double y;

    private double vx;
    private double vy;

    private double velocidad;

    public Proyectil(int origenX,
                     int origenY,
                     int destinoX,
                     int destinoY) {

        this.x = origenX;
        this.y = origenY;

        this.velocidad = 8;

        double dx = destinoX - origenX;
        double dy = destinoY - origenY;

        double distancia =
            Math.sqrt(dx * dx + dy * dy);

        this.vx = dx / distancia;
        this.vy = dy / distancia;
    }

    public void mover() {

        this.x += this.vx * this.velocidad;
        this.y += this.vy * this.velocidad;
    }

    public void dibujar(Entorno entorno, int camaraX) {

        entorno.dibujarRectangulo(
            this.x - camaraX,
            this.y,
            10,
            10,
            0,
            Color.ORANGE
        );
    }

    public boolean fueraDePantalla(int camaraX) {

        double pantallaX = this.x - camaraX;

        return pantallaX < -20
            || pantallaX > 820
            || this.y < -20
            || this.y > 620;
    }
}