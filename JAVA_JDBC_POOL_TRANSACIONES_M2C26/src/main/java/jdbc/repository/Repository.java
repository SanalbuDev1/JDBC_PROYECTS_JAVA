package jdbc.repository;

/**
 * CLase creada para replicar y adquirir los conocimientos
 * santalbu
 * 20220907
 *
 * Clase 13

 * Se crea la interfaz reposiory generica para poder accerder a  datos las clase
 * que implementen esta interfaz atraves del objeto generico*/


import jdbc.models.CategoriaDto;
import jdbc.models.ProductoDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

    List<T> listar() throws SQLException;
    T findForId(Long id) throws SQLException;
    T save(T t) throws SQLException;
    Integer delete(Long t) throws SQLException;
    void setConn(Connection conn);

    Connection getConn();

}
