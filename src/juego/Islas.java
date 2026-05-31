package juego;

import java.awt.*;

import entorno.Entorno;

public class Islas {
	
	private Rectangle area;
	private boolean rompible;
	private boolean inestable;
	private boolean caidaActiva;
	private int tickCaida;
	private String direccion;
   

    public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Islas(int x, int y, int ancho, int alto, boolean esRompible, boolean esInestable) {
    	this.area = new Rectangle(x, y, ancho, alto);
    	this.rompible = esRompible;
    	this.inestable = esInestable;
    	this.caidaActiva = false;
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

	public void dibujar(Entorno entorno, int x) {
    	Color color = Color.GREEN;
    	if(this.rompible) {
    		color = Color.ORANGE;
    	}
    	if(this.inestable) {
    		color = Color.PINK;
    	}
        entorno.dibujarRectangulo(
                x,
                area.y,
                area.width,
                area.height,
                0,
                color);
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



