package model;

public class PedidoEncomienda extends Pedido implements Reservable, Despachable, Cancelable{

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     *
     * @param idPedido         Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param distanciaKm       tipo de solicitud.
     */
    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);

    }

    // Métodos ----------------------------
    @Override
    public void reservar() {
        System.out.println("Encomienda reservada correctamente."+ "\n" +
                "→ En breve se le asignará un repartidor" + "\n"
        );

    }

    @Override
    public void despachar() {
        System.out.println("Encomienda despachada correctamente." + "\n" );

        agregarAlHistorial("PedidoEncomienda #" + getIdPedido() + " - entregado por " + getRepartidorAsignado());

    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando Pedido Encomienda #" + getIdPedido() + "..." + "\n" +
                "→ Pedido cancelado exitosamente." + "\n"
        );
    }

    @Override
    protected int calcularTiempoEntrega(double distanciaKm){
        int tarifaEstandar = 20;
        double tarifaDinámica = 1.5 * distanciaKm;

        return (int) Math.round(tarifaEstandar + tarifaDinámica);
    }

    @Override
    public String asignarRepartidor() {
        String nombreRepartidor = "Repartidor Anónimo";
        setRepartidorAsignado(nombreRepartidor);
        return
                "[Pedido Encomienda]"                                                                       + "\n" +
                    mostrarResumen()                                                                        + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Validando peso y embalaje... OK"                                                         + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" +
                "→ Tiempo estimado de entrega: " + calcularTiempoEntrega(getDistanciaKm()) + " minutos"     + "\n" ;
    }
    @Override
    public String asignarRepartidor(String nombreRepartidor){
        setRepartidorAsignado(nombreRepartidor);
        return
                "[Pedido Encomienda]"                                                                       + "\n" +
                    mostrarResumen()                                                                        + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Validando peso y embalaje... OK"                                                         + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" +
                "→ Tiempo estimado de entrega: " + calcularTiempoEntrega(getDistanciaKm()) + " minutos"     + "\n" ;
    }
}
