package model;

public class PedidoEncomienda extends Pedido{

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     *
     * @param idPedido         Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param distanciaKm       tipo de solicitud.
     */
    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);

    }

    // Métodos ----------------------------
    @Override
    protected String calcularTiempoEntrega(double distanciaKm) {
        int tiempoEstandarSegundos = 20 * 60;
        int tiempoDinamicoKilometro = 1 * 60 + 30;

        int segundosTotales = tiempoEstandarSegundos + (int) Math.round(tiempoDinamicoKilometro * distanciaKm);

        int minutos = segundosTotales / 60;
        int segundos = segundosTotales % 60;

        return minutos + " minutos y " + segundos + " segundos";
    }

    @Override
    public String asignarRepartidor() {
        return
                "Buscando repartidor de encomiendas..."      + "\n" +
                mostrarDatosPedido()                         + "\n" ;
    }
    @Override
    public String asignarRepartidor(String nombreRepartidor){
        return
                "[Pedido Encomienda]"                                                                       + "\n" +
                mostrarDatosPedido()                                                                        + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Validando peso y embalaje... OK"                                                         + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" +
                "→ Tiempo estimado de entrega: " + calcularTiempoEntrega(getDistanciaKm()) + "\n" ;
    }
}
