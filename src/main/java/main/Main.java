package main;

import vista.VentanaPrincipal;
import controlador.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

import controlador.PedidoDAO;
import modelo.Pedido;
import modelo.PedidoComida;
import controlador.EntregaDAO;
import modelo.Entrega;

import java.time.LocalDate;
import java.time.LocalTime;

import controlador.RepartidorDAO;
import modelo.Repartidor;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        new VentanaPrincipal();

        /*
        modelo.ZonaDeCarga zonaDeCarga = new modelo.ZonaDeCarga();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        modelo.PedidoComida pedidoComida = new modelo.PedidoComida(101, "Av. Providencia 1234", 12.0);
        modelo.PedidoComida pedidoComidaDos = new modelo.PedidoComida(102, "Av. Providencia 4321", 11.6);

        modelo.PedidoEncomienda pedidoEncomienda = new modelo.PedidoEncomienda(103, "Los Alerces 456", 5.0);
        modelo.PedidoEncomienda pedidoEncomiendaDos = new modelo.PedidoEncomienda(104, "Los Alerces 897", 5.9);

        modelo.PedidoExpress pedidoExpress = new modelo.PedidoExpress(105, "Av. Las Condes 7890", 30.5);
        modelo.PedidoExpress pedidoExpressDos = new modelo.PedidoExpress(106, "Av. Las Condes 2112", 24);

        modelo.Repartidor repartidorUno = new modelo.Repartidor("Camila", zonaDeCarga);
        modelo.Repartidor repartidorDos = new modelo.Repartidor("Luis", zonaDeCarga);
        modelo.Repartidor repartidorTres = new modelo.Repartidor("Diego", zonaDeCarga);

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
                System.out.println("\n" + "Todos los pedidos han sido entregados correctamente");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }*/
    }
}