package jdbc.repository;

import jdbc.models.CategoriaDto;
import jdbc.models.ProductoDto;
import jdbc.util.ConexionBD_M2C12;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CLase creada para replicar y adquirir los conocimientos
 * santalbu
 * 20221007
 *
 * Clase 19 se agrega la relacion productos con categoria *
 *
 *
 * Se crea la clase productoRepository generica para poder accerder a la
 * interfaz atraves del objeto generico*/


public class ProductoRepositoryImplM2C19 implements Repository<ProductoDto> {

    public Connection conn;
    public ProductoRepositoryImplM2C19(Connection conn) {
        this.conn = conn;
    }

    public ProductoRepositoryImplM2C19(){}

    @Override
    public List<ProductoDto> listar() throws SQLException {

        String  SQL = "select * from productos p " +
                " join categorias c " +
                " on p.id_categoria_fk = c.idcategorias";

        List<ProductoDto> listProductos = new ArrayList<>();
        try(Statement stmt2 = conn.createStatement();
            ResultSet result = stmt2.executeQuery(SQL)){
            while(result.next()){
                ProductoDto product = new ProductoDto();
                CategoriaDto categoria = new CategoriaDto();
                product.setId(result.getLong(1));
                product.setNombre(result.getString(2));
                product.setPrecio(result.getDouble(3));
                product.setFecha(result.getString(4));
                product.setProductoSku(result.getString(6));
                categoria.setIdCategoria(result.getLong(7));
                categoria.setNombreCategoria(result.getString(8));
                product.setIdCategoriaFk(categoria);
                listProductos.add(product);
            }
            return listProductos;
        }


    }

    @Override
    public ProductoDto findForId(Long id) throws SQLException {

        PreparedStatement prepareStmt = null;
        ResultSet result = null;

        try{
            String  SQL = "select * from productos p " +
                    "  join categorias c " +
                    "  on p.id_categoria_fk = c.idcategorias WHERE IDPRODUCTOS = ?";

            prepareStmt = conn.prepareStatement(SQL);
            prepareStmt.setLong(1,id);
            result = prepareStmt.executeQuery();

            if(result.next()){
                ProductoDto product = crearProducto(result);
                return product;
            }
        }finally {
            cerrarConexciones(result,prepareStmt);
        }

        return null;
    }

    private ProductoDto crearProducto(ResultSet result) throws SQLException {
        ProductoDto product = new ProductoDto();
        CategoriaDto categoria = new CategoriaDto();
        product.setId(result.getLong(1));
        product.setNombre(result.getString(2));
        product.setPrecio(result.getDouble(3));
        product.setFecha(result.getString(4));
        product.setProductoSku(result.getString(6));
        categoria.setIdCategoria(result.getLong(7));
        categoria.setNombreCategoria(result.getString(8));
        product.setIdCategoriaFk(categoria);
        return product;
    }

    @Override
    public ProductoDto save(ProductoDto productoDto) throws SQLException {

            PreparedStatement prepareStmt = null;

            try{

                if(productoDto.getId() == null){
                    prepareStmt = conn.prepareStatement("INSERT INTO productos" +
                            "(nombre_producto,precio,fecha_registro,id_categoria_fk,etiquetaSku)" +
                            " VALUES" +
                            "(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                }else{
                    prepareStmt = conn.prepareStatement("UPDATE productos " +
                            "SET " +
                            "nombre_producto = ?, " +
                            "precio = ?, " +
                            "fecha_registro = ?," +
                            "id_categoria_fk = ?," +
                            "etiquetaSku = ? " +
                            "WHERE idproductos = ?");
                }


                Integer contador = 1;
                prepareStmt.setString(contador++,productoDto.getNombre());
                prepareStmt.setDouble(contador++,productoDto.getPrecio());
                prepareStmt.setString(contador++,productoDto.getFecha());
                prepareStmt.setLong(contador++,productoDto.getIdCategoriaFk().getIdCategoria());
                prepareStmt.setString(contador++,productoDto.getProductoSku());
                if(productoDto.getId() != null){
                    prepareStmt.setLong(contador++,productoDto.getId());
                }
                prepareStmt.executeUpdate();

                // ultimo id generado
                if(productoDto.getId() == null){
                    try(ResultSet result = prepareStmt.getGeneratedKeys()){
                        if(result.next()){
                            productoDto.setId(result.getLong(1));
                        }
                    }
                }

                return productoDto;
                // setDate segundo paramatro es de tipo java.sql.date este extiende de
                // java.util.date, como el parametro es de java.util.date toca convertirlo en java.sql.Date
            }finally {
                cerrarConexciones(prepareStmt);
            }





    }

    @Override
    public Integer delete(Long id) throws SQLException {
        PreparedStatement prepareStmt = null;
        try{

            prepareStmt = conn.prepareStatement("DELETE from productos " +
                    "WHERE idproductos = ?");

            Integer contador = 1;
            prepareStmt.setLong(contador++,id);
            return prepareStmt.executeUpdate();
        }finally {
            cerrarConexciones(prepareStmt);
        }
    }

    public static void cerrarConexciones(ResultSet r, Statement s){
        try {

            if(r != null){
                r.close();
            }

            if(s != null){
                s.close();
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

    }

    public static void cerrarConexciones(Statement s){
        try {
            if(s != null){
                s.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
