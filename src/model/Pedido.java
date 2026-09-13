package model;

import java.util.ArrayList;

public abstract class Pedido implements Rastreable, Comparable<Pedido> {

    private int idPedido;
    private String direccionEntrega;
    private double distanciaKm;
    private String repartidorAsignado;
    private EstadoPedido estado;
    private PrioridadPedido prioridad;

    private ArrayList<String> historial = new ArrayList<>();

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     * @param idPedido Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param distanciaKm distancia de entrega.
     */
    public Pedido(int idPedido, String direccionEntrega, double distanciaKm, EstadoPedido estado){

        setIdPedido(idPedido);
        setDireccionEntrega(direccionEntrega);
        setDistanciaKm(distanciaKm);
        setEstado(estado);
    }

    //Getters ----------------------------
    /**
     * Obtiene el id del pedido.
     *
     * @return id del pedido.
     */
    public int getIdPedido() {

        return idPedido;
    }

    /**
     * Obtiene la dirección de entrega.
     *
     * @return direccionEntrega.
     */
    public String getDireccionEntrega() {

        return direccionEntrega;
    }

    /**
     * Obtiene el tipo de pedido.
     *
     * @return tipoPedido.
     */
    public double getDistanciaKm() {

        return distanciaKm;
    }

    /**
     * Obtiene el repartidor asignado al pedido.
     *
     * @return repartidorAsignado.
     */
    public String getRepartidorAsignado() {
        return repartidorAsignado;
    }

    /**
     * Obtiene el estado del pedido.
     *
     * @return estado.
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    public PrioridadPedido getPrioridad() {
        return prioridad;
    }

    //Setters ----------------------------
    /**
     * Configura el id del pedido con un valor absoluto.
     */
    public void setIdPedido(int idPedido) {

        this.idPedido = Math.abs(idPedido);
    }

    /**
     * Configura dirección de entrega.
     */
    public void setDireccionEntrega(String direccionEntrega) {

        if(direccionEntrega == null){
            this.direccionEntrega = "";
            return;
        }

        this.direccionEntrega = direccionEntrega.trim();
    }

    /**
     * Configura tipo de entrega.
     */
    public void setDistanciaKm(double distanciaKm) {

        this.distanciaKm = distanciaKm;
    }

    protected void setRepartidorAsignado(String repartidorAsignado) {
        this.repartidorAsignado = repartidorAsignado;
    }

    public void setEstado(EstadoPedido estado){
        this.estado = estado;
    }

    public void setPrioridad(PrioridadPedido prioridad) {
        this.prioridad = prioridad;
    }

    // Métodos ----------------------------

    @Override
    public int compareTo(Pedido otroPedido) {
        return this.getPrioridad().compareTo(otroPedido.getPrioridad());
    }

    protected void agregarAlHistorial(String registro) {
        historial.add(registro);
    }

    protected ArrayList<String> getHistorial() {
        return historial;
    }


    protected abstract String getTituloHistorial();

    @Override
    public void verHistorial() {

        System.out.println("------------- ");
        System.out.println(getTituloHistorial());
        for (String registro : getHistorial()) {

            System.out.println("- " + registro);
        }
    }

    public String mostrarResumen() {
        return
                "Número de orden: " + idPedido + "\n"
                + "Dirección: " + direccionEntrega + "\n"
                + "Distancia: " + distanciaKm + " km.";
    }

    protected abstract int calcularTiempoEntrega(double distanciaKm);

    public String asignarRepartidor(){
        return "Asignando repartidor genérico..." + "\n" ;
    }
    public String asignarRepartidor(String nombreRepartidor){
        return "Pedido asignado a " + nombreRepartidor + "\n" ;
    }

}
