package jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * CLase creada para replicar y adquirir los conocimientos
 * santalbu
 * 20220910
 *
 * Clase 12,22
 *
 * Clase 12 se habla de que no es buena practicar abrir siempre una conexion
 * siempre que quieres intentar hacer con la BD, es mejor instanciarla una vez
 * y evitar multiples intanciacioens y recuperar el objeto creado
 *
 * Clase 22 se modifica totalmente para crear pool de conexiomes
 *
 */

public class ConexionBD_M2C12 {

    private static String url = "jdbc:mysql://mysqlsantyq.mysql.database.azure.com:3306/java_curso?useSSL=true";
    private static String username = "santyq";
    private static String password = "Mysql123.";
    private static BasicDataSource poolConnection;

    static Logger log = Logger.getLogger(ConexionBD_M2C12.class.getName());

    // Se utiliza con == null para validar que la conexion nunca alla sido creada
    // y tambien si cierra saca un error entonces se agre connect.isClosed() para volver a
    // intanciar
    public static BasicDataSource getInstance() {
       if(poolConnection == null){
           instancePool();
       }
       return poolConnection;
    }

    // Devolve un pool de conexiones
    public static Connection getConnection(){

        Connection conn = null;

        try {
            conn = getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        log.info("Pool de conexiones " +  getInstance().getNumActive());
        return conn;
    }
    public static void instancePool(){
        poolConnection = new BasicDataSource();
        poolConnection.setUrl(url);
        poolConnection.setUsername(username);
        poolConnection.setPassword(password);
        //creacion de conexiones de pool inicial
        poolConnection.setInitialSize(3);
        // creacion del minimo de conexiones activas y la maxima cantida
        poolConnection.setMinIdle(3);
        poolConnection.setMaxIdle(8);
        // Maximo de conexiones en total
        poolConnection.setMaxTotal(8);
    }

}
