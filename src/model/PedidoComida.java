package model;

public class PedidoComida extends Pedido{

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     *
     * @param idPedido         Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param distanciaKm       tipo de solicitud.
     */
    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);

    }

    // Métodos ----------------------------
    @Override
    protected String calcularTiempoEntrega(double distanciaKm) {
        int tiempoEstandarSegundos = 15 * 60;
        int tiempoDinamicoKilometro = 2 * 60;

        int segundosTotales = tiempoEstandarSegundos + (int) Math.round(tiempoDinamicoKilometro * distanciaKm);

        int minutos = segundosTotales / 60;
        int segundos = segundosTotales % 60;

        return minutos + " minutos y " + segundos + " segundos";
    }

    @Override
    public String asignarRepartidor() {
        return
                "Buscando repartidor con mochila térmica..." + "\n" +
                mostrarDatosPedido()                         + "\n" ;
    }

    @Override
    public String asignarRepartidor(String nombreRepartidor){
        return
                "[Pedido Comida]"                                                                           + "\n" +
                mostrarDatosPedido()                                                                        + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Verificando mochila térmica... OK"                                                       + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" +
                "→ Tiempo estimado de entrega: " + calcularTiempoEntrega(getDistanciaKm())  + "\n" ;
    }

}
