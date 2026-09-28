package vista;

import controlador.ControladorPedidos;
import controlador.PedidoDAO;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;


public class VentanaListaPedidos extends JFrame  {


    //private final ControladorPedidos controladorPedidos;
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    private final DefaultTableModel modeloTabla =
            new DefaultTableModel(
                    new Object[]{"ID", "Dirección", "Tipo", "Estado"},
                    0
            );

    private final JTable tablaPedidos = new JTable(modeloTabla);

    public VentanaListaPedidos(ControladorPedidos controladorPedidos) {

        //this.controladorPedidos = controladorPedidos;

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

        for (Object[] pedido : pedidoDAO.listarTodos()) {
            modeloTabla.addRow(pedido);
        }
    }
}
