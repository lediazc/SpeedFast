package vista;

import controlador.EntregaDAO;
import modelo.Entrega;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import controlador.PedidoDAO;
import controlador.RepartidorDAO;

public class VentanaListaEntregas extends JFrame {

    private final EntregaDAO entregaDAO = new EntregaDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final RepartidorDAO repartidorDAO = new RepartidorDAO();

    private final DefaultTableModel modeloTabla =
            new DefaultTableModel(
                    new Object[]{
                            "ID",
                            "Pedido",
                            "Repartidor",
                            "Fecha",
                            "Hora"},
                    0
            );

    private final JTable tablaEntregas = new JTable(modeloTabla);

    private final JButton registrarJB = new JButton("Registrar");
    private final JButton editarJB = new JButton("Editar");
    private final JButton eliminarJB = new JButton("Eliminar");

    public VentanaListaEntregas() {

        setTitle("Listado de entregas");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tablaEntregas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaEntregas);

        JPanel panelBotones = new JPanel();

        panelBotones.add(registrarJB);
        panelBotones.add(editarJB);
        panelBotones.add(eliminarJB);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        registrarJB.addActionListener(e -> registrarEntrega());
        editarJB.addActionListener(e -> editarEntrega());
        eliminarJB.addActionListener(e -> eliminarEntrega());

        refrescarTabla();

