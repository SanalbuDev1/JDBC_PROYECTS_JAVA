package jdbc.repository;

/**
 * CLase creada para replicar y adquirir los conocimientos
 * santalbu
 * 20220907
 *
 * Clase 13

 * Se crea la interfaz reposiory generica para poder accerder a  datos las clase
 * que implementen esta interfaz atraves del objeto generico*/


import java.util.List;

public interface Repository<T> {

    List<T> listar();
    T findForId(Long id);
    Integer save(T t);
    Integer delete(T t);

}
