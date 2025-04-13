package it.lessons.spring.spring_la_mia_pizzeria_crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "pizza") 
public class Pizza {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message="Nome obbligatorio")
    @Column(unique=true, nullable=false)
    private String nome;

    @Column(length=1000)
    private String descrizione;

    @NotNull(message = "Prezzo obbligatorio")
    @DecimalMin(value = "1.00", inclusive = true, message="Il prezzo deve essere maggiore di 0")
    private Double prezzo;

    private String foto;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public Double getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
}