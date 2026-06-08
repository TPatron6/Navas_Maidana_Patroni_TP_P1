package juego;

import java.awt.Color;
import java.awt.Rectangle;

import entorno.Entorno;

import java.awt.Image;
import javax.swing.ImageIcon;

public class AereoRapido {
	
	private Rectangle area;
    private double velocidad;
    private int direccion;
    
    private Image spriteDerecha;
    private Image spriteIzquierda;
    
    public AereoRapido(int xInicial, int direccionInicial) {
    	this.area= new Rectangle (xInicial, generarAltura(), 20, 20);

        // con esto generarAltura ajusto la altura aleatoria entre 0 y 400 (evitando entrar en conflicto con la generacion de islas)
        
    	this.velocidad = 2;
        
        this.direccion = direccionInicial;
        
        spriteDerecha =
        	    new ImageIcon("src/rapidoDerecha.gif")
        	        .getImage();

        spriteIzquierda =
        	    new ImageIcon("src/rapidoIzquierda.gif")
        	        .getImage();
        
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

    	if (direccion == 1) {

    	    entorno.dibujarImagen(
    	        spriteIzquierda,
    	        xCentro,
    	        yCentro,
    	        0
    	    );

    	} else {

    	    entorno.dibujarImagen(
    	        spriteDerecha,
    	        xCentro,
    	        yCentro,
    	        0
    	    );
    	}
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