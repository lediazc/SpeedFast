package controlador;

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

    public List<Repartidor> listarTodos(){

        List<Repartidor> repartidores = new ArrayList<>();

        String sql = "SELECT * FROM repartidor";

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        try(Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

                while(rs.next()){
                    String nombre = rs.getString("nombre");

                    Repartidor repartidor = new Repartidor(nombre, zonaDeCarga);

                    repartidores.add(repartidor);

                }
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener Repartidores de la BBDD");
        }

        return repartidores;

    }

}
