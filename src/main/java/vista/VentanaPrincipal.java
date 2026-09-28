package vista;

import controlador.ControladorPedidos;
import javax.swing.*;
import controlador.RepartidorDAO;

public class VentanaPrincipal extends JFrame {

    private final JButton registrarPedidoJB = new JButton("Registrar Pedido");
    private final JButton listarPedidosJB = new JButton("Listar Pedidos");
    private final JButton iniciarEntregaJB = new JButton("Iniciar Entrega");
    private final JButton registrarRepartidorJB = new JButton("Registrar Repartidor");

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
        panelPrincipal.add(registrarRepartidorJB);

        registrarPedidoJB.addActionListener(e -> abreVentanaRegistroPedido() );
        listarPedidosJB.addActionListener(e -> abreVentanaListaPedidos() );
        iniciarEntregaJB.addActionListener(e -> iniciarEntrega());
        registrarRepartidorJB.addActionListener(e -> registrarRepartidor());

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

    private void registrarRepartidor() {

        String nombre = JOptionPane.showInputDialog( this, "Ingrese el nombre del repartidor:" );

        if (nombre == null || nombre.trim().isEmpty()) {
            return;
        }

        RepartidorDAO repartidorDAO = new RepartidorDAO();

        boolean guardado = repartidorDAO.guardar(nombre.trim());

        if (guardado) {

            JOptionPane.showMessageDialog( this, "Repartidor registrado correctamente." );

        } else {

            JOptionPane.showMessageDialog(  this, "No se pudo registrar el repartidor.", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }


}

