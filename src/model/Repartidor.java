package model;

public class Repartidor implements Runnable{

    private String nombreRepartidor;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombreRepartidor, ZonaDeCarga zonaDeCarga) {
        this.nombreRepartidor = nombreRepartidor;
        this.zonaDeCarga = zonaDeCarga;
    }

    //Getters ----------------------------
    /**
     * Obtiene el nombre del repartidor.
     *
     * @return nombreRepartidor.
     */
    public String getNombreRepartidor() {

        return nombreRepartidor;
    }

    @Override
    public void run(){

        while (!Thread.currentThread().isInterrupted()) {
            try{
                Pedido pedido = zonaDeCarga.retirarPedido();
                if (pedido == null) {
                    break;
                }

                System.out.println("[Repartidor - " + nombreRepartidor + "] Retirando pedido #" + pedido.getIdPedido() + "...");

                pedido.asignarRepartidor(nombreRepartidor);
                pedido.setEstado(EstadoPedido.EN_REPARTO);
                System.out.println("[Repartidor - " + nombreRepartidor + "] Estado: " + pedido.getEstado());
                System.out.println("[Repartidor - " + nombreRepartidor + "] Entregando " + pedido.getClass().getSimpleName() + " #" + pedido.getIdPedido() + "...");
                int tiempoEspera = (int) (Math.random() * 3000) + 1000;
                Thread.sleep(tiempoEspera);

                pedido.setEstado(EstadoPedido.ENTREGADO);
                System.out.println("[Repartidor - " + nombreRepartidor + "] Estado: " + pedido.getEstado());
                System.out.println("[Repartidor - " + nombreRepartidor + "] Pedido #" + pedido.getIdPedido() + " entregado.");

            } catch(InterruptedException e){
                System.out.println("La entrega fue interrumpida.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}