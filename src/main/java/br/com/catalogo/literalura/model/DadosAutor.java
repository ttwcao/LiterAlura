package br.com.catalogo.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonProperty("name") String nome,
                         @JsonProperty("birth_year") Integer nascAno,
                         @JsonProperty("death_year") Integer mortAno) {
}
