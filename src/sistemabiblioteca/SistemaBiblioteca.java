package sistemabiblioteca;

import java.util.Scanner;

public class SistemaBiblioteca {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        Scanner s = new Scanner(System.in);
        int entrada = 100;
        biblioteca.recuperarDados();
        biblioteca.AnalisarEmprestimos();
        System.out.println("Bem Vindo, escolha a operaçao a ser realizada:");
        while (entrada != 9) {
            System.out.println("");
            System.out.println("1 para cadastrar usuário");
            System.out.println("2 para cadastrar livro");
            System.out.println("3 para retirar livro");
            System.out.println("4 para devolver livro");
            System.out.println("5 mostrar livros atrasados");
            System.out.println("6 para mostrar todos os usuarios");
            System.out.println("7 mostrar todos os livros");
            System.out.println("8 para mostrar todos os emprestimos");
            System.out.println("9 para sair");
            entrada = s.nextInt();
            if (entrada == 1) {
                biblioteca.cadastrarPessoa();
            } else if (entrada == 2) {
                biblioteca.cadastrarLivro();
            } else if (entrada == 3) {
                biblioteca.retirarLivro();
            } else if (entrada == 4) {
                biblioteca.devolverLivro();
            } else if (entrada == 5) {
                biblioteca.imprimirAtrasados();
            } else if (entrada == 6) {
                biblioteca.imprimirPessoas();
            } else if (entrada == 7) {
                biblioteca.imprimirLivros();
            } else if (entrada == 8) {
                biblioteca.imprimirEmprestimos();
            }
            System.out.println("Press any key to continue...");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        }
        biblioteca.salvarDados();
        System.out.println("Programa finalizado!");
        System.exit(0);
    }

}
