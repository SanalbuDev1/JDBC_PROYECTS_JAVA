package jdbc.repository;

/**
 * CLase creada para replicar y adquirir los conocimientos
 * santalbu
 * 20220928
 *
 * Clase 27,28 *
 *
 * Clase 28 se crea todo el crud para categoria
 *
 * Se crea la clase productoRepository generica para poder accerder a la
 * interfaz atraves del objeto generico*/

import jdbc.models.CategoriaDto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryImplM2C27 implements Repository<CategoriaDto>{

    private Connection conn;

    public CategoriaRepositoryImplM2C27(Connection conn) {
        this.conn = conn;    }

    public CategoriaRepositoryImplM2C27() {
    }
    @Override
    public List<CategoriaDto> listar() throws SQLException {
        List<CategoriaDto> listaCategoria = new ArrayList<>();

        Statement stmt = null;
        ResultSet result = null;
        String sql = "SELECT * FROM CATEGORIAS";

        try{
            stmt = conn.createStatement();
            result = stmt.executeQuery(sql);

            while(result.next()){
                CategoriaDto categoria = new CategoriaDto();
                categoria.setIdCategoria(result.getLong(1));
                categoria.setNombreCategoria(result.getString(2));
                listaCategoria.add(categoria);
            }
        }finally {
            cerrarConexciones(result,stmt);
        }

        return listaCategoria;

    }

    @Override
    public CategoriaDto findForId(Long id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        String sql = "SELECT * FROM CATEGORIAS WHERE IDCATEGORIAS = ?";
        CategoriaDto categoria = null;

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,id);
            result = stmt.executeQuery();

            while(result.next()){
                categoria = new CategoriaDto();
                categoria.setIdCategoria(result.getLong(1));
                categoria.setNombreCategoria(result.getString(2));
            }
        }finally {
            cerrarConexciones(result,stmt);
        }
        return categoria;
    }

    @Override
    public CategoriaDto save(CategoriaDto c) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        String sqlInset = "INSERT INTO categorias VALUES (null, ?);";
        String sqlUpdate = "UPDATE CATEGORIAS SET NOMBRE = ? WHERE IDCATEGORIAS = ? ";

        try{

            if(c.getIdCategoria() == null){
                stmt = conn.prepareStatement(sqlInset, Statement.RETURN_GENERATED_KEYS);
            }else{
                stmt = conn.prepareStatement(sqlUpdate);
            }

            stmt.setString(1,c.getNombreCategoria());

            if(c.getIdCategoria() != null){
               stmt.setLong(2,c.getIdCategoria());
               stmt.executeUpdate();
            }else{
                 stmt.executeUpdate();
                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if(rs.next()){
                        c.setIdCategoria(rs.getLong(1));
                    }
                }
            }



           return c;
        }finally {
            cerrarConexciones(result,stmt);
        }
    }

    @Override
    public Integer delete(Long id) throws SQLException {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM CATEGORIAS WHERE IDCATEGORIAS = ?";

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,id);
            return stmt.executeUpdate();

        }finally {
            cerrarConexciones(stmt);
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
