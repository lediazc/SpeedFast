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
        agregarAlHistorial("Encomienda reservada correctamente."+ "\n" +
                "→ En breve se le asignará un repartidor"
        );

    }

    @Override
    public void despachar() {
        //System.out.println("Encomienda despachada correctamente." + "\n" );

        agregarAlHistorial("PedidoEncomienda #" + getIdPedido() + " - despachado por " + getRepartidorAsignado());

    }

    @Override
    public void cancelar() {
        agregarAlHistorial("Cancelando Pedido Encomienda #" + getIdPedido() + "..." + "\n" +
                "→ Pedido cancelado exitosamente."
        );
    }

    @Override
    protected int calcularTiempoEntrega(double distanciaKm){
        int tarifaEstandar = 20;
        double tarifaDinámica = 1.5 * distanciaKm;

        return (int) Math.round(tarifaEstandar + tarifaDinámica);
    }

    @Override
    protected String getTituloHistorial() {
        return "Historial de encomiendas #" + getIdPedido() + ":";
    }

    @Override
    public String asignarRepartidor() {
        return asignarRepartidor("Repartidor Anónimo");
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
