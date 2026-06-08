package juego;

import java.awt.*;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Princesa {
	private Rectangle area;
	private int velocidadY;
	private int velocidadX;
	private int totalSaltos;
	private Image sprite;
	private Image quieta;
	private Image moverDerecha;
	private Image moverIzquierda;
	private int vidas;
	
	
	public Princesa() {
		this.area = new Rectangle(390, 280, 20, 40);
		this.velocidadY = 0;
	//	this.angulo = 0;
		this.totalSaltos = 0;
		this.vidas = 1000;

		sprite = new ImageIcon("src/moverder.gif").getImage();
		quieta = new ImageIcon("src/quieta.gif").getImage();
		moverDerecha = new ImageIcon("src/moverder.gif").getImage();
		moverIzquierda = new ImageIcon("src/moverizq.gif").getImage();
		
		sprite = quieta;
	}
	
	public void moverLateralmente(Entorno entorno) {
		 if(entorno.estaPresionada(entorno.TECLA_DERECHA)){
			  this.velocidadX = 4;
			  sprite = moverDerecha;
		}
		 else if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			  this.velocidadX = -4;
			  sprite = moverIzquierda;
		 }
		 else {
			 this.velocidadX = 0;
			 sprite = quieta;
	
		 }
	}
		
	public void saltar(Entorno entorno) {

		if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
			if(this.totalSaltos == 1){
				this.velocidadY = -12;
				this.totalSaltos ++;
			}
			else {
				this.velocidadY = -10;
				this.totalSaltos ++;
			}
		}
	}
	public int getVidas() {
	    return this.vidas;
	}
	
	public void aumentarVida() {
		this.vidas += 1;
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
		
		int xCentro = this.area.x + (this.area.width / 2) ;
		int yCentro = this.area.y + (this.area.height /2);
		
		
	    entorno.dibujarImagen(
	        sprite,
	        xCentro - camaraX,
	        yCentro,
	        0
	    );
	}
	public void perderVida() {
	    if (this.vidas > 0) {
	        this.vidas--;
	    }
	}
	
}
