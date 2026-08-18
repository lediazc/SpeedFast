package model;

public class PedidoComida extends Pedido{

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     *
     * @param idPedido         Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param tipoPedido       tipo de solicitud.
     */
    public PedidoComida(int idPedido, String direccionEntrega, String tipoPedido) {
        super(idPedido, direccionEntrega, tipoPedido);

    }

    // Métodos ----------------------------
    @Override
    public String asignarRepartidor() {
        return
                "Buscando repartidor con mochila térmica..." + "\n" +
                mostrarDatosPedido()                         + "\n" ;
    }

    @Override
    public String asignarRepartidor(String nombreRepartidor){
        return
                "[Pedido Comida]"                           + "\n" +
                mostrarDatosPedido()                        + "\n" +
                "Asignando repartidor..."                   + "\n" +
                "→ Verificando mochila térmica... OK"       + "\n" +
                "→ Pedido asignado a " + nombreRepartidor   + "\n" ;
    }

}
