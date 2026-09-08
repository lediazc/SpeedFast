package model;

import java.util.ArrayList;

public class Repartidor implements Runnable{

    private String nombreRepartidor;
    private ArrayList<Pedido> listaPedidos = new ArrayList<>();

    public Repartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    //Getters ----------------------------
    /**
     * Obtiene el id del pedido.
     *
     * @return id del pedido.
     */
    public String getNombreRepartidor() {

        return nombreRepartidor;
    }

    public void agregarPedido(Pedido pedido) {
        listaPedidos.add(pedido);
    }

    @Override
    public void run() {
        for (Pedido pedido : listaPedidos) {

            System.out.println("[Repartidor: " + nombreRepartidor + "] Entregando " + pedido.getClass().getSimpleName() + " #" + pedido.getIdPedido() + "...");

            try {
                int tiempoEspera = (int) (Math.random() * 3000) + 1000;
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException e) {
                System.out.println("La entrega fue interrumpida.");
                Thread.currentThread().interrupt();
                break;
            }


            System.out.println("[Repartidor: " + nombreRepartidor + "] Pedido #" + pedido.getIdPedido() + " entregado.");
        }
    }
}
