package jdbc.models;

/**
 * CLase creada para replicar y adquirir los conocimientos
 * santalbu
 * 20220907
 *
 * Clase 13,19
 *
 * clase 13 se crea el dto
 * clase 19 se agrega la relacion con categoriasDTO
 *
 * Se crea el DTO para acceder a los datos de mejor manera
 */


import java.util.Date;

public class ProductoDto {

    private Long id;
    private String nombre;
    private Double precio;
    private String fecha;
    private CategoriaDto idCategoriaFk;

    public ProductoDto() {
    }

    public ProductoDto(Long id, String nombre, Double precio, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public CategoriaDto getIdCategoriaFk() {
        return idCategoriaFk;
    }

    public void setIdCategoriaFk(CategoriaDto categoriaFk) {
        this.idCategoriaFk = categoriaFk;
    }
}
