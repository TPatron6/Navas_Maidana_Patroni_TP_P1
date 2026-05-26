package juego;

import java.awt.Color;

import entorno.Entorno;

public class Islas {

    private int x;
    private int y;

    private int ancho;
    private int alto;

    public Islas(int x, int y, int ancho, int alto) {

        this.x = x; 
        this.y = y;

        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujar(Entorno entorno, int camaraX) {

        entorno.dibujarRectangulo(
                x - camaraX,
                y,
                ancho,
                alto,
                0,
                Color.GREEN);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}