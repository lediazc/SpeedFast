package model;

public class PedidoEncomienda extends Pedido{

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     *
     * @param idPedido         Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param tipoPedido       tipo de solicitud.
     */
    public PedidoEncomienda(int idPedido, String direccionEntrega, String tipoPedido) {
        super(idPedido, direccionEntrega, tipoPedido);

    }

    // Métodos ----------------------------

    @Override
    public String asignarRepartidor() {
        return
                "Buscando repartidor de encomiendas..."      + "\n" +
                mostrarDatosPedido()                         + "\n" ;
    }
    @Override
    public String asignarRepartidor(String nombreRepartidor){
        return
                "[Pedido Encomienda]"                       + "\n" +
                mostrarDatosPedido()                        + "\n" +
                "Asignando repartidor..."                   + "\n" +
                "→ Validando peso y embalaje... OK"         + "\n" +
                "→ Pedido asignado a " + nombreRepartidor   + "\n" ;
    }
}
