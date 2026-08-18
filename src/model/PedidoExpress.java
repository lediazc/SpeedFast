package model;

public class PedidoExpress extends Pedido{

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     *
     * @param idPedido         Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param tipoPedido       tipo de solicitud.
     */
    public PedidoExpress(int idPedido, String direccionEntrega, String tipoPedido) {
        super(idPedido, direccionEntrega, tipoPedido);

    }

    // Métodos ----------------------------
    @Override
    public String asignarRepartidor() {
        return
                "Buscando repartidor cercano..."             + "\n" +
                mostrarDatosPedido()                         + "\n" ;
    }

    @Override
    public String asignarRepartidor(String nombreRepartidor){
        return
                "[Pedido Express]"                                                  + "\n" +
                mostrarDatosPedido()                                                + "\n" +
                "Asignando repartidor..."                                           + "\n" +
                "→ Repartidor más cercano con disponibilidad inmediata encontrado." + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                           + "\n" ;
    }
}
