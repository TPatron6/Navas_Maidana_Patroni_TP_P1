package juego;

import java.awt.*;

import entorno.Entorno;

public class Princesa {
	private Rectangle area;
	private int velocidadY;
	private double angulo;
	
	public Princesa() {
		this.area = new Rectangle(400, 300, 20, 40);
		this.velocidadY = 0;
		this.angulo = 0;
	}
	
	public void moverLateralmente(Entorno entorno) {
		 if(entorno.estaPresionada(entorno.TECLA_DERECHA)){
			 this.area.x +=4;
		 }
		 if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			 this.area.x -=4;
		 }
	}
		
	public void saltar(Entorno entorno) {
		if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			this.velocidadY -= 15;
		}
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}
	
	public int getVelocidadY() {
		return velocidadY;
	}

	public void aumentarVelocidad(int variacion) {
		this.area.y += variacion;
	}
	
	public void setY(int y) {
		this.area.y = y;
	}
	
	public int getY() {
		return this.area.y;
	}
	
	public int getX() {
		return this.area.x;
	}
	
	public int getAncho() {
		return this.area.width;
	}
	

	public void dibujarPrincesa(Entorno entorno, int camaraX) {
		entorno.dibujarRectangulo(area.x - camaraX, area.y, area.width, area.height, this.angulo, Color.RED);
	}
	
}
