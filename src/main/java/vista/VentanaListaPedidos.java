package vista;

import controlador.ControladorPedidos;
import modelo.Pedido;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class VentanaListaPedidos extends JFrame  {


    private final ControladorPedidos controladorPedidos;

    private final DefaultTableModel modeloTabla =
            new DefaultTableModel(
                    new Object[]{"ID", "Dirección", "Distancia", "Tipo", "Estado"},
                    0
            );

    private final JTable tablaPedidos = new JTable(modeloTabla);

    public VentanaListaPedidos(ControladorPedidos controladorPedidos) {

        this.controladorPedidos = controladorPedidos;

        setSize(700, 500);
        setLocationRelativeTo(null);
        setTitle("Listado de pedidos");

        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        add(scrollPane);

        refrescarTabla();

        Timer timer = new Timer(250, e -> refrescarTabla());
        timer.start();

        setVisible(true);
    }

    public void refrescarTabla() {

        modeloTabla.setRowCount(0);

        for (Pedido pedido : controladorPedidos.getListaPedidos()) {

            modeloTabla.addRow(new Object[]{
                    pedido.getIdPedido(),
                    pedido.getDireccionEntrega(),
                    pedido.getDistanciaKm(),
                    pedido.getClass().getSimpleName(),
                    pedido.getEstado()
            });
        }
    }
}
