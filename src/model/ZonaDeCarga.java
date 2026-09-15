package model;

import java.util.concurrent.*;

public class ZonaDeCarga {
    private final int capacidadMaxima = 10;
    private final BlockingQueue<Pedido> colaPedidos = new PriorityBlockingQueue<>();
    private boolean cerrada = false;


    public ZonaDeCarga(){
        System.out.println("[Zona de carga inicializada]");

    }
    /**
     * Agrega un pedido en estado PENDIENTE a la zona de carga
     * Si no existen pedidos, el hilo queda esperando
     * @param p Es un pedido
     */
    public synchronized void agregarPedido(Pedido p) {

        if (colaPedidos.size() >= capacidadMaxima) {
            return;
        }

        p.setEstado(EstadoPedido.PENDIENTE);
        colaPedidos.add(p);
        System.out.println("Pedido #" + p.getIdPedido()  + " agregado. Destino: " + p.getDireccionEntrega());
        notifyAll();
    }

    /**
     *Retira el siguiente pedido disponible en la zona de carga
     * Si no existen pedidos, el hilo queda esperando
     * @return Pedido retirado de la zona de carga
     * @throws InterruptedException frente a interrupción
     */
    public synchronized Pedido retirarPedido() throws InterruptedException{

        while(colaPedidos.isEmpty() && !cerrada) {
            System.out.println("No hay pedido en la zona de carga ...");
            wait();
        }

        if (colaPedidos.isEmpty() && cerrada) {
            return null;


        }
        return colaPedidos.poll();
    }

    /**
     * Cierra la zona de carga.
     * Usa NotifyAll para notificar a los hilos que se encuentren esperando.
     */
    public synchronized void cerrarZona() {
        cerrada = true;
        notifyAll();
    }


}
