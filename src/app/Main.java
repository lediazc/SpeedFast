package app;

import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;
import model.Repartidor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        PedidoComida pedidoComida = new PedidoComida(101, "Av. Providencia 1234", 12.0);
        PedidoComida pedidoComidaDos = new PedidoComida(102, "Av. Providencia 4321", 11.6);
        PedidoComida pedidoComidaTres = new PedidoComida(107, "Av. Irarrázaval 2020", 9.4);

        PedidoEncomienda pedidoEncomienda = new PedidoEncomienda(103, "Los Alerces 456", 5.0);
        PedidoEncomienda pedidoEncomiendaDos = new PedidoEncomienda(104, "Los Alerces 897", 5.9);
        PedidoEncomienda pedidoEncomiendaTres = new PedidoEncomienda(108, "Av. Grecia 1540", 7.2);

        PedidoExpress pedidoExpress = new PedidoExpress(105, "Av. Las Condes 7890", 30.5);
        PedidoExpress pedidoExpressDos = new PedidoExpress(106, "Av. Las Condes 2112", 24);
        PedidoExpress pedidoExpressTres = new PedidoExpress(109, "Av. Apoquindo 4500", 18.3);


        Repartidor repartidorUno = new Repartidor("Camila");
        Repartidor repartidorDos = new Repartidor("Luis");
        Repartidor repartidorTres = new Repartidor("Diego");

        // Pedido de comida
        pedidoComida.reservar();
        pedidoComida.asignarRepartidor(repartidorUno.getNombreRepartidor());
        pedidoComida.despachar();

        pedidoComidaDos.reservar();
        pedidoComidaDos.asignarRepartidor();
        pedidoComidaDos.cancelar();

        pedidoComidaTres.reservar();
        pedidoComidaTres.asignarRepartidor(repartidorUno.getNombreRepartidor());
        pedidoComidaTres.despachar();

        // Pedido de encomienda
        pedidoEncomienda.reservar();
        pedidoEncomienda.asignarRepartidor(repartidorDos.getNombreRepartidor());
        pedidoEncomienda.despachar();

        pedidoEncomiendaDos.reservar();
        pedidoEncomiendaDos.asignarRepartidor();
        pedidoEncomiendaDos.cancelar();

        pedidoEncomiendaTres.reservar();
        pedidoEncomiendaTres.asignarRepartidor(repartidorDos.getNombreRepartidor());
        pedidoEncomiendaTres.despachar();

        // Pedido express
        pedidoExpress.reservar();
        pedidoExpress.asignarRepartidor(repartidorTres.getNombreRepartidor());
        pedidoExpress.despachar();

        pedidoExpressDos.reservar();
        pedidoExpressDos.asignarRepartidor();
        pedidoExpressDos.cancelar();

        pedidoExpressTres.reservar();
        pedidoExpressTres.asignarRepartidor(repartidorTres.getNombreRepartidor());
        pedidoExpressTres.despachar();

        // Ver historial
        pedidoComida.verHistorial();
        pedidoComidaDos.verHistorial();
        pedidoComidaTres.verHistorial();

        pedidoEncomienda.verHistorial();
        pedidoEncomiendaDos.verHistorial();
        pedidoEncomiendaTres.verHistorial();

        pedidoExpress.verHistorial();
        pedidoExpressDos.verHistorial();
        pedidoExpressTres.verHistorial();

        repartidorUno.agregarPedido(pedidoComida);
        repartidorUno.agregarPedido(pedidoComidaTres);

        repartidorDos.agregarPedido(pedidoEncomienda);
        repartidorDos.agregarPedido(pedidoEncomiendaTres);

        repartidorTres.agregarPedido(pedidoExpress);
        repartidorTres.agregarPedido(pedidoExpressTres);

        executor.execute(repartidorUno);
        executor.execute(repartidorDos);
        executor.execute(repartidorTres);

        executor.shutdown();
    }
}