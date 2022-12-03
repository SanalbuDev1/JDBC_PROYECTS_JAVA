package jdbc.servicio;

import jdbc.models.CategoriaDto;
import jdbc.models.ProductoDto;
import jdbc.repository.CategoriaRepositoryImplM2C27;
import jdbc.repository.ProductoRepositoryImplM2C19;
import jdbc.repository.Repository;
import jdbc.util.ConexionBD_M2C12;

import java.sql.SQLException;
import java.util.List;

public class CatalogoServicioImplM2C30 implements ServicioCatalogoIM2C30{

    private Repository<ProductoDto> productoRepository;
    private Repository<CategoriaDto> categoriaRepository;

    public CatalogoServicioImplM2C30(){
        productoRepository = new ProductoRepositoryImplM2C19();
        categoriaRepository = new CategoriaRepositoryImplM2C27();
    }

    @Override
    public List<ProductoDto> listarProducto() throws SQLException {
        try{
            productoRepository.setConn(ConexionBD_M2C12.getConnection());
            return productoRepository.listar();
        }finally {
            productoRepository.getConn().close();
            System.out.println("Termino la funcion listar productos");
        }
    }

    @Override
    public ProductoDto porIdProducto(Long id) throws SQLException {
        try{
            productoRepository.setConn(ConexionBD_M2C12.getConnection());
            return productoRepository.findForId(id);
        }finally {
            productoRepository.getConn().close();
            System.out.println("Termino la funcion listar productos por id");
        }
    }

    @Override
    public ProductoDto guardarProducto(ProductoDto productoDto) throws SQLException {
        try{
            productoRepository.setConn(ConexionBD_M2C12.getConnection());
            if (productoRepository.getConn().getAutoCommit() == true) {
                productoRepository.getConn().setAutoCommit(false);
            }
            return productoRepository.save(productoDto);
        }catch (SQLException e) {
            productoRepository.getConn().rollback();
            throw new SQLException(e);
        }
        finally {
            productoRepository.getConn().commit();
            productoRepository.getConn().close();
            System.out.println("Termino la funcion de guardar por producto");
        }
    }

    @Override
    public Integer eliminarProducto(Long id) throws SQLException {
        try {
            productoRepository.setConn(ConexionBD_M2C12.getConnection());
            if (productoRepository.getConn().getAutoCommit() == true) {
                productoRepository.getConn().setAutoCommit(false);
            }
            return productoRepository.delete(id);
        } catch (SQLException e) {
            productoRepository.getConn().rollback();
            throw new SQLException(e);
        }
        finally {
            productoRepository.getConn().commit();
            productoRepository.getConn().close();
            System.out.println("Termino la funcion de elimminar por producto");
        }
    }

    @Override
    public List<CategoriaDto> listarCategoria() throws SQLException {
        try{
            categoriaRepository.setConn(ConexionBD_M2C12.getConnection());
            return categoriaRepository.listar();
        }finally {
            categoriaRepository.getConn().close();
            System.out.println("Termino la funcion de listar categoria");
        }
    }

    @Override
    public CategoriaDto porIdCategoria(Long id) throws SQLException {
        try{
            categoriaRepository.setConn(ConexionBD_M2C12.getConnection());
            return categoriaRepository.findForId(id);
        }finally {
            categoriaRepository.getConn().close();
            System.out.println("Termino la funcion de listar categoria por id");
        }
    }

    @Override
    public CategoriaDto crearCategoria(CategoriaDto c) throws SQLException {
        try{
            categoriaRepository.setConn(ConexionBD_M2C12.getConnection());
            if (categoriaRepository.getConn().getAutoCommit() == true) {
                productoRepository.getConn().setAutoCommit(false);
            }
             return categoriaRepository.save(c);
        }catch (SQLException e) {
            categoriaRepository.getConn().rollback();
            throw new SQLException(e);}
        finally {
            categoriaRepository.getConn().commit();
            categoriaRepository.getConn().close();
            System.out.println("Termino la funcion de guardar categorias con producto");
        }
    }

    @Override
    public Integer eliminarCategoria(Long id) throws SQLException {
        try{
            categoriaRepository.setConn(ConexionBD_M2C12.getConnection());
            if (categoriaRepository.getConn().getAutoCommit() == true) {
                productoRepository.getConn().setAutoCommit(false);
            }
            return categoriaRepository.delete(id);
        } catch (SQLException e) {
            categoriaRepository.getConn().rollback();
            throw new SQLException(e);
        } finally {
            categoriaRepository.getConn().commit();
            categoriaRepository.getConn().close();
            System.out.println("Termino la funcion de eliminar categorias con producto");
        }
    }
}
