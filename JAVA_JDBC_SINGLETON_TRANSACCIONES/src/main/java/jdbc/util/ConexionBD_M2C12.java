package jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * CLase creada para replicar y adquirir los conocimientos
 * santalbu
 * 20220910
 *
 * Clase 12 se habla de que no es buena practicar abrir siempre una conexion
 * siempre que quieres intentar hacer con la BD, es mejor instanciarla una vez
 * y evitar multiples intanciacioens y recuperar el objeto creado
 *
 * En esta clase se crea un util para conectarnos a la BD con Patron singleton
 * el patron  en este caso se utiliza para que la clase devuelva una instancia
 * ya creada y solo se crea una vez, y no instanciarla muchas veces sin necesidad
 *
 * Con el nuevo cambio de cerrar la conexion por cada consulta connection.isClosed() == true deja de ser
 * una clase singleton ya que se intancia n veces
 */

public class ConexionBD_M2C12 {

    private static String url = "jdbc:mysql://mysqlsantyq.mysql.database.azure.com:3306/java_curso?useSSL=true";
    private static String username = "santyq";
    private static String password = "Mysql123.";
    private static Connection connection;

    // Se utiliza con == null para validar que la conexion nunca alla sido creada
    // y tambien si cierra saca un error entonces se agre connect.isClosed() para volver a
    // intanciar
    public static Connection getInstance() throws SQLException {
        if(connection == null || connection.isClosed() == true  ){
            try {
                connection = DriverManager.getConnection(url,username,password);
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            return connection;
        }
    }

}
