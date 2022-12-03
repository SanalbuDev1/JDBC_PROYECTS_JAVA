package jdbc; /**
 * CLase creada para replicar y adquirir los conocimientos
 * santalbu
 * 20220907
 *
 * Copia De ExampleJDBC_M2C10
 *
 * Clase 19 se utiliza el productoRepositoryImpM2C19
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
import jdbc.repository.CategoriaRepositoryImplM2C27;
import jdbc.repository.ProductoRepositoryImplM2C19;
import jdbc.repository.Repository;
import jdbc.servicio.CatalogoServicioImplM2C30;
import jdbc.servicio.ServicioCatalogoIM2C30;
import jdbc.util.ConexionBD_M2C12;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class ExampleJDBC_M2C19 {

    static final Logger log = Logger.getLogger(ExampleJDBC_M2C19.class.getName());;

    static ServicioCatalogoIM2C30 servicioCatalogo = new CatalogoServicioImplM2C30();

    public static void main(String[] args) throws SQLException {

        /*Listar productos*/
     List<ProductoDto> list = servicioCatalogo.listarProducto();
     for(ProductoDto recorrerLista:list){
         System.out.println(recorrerLista);
     }
        System.out.println("");

     ProductoDto producto = servicioCatalogo.porIdProducto(2l);
     System.out.println("Producto encontrado ->" + producto);

        System.out.println();

        ProductoDto productoCrear = new ProductoDto();
        productoCrear.setProductoSku("100");
        productoCrear.setPrecio(1d);
        productoCrear.setNombre("Libro");
        productoCrear.setFecha(crearFecha());
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setIdCategoria(3l);
        productoCrear.setIdCategoriaFk(categoriaDto);
        ProductoDto response = servicioCatalogo.guardarProducto(productoCrear);
        System.out.println("====== Producto creado =========" +response);


        /*Listar categorias*/
        List<CategoriaDto> list2 = servicioCatalogo.listarCategoria();
        for(CategoriaDto recorrerLista:list2){
            System.out.println(recorrerLista);
        }

        System.out.println("");

        CategoriaDto categoriaDto2 = servicioCatalogo.porIdCategoria(2l);
        System.out.println("Categoria encontrado ->" + categoriaDto2);













    }


    public static String crearFecha(){
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha =  new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatoFecha.format(fechaActual));
        return formatoFecha.format(fechaActual);
    }




}
