package controlador;

import modelo.Entrega;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    public EntregaDAO() {

    }

    public void guardar(Entrega entrega){

        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";
        try( Connection conn = ConexionBD.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());
            stmt.setDate(3, Date.valueOf(entrega.getFecha()));
            stmt.setTime(4, Time.valueOf(entrega.getHora()));

            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();

            JOptionPane.showMessageDialog( null, "Error al guardar la entrega en la BBDD" );
        }

    }

    public List<Object[]> listarTodos() {

        List<Object[]> entregas = new ArrayList<>();

        String sql = "SELECT * FROM entrega";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                entregas.add(new Object[]{
                        rs.getInt("id"),
                        rs.getInt("id_pedido"),
                        rs.getInt("id_repartidor"),
                        rs.getDate("fecha"),
                        rs.getTime("hora")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar las entregas de la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return entregas;
    }

    public boolean actualizar(int id, Entrega entrega) {

        String sql = "UPDATE entrega SET id_pedido = ?, id_repartidor = ?, fecha = ?, hora = ? WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());
            stmt.setDate(3, Date.valueOf(entrega.getFecha()));
            stmt.setTime(4, Time.valueOf(entrega.getHora()));
            stmt.setInt(5, id);

            int filasActualizadas = stmt.executeUpdate();

            return filasActualizadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar la entrega en la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM entrega WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int filasEliminadas = stmt.executeUpdate();

            return filasEliminadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar la entrega de la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
