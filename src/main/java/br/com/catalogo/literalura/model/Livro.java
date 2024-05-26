package br.com.catalogo.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//notação de entidade para o banco de dados
@Entity

//cdefinição do nome da tabela no banco
@Table(name="livros")
public class Livro {

    //parametros para coluna ID do bando de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //definição de registro único de nome para livro
    @Column(unique = true)
    private String titulo;

    private String idiomas;

    //private List<Idioma> idiomas = new ArrayList<>();
    private Integer contadorDown;

    //relacionamento com tabela autor
    //@Transient //não persistir o campo no banco
    //cascade instrui o JPA a gravar pela persistencia
    @OneToMany (mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores = new ArrayList<>();

    //construto padrão
    public Livro(){}
    //public Livro(ResultadoApi dadosLivros){}

        public Livro(DadosLivro livro){
            this.titulo = livro.getTitle();
//            this.idiomas = new ArrayList<>();
//            for (String idioma: livro.getLanguages()){
//                this.idiomas.add(Idioma.fromCode(idioma.toUpperCase()));
//            }
            this.contadorDown = livro.getDownloadCount();
            for(DadosAutor dadosAutor : livro.getAuthors()){
                Autor autor = new Autor(dadosAutor, this);
                this.autores.add(autor);
            }

        }

    // Getters e setters
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

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
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
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "Livro: " +
                "Título = '" + titulo + '\'' +
                ", Idiomas =" + idiomas +
                ", Downloads realizados = " + contadorDown +
                ", Autores = " + autores;
    }
}




