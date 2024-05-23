package br.com.catalogo.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String[] idiomas;
    private Integer contadorDown;

    @OneToMany(mappedBy = "livro")
    private List<Autor> autores = new ArrayList<>();

    public Livro(){}

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.idiomas = dadosLivro.idiomas().toArray(new String[0]);
        this.contadorDown = dadosLivro.contadorDown();
        List<DadosAutor> dadosAutores = dadosLivro.autores().stream().toList();
        dadosAutores.forEach(a -> autores.add(new Autor(a,this)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String[] getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String[] idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getContadorDown() {
        return contadorDown;
    }

    public void setContadorDown(Integer contadorDown) {
        this.contadorDown = contadorDown;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach(a -> a.setLivro(this));
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "Livro: " +
                "título: '" + titulo + '\'' +
                ", idiomas: " + Arrays.toString(idiomas) +
                ", downloads: " + contadorDown +
                ", autor(s): " + autores ;
    }

    private String nomeAutor(){
        StringBuilder nomeAutor = new StringBuilder();
        for (Autor a: autores){
            nomeAutor.append(a.getNome()).append(", ");
        }
        return nomeAutor.toString();
    }
}
