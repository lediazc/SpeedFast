package vista;

import controlador.ControladorPedidos;
import javax.swing.*;
import dao.RepartidorDAO;

public class VentanaPrincipal extends JFrame {

    private final JButton registrarPedidoJB = new JButton("Registrar Pedido");
    private final JButton listarPedidosJB = new JButton("Listar Pedidos");
    private final JButton iniciarEntregaJB = new JButton("Iniciar Entrega");
    private final JButton registrarRepartidorJB = new JButton("Registrar Repartidor");
    private final JButton listarRepartidoresJB = new JButton("Listar Repartidores");
    private final JButton listarEntregasJB = new JButton("Listar Entregas");



    private final ControladorPedidos controladorPedidos =  new ControladorPedidos();
    private VentanaListaPedidos ventanaListaPedidos;

    public VentanaPrincipal(){
        JPanel panelPrincipal = new JPanel();

        setSize(520, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("SpeedFast \uD83D\uDCE8 - Bienvenido a nuestro sistema de encomiendas");

        panelPrincipal.add(registrarPedidoJB);
        panelPrincipal.add(listarPedidosJB);
        panelPrincipal.add(iniciarEntregaJB);
        panelPrincipal.add(registrarRepartidorJB);
        panelPrincipal.add(listarRepartidoresJB);
        panelPrincipal.add(listarEntregasJB);

        registrarPedidoJB.addActionListener(e -> abreVentanaRegistroPedido() );
        listarPedidosJB.addActionListener(e -> abreVentanaListaPedidos() );
        iniciarEntregaJB.addActionListener(e -> iniciarEntrega());
        registrarRepartidorJB.addActionListener(e -> registrarRepartidor());
        listarRepartidoresJB.addActionListener(e -> abreVentanaListaRepartidores());
        listarEntregasJB.addActionListener(e -> abreVentanaListaEntregas());

        add(panelPrincipal);

        setVisible(true);
    }

    private void abreVentanaRegistroPedido(){
        new VentanaRegistroPedido(controladorPedidos, ventanaListaPedidos);
    }

    private void abreVentanaListaPedidos(){
        ventanaListaPedidos = new VentanaListaPedidos(controladorPedidos);
    }

    private void abreVentanaListaRepartidores() {
        new VentanaListaRepartidores();
    }

    private void abreVentanaListaEntregas() {
        new VentanaListaEntregas();
    }

    private void iniciarEntrega() {

        boolean iniciado = controladorPedidos.iniciarEntregas();

        if (iniciado) {

            if (ventanaListaPedidos == null || !ventanaListaPedidos.isVisible()) {
                abreVentanaListaPedidos();
            }

            Timer timer = new Timer(5000, e -> {
                if (ventanaListaPedidos != null && ventanaListaPedidos.isVisible()) {
                    ventanaListaPedidos.refrescarTabla();
                }
            });

            timer.setRepeats(false);
            timer.start();

        } else {
            JOptionPane.showMessageDialog(this, "No existen pedidos pendientes.", "Aviso", JOptionPane.WARNING_MESSAGE);
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

