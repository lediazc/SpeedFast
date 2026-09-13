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
        super(idPedido, direccionEntrega, distanciaKm, EstadoPedido.PENDIENTE);
        setPrioridad(PrioridadPedido.ALTA);

    }

    // Métodos ----------------------------

    /**
     * Reserva el pedido Express agregando información al historial
     */
    @Override
    public void reservar() {
        agregarAlHistorial("Pedido express reservado correctamente."+ "\n" +
                "→ Tu pedido tiene prioridad para localizar repartidor"
        );

    }

    /**
     * Despacha el pedido Express agregando información al historial
     */
    @Override
    public void despachar() {
        //System.out.println("Pedido express despachado con prioridad." + "\n" );

        agregarAlHistorial("PedidoExpress #" + getIdPedido() + " - despachado por " + getRepartidorAsignado());

    }

    /**
     * Cancela el pedido Express agregando información al historial
     */
    @Override
    public void cancelar() {
        agregarAlHistorial("Cancelando Pedido Express #" + getIdPedido() + "..." + "\n" +
                "→ Pedido cancelado exitosamente."
        );
    }

    /**
     * Calcula el tiempo de entrega sgún la distancia del pedido.
     * Presenta condiional para pedidos por sobre los 5 km
     *
     * @param distanciaKm distancia del pedido en kilómetros
     * @return tiempo estimado de entrega en minutos
     */
    @Override
    protected int calcularTiempoEntrega(double distanciaKm){

        double tarifaDinámica = 0;
        int tarifaEstandar = 10;

        if(distanciaKm > 5) {
            tarifaDinámica = 5;

        }
        return (int) Math.round(tarifaEstandar + tarifaDinámica);
    }


    /**
     * Sobreescribe metodo heredado para configurarlo a un historial de pedido express
     *
     * @return título del historial del pedido express
     */
    @Override
    protected String getTituloHistorial() {
        return "Historial de pedidos express #" + getIdPedido() + ":";
    }

    /**
     * Asiigna repartidor anónimo a un pedido express cuando no se especifica nombre de repartidor
     * Utiliza la versión sobrecargada de asignarRepartidor(String)
     *
     * @return mensaje de confirmación de asignación de repartidor con Repartidor anónimo
     */
    @Override
    public String asignarRepartidor() {
        return asignarRepartidor("Repartidor Anónimo");
    }

    /**
     * Asigna un repartidor específico al pedido express y retorna un mensaje con el resumen de la asignación
     *
     * @param nombreRepartidor nombre de repartidor que será asignado
     * @return mensaje de confirmación de asignación de repartidor
     */
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
