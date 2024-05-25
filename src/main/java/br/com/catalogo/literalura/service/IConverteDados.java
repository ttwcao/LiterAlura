package br.com.catalogo.literalura.service;

import java.util.List;

public interface IConverteDados {
    <T> T ObterDados(String json, Class<T> classe);

}
