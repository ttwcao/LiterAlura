package br.com.catalogo.literalura.service;

import java.util.List;

public interface IConverteDados {

    //aplicação do tipo genérico para devovler alguma entidade
    //passa o json para tratar e requisita que
    // devolva uma classe genérica
    <T> T ObterDados(String json, Class<T> classe);

}
