package vista;

import controlador.ControladorPedidos;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    private final JButton registrarPedidoJB = new JButton("Registrar Pedido");
    private final JButton listarPedidosJB = new JButton("Listar Pedidos");
    private final JButton iniciarEntregaJB = new JButton("Iniciar Entrega");


    private final ControladorPedidos controladorPedidos =  new ControladorPedidos();
    private VentanaListaPedidos ventanaListaPedidos;

    public VentanaPrincipal(){
        JPanel panelPrincipal = new JPanel();

        setSize(420, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("SpeedFast \uD83D\uDCE8 - Bienvenido a nuestro sistema de encomiendas");

        panelPrincipal.add(registrarPedidoJB);
        panelPrincipal.add(listarPedidosJB);
        panelPrincipal.add(iniciarEntregaJB);

        registrarPedidoJB.addActionListener(e -> abreVentanaRegistroPedido() );
        listarPedidosJB.addActionListener(e -> abreVentanaListaPedidos() );
        iniciarEntregaJB.addActionListener(e -> iniciarEntrega());

        add(panelPrincipal);

        setVisible(true);
    }

    private void abreVentanaRegistroPedido(){
        new VentanaRegistroPedido(controladorPedidos, ventanaListaPedidos);
    }

    private void abreVentanaListaPedidos(){
        ventanaListaPedidos = new VentanaListaPedidos(controladorPedidos);
    }

    private void iniciarEntrega() {

        boolean iniciado = controladorPedidos.iniciarEntregas();

        if (iniciado) {
            if (ventanaListaPedidos == null || !ventanaListaPedidos.isVisible()) {
                abreVentanaListaPedidos();
            }
        } else {
            JOptionPane.showMessageDialog(  this, "No existen pedidos pendientes.", "Aviso", JOptionPane.WARNING_MESSAGE );
        }
    }


}

