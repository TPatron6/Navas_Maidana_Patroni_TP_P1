package juego;

import java.awt.Color;
import entorno.Entorno;

public class EnemigoTerrestre {

    private int x;
    private int y;
    
    private int velocidadY;

    private int velocidad;
    private int direccion;
    
    private int limiteIzquierdo;
    private int limiteDerecho;

    public EnemigoTerrestre(int x, int y, int limteIzquierdo, int limiteDerecho) {

        this.x = x;
        this.y = y;
        
        this.limiteIzquierdo =limteIzquierdo;
        this.limiteDerecho =limiteDerecho;
        
        this.velocidadY = 0;

        this.velocidad = 2;

        this.direccion = 1;
    }

    public void mover() {

        x += velocidad * direccion;

        if(x <= limiteIzquierdo) {

            x = limiteIzquierdo;
            cambiarDireccion();
        }

        if(x >= limiteDerecho) {

            x = limiteDerecho;
            cambiarDireccion();
        }
    }

    public void cambiarDireccion() {

        direccion *= -1;
    }

    public void dibujar(Entorno entorno, int camaraX) {

        entorno.dibujarRectangulo(
            x - camaraX,
            y,
            20,
            20,
            0,
            Color.RED
        );
    }
    
    public void gravedad() {

        velocidadY++;

        if(velocidadY > 10) {
            velocidadY = 10;
        }

        y += velocidadY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(int velocidadY) {
        this.velocidadY = velocidadY;
    }

    public void setY(int y) {
        this.y = y;
    }
}

