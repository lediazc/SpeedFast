package app;

import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

public class Main {

    public static void main(String[] args) {

        PedidoComida pedidoComida = new PedidoComida(101, "Av. Providencia 1234", 12.0);
        PedidoComida pedidoComidaDos = new PedidoComida(102, "Av. Providencia 4321", 11.6);

        PedidoEncomienda pedidoEncomienda = new PedidoEncomienda(103, "Los Alerces 456", 5.0);
        PedidoEncomienda pedidoEncomiendaDos = new PedidoEncomienda(104, "Los Alerces 897", 5.9);

        PedidoExpress pedidoExpress = new PedidoExpress(105, "Av. Las Condes 7890", 30.5);
        PedidoExpress pedidoExpressDos = new PedidoExpress(106, "Av. Las Condes 2112", 24);

        // Pedido de comida
        pedidoComida.reservar();
        System.out.println(pedidoComida.asignarRepartidor("Carlos"));
        pedidoComida.despachar();

        pedidoComidaDos.reservar();
        System.out.println(pedidoComidaDos.asignarRepartidor());
        pedidoComidaDos.cancelar();

        // Pedido de encomienda
        pedidoEncomienda.reservar();
        System.out.println(pedidoEncomienda.asignarRepartidor("Fernanda"));
        pedidoEncomienda.despachar();

        pedidoEncomiendaDos.reservar();
        System.out.println(pedidoEncomiendaDos.asignarRepartidor());
        pedidoEncomiendaDos.cancelar();

        // Pedido express
        pedidoExpress.reservar();
        System.out.println(pedidoExpress.asignarRepartidor("Diego"));
        pedidoExpress.despachar();

        pedidoExpressDos.reservar();
        System.out.println(pedidoExpressDos.asignarRepartidor());
        pedidoExpressDos.cancelar();


        // Ver historial
        pedidoComida.verHistorial();
        pedidoEncomienda.verHistorial();
        pedidoExpress.verHistorial();
    }
}