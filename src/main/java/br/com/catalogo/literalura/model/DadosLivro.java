package br.com.catalogo.literalura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonProperty("title") String titulo,
                         @JsonProperty("authors") List<DadosAutor> autores,
                         @JsonProperty("download_count") Integer contadorDown,
                         @JsonProperty("languages") List<String> idiomas) {
}
