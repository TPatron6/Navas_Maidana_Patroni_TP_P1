package juego;

import java.awt.Color;
import java.awt.Rectangle;

import entorno.Entorno;

public class AereoNormal {

	private Rectangle area;
  //  private double x;
  // private double y;
    private int velocidad;
    private int direccion;

    public AereoNormal(int xInicial, int direccionInicial) {
    	
    	this.area= new Rectangle (xInicial, generarAltura(), 20, 20);
    	//this.x = xInicial;

        // con esto ajusto la altura aleatoria entre 0 y 400 (evitando entrar en conflicto con la generacion de islas)
        //this.y = generarAltura();

        this.velocidad = 1;
        
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

    	//this.x += this.velocidad * this.direccion;
    	this.area.x += this.velocidad * this.direccion;
    }

    // dibujar

    public void dibujar(Entorno entorno) {

        entorno.dibujarRectangulo(
            this.area.x,
            this.area.y,
            20,
            20,
            0,
            Color.MAGENTA
        );
    }

    // limite para cuando sale de la pantalla

    public boolean fueraDePantalla() {

        return this.area.x < 0 || this.area.x > 800;
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
}