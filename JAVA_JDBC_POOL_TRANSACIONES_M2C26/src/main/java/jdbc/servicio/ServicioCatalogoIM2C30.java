package jdbc.servicio;

import jdbc.models.CategoriaDto;
import jdbc.models.ProductoDto;

import java.sql.SQLException;
import java.util.List;

public interface ServicioCatalogoIM2C30 {

    List<ProductoDto> listarProducto() throws SQLException;

    ProductoDto porIdProducto(Long id) throws SQLException;

    ProductoDto guardarProducto(ProductoDto productoDto) throws SQLException;

    Integer eliminarProducto(Long id) throws SQLException;

    List<CategoriaDto> listarCategoria() throws SQLException;

    CategoriaDto porIdCategoria(Long id) throws SQLException;

    CategoriaDto crearCategoria(CategoriaDto p) throws SQLException;
    Integer eliminarCategoria(Long id) throws SQLException;

}
