package juego;

import java.awt.Color;
import entorno.Entorno;

public class AereoNormal {

    private double x;
    private double y;
    private double velocidad;
    private int direccion;

    public AereoNormal(double xInicial, int direccionInicial) {

        this.x = xInicial;

        // con esto ajusto la altura aleatoria entre 0 y 400
        this.y = Math.random() * 500;

        this.velocidad = 1;
        
        this.direccion = direccionInicial;
    }

    // movimento

    public void mover() {

    	this.x += this.velocidad * this.direccion;
    }

    // dibujar

    public void dibujar(Entorno entorno) {

        entorno.dibujarRectangulo(
            this.x,
            this.y,
            20,
            20,
            0,
            Color.MAGENTA
        );
    }

    // limite para cuando sale de la pantalla

    public boolean fueraDePantalla() {

        return this.x < 0 || this.x > 800;
    }

    // getters

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}