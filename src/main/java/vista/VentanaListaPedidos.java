package vista;

import controlador.ControladorPedidos;
import controlador.PedidoDAO;
import modelo.EstadoPedido;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;


public class VentanaListaPedidos extends JFrame  {


    //private final ControladorPedidos controladorPedidos;
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    private final DefaultTableModel modeloTabla =
            new DefaultTableModel(
                    new Object[]{"ID", "Dirección", "Tipo", "Estado"},
                    0
            );

    private final JTable tablaPedidos = new JTable(modeloTabla);
    private final JButton editarJB = new JButton("Editar");
    private final JButton eliminarJB = new JButton("Eliminar");

    public VentanaListaPedidos(ControladorPedidos controladorPedidos) {

        //this.controladorPedidos = controladorPedidos;

        setSize(700, 500);
        setLocationRelativeTo(null);
        setTitle("Listado de pedidos");
        setLayout(new BorderLayout());

        tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPedidos.setRowSelectionAllowed(true);
        tablaPedidos.setColumnSelectionAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        JPanel panelBotones = new JPanel();
        panelBotones.add(editarJB);
        panelBotones.add(eliminarJB);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);


        refrescarTabla();

        //Timer timer = new Timer(250, e -> refrescarTabla());
        //timer.start();

        editarJB.addActionListener(e -> editarPedido());
        eliminarJB.addActionListener(e -> eliminarPedido());

        setVisible(true);
    }

    private void eliminarPedido() {

        int filaSeleccionada = tablaPedidos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        int confirmacion = JOptionPane.showConfirmDialog( this,  "¿Desea eliminar el pedido #" + id + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean eliminado = pedidoDAO.eliminar(id);

        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Pedido eliminado correctamente.");
            refrescarTabla();
        } else {
            JOptionPane.showMessageDialog(
                    this, "No se pudo eliminar el pedido.", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    private void editarPedido() {

        int filaSeleccionada = tablaPedidos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtenemos los datos actuales desde la fila seleccionada
        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        String direccionActual = modeloTabla.getValueAt(filaSeleccionada, 1).toString();
        String tipoActual = modeloTabla.getValueAt(filaSeleccionada, 2).toString();
        String estadoActual = modeloTabla.getValueAt(filaSeleccionada, 3).toString();

        // Componentes del formulario
        JTextField direccionTF = new JTextField(direccionActual);

        JComboBox<String> tipoCB = new JComboBox<>(
                new String[]{
                        "COMIDA",
                        "ENCOMIENDA",
                        "EXPRESS"
                }
        );
        tipoCB.setSelectedItem(tipoActual);

        JComboBox<String> estadoCB = new JComboBox<>(
                new String[]{
                        "PENDIENTE",
                        "EN_REPARTO",
                        "ENTREGADO"
                }
        );
        estadoCB.setSelectedItem(estadoActual);

        // Panel del formulario
        JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(new JLabel("Dirección:"));
        panel.add(direccionTF);

        panel.add(new JLabel("Tipo:"));
        panel.add(tipoCB);

        panel.add(new JLabel("Estado:"));
        panel.add(estadoCB);

        int opcion = JOptionPane.showConfirmDialog( this, panel, "Editar pedido #" + id, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        String nuevaDireccion = direccionTF.getText().trim();

        if (nuevaDireccion.isEmpty()) {
            JOptionPane.showMessageDialog( this, "La dirección no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nuevoTipo = tipoCB.getSelectedItem().toString();
        EstadoPedido nuevoEstado = EstadoPedido.valueOf(estadoCB.getSelectedItem().toString());

        boolean actualizado = pedidoDAO.actualizar(
                id,
                nuevaDireccion,
                nuevoTipo,
                nuevoEstado
        );

        if (actualizado) {
            JOptionPane.showMessageDialog( this, "Pedido actualizado correctamente." );

            refrescarTabla();

        } else {
            JOptionPane.showMessageDialog( this, "No se pudo actualizar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refrescarTabla() {

        modeloTabla.setRowCount(0);

        for (Object[] pedido : pedidoDAO.listarTodos()) {
            modeloTabla.addRow(pedido);
        }
    }
}
