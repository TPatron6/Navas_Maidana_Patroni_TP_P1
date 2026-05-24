package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	private AereoNormal[] enemigos;
	private AereoRapido[] enemigosRapidos;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.enemigos = new AereoNormal[20];
		this.enemigosRapidos = new AereoRapido[20];
		
		
		
		// Inicia el juego!
		this.entorno.iniciar();
	}
	
//esto limita la cantidad de enemigos en pantalla a 10
	public int contarEnemigos() {

	    int cantidad = 0;

	    // normales
	    for (int i = 0; i < enemigos.length; i++) {

	        if (enemigos[i] != null) {

	            cantidad++;
	        }
	    }

	    // rápidos
	    for (int i = 0; i < enemigosRapidos.length; i++) {

	        if (enemigosRapidos[i] != null) {

	            cantidad++;
	        }
	    }

	    return cantidad;
	}
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {

	    //GENERAR NORMALES

	    if (entorno.tiempo() > 3000
	        && contarEnemigos() < 10) {

	        if (entorno.numeroDeTick() % 100 == 0) {

	            for (int i = 0; i < enemigos.length; i++) {

	                if (enemigos[i] == null) {

	                	if (Math.random() < 0.5) {

	                	    //asi va de derecha a izquierda
	                	    enemigos[i] =
	                	        new AereoNormal(entorno.ancho(), -1);

	                	} else {

	                	    //asi va de izquierda a derecha
	                	    enemigos[i] =
	                	        new AereoNormal(0, 1);
	                	}

	                    break;
	                }
	            }
	        }
	    }

	    //GENERAR RAPIDOS

	    if (entorno.tiempo() > 3000
	        && contarEnemigos() < 10) {

	        if (entorno.numeroDeTick() % 150 == 0) {

	            for (int i = 0; i < enemigosRapidos.length; i++) {

	                if (enemigosRapidos[i] == null) {

	                	if (Math.random() < 0.5) {

	                	    enemigosRapidos[i] =
	                	        new AereoRapido(entorno.ancho(), -1);

	                	} else {

	                	    enemigosRapidos[i] =
	                	        new AereoRapido(0, 1);
	                	}

	                    break;
	                }
	            }
	        }
	    }

	    //NORMALES

	    for (int i = 0; i < enemigos.length; i++) {

	        if (enemigos[i] != null) {

	            enemigos[i].mover();

	            enemigos[i].dibujar(entorno);

	            if (enemigos[i].fueraDePantalla()) {

	                enemigos[i] = null;
	            }
	        }
	    }

	    //RAPIDOS

	    for (int i = 0; i < enemigosRapidos.length; i++) {

	        if (enemigosRapidos[i] != null) {

	            enemigosRapidos[i].mover();

	            enemigosRapidos[i].dibujar(entorno);

	            if (enemigosRapidos[i].fueraDePantalla()) {

	                enemigosRapidos[i] = null;
	            }
	        }
	    }
	}	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
