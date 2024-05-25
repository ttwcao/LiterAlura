package br.com.catalogo.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosAutor {
    private String name;
    @JsonProperty("birth_year")
    private int birthYear;
    @JsonProperty("death_year")
    private int deathYear;

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }
}

//@JsonIgnoreProperties(ignoreUnknown = true)
//public record DadosAutor(@JsonProperty("name") String nome,
//                         @JsonProperty("birth_year") Integer nascAno,
//                         @JsonProperty("death_year") Integer mortAno) {
//}

