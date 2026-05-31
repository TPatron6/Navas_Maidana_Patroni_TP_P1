package juego;


import java.awt.*;
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
	
	private EnemigoTerrestre[] enemigosTerrestres;
	
	//JUGADOR
	private Princesa princesa;
	//VIDAS
	private vidas vidas;
	//Camara
	private int camaraX;
	
	//Proyectil
    private Proyectil proyectil;
	    
	//Islas 
	private Islas[][] islas; //[] lo converti en matriz
	private int[] niveles = {585, 425, 285, 165};
	
	//GRAVEDAD
	private void gravedad(Princesa princesa) {
		int gravedad = princesa.getVelocidadY() + 1;
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
		
		this.enemigosTerrestres = new EnemigoTerrestre[20];
		
        // posicion inicial jugador
		this.princesa = new Princesa();
		// vidas
		this.vidas = new vidas(3);
        // cámara
        this.camaraX = 0;
        
        

     
        // ISLAS RANDOM
        
        Random random = new Random();

    	
        islas = new Islas[4][240];     //SUPONEMOS 30 ISLAS DE 8 BLOQUES CADA UNA POR NIVEL
        
        int y;
        int x;
        int ancho = 40; 
        int alto = 30; 
        int cantBloquesPorIsla;
        int totalIslasPorNivel = 0;
    	int totalDeIslas2 = random.nextInt(6,11);
    	boolean primerIsla = true;
    	
        for(int fila=0; fila < 4; fila++) {
        	y = niveles[fila];
        	x = random.nextInt(301);
        	cantBloquesPorIsla = 0;
        	
        	switch(fila) {
        	case 0 : totalIslasPorNivel = 30; break;
        	case 1 : totalIslasPorNivel = random.nextInt(12,19); break;
        	case 2 : totalIslasPorNivel = totalDeIslas2; break;
        	case 3 : totalIslasPorNivel = random.nextInt(2, totalDeIslas2 + 1); break;
        	}
        	
        	int columna = 0;
        	while(totalIslasPorNivel > 0 || cantBloquesPorIsla >0){
        		if(cantBloquesPorIsla == 0) { //SI ES 0 HAY QUE INICIAR UNA NUEVA ISLA
        			cantBloquesPorIsla = random.nextInt(3,9);
        			totalIslasPorNivel --;
        			if(!primerIsla) {
        				if(fila == 0 || fila == 1) {
        					x += random.nextInt(80,121);
        				}
        				else {
        					x += random.nextInt(160, 200);
        				}
        			}
        			else {
        				primerIsla = false;
        			}
        		}
        		islas[fila][columna] = new Islas(x, y, ancho, alto);
	       		columna ++;
	       		cantBloquesPorIsla --;
	       		x += 40; //20PX DEL ANCHO DE LA PRIMER ISLA + 20 PX DEL ANCHO DE LA SEGUNDA ISLA
        	}
        	
        }
        
        int indiceEnemigo = 0;

        for(int i = 0;
            i < islas[0].length
            && indiceEnemigo < enemigosTerrestres.length;
            i++) {

            if(islas[0][i] == null) {
                continue;
            }

            boolean inicioDeIsla = false;

            if(i == 0) {
                inicioDeIsla = true;
            }
            else if(islas[0][i - 1] == null) {
                inicioDeIsla = true;
            }
            else {

                int distancia =
                    islas[0][i].getX()
                    - islas[0][i - 1].getX();

                if(distancia > 40) {
                    inicioDeIsla = true;
                }
            }

            if(inicioDeIsla) {

                int ultimoBloque = i;

                while(
                    ultimoBloque + 1 < islas[0].length
                    && islas[0][ultimoBloque + 1] != null
                    && islas[0][ultimoBloque + 1].getX()
                       - islas[0][ultimoBloque].getX() == 40
                ) {
                    ultimoBloque++;
                }

                int limiteIzquierdo =
                    islas[0][i].getX()
                    - islas[0][i].getAncho()/2;

                int limiteDerecho =
                    islas[0][ultimoBloque].getX()
                    + islas[0][ultimoBloque].getAncho()/2;

                enemigosTerrestres[indiceEnemigo] =
                    new EnemigoTerrestre(
                        islas[0][i].getX(),
                        niveles[0] - 15 - 10,
                        limiteIzquierdo,
                        limiteDerecho
                    );

                indiceEnemigo++;
            }
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
		
		public void generarEnemigoTerrestre() {

		    int cantidadIslas = 0;

		    while (cantidadIslas < islas[0].length
		            && islas[0][cantidadIslas] != null) {

		        cantidadIslas++;
		    }

		    if (cantidadIslas == 0) {
		        return;
		    }

		    int indiceIsla =
		        (int)(Math.random() * cantidadIslas);

		    Islas isla = islas[0][indiceIsla];

		    int x = isla.getX();

		    int y = isla.getY() - 30;

		    for (int i = 0;
		         i < enemigosTerrestres.length;
		         i++) {

		        if (enemigosTerrestres[i] == null) {

		            enemigosTerrestres[i] =
		                new EnemigoTerrestre(x, y, i, i);

		            break;
		        }
		    }
		}
		
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		if (vidas.getCantidad() <= 0) {

		    entorno.escribirTexto(
		        "GAME OVER",
		        330,
		        300
		    );

		    return;
		}

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
	    
	    
	    
	    for (int i = 0;
	    	     i < enemigosTerrestres.length;
	    	     i++) {

	    	    if (enemigosTerrestres[i] != null) {

	    	        enemigosTerrestres[i].mover();

	    	        enemigosTerrestres[i].gravedad();
	    	        
	    	        int pisoEnemigo = niveles[0] - 15 - 10;

	    	        if (enemigosTerrestres[i].getY() > pisoEnemigo) {

	    	            enemigosTerrestres[i].setY(pisoEnemigo);
	    	            enemigosTerrestres[i].setVelocidadY(0);
	    	        }

	    	        enemigosTerrestres[i].dibujar(
	    	            entorno,
	    	            camaraX
	    	        );
	    	    }
	    	}
	    
	 // MOVIMIENTO JUGADOR SOBRE EJE X

	    this.princesa.moverLateralmente(entorno);      

        
     // COLISIONES CON ISLAS
        
       	int izqPrincesa;
       	int derPrincesa;
       	int abajoPrincesa;
      	boolean enIsla = false;
    	int arribaPrincesa = this.princesa.getY() - 20;
       	boolean debajoIsla = false;
       	boolean toco = false;
       	
         
     	//COLISION CON BORDE LATERAL DE ISLA
      	for(int i=0; i < niveles.length; i++ ) {
        		
      		if(this.princesa.getY() - 20 < niveles[i] + 15 && this.princesa.getY() + 20 > niveles[i] - 15) {
	       		int k = 0;
	        		
	       		while(!toco && k< islas[i].length && islas[i][k] != null) {
	        		
	        		int izqIsla = islas[i][k].getX() - islas[i][k].getAncho() / 2;
	        		int derIsla = islas[i][k].getX() + islas[i][k].getAncho() / 2;
	        		  
		        	izqPrincesa = this.princesa.getX() - this.princesa.getAncho() /2;
		        	derPrincesa = this.princesa.getX() + this.princesa.getAncho() /2;
		        	
	        		if(princesa.getVelocidadX() > 0 && derPrincesa <= izqIsla && derPrincesa + princesa.getVelocidadX() >= izqIsla ) {
	        			toco = true;
	        			princesa.setVelocidadY(3);
	        			princesa.setVelocidadX(0);
		        			
	        			int nuevaPosicionx = izqIsla - (this.princesa.getAncho()/2); 
	        			princesa.setX(nuevaPosicionx);
	        			
	          		}
	        		if(princesa.getVelocidadX() < 0 && izqPrincesa >= derIsla && izqPrincesa + princesa.getVelocidadX() <= derIsla ) {
	        			toco = true;
	        			princesa.setVelocidadY(3);
		        		princesa.setVelocidadX(0);
		        		
	        			int nuevaPosicionx = derIsla + (this.princesa.getAncho()/2);
	        			princesa.setX(nuevaPosicionx);
	        			
	          		}
	        	
	        		k++;
	       		}	
	       	}
	       	if(toco) {
	       		break;
	       	}
       	}
       	
       	if(!toco) {
       		int nuevoX = princesa.getX() + princesa.getVelocidadX();
       		princesa.setX(nuevoX);
       	}
       	
     // MOVIMIENTO JUGADOR SOBRE EJE Y 
	    
	    if(this.princesa.getTotalSaltos() < 2) {
	    	this.princesa.saltar(entorno);
	    }
        
     // GRAVEDAD
        
	 	this.gravedad(princesa);


	 // COLISIONES CON ISLAS
	 	int nuevoY = princesa.getY() + princesa.getVelocidadY();
	 	
       	izqPrincesa = this.princesa.getX() - 8;
       	derPrincesa = this.princesa.getX() + 8;
       	abajoPrincesa = nuevoY + 20;
    	arribaPrincesa = nuevoY - 20;
       	debajoIsla = false;
     	enIsla = false;
     	
       	//COLISION CON EL BORDE INFERIOR DE LA ISLA (TECHO)
  
     	if (princesa.getVelocidadY() < 0) {
     	    for(int i=0 ; i < islas.length; i++ ) {
     	    	
     	        int bordeInferiorIsla = niveles[i] + 15; 
     	        int bordeSuperiorIsla = niveles[i] - 15;

     	        if (arribaPrincesa >= bordeInferiorIsla || abajoPrincesa <= bordeSuperiorIsla) { //SI LA PRINCESA ESTA COMPLETAMENTE POR ENCIMA O POR DEBAJO DE UNA ISLA, NO ESTA A NIVEL DE LAS ISLAS
     	            continue;
     	        }

     	        int k = 0;
     	        while(!debajoIsla && k < islas[i].length && islas[i][k] != null) {
     	            int izqIsla = islas[i][k].getX() - islas[i][k].getAncho() / 2;
     	            int derIsla = islas[i][k].getX() + islas[i][k].getAncho() / 2;
     	            
     	            if(izqPrincesa <= derIsla && derPrincesa >= izqIsla) {
     	                debajoIsla = true;
     	                this.princesa.setVelocidadY(0);
     	                nuevoY = niveles[i] + 15 + 20; 
     	            }
     	            k++;
     	        }
     	        if(debajoIsla) break;
     	    }
     	}
        
     	abajoPrincesa = nuevoY + 20; 
        arribaPrincesa = nuevoY - 20;
     	
       	//COLISION CON BORDE SUPERIOR DE ISLA (PISO)
        if (princesa.getVelocidadY() >= 0) {
            for(int i=0; i < niveles.length; i++ ) {
                int bordeSuperiorDeIsla = niveles[i] - 15;
                int bordeInferiorIsla = niveles[i] + 15;
                
                if (abajoPrincesa <= bordeSuperiorDeIsla || arribaPrincesa >= bordeInferiorIsla) {
                    continue; 
                }

                int k = 0;
                while(!enIsla && k < islas[i].length && islas[i][k] != null) {
                    int izqIsla = islas[i][k].getX() - islas[i][k].getAncho() / 2;
                    int derIsla = islas[i][k].getX() + islas[i][k].getAncho() / 2;
                            
                    if(izqPrincesa <= derIsla && derPrincesa >= izqIsla) {
                        enIsla = true;
                        this.princesa.setVelocidadY(0);
                        nuevoY = niveles[i] - 15 - 20; 
                        this.princesa.setTotalSaltos(0);
                    }
                    k++;
                }   
                if(enIsla) break;
            }
        }
        
        if(nuevoY <= 20) {
        	nuevoY = 20;
        	this.princesa.setVelocidadY(0);
        }
       	
        princesa.setY(nuevoY);
       	
	    
        // PERDER VIDA SI CAE

	 	if (this.princesa.getY() > 700) {

	 	    vidas.perderVida();

	 	    this.princesa = new Princesa();

	 	    camaraX = 0;
	 	}
	    
        // CAMARA QUE SIGUE
        

        camaraX = this.princesa.getX() - 400;

        if (camaraX < 0) {

            camaraX = 0;
        }

        
        // DIBUJAR ISLAS
        

        for (Islas[] fila : islas) {
        	for(Islas columna : fila) {
        		if(columna != null) {
        			int x = columna.getX() - camaraX;
        			columna.dibujar(entorno, x);
        		}
        	}
     //       islasDibujo[i].dibujar(entorno, camaraX);
        }
        
        

        
        // DIBUJAR JUGADOR
       
        this.princesa.dibujarPrincesa(entorno, camaraX);

        entorno.escribirTexto(
        	    "Vidas: " + vidas.getCantidad(),
        	    20,
        	    30
        	);
    

    
	
		//CREAR EL PREYOCTIL
		if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && proyectil == null) {
	
			    proyectil = new Proyectil(princesa.getX(), princesa.getY(), entorno.mouseX() + camaraX, entorno.mouseY());
			}
	
		if (proyectil != null) {

		    proyectil.mover();

		    proyectil.dibujar(entorno, camaraX);

		    if (proyectil.fueraDePantalla(camaraX)) {

		        proyectil = null;
		    }
		}
	}

	
	
	
	

	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
