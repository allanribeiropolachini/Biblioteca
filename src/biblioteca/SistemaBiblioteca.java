/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Adriller
 */
public class SistemaBiblioteca extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        Scanner s = new Scanner(System.in);
        int entrada = 100;
        biblioteca.recuperarDados();
        biblioteca.AnalisarEmprestimos();
        System.out.println("Bem Vindo, escolha a operaçao a ser realizada:");
        while (entrada != 10) {
            System.out.println("");
            System.out.println("1 para cadastrar usuário");
            System.out.println("2 para cadastrar livro");
            System.out.println("3 para retirar livro");
            System.out.println("4 para devolver livro");
            System.out.println("5 para renovar livro");
            System.out.println("6 mostrar livros atrasados");
            System.out.println("7 para mostrar todos os usuarios");
            System.out.println("8 mostrar todos os livros");
            System.out.println("9 para mostrar todos os emprestimos");
            System.out.println("10 para sair");
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
                //biblioteca.renovarLivro();
            } else if (entrada == 6) {
                biblioteca.imprimirAtrasados();
            } else if (entrada == 7) {
                biblioteca.imprimirPessoas();
            } else if (entrada == 8) {
                biblioteca.imprimirLivros();
            } else if (entrada == 9) {
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
