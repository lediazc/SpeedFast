package model;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ZonaDeCarga {
    private final int capacidadMaxima = 10;
    private final BlockingQueue<Pedido> colaPedidos = new PriorityBlockingQueue<>();


    public ZonaDeCarga(){

    }
    /**
     * Agrega un pedido en estado EN_REPARTO a la zona de carga
     * Si no existen pedidos, el hilo queda esperando
     * @param p Es un pedido
     * @throws InterruptedException frente a interrupción
     */
    public synchronized void agregarPedido(Pedido p) {

        if (colaPedidos.size() >= capacidadMaxima) {
            return;
        }

        p.setEstado(EstadoPedido.PENDIENTE);
        colaPedidos.add(p);
        notifyAll();
    }

    /**
     *Retira el siguiente pedido disponible en la zona de carga
     * Si no existen pedidos, el hilo queda esperando
     * @return Pedido retirado de la zona de carga
     * @throws InterruptedException frente a interrupción
     */
    public synchronized Pedido retirarPedido() throws InterruptedException{
        while(colaPedidos.isEmpty()) {
            System.out.println("No hay pedido en la zona de carga ...");
            wait();
        }

        return colaPedidos.poll();
    }


}
