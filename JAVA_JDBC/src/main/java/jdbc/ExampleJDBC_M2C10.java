package jdbc;

/**
 * CLase creada para replicar y adquirir los conocimientos
 * santalbu
 * 20220907
 *
 * Clase 10,11,12 tocan esta clase
 *
 * Clase 10,11 se crea las conexioens a la base de datos y se muestran
 * consumos y algunos errores
 *
 * Clase 12 se habla de que no es buena practicar abrir siempre una conexion
 * siempre que quieres intentar hacer con la BD, es mejor instanciarla una vez
 * y evitar multiples intanciacioens y recuperar el objeto creado
 *
 * Descripcion: En esta clase se enseña como crear una conexion
 * como consultar tablas del esquema y tambien algunos errores
 * que pueden salir si la consulta esta mal echa o si los parametros
 * de conexion esta mal
 */



import jdbc.util.ConexionBD_M2C12;

import java.sql.*;
import java.util.logging.Logger;

public class ExampleJDBC_M2C10 {

    static final Logger log = Logger.getLogger(ExampleJDBC_M2C10.class.getName());;

    public static void main(String[] args) {

        Connection myDbConn = null;
        ResultSet result = null;
        Statement stmt = null;

        try {
            myDbConn = ConexionBD_M2C12.getInstance();
            String query = "SELECT * FROM PRODUCTOS";
            stmt = myDbConn.createStatement();
            result = stmt.executeQuery(query);

            while(result.next()){
                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
                System.out.println(result.getString(3));
                System.out.println(result.getString(4));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            cerrarConexciones(myDbConn,result,stmt);
        }

    }

    public static void cerrarConexciones(Connection con, ResultSet r, Statement s){
        try {

            if(con != null){
                con.close();
            }

            if(r != null){
                r.close();
            }

            if(s != null){
                s.close();
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

}
