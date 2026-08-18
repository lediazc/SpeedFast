package app;

import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

public class Main {

    public static void main(String[] args) {

        Pedido pedidoComida = new PedidoComida(101,"Av. Providencia 1234","Comida");
        Pedido pedidoEncomienda = new PedidoEncomienda(102,"Los Alerces 456","Encomienda");
        Pedido pedidoExpress = new PedidoExpress(103,"Av. Las Condes 7890","Compra Express");

        // Pedido de comida
        System.out.println(pedidoComida.asignarRepartidor());
        System.out.println(pedidoComida.asignarRepartidor("Carlos"));

        // Pedido de encomienda
        System.out.println(pedidoEncomienda.asignarRepartidor());
        System.out.println(pedidoEncomienda.asignarRepartidor("Fernanda"));

        // Pedido express
        System.out.println(pedidoExpress.asignarRepartidor());
        System.out.println(pedidoExpress.asignarRepartidor("Diego"));
    }
}