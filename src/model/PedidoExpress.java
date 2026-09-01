package model;

public class PedidoExpress extends Pedido implements Reservable, Despachable, Cancelable{

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
    public void reservar() {
        System.out.println("Pedido express reservado correctamente."+ "\n" +
                "→ Tu pedido tiene prioridad para localizar repartidor" + "\n"
        );

    }

    @Override
    public void despachar() {
        System.out.println("Pedido express reservado y despachado con prioridad." + "\n" );

        agregarAlHistorial("PedidoExpress #" + getIdPedido() + " - entregado por " + getRepartidorAsignado());

    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando Pedido Express #" + getIdPedido() + "..." + "\n" +
                "→ Pedido cancelado exitosamente." + "\n"
        );
    }

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
        String nombreRepartidor = "Repartidor Anónimo";
        setRepartidorAsignado(nombreRepartidor);
        return
                "[Pedido Express]"                                                                          + "\n" +
                    mostrarResumen()                                                                        + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Repartidor más cercano con disponibilidad inmediata encontrado."                         + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" +
                "→ Tiempo estimado de entrega: " + calcularTiempoEntrega(getDistanciaKm())  + " minutos"    + "\n" ;
    }

    @Override
    public String asignarRepartidor(String nombreRepartidor){
        setRepartidorAsignado(nombreRepartidor);
        return
                "[Pedido Express]"                                                                          + "\n" +
                    mostrarResumen()                                                                        + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Repartidor más cercano con disponibilidad inmediata encontrado."                         + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" +
                "→ Tiempo estimado de entrega: " + calcularTiempoEntrega(getDistanciaKm())  + " minutos"    + "\n" ;

    }
}
