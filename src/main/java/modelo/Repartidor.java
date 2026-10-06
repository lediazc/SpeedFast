package modelo;

import dao.PedidoDAO;
import dao.EntregaDAO;
import java.time.LocalDate;
import java.time.LocalTime;

public class Repartidor implements Runnable{

    private int idRepartidor;
    private String nombreRepartidor;
    private ZonaDeCarga zonaDeCarga;
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final EntregaDAO entregaDAO = new EntregaDAO();

    public Repartidor(int idRepartidor, String nombreRepartidor, ZonaDeCarga zonaDeCarga) {
        this.idRepartidor = idRepartidor;
        this.nombreRepartidor = nombreRepartidor;
        this.zonaDeCarga = zonaDeCarga;
    }

    //Getters ----------------------------
    /**
     * Obtiene el nombre del repartidor.
     *
     * @return nombreRepartidor.
     */
    public String getNombreRepartidor() {

        return nombreRepartidor;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }

    @Override
    public void run(){

        while (!Thread.currentThread().isInterrupted()) {
            try{
                Pedido pedido = zonaDeCarga.retirarPedido();
                if (pedido == null) {
                    break;
                }

                System.out.println("[modelo.Repartidor - " + nombreRepartidor + "] Retirando pedido #" + pedido.getIdPedido() + "...");

                pedido.setEstado(EstadoPedido.EN_REPARTO);
                pedidoDAO.actualizarEstado(pedido.getIdPedido(), EstadoPedido.EN_REPARTO);

                System.out.println("[modelo.Repartidor - " + nombreRepartidor + "] Estado: " + pedido.getEstado());
                System.out.println("[modelo.Repartidor - " + nombreRepartidor + "] Estado: " + pedido.getEstado());
                System.out.println("[modelo.Repartidor - " + nombreRepartidor + "] Entregando " + pedido.getClass().getSimpleName() + " #" + pedido.getIdPedido() + "...");
                int tiempoEspera = (int) (Math.random() * 3000) + 1000;
                Thread.sleep(tiempoEspera);

                pedido.setEstado(EstadoPedido.ENTREGADO);
                pedidoDAO.actualizarEstado(pedido.getIdPedido(), EstadoPedido.ENTREGADO);
                Entrega entrega = new Entrega(
                        pedido.getIdPedido(),
                        idRepartidor,
                        LocalDate.now(),
                        LocalTime.now()
                );

                entregaDAO.guardar(entrega);
                System.out.println("[modelo.Repartidor - " + nombreRepartidor + "] Estado: " + pedido.getEstado());

            } catch(InterruptedException e){
                System.out.println("La entrega fue interrumpida.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}