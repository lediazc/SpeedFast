package vista;

import controlador.ControladorPedidos;
import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private final ControladorPedidos controladorPedidos;
    private final VentanaListaPedidos ventanaListaPedidos;
    private final JTextField idRegistroJTF = new JTextField(20);
    private final JTextField direccionJTF = new JTextField(20);
    private final JTextField distanciaJTF = new JTextField(20);
    private final JComboBox<String> tipoJCB =
            new JComboBox(new String[]{
                    "Comida",
                    "Encomienda",
                    "Express"
            });

    private final JButton guardarJB = new JButton("Guardar solicitud");


    public VentanaRegistroPedido(ControladorPedidos controladorPedidos, VentanaListaPedidos ventanaListaPedidos){

        this.controladorPedidos = controladorPedidos;
        this.ventanaListaPedidos = ventanaListaPedidos;

        JPanel panelPrincipal = new JPanel(new GridLayout(5, 2, 10, 10));

        idRegistroJTF.setEditable(false);
        distanciaJTF.setEditable(false);

        JPanel panelID = new JPanel();
        panelID.add(new JLabel("ID: "));
        panelID.add(idRegistroJTF);

        JPanel panelDireccion = new JPanel();
        panelDireccion.add(new JLabel("Dirección: "));
        panelDireccion.add(direccionJTF);

        JPanel panelDistancia = new JPanel();
        panelDistancia.add(new JLabel("Distancia: "));
        panelDistancia.add(distanciaJTF);

        JPanel panelTipo = new JPanel();
        panelTipo.add(new JLabel("Tipo encomienda: "));
        panelTipo.add(tipoJCB);

        setSize(550, 500);
        setLocationRelativeTo(null);
        setTitle("Registra tu pedido \uD83D\uDCE8");

        panelPrincipal.add(panelID);
        panelPrincipal.add(panelDireccion);
        panelPrincipal.add(panelDistancia);
        panelPrincipal.add(panelTipo);
        panelPrincipal.add(guardarJB);

        add(panelPrincipal);

        setVisible(true);

        guardarJB.addActionListener(e -> guardarPedido());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private double calcularDistanciaSimulada() {
        double distancia = Math.random() * 39 + 1;
        return Math.round(distancia * 10.0) / 10.0;
    }

    private void guardarPedido() {

        String direccion = direccionJTF.getText().trim();
        String tipo = (String) tipoJCB.getSelectedItem();

        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Debe ingresar una dirección.","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = controladorPedidos.generarNuevoId();
        double distancia = calcularDistanciaSimulada();

        Pedido pedido;

        switch (tipo) {

            case "Comida":
                pedido = new PedidoComida(id, direccion, distancia);
                break;

            case "Encomienda":
                pedido = new PedidoEncomienda(id, direccion, distancia);
                break;

            case "Express":
                pedido = new PedidoExpress(id, direccion, distancia);
                break;

            default:
                return;
        }

        controladorPedidos.agregarPedido(pedido);



        idRegistroJTF.setText(String.valueOf(id));
        distanciaJTF.setText(String.valueOf(distancia));

        JOptionPane.showMessageDialog(this,"Pedido registrado correctamente.");
        dispose();

        direccionJTF.setText("");
    }
}
