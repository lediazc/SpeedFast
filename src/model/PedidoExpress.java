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
        agregarAlHistorial("Pedido express reservado correctamente."+ "\n" +
                "→ Tu pedido tiene prioridad para localizar repartidor"
        );

    }

    @Override
    public void despachar() {
        //System.out.println("Pedido express despachado con prioridad." + "\n" );

        agregarAlHistorial("PedidoExpress #" + getIdPedido() + " - despachado por " + getRepartidorAsignado());

    }

    @Override
    public void cancelar() {
        agregarAlHistorial("Cancelando Pedido Express #" + getIdPedido() + "..." + "\n" +
                "→ Pedido cancelado exitosamente."
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
    protected String getTituloHistorial() {
        return "Historial de pedidos express #" + getIdPedido() + ":";
    }

    @Override
    public String asignarRepartidor() {
        return asignarRepartidor("Repartidor Anónimo");
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
