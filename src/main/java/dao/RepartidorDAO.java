package dao;

import controlador.ConexionBD;
import modelo.Repartidor;
import modelo.ZonaDeCarga;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    public RepartidorDAO() {

    }

    public List<Repartidor> listarTodos(ZonaDeCarga zonaDeCarga){

        List<Repartidor> repartidores = new ArrayList<>();

        String sql = "SELECT * FROM repartidor";


        try(Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                Repartidor repartidor =
                        new Repartidor(id, nombre, zonaDeCarga);

                repartidores.add(repartidor);
            }
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener Repartidores de la BBDD");
        }

        return repartidores;

    }


    public List<Repartidor> listarTodos() {

        List<Repartidor> repartidores = new ArrayList<>();

        String sql = "SELECT * FROM repartidor";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                Repartidor repartidor = new Repartidor(id, nombre, null);

                repartidores.add(repartidor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar los repartidores de la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return repartidores;
    }
    public boolean guardar(String nombre) {

        String sql = "INSERT INTO repartidor (nombre) VALUES (?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el repartidor en la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean actualizar(int id, String nombre) {

        String sql = "UPDATE repartidor SET nombre = ? WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setInt(2, id);

            int filasActualizadas = stmt.executeUpdate();

            return filasActualizadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el repartidor en la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM repartidor WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int filasEliminadas = stmt.executeUpdate();

            return filasEliminadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el repartidor de la BBDD.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


}
