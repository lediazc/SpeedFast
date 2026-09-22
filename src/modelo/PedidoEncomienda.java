package modelo;

public class PedidoEncomienda extends Pedido {

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     *
     * @param idPedido         Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param distanciaKm       tipo de solicitud.
     */
    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm, EstadoPedido.PENDIENTE);
        setPrioridad(PrioridadPedido.MEDIA);

    }

    // Métodos ----------------------------

    @Override
    public String asignarRepartidor() {
        return asignarRepartidor("Repartidor Anónimo");
    }
    @Override
    public String asignarRepartidor(String nombreRepartidor){
        setRepartidorAsignado(nombreRepartidor);
        return
                "[Pedido Encomienda]"                                                                       + "\n" +
                        toString()                                                                          + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Validando peso y embalaje... OK"                                                         + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" ;
    }
}
