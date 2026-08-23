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
    protected String calcularTiempoEntrega(double distanciaKm){
        int tiempoPorKilometroSegundos = 0;
        int tiempoEstandarSegundos = 10;

        if(distanciaKm > 5) {
            tiempoPorKilometroSegundos = 5;
        }

        int segundosTotales = tiempoEstandarSegundos + tiempoPorKilometroSegundos;

        int minutos = segundosTotales / 60;
        int segundos = segundosTotales % 60;

        return minutos + " minutos y " + segundos + " segundos";
    }



    @Override
    public String asignarRepartidor() {
        return
                "Buscando repartidor cercano..."             + "\n" +
                mostrarDatosPedido()                         + "\n" ;
    }

    @Override
    public String asignarRepartidor(String nombreRepartidor){
        return
                "[Pedido Express]"                                                                          + "\n" +
                mostrarDatosPedido()                                                                        + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Repartidor más cercano con disponibilidad inmediata encontrado."                         + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" +
                "→ Tiempo estimado de entrega: " + calcularTiempoEntrega(getDistanciaKm())    + "\n" ;

    }
}
