package dao;

import controlador.ConexionBD;
import modelo.Pedido;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.EstadoPedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

public class PedidoDAO {

    public PedidoDAO(){

    }
    public List<Pedido> listarTodos() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT * FROM pedido";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String direccion = rs.getString("direccion");
                String tipo = rs.getString("tipo");
                EstadoPedido estado = EstadoPedido.valueOf(rs.getString("estado"));

                Pedido pedido;

                switch (tipo) {
                    case "COMIDA":
                        pedido = new PedidoComida(id, direccion, 0);
                        break;

                    case "ENCOMIENDA":
                        pedido = new PedidoEncomienda(id, direccion, 0);
                        break;

                    case "EXPRESS":
                        pedido = new PedidoExpress(id, direccion, 0);
                        break;

                    default:
                        throw new IllegalArgumentException("Tipo de pedido desconocido: " + tipo);
                }

                pedido.setEstado(estado);
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar los pedidos de la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return pedidos;
    }

    public boolean guardar(Pedido pedido){
        String sql = "INSERT INTO pedido (direccion,tipo ,estado) VALUES (?, ?,?)";

        try(Connection conn = ConexionBD.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql,  PreparedStatement.RETURN_GENERATED_KEYS )) {

            stmt.setString(1, pedido.getDireccionEntrega());
            stmt.setString(2, pedido.getClass().getSimpleName().replace("Pedido","").toUpperCase());
            stmt.setString(3, pedido.getEstado().name());


            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    pedido.setIdPedido(idGenerado);
                }
            }

            return true;


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el pedido en la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean actualizar(int id, String direccion, String tipo, EstadoPedido estado){

        String sql = "UPDATE pedido SET direccion =?, tipo =?, estado =? WHERE id =?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, direccion);
            stmt.setString(2, tipo);
            stmt.setString(3, estado.name());
            stmt.setInt(4, id);

            int filasAfctualizada = stmt.executeUpdate();

            return filasAfctualizada > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el pedido en la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean eliminar(int id){

        String sql = "DELETE FROM pedido WHERE id =?";

        try( Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);

            int filasEliminadas = stmt.executeUpdate();

            return filasEliminadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el pedido de la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean actualizarEstado(int idPedido, EstadoPedido estado) {

        String sql = "UPDATE pedido SET estado = ? WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estado.name());
            stmt.setInt(2, idPedido);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el estado del pedido en la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Pedido> listarPendientes() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT * FROM pedido WHERE estado = 'PENDIENTE'";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String direccion = rs.getString("direccion");
                String tipo = rs.getString("tipo");
                EstadoPedido estado = EstadoPedido.valueOf(rs.getString("estado"));

                Pedido pedido;

                switch (tipo) {
                    case "COMIDA":
                        pedido = new PedidoComida(id, direccion, 0);
                        break;

                    case "ENCOMIENDA":
                        pedido = new PedidoEncomienda(id, direccion, 0);
                        break;

                    case "EXPRESS":
                        pedido = new PedidoExpress(id, direccion, 0);
                        break;

                    default:
                        throw new IllegalArgumentException("Tipo de pedido desconocido: " + tipo);
                }

                pedido.setEstado(estado);
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar los pedidos pendientes de la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return pedidos;
    }
}
