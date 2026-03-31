package Infraestrutura.Roteadores;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class RoteadorConsole {

    private final String titulo;
    private final Map<Integer, String> descricoes;
    private final Map<Integer, Runnable> acoes;
    private final Map<Integer, RoteadorConsole> subRoteadores;
    private Scanner scanner;

    public RoteadorConsole(String titulo) {
        this.titulo = titulo;
        this.descricoes = new LinkedHashMap<>();
        this.acoes = new LinkedHashMap<>();
        this.subRoteadores = new LinkedHashMap<>();
        this.scanner = new Scanner(System.in);
    }

    // Injeta Acao de Controlador e segue um padrão Builder
    public RoteadorConsole adicionarRota(int chave, String texto, Runnable acao) {
        // Verifica se é um sub-menu (navegação)
        if (subRoteadores.containsKey(chave)) {
            return this;
        }

        descricoes.put(chave, texto);
        acoes.put(chave, acao);

        return this;
    }

    // injeta subRoteador
    public RoteadorConsole adicionarSubRoteador(int chave, String texto, RoteadorConsole proximoRoteador) {
        descricoes.put(chave, texto);
        subRoteadores.put(chave, proximoRoteador);
        return this;
    }

    public RoteadorConsole SetScanner(Scanner in) {
        this.scanner = in;
        return this;
    }

    public RoteadorConsole Roteamento(int opcao) {
        // if (opcao == 0) return null; // Padrão para voltar

        // Verifica se é um sub-menu (navegação)
        if (subRoteadores.containsKey(opcao)) {
            return subRoteadores.get(opcao);
        }

        // Verifica se é uma ação (execução)
        if (acoes.containsKey(opcao)) {
            if (acoes.get(opcao) == null)
                return null;
            else
                acoes.get(opcao).run();

            return this; // Mantém na mesma tela após executar
        }

        System.out.println("Opção inválida!");
        return this;
    }

    public Map<Integer, String> GetMenu() {
        return descricoes;
    }

    public Scanner GetScanner() {
        return scanner;
    }

    public String GetTitulo() {
        return titulo;
    }

    public void Voltar() {
        return;
    }
}