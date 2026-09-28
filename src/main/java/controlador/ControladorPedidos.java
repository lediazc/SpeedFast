package controlador;

import modelo.Pedido;
import java.util.ArrayList;
import java.util.List;
import modelo.EstadoPedido;
import modelo.Repartidor;
import modelo.ZonaDeCarga;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControladorPedidos {


    private final List<Pedido> listaPedidos;
    private final PedidoDAO pedidoDAO;

    public ControladorPedidos() {
        listaPedidos = new ArrayList<>();
        pedidoDAO = new PedidoDAO();
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

        for (Pedido pedido : listaPedidos) {

            if (pedido.getEstado() == EstadoPedido.PENDIENTE) {
                zonaDeCarga.agregarPedido(pedido);
                existenPendientes = true;
            }
        }

        if (!existenPendientes) {
            return false;
        }

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Repartidor repartidorUno =  new Repartidor("Camila", zonaDeCarga);

        Repartidor repartidorDos =   new Repartidor("Luis", zonaDeCarga);

        Repartidor repartidorTres =  new Repartidor("Diego", zonaDeCarga);

        executor.execute(repartidorUno);
        executor.execute(repartidorDos);
        executor.execute(repartidorTres);

        zonaDeCarga.cerrarZona();

        executor.shutdown();

        return true;
    }
}