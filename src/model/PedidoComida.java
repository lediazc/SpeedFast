package model;

public class PedidoComida extends Pedido implements Reservable, Despachable, Cancelable{

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     *
     * @param idPedido         Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param distanciaKm       tipo de solicitud.
     */
    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);

    }

    // Métodos ----------------------------
    @Override
    public void reservar() {
        System.out.println("Pedido de comida reservado correctamente."+ "\n" +
                "→ En breve se le asignará un repartidor"+ "\n"
        );

    }

    @Override
    public void despachar() {
        System.out.println("Pedido de comida despachado correctamente." + "\n" );

        agregarAlHistorial("PedidoComida #" + getIdPedido() + " - entregado por " + getRepartidorAsignado());

    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando Pedido Comida #" + getIdPedido() + "..." + "\n" +
                        "→ Pedido cancelado exitosamente." + "\n"
        );
    }

    @Override
    protected int calcularTiempoEntrega(double distanciaKm) {
        int tarifaEstandar = 15;
        double tarifaDinámica = 2 * distanciaKm;

        return (int) Math.round(tarifaEstandar + tarifaDinámica);
    }

    @Override
    protected String getTituloHistorial() {
        return "Historial gastronómico:";
    }

    @Override
    public String asignarRepartidor() {
        return asignarRepartidor("Repartidor Anónimo");
    }

    @Override
    public String asignarRepartidor(String nombreRepartidor){

        setRepartidorAsignado(nombreRepartidor);
        return
                "[Pedido Comida]"                                                                           + "\n" +
                    mostrarResumen()                                                                        + "\n" +
                "Asignando repartidor..."                                                                   + "\n" +
                "→ Verificando mochila térmica... OK"                                                       + "\n" +
                "→ Pedido asignado a " + nombreRepartidor                                                   + "\n" +
                "→ Tiempo estimado de entrega: " + calcularTiempoEntrega(getDistanciaKm()) + " minutos"     + "\n" ;
    }

}
