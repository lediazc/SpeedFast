package modelo;

public class PedidoExpress extends Pedido {

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     *
     * @param idPedido         Número para identificar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param distanciaKm       tipo de solicitud.
     */
    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm, EstadoPedido.PENDIENTE);
        setPrioridad(PrioridadPedido.ALTA);

    }

    // Métodos ----------------------------


    /**
     * Asiigna repartidor anónimo a un pedido express cuando no se especifica nombre de repartidor
     * Utiliza la versión sobrecargada de asignarRepartidor(String)
     *
     * @return mensaje de confirmación de asignación de repartidor con Repartidor anónimo
     */
    @Override
    public String asignarRepartidor() {
        return asignarRepartidor("Repartidor Anónimo");
    }

    /**
     * Asigna un repartidor específico al pedido express y retorna un mensaje con el resumen de la asignación
     *
     * @param nombreRepartidor nombre de repartidor que será asignado
     * @return mensaje de confirmación de asignación de repartidor
     */
    @Override
    public String asignarRepartidor(String nombreRepartidor){
        setRepartidorAsignado(nombreRepartidor);
        return
                "[Pedido Express]"                                                                          + "\n" +
                    toString()                                                                              + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Repartidor más cercano con disponibilidad inmediata encontrado."                         + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" ;

    }
}
