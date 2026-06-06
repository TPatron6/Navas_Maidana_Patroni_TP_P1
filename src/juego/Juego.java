package juego;

import java.awt.Image;
import java.awt.*;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;


public class Juego extends InterfaceJuego
{
	//inicio de juego 
	private static final int MENU = 0;
	private static final int JUGANDO = 1;
	private static final int GAME_OVER = 2;

	private int estado = MENU;
	// game over 
	//victoria
	private int castilloX;
	private int castilloY;
	private boolean victoria;
	
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	private AereoNormal[] enemigos;
	private AereoRapido[] enemigosRapidos;
	
	private EnemigoTerrestre[] enemigosTerrestres;
	
	//JUGADOR
	private Princesa princesa;

	
	
	//Camara
	private int camaraX;
	
	//Proyectil
    private Proyectil proyectil;
	    
    //Islas 
  	private Bloques[][] bloques;
  	private int[] niveles = {570, 410, 270, 150};
  	
	
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
		 // cámara
        this.camaraX = 0;
        
        generarMapa();
	        
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

		    int cantidadBloques = 0;

		    while (cantidadBloques < bloques[0].length
		            && bloques[0][cantidadBloques] != null) {

		        cantidadBloques++;
		    }

		    if (cantidadBloques == 0) {
		        return;
		    }

		    int indiceBloque =
		        (int)(Math.random() * cantidadBloques);

		    Bloques bloque = bloques[0][indiceBloque];

		    int x = bloque.getX();

		    int y = bloque.getY() - 20; //ALTO DEL ENEMIGO

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
		
		public void escribirTextoCentrado(String texto, int centroX, int y) {

		    int x = centroX - (texto.length() * 13);

		    entorno.escribirTexto(texto, x, y);
		}
		
		
		public void dibujarMenu() {

		    entorno.cambiarFont("Arial", 40, Color.WHITE);
		    escribirTextoCentrado(
		    	    "PRINCESA AVENTURA",
		    	    400,
		    	    150
		    	);

		    entorno.dibujarRectangulo(
		        400, 250, 200, 50, 0, Color.GREEN
		    );

		    entorno.dibujarRectangulo(
		        400, 350, 200, 50, 0, Color.RED
		    );

		    entorno.cambiarFont("Arial", 30, Color.BLACK);
		    escribirTextoCentrado(
		    	    "INICIAR",
		    	    400,
		    	    255
		    	);

		    entorno.cambiarFont("Arial", 30, Color.BLACK);
		    escribirTextoCentrado(
		    	    "SALIR",
		    	    400,
		    	    355
		    	);
		}
		
		
		
		public void controlarMenu() {

		    if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {

		        int mx = entorno.mouseX();
		        int my = entorno.mouseY();

		        // BOTON INICIAR

		        if (mx >= 300 && mx <= 500 &&
		            my >= 225 && my <= 275) {

		            estado = JUGANDO;
		        }

		        // BOTON SALIR

		        if (mx >= 300 && mx <= 500 &&
		            my >= 325 && my <= 375) {

		            System.exit(0);
		        }
		    }
		}
		
		public void dibujarGameOver() {

		    entorno.escribirTexto(
		        "GAME OVER",
		        350,
		        220
		    );

		    entorno.escribirTexto(
		        "CLICK PARA REINTENTAR",
		        320,
		        320
		    );
		}

		
		public void reiniciarJuego() {

		   
		    princesa = new Princesa();

		    camaraX = 0;

		    proyectil = null;

		    enemigos = new AereoNormal[20];

		    enemigosRapidos = new AereoRapido[20];

		    enemigosTerrestres = new EnemigoTerrestre[20];

		    estado = JUGANDO;
		}
		
