package controlador;

import modelo.Pedido;
import java.util.ArrayList;
import java.util.List;
import modelo.EstadoPedido;
import modelo.Repartidor;
import modelo.ZonaDeCarga;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControladorPedidos {


    private final List<Pedido> listaPedidos;
    private final PedidoDAO pedidoDAO;
    private final RepartidorDAO repartidorDAO;

    public ControladorPedidos() {
        listaPedidos = new ArrayList<>();
        pedidoDAO = new PedidoDAO();
        repartidorDAO = new RepartidorDAO();
    }

    public boolean agregarPedido(Pedido pedido) {

        if (pedido == null) {
            return false;
        }

        if (existePedido(pedido.getIdPedido())) {
            return false;
        }

        boolean guardado = pedidoDAO.guardar(pedido);

        if (!guardado) {
            return false;
        }

        listaPedidos.add(pedido);

        return true;
    }

    public boolean existePedido(int idPedido) {

        for (Pedido pedido : listaPedidos) {

            if (pedido.getIdPedido() == idPedido) {
                return true;
            }
        }

        return false;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public int generarNuevoId() {
        return listaPedidos.size() + 1;
    }

    public boolean iniciarEntregas() {

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        boolean existenPendientes = false;

        for (Object[] fila : pedidoDAO.listarPendientes()) {

            int id = (int) fila[0];
            String direccion = (String) fila[1];
            String tipo = (String) fila[2];

            Pedido pedido;

            switch (tipo) {

                case "COMIDA":
                    pedido = new PedidoComida(id, direccion, 0);
                    break;

                case "ENCOMIENDA":
                    pedido = new PedidoEncomienda(id, direccion, 0);
                    break;

                case "EXPRESS":
                    pedido = new PedidoExpress(id, direccion, 0);
                    break;

                default:
                    continue;
            }

            zonaDeCarga.agregarPedido(pedido);
            existenPendientes = true;
        }

        if (!existenPendientes) {
            return false;
        }

        List<Repartidor> repartidores = repartidorDAO.listarTodos(zonaDeCarga);

        if (repartidores.isEmpty()) {
            return false;
        }

        ExecutorService executor =
                Executors.newFixedThreadPool(repartidores.size());

        for (Repartidor repartidor : repartidores) {
            executor.execute(repartidor);
        }

        zonaDeCarga.cerrarZona();

        executor.shutdown();

        return true;
    }
}