package jdbc.repository;

/**
 * CLase creada para replicar y adquirir los conocimientos
 * santalbu
 * 20220907
 *
 * Clase 13

 * Se crea la interfaz reposiory generica para poder accerder a  datos las clase
 * que implementen esta interfaz atraves del objeto generico*/


import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

    List<T> listar() throws SQLException;
    T findForId(Long id) throws SQLException;
    Integer save(T t) throws SQLException;
    Integer delete(T t) throws SQLException;

}
