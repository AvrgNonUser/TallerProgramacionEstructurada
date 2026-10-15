package model;

import jakarta.persistence.*;

@Entity
@Table(name = "registros")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String descripcion;
    private Double valor;

    // Constructor vacío (obligatorio para JPA)
    public Registro() {}

    // Constructor con parámetros
    public Registro(String codigo, String descripcion, Double valor) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}
