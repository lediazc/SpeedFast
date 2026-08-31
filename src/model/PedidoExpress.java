package model;

public class PedidoExpress extends Pedido{

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     *
     * @param idPedido         Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param distanciaKm       tipo de solicitud.
     */
    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);

    }

    // Métodos ----------------------------
    @Override
    protected int calcularTiempoEntrega(double distanciaKm){

        double tarifaDinámica = 0;
        int tarifaEstandar = 10;

        if(distanciaKm > 5) {
            tarifaDinámica = 5;

        }
        return (int) Math.round(tarifaEstandar + tarifaDinámica);
    }



    @Override
    public String asignarRepartidor() {
        return
                "Buscando repartidor cercano..."             + "\n" +
                    mostrarResumen()                         + "\n" ;
    }

    @Override
    public String asignarRepartidor(String nombreRepartidor){
        return
                "[Pedido Express]"                                                                          + "\n" +
                    mostrarResumen()                                                                        + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Repartidor más cercano con disponibilidad inmediata encontrado."                         + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" +
                "→ Tiempo estimado de entrega: " + calcularTiempoEntrega(getDistanciaKm())  + " minutos"    + "\n" ;

    }
}
