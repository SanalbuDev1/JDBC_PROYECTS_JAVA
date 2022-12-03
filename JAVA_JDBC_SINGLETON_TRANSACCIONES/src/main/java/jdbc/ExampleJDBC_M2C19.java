package jdbc; /**
 * CLase creada para replicar y adquirir los conocimientos
 * santalbu
 * 20220907
 *
 * Copia De ExampleJDBC_M2C10
 *
 * Clase 19,Clase 25
 *
 * Clase 19 se utiliza el productoRepositoryImpM2C19
 *
 * Clase 25 se crea en producto el nuevo campo etiquetaSku se modifica la clase
 * por metodo individual, se agrega la conexion y se elimina el cierre de recursos
 * en la clase repository
 *
 * En esta clase se utiliza lo que se creo para la clase ProductosRepository y la interfaz repository
 *
 * Descripcion: En esta clase se enseña como crear una conexion
 * como consultar tablas del esquema y tambien algunos errores
 * que pueden salir si la consulta esta mal echa o si los parametros
 * de conexion esta mal
 */


import jdbc.models.CategoriaDto;
import jdbc.models.ProductoDto;
import jdbc.repository.ProductoRepositoryImplM2C19;
import jdbc.repository.Repository;
import jdbc.util.ConexionBD_M2C12;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class ExampleJDBC_M2C19 {

    static final Logger log = Logger.getLogger(ExampleJDBC_M2C19.class.getName());;

    private static Repository<ProductoDto> repo = new ProductoRepositoryImplM2C19();

    static Connection conn = null;

    public static void main(String[] args) {
        try {
            conn = ConexionBD_M2C12.getInstance();
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            listarTodo();
            crear();
            actualizar(1l);
            listarTodo();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            new ProductoRepositoryImplM2C19().cerrarConexion();
        }

    }

    public static void listarTodo() throws SQLException {
        // metodo listar
        List<ProductoDto> list =  repo.listar();
        System.out.println("Metodo listar -> \n");
        list.forEach(result -> { System.out.println(result.getId());
            System.out.println(result.getNombre());
            System.out.println(result.getPrecio());
            System.out.println(result.getIdCategoriaFk().getIdCategoria());
            System.out.println(result.getIdCategoriaFk().getNombreCategoria());
            System.out.println("--------------------");
        });
    }

    public static void listarPorId(Long id) throws SQLException {
        // metodo buscar por id
        System.out.println("Metodo buscar por id -> \n");
        ProductoDto productoDto = new ProductoDto();
        productoDto = repo.findForId(id);
        System.out.println(productoDto.getId());
        System.out.println(productoDto.getNombre());
        System.out.println(productoDto.getPrecio());
        System.out.println(productoDto.getFecha());
        System.out.println(productoDto.getIdCategoriaFk().getIdCategoria());
        System.out.println(productoDto.getIdCategoriaFk().getNombreCategoria());
    }

    public static void crear() throws SQLException {
        // Metodo para crear un producto
        System.out.println("Metodo para crear un producto -> ");
        // se comenta para que no cree multipels registros repetidos
        ProductoDto crearProducto = new ProductoDto();
        CategoriaDto categoria = new CategoriaDto();
        categoria.setIdCategoria(1l);
        crearProducto.setNombre("Guayos SKU2");
        crearProducto.setPrecio(1234d);
        crearProducto.setFecha(crearFecha());
        crearProducto.setIdCategoriaFk(categoria);
        crearProducto.setEtiquetaSku("11");
        int contador1 = repo.save(crearProducto);
        System.out.println("registros creados en BD " + contador1 +  "\n");
    }

    public static void actualizar(Long id) throws SQLException {
        System.out.println("Metodo para actualizar un producto -> \n");
        ProductoDto actualizarProducto = new ProductoDto();
        CategoriaDto categoria2 = new CategoriaDto();
        categoria2.setIdCategoria(2l);
        actualizarProducto.setId(id);
        actualizarProducto.setNombre("Anillo de diamante");
        actualizarProducto.setPrecio(399d);
        actualizarProducto.setFecha(crearFecha());
        actualizarProducto.setEtiquetaSku("2");
        actualizarProducto.setIdCategoriaFk(categoria2);
        int contador2 = repo.save(actualizarProducto);
        System.out.println("registros actualizados en BD " + contador2 + "\n");
    }

    public static void eliminar(Long id) throws SQLException {

        System.out.println("Metodo para eliminar un producto -> \n");
        ProductoDto eliminarProducto = new ProductoDto();
        eliminarProducto.setId(id);
        int contador3 = repo.delete(eliminarProducto);
        System.out.println("registros eliminados en la BD " + contador3 + "\n");
    }

    public static String crearFecha(){
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha =  new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatoFecha.format(fechaActual));
        return formatoFecha.format(fechaActual);
    }




}
