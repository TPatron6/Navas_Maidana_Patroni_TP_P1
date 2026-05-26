package juego;


import java.awt.Color;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	private AereoNormal[] enemigos;
	private AereoRapido[] enemigosRapidos;
	
	//JUGADOR
		private int jugadorX;
	    private int jugadorY;
	    
	    //gravedad 
	    private int velocidadY;

	    private boolean enElAire;
	    //Camara
	    private int camaraX;
	    
	    //Islas 
	    private Islas[] islas;

	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.enemigos = new AereoNormal[20];
		this.enemigosRapidos = new AereoRapido[20];
		
		
		
		
		
        // posicion inicial jugador
        this.jugadorX = 100;
        this.jugadorY = 100;

        // gravedad
        this.velocidadY = 0;

        this.enElAire = true;

        // cámara
        this.camaraX = 0;

      
        // ISLAS RANDOM
        

        Random random = new Random();

        islas = new Islas[10];

        for (int i = 0; i < islas.length; i++) {

            int x = i * 300;

            int y = 250 + random.nextInt(250);

            int ancho = 100 + random.nextInt(200);

            int alto = 20;

            islas[i] = new Islas(x, y, ancho, alto);
        }
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
	 // MOVIMIENTO JUGADOR


        if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {

            jugadorX += 5;
        }

        if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {

            jugadorX -= 5;
        }

        
        // SALTO
        

        if (entorno.sePresiono(entorno.TECLA_ESPACIO) && !enElAire) {

            velocidadY = -15;

            enElAire = true;
        }

        
        // GRAVEDAD
        

        velocidadY += 1;

        jugadorY += velocidadY;

        
        // COLISIONES CON ISLAS
        

        enElAire = true;

        for (int i = 0; i < islas.length; i++) {

            Islas isla = islas[i];

            int izquierdaJugador = jugadorX - 20;
            int derechaJugador = jugadorX + 20;

            int abajoJugador = jugadorY + 20;

            int izquierdaIsla = isla.getX() - isla.getAncho() / 2;
            int derechaIsla = isla.getX() + isla.getAncho() / 2;

            int arribaIsla = isla.getY() - isla.getAlto() / 2;

            boolean colisionX =
                    derechaJugador > izquierdaIsla &&
                    izquierdaJugador < derechaIsla;

            boolean colisionY =
                    abajoJugador >= arribaIsla &&
                    abajoJugador <= arribaIsla + 10;

            if (colisionX && colisionY && velocidadY >= 0) {

                jugadorY = arribaIsla - 20;

                velocidadY = 0;

                enElAire = false;
            }
        }

        
        // CAMARA QUE SIGUE
        

        camaraX = jugadorX - 400;

        if (camaraX < 0) {

            camaraX = 0;
        }

        
        // DIBUJAR ISLAS
        

        for (int i = 0; i < islas.length; i++) {

            islas[i].dibujar(entorno, camaraX);
        }

        
        // DIBUJAR JUGADOR
       

        entorno.dibujarRectangulo(
                jugadorX - camaraX,
                jugadorY,
                40,
                40,
                0,
                Color.RED);
    }

    
	
	
	
	

	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
