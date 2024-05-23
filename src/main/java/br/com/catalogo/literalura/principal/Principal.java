package br.com.catalogo.literalura.principal;

import br.com.catalogo.literalura.model.DadosLivro;
import br.com.catalogo.literalura.model.Livro;
import br.com.catalogo.literalura.model.ResultadoDados;
import br.com.catalogo.literalura.repository.LivroRepository;
import br.com.catalogo.literalura.service.ConsumoApi;
import br.com.catalogo.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final LivroRepository repositorio;

    private Scanner leitura = new Scanner(System.in);
    private final String URL = "http://gutendex.com/books/?search=";
    //private final String URL = "https://parallelum.com.br/fipe/api/v1/carros";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    List<Livro> livros = new ArrayList<>();

    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                        ================================================
                                     Bem-vindo ao LiterAlura
                               Escolha o número da opção no menu:                    
                        ================================================
                                            
                        1 - Buscar livro pelo título
                        2 - Listar livros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos em um determinado ano
                        5 - Listar livroOlds em um determinado idioma                                  
                        0 - Sair
                        """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivreIdioma();
                    break;
                case 0:
                    System.out.println("Encerrando a LiterAlura!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }


    }

    private ResultadoDados obterDadosLivro(String tituloBusca) {
        var json = consumoApi.obterDados(URL + tituloBusca.toLowerCase().replace(" ", "%20"));
        //System.out.println(json);
        return conversor.ObterDados(json, ResultadoDados.class);
    }

    private void buscarLivro() {

        var cadastrarNovo = "s";
        //while (cadastrarNovo.equalsIgnoreCase("s")) {

            System.out.println("Informe o título do livro: ");
            var tituloBusca = leitura.nextLine();

            ResultadoDados resultadoDados = obterDadosLivro(tituloBusca);

            List<DadosLivro> dadosLivros = resultadoDados.resultados().stream().toList();
        System.out.println(dadosLivros);
//            dadosLivros.forEach(l -> livros.add(new Livro(l)));
//            try{
//                livros.forEach(l -> repositorio.save(l));
//            } catch (Exception e){
//
//            }
//            System.out.println(repositorio.findBytituloContainingIgnoreCase‎(tituloBusca));




//            //construtor para salvar o Livro
//            Livro livro = new Livro(tituloBusca);
//            //método para salvar o livro no banco
//            repositorio.save(livro);
            //System.out.println("Deseja cadastrar novo Livro?");
            //cadastrarNovo = leitura.nextLine();
       //}
    }



    private void listarLivros() {

    }
    private void listarAutores() {

    }
    private void listarAutoresVivos() {

    }
    private void listarLivreIdioma() {

    }

}
