package modelo;

public class PedidoComida extends Pedido {

    /**
     * Constructor que permite crear un modelo.Pedido con todos sus datos.
     *
     * @param idPedido         Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param distanciaKm       tipo de solicitud.
     */
    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm, EstadoPedido.PENDIENTE);
        setPrioridad(PrioridadPedido.BAJA);

    }

    // Métodos ----------------------------
    @Override
    public String asignarRepartidor() {
        return asignarRepartidor("modelo.Repartidor Anónimo");
    }

    @Override
    public String asignarRepartidor(String nombreRepartidor){

        setRepartidorAsignado(nombreRepartidor);
        return
                "[modelo.Pedido Comida]"                                                                           + "\n" +
                        toString()                                                                          + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Verificando mochila térmica... OK"                                                       + "\n" +
                "→ modelo.Pedido asignado a " + nombreRepartidor                                                   + "\n" ;
    }

}
