package juego;

import java.awt.Color;
import java.awt.Rectangle;
import entorno.Entorno;

import java.awt.Image;
import javax.swing.ImageIcon;

public class EnemigoTerrestre {
   
    private Rectangle area;
    
    private int velocidadY;

    private int velocidad;
    private int direccion;
    
    private int limiteIzquierdo;
    private int limiteDerecho;
    
    private Image spriteDerecha;
    private Image spriteIzquierda;

    public EnemigoTerrestre(int x, int y, int limteIzquierdo, int limiteDerecho) {
    	this.area= new Rectangle(x, y, 20, 20);
        
        this.limiteIzquierdo =limteIzquierdo;
        this.limiteDerecho =limiteDerecho;
        
        this.velocidadY = 0;

        this.velocidad = 2;

        this.direccion = 1;
        
        spriteDerecha =
        	    new ImageIcon("src/terrestreDer.png")
        	        .getImage();

        	spriteIzquierda =
        	    new ImageIcon("src/terrestreIzq.png")
        	        .getImage();
    }

    public void mover() {

        this.area.x += velocidad * direccion;

        if(this.area.x <= limiteIzquierdo) {

            this.area.x = limiteIzquierdo;
            cambiarDireccion();
        }

        if(this.area.x + this.area.width >= limiteDerecho) {

            this.area.x = limiteDerecho - this.area.width;
            cambiarDireccion();
        }
    }

    public void cambiarDireccion() {

        direccion *= -1;
    }

    public void dibujar(Entorno entorno, int camaraX) {

        int xCentro = this.area.x + 10 - camaraX;
        int yCentro = this.area.y + 10;

        if (direccion == 1) {

            entorno.dibujarImagen(
                spriteDerecha,
                xCentro,
                yCentro,
                0
            );

        } else {

            entorno.dibujarImagen(
                spriteIzquierda,
                xCentro,
                yCentro,
                0
            );
        }
    }
    
    public void gravedad() {

        velocidadY++;

        if(velocidadY > 10) {
            velocidadY = 10;
        }

        this.area.y += velocidadY;
    }

    public int getX() {
        return this.area.x;
    }

    public int getY() {
        return this.area.y;
    }
    
    public int getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(int velocidadY) {
        this.velocidadY = velocidadY;
    }

    public void setY(int y) {
        this.area.y = y;
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

