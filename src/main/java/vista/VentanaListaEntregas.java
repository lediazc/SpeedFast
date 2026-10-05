package vista;

import controlador.EntregaDAO;
import modelo.Entrega;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class VentanaListaEntregas extends JFrame {

    private final EntregaDAO entregaDAO = new EntregaDAO();

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
        panelBotones.add(editarJB);
        panelBotones.add(eliminarJB);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

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

        JTextField pedidoTF = new JTextField(String.valueOf(idPedido));

        JTextField repartidorTF = new JTextField(String.valueOf(idRepartidor));

        JTextField fechaTF = new JTextField(fechaActual);

        JTextField horaTF =  new JTextField(horaActual);

        JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(new JLabel("ID Pedido:"));
        panel.add(pedidoTF);

        panel.add(new JLabel("ID Repartidor:"));
        panel.add(repartidorTF);

        panel.add(new JLabel("Fecha (AAAA-MM-DD):"));
        panel.add(fechaTF);

        panel.add(new JLabel("Hora (HH:MM:SS):"));
        panel.add(horaTF);

        int opcion = JOptionPane.showConfirmDialog(this, panel,"Editar entrega #" + id,JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            int nuevoIdPedido = Integer.parseInt(pedidoTF.getText().trim());

            int nuevoIdRepartidor = Integer.parseInt(repartidorTF.getText().trim());

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

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,"Los identificadores deben ser números.","Error",JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,"La fecha u hora ingresada no tiene un formato válido.","Error",JOptionPane.ERROR_MESSAGE);
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
}