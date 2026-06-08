package juego;

import java.awt.*;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Regalo {
	private Rectangle area;
	private int tickAparicion;
	private boolean Activo;
	private Image imagen;
	
	public Regalo(int x, int y, int tick, boolean activo) {
		this.area = new Rectangle(x, y, 20, 20);
		this.tickAparicion = tick;
		this.Activo = activo;
		this.imagen = new ImageIcon("src/corazon.png").getImage();
       
	}
	
	public Rectangle getArea() {
		return this.area;
	}

	public int getTickAparicion() {
		return tickAparicion;
	}

	public void setTickAparicion(int tickAparicion) {
		this.tickAparicion = tickAparicion;
	}

	public boolean isActivo() {
		return Activo;
	}

	public void setActivo(boolean activo) {
		Activo = activo;
	}
	
	 public boolean fueraDePantalla(int camaraX) {

	        return this.area.x <= camaraX;
	 }
	 
	 public void setX(int x) {
		 this.area.x = x;
	 }
	
	 
	 public void dibujar(Entorno entorno, int camaraX) {
		 
		 entorno.dibujarImagen(this.imagen, this.area.x - camaraX, this.area.y, 0);
		

	 }

	
}
