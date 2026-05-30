package juego;

public class vidas {

    private int cantidad;

    public vidas(int cantidadInicial) {
        this.cantidad = cantidadInicial;
    }

    public void perderVida() {
        this.cantidad--;
    }

    public int getCantidad() {
        return this.cantidad;
    }
}
