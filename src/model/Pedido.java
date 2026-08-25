package model;

public abstract class Pedido{

    private int idPedido;
    private String direccionEntrega;
    private double distanciaKm;

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     * @param idPedido Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param distanciaKm distancia de entrega.
     */
    public Pedido(int idPedido, String direccionEntrega, double distanciaKm){

        setIdPedido(idPedido);
        setDireccionEntrega(direccionEntrega);
        setDistanciaKm(distanciaKm);
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

    // Métodos ----------------------------

    public String mostrarDatosPedido() {
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
