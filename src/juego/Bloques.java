package juego;

import java.awt.*;

import entorno.Entorno;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Bloques {
	
	private Rectangle area;
	private boolean rompible;
	private boolean inestable;
	private boolean caidaActiva;
	private int tickCaida;
	private String direccion;
	private static Image texturaNormal;
	private static Image texturaRompible;
	private static Image texturaInestable;

    public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Bloques(int x, int y, int ancho, int alto, boolean esRompible, boolean esInestable) {
    	this.area = new Rectangle(x, y, ancho, alto);
    	this.rompible = esRompible;
    	this.inestable = esInestable;
    	this.caidaActiva = false;
    	
    	if (texturaNormal == null) {

    	    texturaNormal =
    	        new ImageIcon("src/bloqueNormal.png")
    	        .getImage()
    	        .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

    	    texturaRompible =
    	        new ImageIcon("src/bloqueRompible.png")
    	        .getImage()
    	        .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

    	    texturaInestable =
    	        new ImageIcon("src/bloqueInestable.png")
    	        .getImage()
    	        .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    	}
	}

    public boolean isCaidaActiva() {
		return caidaActiva;
	}

	public void setCaidaActiva(boolean caidaActiva) {
		this.caidaActiva = caidaActiva;
	}

	public int getTickCaida() {
		return tickCaida;
	}

	public void setTickCaida(int tickCaida) {
		this.tickCaida = tickCaida;
	}

	public boolean isRompible() {
		return rompible;
	}

	public boolean isInestable() {
		return inestable;
	}

	public void dibujar(Entorno entorno, int xCentro, int yCentro) {
		if (rompible) {

	        entorno.dibujarImagen(
	            texturaRompible,
	            xCentro,
	            yCentro,
	            0
	        );
	    }
	    else if (inestable) {

	        entorno.dibujarImagen(
	            texturaInestable,
	            xCentro,
	            yCentro,
	            0
	        );
	    }
	    else {

	        entorno.dibujarImagen(
	            texturaNormal,
	            xCentro,
	            yCentro,
	            0
	        );
	    }
	}
	

    public int getX() {
        return area.x;
    }

    public int getY() {
        return area.y;
    }

    public int getAncho() {
        return area.width;
    }

    public int getAlto() {
        return area.height;
    }
    
    public Rectangle getArea() {

        return this.area;
    }
}



