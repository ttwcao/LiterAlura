package br.com.catalogo.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer nascAno;
    private Integer mortAno;

    @ManyToOne
    private Livro livro;

    public Autor() {}

    public Autor(DadosAutor dadosAutor, Livro livro) {
        String[] autor = dadosAutor.nome().split(",");
        this.nome = autor[1] + " " + autor[0];
        this.nascAno = dadosAutor.nascAno();
        this.mortAno = dadosAutor.mortAno();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNascAno() {
        return nascAno;
    }

    public void setNascAno(Integer nascAno) {
        this.nascAno = nascAno;
    }

    public Integer getMortAno() {
        return mortAno;
    }

    public void setMortAno(Integer mortAno) {
        this.mortAno = mortAno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public String toString(){
        return
                nome;
    }

    //    //anotação para o relacionamento
//    @OneToMany(mappedBy = "autor")
//    //cada autor tem uma lista de livros.
//    private List<LivroOld> livroOlds = new ArrayList<>();


}