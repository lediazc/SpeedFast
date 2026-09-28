package controlador;

import modelo.Pedido;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PedidoDAO {

    public PedidoDAO(){

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


        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }
}
