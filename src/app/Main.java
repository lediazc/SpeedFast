package app;

import model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        PedidoComida pedidoComida = new PedidoComida(101, "Av. Providencia 1234", 12.0);
        PedidoComida pedidoComidaDos = new PedidoComida(102, "Av. Providencia 4321", 11.6);

        PedidoEncomienda pedidoEncomienda = new PedidoEncomienda(103, "Los Alerces 456", 5.0);
        PedidoEncomienda pedidoEncomiendaDos = new PedidoEncomienda(104, "Los Alerces 897", 5.9);

        PedidoExpress pedidoExpress = new PedidoExpress(105, "Av. Las Condes 7890", 30.5);
        PedidoExpress pedidoExpressDos = new PedidoExpress(106, "Av. Las Condes 2112", 24);

        Repartidor repartidorUno = new Repartidor("Camila", zonaDeCarga);
        Repartidor repartidorDos = new Repartidor("Luis", zonaDeCarga);
        Repartidor repartidorTres = new Repartidor("Diego", zonaDeCarga);

        zonaDeCarga.agregarPedido(pedidoComida);
        zonaDeCarga.agregarPedido(pedidoComidaDos);
        zonaDeCarga.agregarPedido(pedidoEncomienda);
        zonaDeCarga.agregarPedido(pedidoEncomiendaDos);
        zonaDeCarga.agregarPedido(pedidoExpress);
        zonaDeCarga.agregarPedido(pedidoExpressDos);

        executor.execute(repartidorUno);
        executor.execute(repartidorDos);
        executor.execute(repartidorTres);
        zonaDeCarga.cerrarZona();


        executor.shutdown();

        try {
            if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("\n" + "..." + "\n");
                System.out.println("[Zona de carga vacía]");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}