package juego;

import java.awt.Color;
import entorno.Entorno;

public class AereoRapido {

    private double x;
    private double y;
    private double velocidad;
    private int direccion;
    
    public AereoRapido(double xInicial, int direccionInicial) {

        this.x = xInicial;

        // con esto ajusto la altura aleatoria entre 0 y 400 (evitando entrar en conflicto con la generacion de islas)
        this.y = generarAltura();

        this.velocidad = 2;
        
        this.direccion = direccionInicial;
        
    }

    
    private double generarAltura() {

        double[] alturasValidas = {

           
        		70,
                230,
                370,
                470,

        };

        int indice =
            (int)(Math.random() * alturasValidas.length);

        return alturasValidas[indice];
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
            Color.BLUE
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