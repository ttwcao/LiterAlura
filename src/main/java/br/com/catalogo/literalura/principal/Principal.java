package br.com.catalogo.literalura.principal;

import br.com.catalogo.literalura.model.*;
import br.com.catalogo.literalura.repository.LivroRepository;
import br.com.catalogo.literalura.service.ConsumoApi;
import br.com.catalogo.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.midi.Soundbank;
import javax.swing.text.html.Option;
import java.sql.SQLOutput;
import java.util.*;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private final String URL = "http://gutendex.com/books/?search=";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private List<DadosLivro> dadosLivros = new ArrayList<>();

    //referencia para o JPA passado na aplicação
    private LivroRepository repositorio;

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
                        5 - Listar livro em um determinado idioma                                  
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

    private void buscarLivro() {

        System.out.println("Informe o título do livro: ");
        var tituloBusca = leitura.nextLine();
        var cadastrarNovo = "s";

        while (cadastrarNovo.equalsIgnoreCase("s")) {

            var tituloBuscaFormatado = tituloBusca.toLowerCase().replace(" ", "%20");
            var json = consumoApi.obterDados(URL + tituloBuscaFormatado);
            ResultadoApi resultadoApi = conversor.ObterDados(json, ResultadoApi.class);

            if (resultadoApi != null && resultadoApi.getResults() != null && !resultadoApi.getResults().isEmpty()) {
                System.out.println(" \n =============== Obra(s) Localizadas =============== \n");

                for (DadosLivro livro : resultadoApi.getResults()) {
                    System.out.println("Título: " + livro.getTitle());
                    System.out.println("Autore(s) da obra ");

                    for (DadosAutor autor : livro.getAuthors()) {
                        System.out.println("  Nome: " + autor.getName());
                        System.out.println("  Ano de Nascimento: " + autor.getBirthYear());
                        System.out.println("  Ano de Morte: " + autor.getDeathYear());
                    }
                    System.out.println("Idiomas: " + String.join(", ", livro.getLanguages()));
                    System.out.println("Downloads até agora: " +livro.getDownloadCount());
                }
                System.out.println("===================================\n" +
                        "Deseja salvar o(s) título(s)? (s/n) \n" +
                        "=================================== \n");
                var salvarBanco = leitura.nextLine();

                if(salvarBanco.equalsIgnoreCase("s")){
                    System.out.println("\n ***** dados salvos com sucesso ***** \n");
                    List<Livro> livros = new ArrayList<>();

                    //eliminando titulos duplicados
                    Set<String> titulosUnicos = new HashSet<>();

                    for (DadosLivro dadosLivro:resultadoApi.getResults()){

                        if(titulosUnicos.add(dadosLivro.getTitle())) {
                            Livro livro = new Livro(dadosLivro);

                                // Comparação do idioma retornado com os valores do enum
                                for(String idiomaStr: dadosLivro.getLanguages()){
                                    Idioma idiomaEnum = Idioma.fromCode(idiomaStr.toLowerCase());
                                            if(idiomaEnum != null){
                                                livro.setIdiomas(idiomaEnum.getIdiomaTraduzido());
                                                break;
                                            }
                                }

                            //salvar dados no banco
                            livros.add(livro);
                            repositorio.save(livro);
                        } else {
                            System.out.println("Títulos duplicados não registrados: " + dadosLivro.getTitle());
                        }
                    }

                    cadastrarNovo = "n";
                } else {
                    cadastrarNovo = "n";
                }
            } else {
                System.out.println("Nenhum título encontrado com este nome: " +tituloBusca);
                System.out.println("Deseja pesquisar outro título? (s/n)");
                cadastrarNovo = leitura.nextLine();
            }
        }
    }



    private void listarLivros() {
        List <Livro> acervo = repositorio.findAll();
        if(acervo.isEmpty()){
            System.out.println("Acervo ainda sem registros! \n");
            acervo.forEach(System.out::println);
        } else {
            System.out.println("Acervo Registrado: \n");
            acervo.forEach(System.out::println);
        }
    }

    private void listarAutores() {
        List<String> acervo = repositorio.findNomeAutores();
        if(acervo.isEmpty()){
            System.out.println("Acervo ainda sem registros! \n");
            acervo.forEach(System.out::println);
        } else {
            System.out.println("Acervo Registrado: \n");
            acervo.forEach(System.out::println);
        }

    }
    private void listarAutoresVivos() {
        System.out.println("Digite o ano que deseja pesquisar: ");
        Integer ano = leitura.nextInt();
        List<Autor> acervo = repositorio.findAutoresEmDeterminadoAno(ano);
        if(acervo.isEmpty()){
            System.out.println("Acervo ainda sem registros! \n");
            acervo.forEach(System.out::println);
        } else {
            System.out.println("Acervo Registrado: \n");
            acervo.forEach(System.out::println);
        }

    }
    private void listarLivreIdioma() {
        System.out.println("Digite o idioma que deseja consultarr: ");
        String idioma = leitura.nextLine();

    }

}
