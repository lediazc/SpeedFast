package model;

public class Pedido{

    private int idPedido;
    private String direccionEntrega;
    private String tipoPedido;

    /**
     * Constructor que permite crear un Pedido con todos sus datos.
     * @param idPedido Número para identidicar la solicitud.
     * @param direccionEntrega Ubicación de entrega de la solicitud.
     * @param tipoPedido tipo de solicitud.
     */
    public Pedido(int idPedido, String direccionEntrega, String tipoPedido){

        setIdPedido(idPedido);
        setDireccionEntrega(direccionEntrega);
        setTipoPedido(tipoPedido);
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
    public String getTipoPedido() {

        return tipoPedido;
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
    public void setTipoPedido(String tipoPedido) {
        if(tipoPedido == null){
            this.tipoPedido = "";
            return;
        }

        this.tipoPedido = tipoPedido.trim();
    }

    // Métodos ----------------------------

    public String mostrarDatosPedido() {
        return
                "Número de orden: " + idPedido + "\n"
                + "Dirección: " + direccionEntrega + "\n"
                + "Tipo: " + tipoPedido;
    }

    public String asignarRepartidor(){
        return "Asignando repartidor genérico..." + "\n" ;
    }
    public String asignarRepartidor(String nombreRepartidor){
        return "Pedido asignado a " + nombreRepartidor + "\n" ;
    }

}
