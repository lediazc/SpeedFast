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
    protected int calcularTiempoEntrega(double distanciaKm){
        int tarifaEstandar = 20;
        double tarifaDinámica = 1.5 * distanciaKm;

        return (int) Math.round(tarifaEstandar + tarifaDinámica);
    }

    @Override
    public String asignarRepartidor() {
        return
                "Buscando repartidor de encomiendas..."      + "\n" +
                    mostrarResumen()                         + "\n" ;
    }
    @Override
    public String asignarRepartidor(String nombreRepartidor){
        return
                "[Pedido Encomienda]"                                                                       + "\n" +
                    mostrarResumen()                                                                        + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Validando peso y embalaje... OK"                                                         + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" +
                "→ Tiempo estimado de entrega: " + calcularTiempoEntrega(getDistanciaKm()) + " minutos"     + "\n" ;
    }
}
