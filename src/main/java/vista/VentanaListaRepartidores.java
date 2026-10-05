package vista;

import controlador.RepartidorDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaRepartidores extends JFrame {

    private final RepartidorDAO repartidorDAO = new RepartidorDAO();

    private final DefaultTableModel modeloTabla =
            new DefaultTableModel(new Object[]{"ID", "Nombre"}, 0);

    private final JTable tablaRepartidores = new JTable(modeloTabla);

    private final JButton editarJB = new JButton("Editar");
    private final JButton eliminarJB = new JButton("Eliminar");

    public VentanaListaRepartidores() {

        setTitle("Listado de repartidores");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tablaRepartidores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaRepartidores);

        JPanel panelBotones = new JPanel();
        panelBotones.add(editarJB);
        panelBotones.add(eliminarJB);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        editarJB.addActionListener(e -> editarRepartidor());
        eliminarJB.addActionListener(e -> eliminarRepartidor());

        refrescarTabla();

        setVisible(true);
    }

    public void refrescarTabla() {

        modeloTabla.setRowCount(0);

        for (Object[] repartidor : repartidorDAO.listarTodosTabla()) {
            modeloTabla.addRow(repartidor);
        }
    }

    private void editarRepartidor() {

        int filaSeleccionada = tablaRepartidores.getSelectedRow();

        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog( this, "Debe seleccionar un repartidor.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        String nombreActual = modeloTabla.getValueAt(filaSeleccionada,1).toString();

        String nuevoNombre = JOptionPane.showInputDialog(this,"Nombre del repartidor:",nombreActual);

        // Canceló
        if (nuevoNombre == null) {
            return;
        }

        nuevoNombre = nuevoNombre.trim();

        // Validación
        if (nuevoNombre.isEmpty()) {

            JOptionPane.showMessageDialog(this,"El nombre no puede estar vacío.","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean actualizado =repartidorDAO.actualizar(id,nuevoNombre);

        if (actualizado) {

            JOptionPane.showMessageDialog(this,"Repartidor actualizado correctamente.");
            refrescarTabla();

        } else {

            JOptionPane.showMessageDialog(this,"No se pudo actualizar el repartidor.","Error",JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void eliminarRepartidor() {

        int filaSeleccionada =
                tablaRepartidores.getSelectedRow();

        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog(this, "Debe seleccionar un repartidor.","Aviso",JOptionPane.WARNING_MESSAGE);

            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada,0);

        int confirmacion =
                JOptionPane.showConfirmDialog( this,"¿Desea eliminar el repartidor #" + id + "?", "Confirmar eliminación",JOptionPane.YES_NO_OPTION);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean eliminado =
                repartidorDAO.eliminar(id);

        if (eliminado) {

            JOptionPane.showMessageDialog(this,"Repartidor eliminado correctamente." );

            refrescarTabla();

        } else {

            JOptionPane.showMessageDialog(this,"No se pudo eliminar el repartidor.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}