		public void generarMapa() {
	        // ISLAS RANDOM
	        
	        Random random = new Random();

	    	
	        bloques = new Bloques[4][240];     //SUPONEMOS 30 ISLAS DE 8 BLOQUES CADA UNA POR NIVEL
	        
	        int y;
	        int x;
	        int ancho = 40; 
	        int alto = 30; 
	        int cantBloquesPorIsla;
	        int totalIslasPorNivel = 0;
	    	int totalDeIslas2 = random.nextInt(6,11);
	    	int variacionDeBloque = 0;
	    	boolean esRompible = false;
	    	boolean esInestable = false;
	    	boolean primerIsla = true;
	    	
	        for(int fila=0; fila < 4; fila++) {
	        	y = niveles[fila];
	        	x = random.nextInt(41);
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
	        					x += random.nextInt(80, 120); //DISTANCIA ENTRE ISLAS
	        				}
	        				else {
	        					x += random.nextInt(160, 200);
	        				}
	        			}
	        			else {
	        				primerIsla = false;
	        			}
	        			if(fila != 0) {
	        				variacionDeBloque = random.nextInt(3);
	        			}
	        		}
	        		if (variacionDeBloque == 2) {
	        			esInestable = true;
	        			esRompible = false;
	        		}
	        		else if(variacionDeBloque == 1) {
	        			esInestable = false;
	        			esRompible = true;
	        		}
	        		else {
	        			esInestable = false;
	        			esRompible = false;
	        		}
	        		bloques[fila][columna] = new Bloques(x, y, ancho, alto, esRompible, esInestable);
		       		columna ++;
		       		cantBloquesPorIsla --;
		       		x += 40; //ANCHO DEL BLOQUE
	        	}
	        	
	        }
	 
	        
	        int indiceEnemigo = 0;

	        for(int i = 0;
	            i < bloques[0].length
	            && indiceEnemigo < enemigosTerrestres.length;
	            i++) {

	            if(bloques[0][i] == null) {
	                continue;
	            }

	            boolean inicioDeIsla = false;

	            if(i == 0) {
	                inicioDeIsla = true;
	            }
	            else if(bloques[0][i - 1] == null) {
	                inicioDeIsla = true;
	            }
	            else {

	                int distancia =
	                    bloques[0][i].getX()
	                    - bloques[0][i - 1].getX();

	                if(distancia > 40) {
	                    inicioDeIsla = true;
	                }
	                this.castilloX = 2000;
	                this.castilloY = 540;
	                this.victoria = false;
	            }

	            if(inicioDeIsla) {

	                int ultimoBloque = i;

	                while(
	                    ultimoBloque + 1 < bloques[0].length
	                    && bloques[0][ultimoBloque + 1] != null
	                    && bloques[0][ultimoBloque + 1].getX()
	                       - bloques[0][ultimoBloque].getX() == 40
	                ) {
	                    ultimoBloque++;
	                }

	                int limiteIzquierdo =
	                    bloques[0][i].getX();

	                int limiteDerecho =
	                    bloques[0][ultimoBloque].getX()
	                    + bloques[0][ultimoBloque].getAncho();

	                enemigosTerrestres[indiceEnemigo] =
	                    new EnemigoTerrestre(
	                        bloques[0][i].getX(),
	                        niveles[0] - 20, // ALTO DEL ENEMIGO
	                        limiteIzquierdo,
	                        limiteDerecho
	                    );

	                indiceEnemigo++;
	            }
	            this.castilloX = 8000;
                this.castilloY = 540;
                this.victoria = false;
	        }
		}
		
		public void reiniciarMapa() {

		    enemigos = new AereoNormal[20];

		    enemigosRapidos = new AereoRapido[20];

		    enemigosTerrestres =
		        new EnemigoTerrestre[20];

		    proyectil = null;

		    princesa = new Princesa();

		    
		    camaraX = 0;

		    generarMapa();
		    
		    estado = MENU;
		}
	
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		if (victoria) {

		    entorno.escribirTexto("¡GANASTE!", 320, 250);

		    entorno.escribirTexto("LLEGASTE AL CASTILLO", 250, 320);

		    entorno.escribirTexto("ESC = SALIR", 320, 380);

		    if (entorno.sePresiono(entorno.TECLA_ESCAPE)) {
		        System.exit(0);
		    }

		    return;
		}

		if (estado == MENU) {

		    dibujarMenu();

		    controlarMenu();

		    return;
		}
		
		
		
		if (princesa.getVidas() <= 0) {

		    estado = GAME_OVER;
		}
		
		if (estado == GAME_OVER) {

		    dibujarGameOver();

		    if (entorno.sePresionoBoton(
		            entorno.BOTON_IZQUIERDO)) {

		        reiniciarMapa();
		    }

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
	                	        new AereoNormal(camaraX + 800, -1);


	                	} else {

	                	    //asi va de izquierda a derecha
	                	    enemigos[i] =
	                	        new AereoNormal(camaraX, 1);
	                	}

	                    break;
	                }
	            }
	        }
	    }

	    //GENERAR RAPIDOS

	    if (entorno.tiempo() > 20000
	        && contarEnemigos() < 10) {

	        if (entorno.numeroDeTick() % 150 == 0) {

	            for (int i = 0; i < enemigosRapidos.length; i++) {

	                if (enemigosRapidos[i] == null) {

	                	if (Math.random() < 0.5) {

	                	    enemigosRapidos[i] =
	                	        new AereoRapido(camaraX + 800, -1);

	                	} else {

	                	    enemigosRapidos[i] =
	                	        new AereoRapido(camaraX, 1);
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

	            enemigos[i].dibujar(entorno, camaraX);

	            if (enemigos[i].fueraDePantalla(camaraX)) {

	                enemigos[i] = null;
	            }
	        }
	    }

	    //RAPIDOS

	    for (int i = 0; i < enemigosRapidos.length; i++) {

	        if (enemigosRapidos[i] != null) {

	            enemigosRapidos[i].mover();

	            enemigosRapidos[i].dibujar(entorno, camaraX);

	            if (enemigosRapidos[i].fueraDePantalla(camaraX)) {

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
	    	        
	    	        int pisoEnemigo = niveles[0] - enemigosTerrestres[i].getAlto();

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
        
       	int izqPrincesa = this.princesa.getX();
       	int derPrincesa = this.princesa.getX() + this.princesa.getAncho();
       	int abajoPrincesa = this.princesa.getY() + this.princesa.getAlto();
    	int arribaPrincesa = this.princesa.getY();

       	boolean toco = false;
       	
         
     	//COLISION CON BORDE LATERAL DE ISLA
      	for(int i=0; i < niveles.length; i++ ) {
      		int bordeInferiorIsla = niveles[i] + 30; //ALTO DE LOS BLOQUES 
      		int bordeSuperiorIsla = niveles[i];
        		
      		if(arribaPrincesa < bordeInferiorIsla && abajoPrincesa > bordeSuperiorIsla) {
	       		int k = 0;
	        		
	       		while(!toco && k< bloques[i].length) {
	       			if(bloques[i][k] != null) {
	        		
		        		int izqIsla = bloques[i][k].getX();
		        		int derIsla = bloques[i][k].getX() + bloques[i][k].getAncho();
		        		  		        	
		        		if(princesa.getVelocidadX() > 0 && derPrincesa <= izqIsla && derPrincesa + princesa.getVelocidadX() >= izqIsla ) { //DESPLAZAMIENTO A LA DERECHA
		        			toco = true;
		        			princesa.setVelocidadY(3);
		        			princesa.setVelocidadX(0);
			        			
		        			int nuevaPosicionx = izqIsla - this.princesa.getAncho(); 
		        			princesa.setX(nuevaPosicionx);
		        			
		          		}
		        		
		        		if(princesa.getVelocidadX() < 0 && izqPrincesa >= derIsla && izqPrincesa + princesa.getVelocidadX() <= derIsla ) { //DESPLAZAMIENTO A LA IZQUIERDA
		        			toco = true;
		        			princesa.setVelocidadY(3);
			        		princesa.setVelocidadX(0);
			        		
		        			int nuevaPosicionx = derIsla;
		        			princesa.setX(nuevaPosicionx);
		        			
		          		}
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
   	 	
          	izqPrincesa = this.princesa.getX() + 2;
          	derPrincesa = this.princesa.getX() + this.princesa.getAncho() - 2;
          	abajoPrincesa = nuevoY + this.princesa.getAlto(); //NUEVA POSICION LUEGO DEL MOVIMIENTO
       	arribaPrincesa = nuevoY;  //NUEVA POSICION LUEGO DEL MOVIMIENTO 	
          	boolean sobreIsla = false;
     
          	//COLISION CON EL BORDE INFERIOR DE LA ISLA (TECHO)
     
        	if (princesa.getVelocidadY() < 0) {
        	    for(int i=0 ; i < niveles.length; i++ ) {
        	    	
        	        int bordeInferiorIsla = niveles[i] + 30; //ALTO DEL BLOQUE 
        	        int bordeSuperiorIsla = niveles[i];

        	        if (arribaPrincesa >= bordeInferiorIsla || abajoPrincesa <= bordeSuperiorIsla) { //SI LA PRINCESA ESTA COMPLETAMENTE POR ENCIMA O POR DEBAJO DE UNA ISLA, NO ESTA A NIVEL DE LAS ISLAS
        	            continue;
        	        }

        	        int k = 0;
        	        while(!sobreIsla && k < bloques[i].length) {
        	        	if(bloques[i][k] != null) {
   	     	            int izqIsla = bloques[i][k].getX();
   	     	            int derIsla = bloques[i][k].getX() + bloques[i][k].getAncho();
   	     	            
   	     	            if(izqPrincesa <= derIsla && derPrincesa >= izqIsla) {
   	     	            	if(bloques[i][k].isRompible()) {
   	     	                	bloques[i][k] = null;
   	     	            	}
   	     	            		sobreIsla = true;
   	     	            		this.princesa.setVelocidadY(0);
   	     	            		nuevoY = bordeInferiorIsla;
   	     	            }
        	        	}
        	            k++;
        	        }
        	        if(sobreIsla) break;
        	    }
        	}
           
        	abajoPrincesa = nuevoY + this.princesa.getAlto(); 
           arribaPrincesa = nuevoY;
        	
          	//COLISION CON BORDE SUPERIOR DE ISLA (PISO)
           if (princesa.getVelocidadY() >= 0) {
               for(int i=0; i < niveles.length; i++ ) {
         
                   int k = 0;
                   while(k < bloques[i].length) { 
                   	if(bloques[i][k] != null){
                   		
                   		if(bloques[i][k].isCaidaActiva() && entorno.numeroDeTick() >= bloques[i][k].getTickCaida()) {
                        		int columnaSiguiente = k;
                        		int xSiguiente = bloques[i][k].getX();
                        		String direccionActual = bloques[i][k].getDireccion();
                        		
                   			if(direccionActual != null && direccionActual.equals("Derecha")) {
                   				columnaSiguiente += 1;
                   				xSiguiente += 40;
                   			}
                   			else {
                   				columnaSiguiente -= 1;
                   				xSiguiente -= 40;
                   			}
                   			
                   			if(columnaSiguiente >= 0 && columnaSiguiente < bloques[i].length) {
                   				
   	                			if(bloques[i][columnaSiguiente] != null && bloques[i][columnaSiguiente].getX() == xSiguiente && bloques[i][columnaSiguiente].isInestable()) {
   	                				bloques[i][columnaSiguiente].setCaidaActiva(true);
   	                				bloques[i][columnaSiguiente].setTickCaida(entorno.numeroDeTick() + 4);
   	                				bloques[i][columnaSiguiente].setDireccion(direccionActual);
   	                			}
                   			}
                   			bloques[i][k] = null;	
                   			k++;
                   			continue;
                   		}
                   		
                   		   int bordeSuperiorDeIsla = niveles[i];
                           int bordeInferiorIsla = niveles[i] + 30; //ALTO DE LA ISLA
                           
                           if (abajoPrincesa <= bordeSuperiorDeIsla || arribaPrincesa >= bordeInferiorIsla) { //SI LA PRINCESA ESTA COMPLETAMENTE POR ENCIMA O POR DEBAJO DE UNA ISLA, NO ESTA A NIVEL DE LAS ISLAS
                           	k++;
                               continue; 
                           }
                           
   	                    int izqIsla = bloques[i][k].getX();
   	                    int derIsla = bloques[i][k].getX() + bloques[i][k].getAncho();
   	                            
   	                    if(izqPrincesa <= derIsla && derPrincesa >= izqIsla) {
   	                    	if(!bloques[i][k].isCaidaActiva() && bloques[i][k].isInestable()) {
   	                            bloques[i][k].setCaidaActiva(true);
   	                            bloques[i][k].setTickCaida(entorno.numeroDeTick() + 20);
   	                            
   	                            if(princesa.getVelocidadX() >= 0) {
   	                                bloques[i][k].setDireccion("Derecha");
   	                            } else {
   	                                bloques[i][k].setDireccion("Izquierda");
   	                            }
   	                        }
   	                    	
   	                        this.princesa.setVelocidadY(0);
   	                        nuevoY = niveles[i] - this.princesa.getAlto(); 
   	                        this.princesa.setTotalSaltos(0);
   	                    }
                   	}
                       k++;
                   }   
               }
           }
           
           if(nuevoY < 0) {
           	nuevoY = 0;
           	this.princesa.setVelocidadY(0);
           }
          	
           princesa.setY(nuevoY);
           
           
           // PERDER VIDA SI CAE

   	 	if (this.princesa.getY() > 700) {

   	 	   this.princesa.perderVida();
   	 	   this.princesa.setX(390);
   	 	   this.princesa.setY(280);


   	 	    camaraX = 0;
   	 	}
   	 	
   	 	
   	 	// PERDER VIDA SI CHOCA CON UN ENEMIGO LENTO:
   	 	for (int i = 0; i < enemigos.length; i++) {
   	 	    if (enemigos[i] != null) {
   	 	        if (enemigos[i].getArea().intersects(this.princesa.getArea())) {
   	 	            this.princesa.perderVida();
   	 	            enemigos[i] = null;
   	 	            break; 
   	 	        }
   	 	        if(proyectil != null) {
	   	 	        if (enemigos[i].getArea().intersects(this.proyectil.getArea())) {
		 	            enemigos[i] = null;
		 	            this.proyectil = null;
		 	            break; 
		 	        }
   	 	        }
   	 	    }
   	 	}
   	 	
   		// PERDER VIDA SI CHOCA CON UN ENEMIGO RAPIDO:
   	 	for (int i = 0; i < enemigosRapidos.length; i++) {
   	 	    if (enemigosRapidos[i] != null) {
   	 	        if (enemigosRapidos[i].getArea().intersects(this.princesa.getArea())) {
   	 	            this.princesa.perderVida();
   	 	            enemigosRapidos[i] = null;
   	 	            break; 
   	 	        }
   	 	        if(proyectil != null) {
	   	 	        if (enemigosRapidos[i].getArea().intersects(this.proyectil.getArea())) {
		 	            enemigosRapidos[i] = null;
		 	            this.proyectil = null;
		 	            break; 
		 	        }
	 	        }
   	 	    }
   	 	}
   	 	
   	 	// PERDER VIDA SI CHOCA CON UN ENEMIGO TERRESTRE:
   	 	for (int i = 0; i < enemigosRapidos.length; i++) {
   	 	    if (enemigosTerrestres[i] != null) {
   	 	        if (enemigosTerrestres[i].getArea().intersects(this.princesa.getArea())) {
   	 	            this.princesa.perderVida();
   	 	            enemigosTerrestres[i] = null;
   	 	            break; 
   	 	        }
   	 	        if(proyectil != null) {
	   	 	        if (enemigosTerrestres[i].getArea().intersects(this.proyectil.getArea())) {
		 	            enemigosTerrestres[i] = null;
		 	            this.proyectil = null;
		 	            break; 
		 	        }
	 	        }
   	 	    }
   	 	}
	 	
		//CREAR EL PREYOCTIL
		if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && proyectil == null) {
	
			    proyectil = new Proyectil(princesa.getX(), princesa.getY(), entorno.mouseX() + camaraX, entorno.mouseY());
			}
	
		if (proyectil != null) {

		    proyectil.mover();
		    
		    for(Bloques[] fila : bloques) {
		    	for(Bloques columna : fila) {
		    		if( columna != null) {
			    		if(proyectil.getArea().intersects(columna.getArea())) {
			    			proyectil = null;
			    			break;
			    		}
		    		}
		    	}
		    	
		    	if(proyectil == null) {
	    			break;
	    		}
		    }
		    
		    
		}
   	 	
	    
        // CAMARA QUE SIGUE
        

        camaraX = this.princesa.getX() - 400;

        if (camaraX < 0) {

            camaraX = 0;
        }

        
        // DIBUJAR ISLAS
        

        for (Bloques[] fila : bloques) {
        	for(Bloques columna : fila) {
        		if(columna != null) {
        			int xCentro = columna.getX() + (columna.getAncho()/2) - camaraX;
        			int yCentro = columna.getY() + (columna.getAlto()/2);
        			
        			columna.dibujar(entorno, xCentro, yCentro);
        		}
        	}
        }
            
        
        
        
        //DIBUJAR CASTILLO
        entorno.dibujarRectangulo(
        	    castilloX - camaraX,
        	    castilloY,
        	    80,
        	    120,
        	    0,
        	    Color.GRAY
        	);
        
        

        
        // DIBUJAR JUGADOR
       
        this.princesa.dibujarPrincesa(entorno, camaraX);

        entorno.escribirTexto(
        	    "Vidas: " + princesa.getVidas(),
        	    30,
        	    30
        	);
    

       //DIBUJAR PROYECTIL
        
        if(proyectil != null){
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
