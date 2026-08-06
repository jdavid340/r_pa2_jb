package edu.com.uce.domain.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mate_seq")
    @SequenceGenerator(name = "mate_seq", sequenceName = "mate_seq", allocationSize = 1)
    @Column(name = "mate_id")
    private Integer id;

    @Column(name = "mate_codigo")
    private String codigo;

    @Column(name = "mate_nombre")
    private String nombre;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Matricula> matriculas = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}