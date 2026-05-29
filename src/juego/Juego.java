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
	private Princesa princesa;

	//Camara
	private int camaraX;
	    
	//Islas 
	private Islas[][] islas; //[] lo converti en matriz
	private Islas[] islasDibujo;
	private int cantIslas;
	private int[] niveles = {590, 480, 370, 260};
	
	//GRAVEDAD
	private void gravedad(Princesa princesa) {
		int gravedad = princesa.getVelocidadY() + 1;
		if(gravedad > 15) {
			gravedad = 15;
		}
		princesa.setVelocidadY(gravedad);
		princesa.aumentarVelocidad(gravedad);
	}
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.enemigos = new AereoNormal[20];
		this.enemigosRapidos = new AereoRapido[20];
		
        // posicion inicial jugador
		this.princesa = new Princesa();
      
        // cámara
        this.camaraX = 0;

     
        // ISLAS RANDOM
        
        Random random = new Random();

        islas = new Islas[4][30];
        
        islasDibujo = new Islas[120];
        
        cantIslas = 0;

        int nivel = 0;
        for(Islas[] i: islas) {
        	int finIslaAnterior =0;
        	int y=0;
	        int x =0;
	        int totalDeIslas = 30;
	        int inicio = 50;
	        int fin = 200;
        	
        	switch(nivel) {
	        case 0: y = 590; break;
	        case 1: y = 480; totalDeIslas = random.nextInt(20,31); inicio = random.nextInt(50, 101); fin = random.nextInt(200, 401); break;
	        case 2: y = 370; totalDeIslas = random.nextInt(26,31); inicio = random.nextInt(50, 101); fin = random.nextInt(200, 301); break;
	        case 3: y = 260; totalDeIslas = random.nextInt(20,31); inicio = random.nextInt(100, 151); fin = random.nextInt(200, 401); break; 
	        }
	        
	        for (int k = 0; k < totalDeIslas; k++) {
	        	if(k>0) {
	        		finIslaAnterior += random.nextInt(inicio, fin);
	        		x = finIslaAnterior + random.nextInt(100, 200);
	        	}
	        	else {
	        		x = random.nextInt(300); //k * 300
	        	}
	
	            int ancho = random.nextInt(200, 401);
	            
	            finIslaAnterior = x + ancho /2;
	        	
	            int alto = 20;
	
	            i[k] = new Islas(x, y, ancho, alto);
	            
	            int indice = 0;
	            while(indice < islasDibujo.length && islasDibujo[indice] != null) {
	            	indice ++;
	            }
	            islasDibujo[indice] = new Islas(x, y, ancho, alto);
	            cantIslas ++;
	        }
	        
	        nivel++;
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
	 // MOVIMIENTO JUGADOR SOBRE EJE X

	    this.princesa.moverLateralmente(entorno);

      
     // MOVIMIENTO JUGADOR SOBRE EJE Y 
        
	    this.princesa.saltar(entorno);

        
     // GRAVEDAD
        
	 	this.gravedad(princesa);
	    
        
     // COLISIONES CON ISLAS
        
        
       	int abajoPrincesa = this.princesa.getY() + 20;
       	int izqPrincesa = this.princesa.getX() - 10;
       	int derPrincesa = this.princesa.getX() + 10;
       	boolean enIsla = false;
        	
       	for(int i=0; i < niveles.length; i++ ) {
        		
	       	if(princesa.getVelocidadY() >= 0 && abajoPrincesa >= niveles[i]-10 && (abajoPrincesa - this.princesa.getVelocidadY()) <= niveles[i]-10) { //NIVELES TIENE LAS COORDENADAS Y DE LAS ISLAS
	       		int k = 0;
	        		
	       		while(!enIsla && k< islas[i].length && islas[i][k] != null) {
	        		
	        		int izqIsla = islas[i][k].getX() - islas[i][k].getAncho() / 2;
	        		int derIsla = islas[i][k].getX() + islas[i][k].getAncho() / 2;
		        			
	        		if(izqPrincesa <= derIsla && derPrincesa >= izqIsla) {
	        			enIsla = true;
	        			this.princesa.setVelocidadY(0);
		        			
	        			int nuevaAltura = islas[i][k].getY() - 30; //MITAD DEL ALTO DEL PERSONAJE + MITAD DEL ALTO DE LA ISLA
	        			this.princesa.setY(nuevaAltura);
	          		}
		        		
	        		k++;
	       		}	
	       	}
	       	if(enIsla) {
	       		break;
	       	}
       	}
      
        
        // CAMARA QUE SIGUE
        

        camaraX = this.princesa.getX() - 400;

        if (camaraX < 0) {

            camaraX = 0;
        }

        
        // DIBUJAR ISLAS
        

        for (int i = 0; i < cantIslas; i++) {
        	
            islasDibujo[i].dibujar(entorno, camaraX);
        }
        
        

        
        // DIBUJAR JUGADOR
       
        this.princesa.dibujarPrincesa(entorno, camaraX);
    }
	
	
	
	

	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
