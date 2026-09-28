package controlador;

import modelo.Entrega;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

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
}
