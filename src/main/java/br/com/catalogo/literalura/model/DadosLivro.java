package br.com.catalogo.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosLivro {
    private String title;
    private List<DadosAutor> authors;
    private List<String> languages;

    @JsonProperty("download_count")
    private int downloadCount;

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DadosAutor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<DadosAutor> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }
}


//@JsonIgnoreProperties(ignoreUnknown = true)
//public record DadosLivro(@JsonAlias("title") String titulo,
//                         @JsonAlias("authors") List<DadosAutor> autores,
//                         @JsonAlias("download_count") Integer contadorDown,
//                         @JsonAlias("languages") String[] idiomas,
//                         @JsonAlias("formats") Map<String, String> formatos){
//}