        setVisible(true);
    }

    public void refrescarTabla() {

        modeloTabla.setRowCount(0);

        for (Object[] entrega : entregaDAO.listarTodos()) {
            modeloTabla.addRow(entrega);
        }
    }

    private void registrarEntrega() {

        JComboBox<comboB> pedidoCB = new JComboBox<>();
        JComboBox<comboB> repartidorCB = new JComboBox<>();

        // Cargar pedidos desde la BD
        for (Object[] pedido : pedidoDAO.listarTodos()) {

            int idPedido = (int) pedido[0];
            String direccion = pedido[1].toString();

            pedidoCB.addItem(
                    new comboB(
                            idPedido,
                            idPedido + " - " + direccion
                    )
            );
        }

        // Cargar repartidores desde la BD
        for (Object[] repartidor : repartidorDAO.listarTodosTabla()) {

            int idRepartidor = (int) repartidor[0];
            String nombre = repartidor[1].toString();

            repartidorCB.addItem(
                    new comboB(
                            idRepartidor,
                            idRepartidor + " - " + nombre
                    )
            );
        }

        JTextField fechaTF = new JTextField(LocalDate.now().toString());
        JTextField horaTF = new JTextField(LocalTime.now().withNano(0).toString());

        JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(new JLabel("Pedido:"));
        panel.add(pedidoCB);

        panel.add(new JLabel("Repartidor:"));
        panel.add(repartidorCB);

        panel.add(new JLabel("Fecha (AAAA-MM-DD):"));
        panel.add(fechaTF);

        panel.add(new JLabel("Hora (HH:MM:SS):"));
        panel.add(horaTF);

        int opcion = JOptionPane.showConfirmDialog(this, panel, "Registrar entrega", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        comboB pedidoSeleccionado = (comboB) pedidoCB.getSelectedItem();
        comboB repartidorSeleccionado = (comboB) repartidorCB.getSelectedItem();

        if (pedidoSeleccionado == null || repartidorSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido y un repartidor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idPedido = pedidoSeleccionado.getId();
        int idRepartidor = repartidorSeleccionado.getId();

        if (fechaTF.getText().trim().isEmpty() || horaTF.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha y la hora son obligatorias.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            LocalDate fecha = LocalDate.parse(fechaTF.getText().trim());
            LocalTime hora = LocalTime.parse(horaTF.getText().trim());

            Entrega entrega = new Entrega(idPedido, idRepartidor, fecha, hora);

            entregaDAO.guardar(entrega);

            JOptionPane.showMessageDialog(this, "Entrega registrada correctamente.");

            refrescarTabla();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "La fecha u hora ingresada no tiene un formato válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarEntrega() {

        int filaSeleccionada = tablaEntregas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,"Debe seleccionar una entrega.","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        int idPedido = (int) modeloTabla.getValueAt(filaSeleccionada, 1);
        int idRepartidor = (int) modeloTabla.getValueAt(filaSeleccionada, 2);

        String fechaActual = modeloTabla.getValueAt(filaSeleccionada, 3).toString();

        String horaActual = modeloTabla.getValueAt(filaSeleccionada, 4).toString();

        JComboBox<comboB> pedidoCB = new JComboBox<>();
        JComboBox<comboB> repartidorCB = new JComboBox<>();

        for (Object[] pedido : pedidoDAO.listarTodos()) {

            int idPedidoCombo = (int) pedido[0];
            String direccion = pedido[1].toString();

            pedidoCB.addItem(
                    new comboB(
                            idPedidoCombo,
                            idPedidoCombo + " - " + direccion
                    )
            );

            if (idPedidoCombo == idPedido) {
                pedidoCB.setSelectedIndex(
                        pedidoCB.getItemCount() - 1
                );
            }
        }

        for (Object[] repartidor : repartidorDAO.listarTodosTabla()) {

            int idRepartidorCombo = (int) repartidor[0];
            String nombre = repartidor[1].toString();

            repartidorCB.addItem(
                    new comboB(
                            idRepartidorCombo,
                            idRepartidorCombo + " - " + nombre
                    )
            );

            // Dejar seleccionado el repartidor actual de la entrega
            if (idRepartidorCombo == idRepartidor) {
                repartidorCB.setSelectedIndex(
                        repartidorCB.getItemCount() - 1
                );
            }
        }

        JTextField fechaTF = new JTextField(fechaActual);

        JTextField horaTF =  new JTextField(horaActual);

        JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(new JLabel("Pedido:"));
        panel.add(pedidoCB);

        panel.add(new JLabel("Repartidor:"));
        panel.add(repartidorCB);

        panel.add(new JLabel("Fecha (AAAA-MM-DD):"));
        panel.add(fechaTF);

        panel.add(new JLabel("Hora (HH:MM:SS):"));
        panel.add(horaTF);

        int opcion = JOptionPane.showConfirmDialog(this, panel,"Editar entrega #" + id,JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        comboB pedidoSeleccionado = (comboB) pedidoCB.getSelectedItem();

        comboB repartidorSeleccionado = (comboB) repartidorCB.getSelectedItem();

            if (pedidoSeleccionado == null ||  repartidorSeleccionado == null) {

                JOptionPane.showMessageDialog(this,"Debe seleccionar un pedido y un repartidor.","Error",JOptionPane.ERROR_MESSAGE);

                return;
            }

            int nuevoIdPedido = pedidoSeleccionado.getId();
            int nuevoIdRepartidor = repartidorSeleccionado.getId();

            if (fechaTF.getText().trim().isEmpty() || horaTF.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "La fecha y la hora son obligatorias.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        try{
            LocalDate nuevaFecha = LocalDate.parse(fechaTF.getText().trim());

            LocalTime nuevaHora = LocalTime.parse(horaTF.getText().trim());

            Entrega entrega = new Entrega(
                    nuevoIdPedido,
                    nuevoIdRepartidor,
                    nuevaFecha,
                    nuevaHora
            );

            boolean actualizado =entregaDAO.actualizar(id, entrega);

            if (actualizado) {

                JOptionPane.showMessageDialog(this,"Entrega actualizada correctamente.");

                refrescarTabla();

            } else {

                JOptionPane.showMessageDialog(this,"No se pudo actualizar la entrega.","Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e){

            JOptionPane.showMessageDialog(this,"La fecha u hora ingresada no tiene un formato válido.","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEntrega() {

        int filaSeleccionada = tablaEntregas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,"Debe seleccionar una entrega.","Aviso",JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada,0);

        int confirmacion = JOptionPane.showConfirmDialog(this,"¿Desea eliminar la entrega #" + id + "?","Confirmar eliminación",JOptionPane.YES_NO_OPTION);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean eliminado = entregaDAO.eliminar(id);

        if (eliminado) {

            JOptionPane.showMessageDialog(this,"Entrega eliminada correctamente.");

            refrescarTabla();

        } else {

            JOptionPane.showMessageDialog(this,"No se pudo eliminar la entrega.","Error",JOptionPane.ERROR_MESSAGE );
        }


    }
    private static class comboB {

        private final int id;
        private final String texto;

        public comboB(int id, String texto) {
            this.id = id;
            this.texto = texto;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return texto;
        }
    }
}
