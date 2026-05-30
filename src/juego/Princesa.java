package juego;

import java.awt.*;

import entorno.Entorno;

public class Princesa {
	private Rectangle area;
	private int velocidadY;
	private int velocidadX;
	private double angulo;
	private int totalSaltos;
	
	public Princesa() {
		this.area = new Rectangle(400, 300, 20, 40);
		this.velocidadY = 0;
		this.angulo = 0;
		this.totalSaltos = 0;
	}
	
	public void moverLateralmente(Entorno entorno) {
		 if(entorno.estaPresionada(entorno.TECLA_DERECHA)){
			  this.velocidadX = 4;
		}
		 else if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			  this.velocidadX = -4;
		 }
		 else {
			 this.velocidadX = 0;
	
		 }
	}
		
	public void saltar(Entorno entorno) {
		if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			this.velocidadY = -15;
			this.totalSaltos ++;
		}
	}

	public int getTotalSaltos() {
		return totalSaltos;
	}

	public void setTotalSaltos(int totalSaltos) {
		this.totalSaltos = totalSaltos;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}
	
	public int getVelocidadY() {
		return velocidadY;
	}
	
	public void setVelocidadX(int velocidadx) {
		this.velocidadX = velocidadx;
	}

	public int getVelocidadX() {
		return velocidadX;
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
	
	public void setX(int x) {
		this.area.x = x;
	}
	
	public int getX() {
		return this.area.x;
	}
	
	public int getAncho() {
		return this.area.width;
	}
	
	public int getAlto() {
		return this.area.height;
	}
	
	public Rectangle getArea() {
		return area;
	}

	public void dibujarPrincesa(Entorno entorno, int camaraX) {
		entorno.dibujarRectangulo(area.x - camaraX, area.y, area.width, area.height, this.angulo, Color.RED);
	}
	
}
