package juego;

import java.awt.Color;
import java.awt.Rectangle;

import entorno.Entorno;

public class AereoRapido {
	
	private Rectangle area;
    private double velocidad;
    private int direccion;
    
    public AereoRapido(int xInicial, int direccionInicial) {
    	this.area= new Rectangle (xInicial, generarAltura(), 20, 20);

        // con esto generarAltura ajusto la altura aleatoria entre 0 y 400 (evitando entrar en conflicto con la generacion de islas)
        
    	this.velocidad = 2;
        
        this.direccion = direccionInicial;
        
    }

    
    private int generarAltura() {

        int[] alturasValidas = {

           
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

    	this.area.x += this.velocidad * this.direccion;
    }

    // dibujar

    public void dibujar(Entorno entorno, int camaraX) {
    	
    	int xCentro = this.area.x + 10 - camaraX;
    	int yCentro = this.area.y + 10;

        entorno.dibujarRectangulo(
        	xCentro,
            yCentro,
            20,
            20,
            0,
            Color.BLUE
        );
    }

    // limite para cuando sale de la pantalla

    public boolean fueraDePantalla(int camaraX) {

    	return this.area.x < camaraX || this.area.x > camaraX + 800;
    }

    // getters

    public int getX() {
        return area.x;
    }

    public int getY() {
        return area.y;
    }
    
    public Rectangle getArea() {
    	return area;
    }
    
    public int getAncho() {
    	return area.width;
    }
    
    public int getAlto() {
    	return area.height;
    }
}