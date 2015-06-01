package sistemabiblioteca;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {

    private int dia, mes, ano;
    ArrayList<Livro> livros = new ArrayList<>();
    ArrayList<Livro> livrosDisponiveis = new ArrayList<>();
    ArrayList<Livro> livrosEmprestados = new ArrayList<>();
    ArrayList<Pessoa> usuarios = new ArrayList<>();
    Scanner s = new Scanner(System.in);
    String nome, tipo, str;

    public Biblioteca(int d, int m, int a) {
        dia = d;
        mes = m;
        ano = a;
    }

    void recuperarDados() {
        File file = new File("data.csv");
        int i = 0;
        int index;
        Pessoa p;
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String line = inputStream.nextLine();
                String[] campo = line.split(",");
                /*Le os usuarios*/
                while (!campo[i].equals("fim")) {

                    p = this.cadastrarPessoaArq(campo[i + 3], campo[i + 4], Integer.parseInt(campo[i]), Integer.parseInt(campo[i + 1]), Integer.parseInt(campo[i + 2]));
                    p.bloquear(Integer.parseInt(campo[i + 5]), Integer.parseInt(campo[i + 6]), Integer.parseInt(campo[i + 7]));

                    i += 8;
                }
                i = 0;
                line = inputStream.nextLine();
                campo = line.split(",");
                while (!campo[i].equals("fim")) {

                    this.cadastrarLivroArq(campo[i + 3], campo[i + 4], campo[i + 5], Integer.parseInt(campo[i]), Integer.parseInt(campo[i + 1]), Integer.parseInt(campo[i + 2]));

                    i += 6;
                }
                i = 0;
                line = inputStream.nextLine();
                campo = line.split(",");
                while (!campo[i].equals("fim")) {

                    this.retirarLivroArq(campo[i + 3], campo[i + 4], Integer.parseInt(campo[i + 5]), Integer.parseInt(campo[i + 6]), Integer.parseInt(campo[i + 7]), Integer.parseInt(campo[i]), Integer.parseInt(campo[i + 1]), Integer.parseInt(campo[i + 2]));

                    i += 8;
                }

            }
        } catch (Exception e) {
            System.out.println("Erro ao ler dados: " + e);
        }

    }

    void salvarDados() {
        try {
            File file = new File("data.csv");
            file.delete();
            try (FileWriter writer = new FileWriter("data.csv")) {

                /*Escreve os usuarios*/
                for (Pessoa p : usuarios) {
                    writer.append(Integer.toString(p.getCadastroDia()));
                    writer.append(',');
                    writer.append(Integer.toString(p.getCadastroMes()));
                    writer.append(',');
                    writer.append(Integer.toString(p.getCadastroAno()));
                    writer.append(',');
                    writer.append(p.getNome());
                    writer.append(',');
                    writer.append(p.getTipo());
                    writer.append(',');
                    writer.append(Integer.toString(p.getDiaBloq()));
                    writer.append(',');
                    writer.append(Integer.toString(p.getMesBloq()));
                    writer.append(',');
                    writer.append(Integer.toString(p.getAnoBloq()));
                    writer.append(',');
                }
                writer.append("fim");
                writer.append('\n');

                /*escreve os livros*/
                for (Livro l : livros) {
                    writer.append(Integer.toString(l.getCadastroDia()));
                    writer.append(',');
                    writer.append(Integer.toString(l.getCadastroMes()));
                    writer.append(',');
                    writer.append(Integer.toString(l.getCadastroAno()));
                    writer.append(',');
                    writer.append(l.getNome());
                    writer.append(',');
                    writer.append(l.getAutor());
                    writer.append(',');
                    writer.append(l.getTipo());
                    writer.append(',');
                }
                writer.append("fim");
                writer.append('\n');
                /*escreve os livros emprestados*/
                for (Livro l : livrosEmprestados) {
                    writer.append(Integer.toString(l.getEmprestimoDia()));
                    writer.append(',');
                    writer.append(Integer.toString(l.getEmprestimoMes()));
                    writer.append(',');
                    writer.append(Integer.toString(l.getEmprestimoAno()));
                    writer.append(',');
                    writer.append(l.getResponsavel());
                    writer.append(',');
                    writer.append(l.getNome());
                    writer.append(',');
                    writer.append(Integer.toString(l.getVencimentoDia()));
                    writer.append(',');
                    writer.append(Integer.toString(l.getVencimentoMes()));
                    writer.append(',');
                    writer.append(Integer.toString(l.getVencimentoAno()));
                    writer.append(',');
                }
                writer.append("fim");
                writer.append('\n');
                //close the writer
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler dados: " + e);
        }
    }

    void cadastrarLivro() {
        String autor;
        System.out.println("Digite o nome:");
        nome = s.nextLine().toUpperCase();
        System.out.println("Digite o autor:");
        autor = s.nextLine().toUpperCase();
        System.out.println("Digite o tipo (Texto ou Geral):");
        tipo = s.nextLine().toUpperCase();
        Livro l = new Livro(nome, autor, tipo, dia, mes, ano);
        livros.add(l);
        livrosDisponiveis.add(l);
        System.out.println("Livro cadastraco com sucesso!");
    }

    void cadastrarLivroArq(String nome, String autor, String tipo, int cadDia, int cadMes, int cadAno) {
        Livro l = new Livro(nome, autor, tipo, cadDia, cadMes, cadAno);
        livros.add(l);
        livrosDisponiveis.add(l);
        System.out.println("Livro cadastraco com sucesso!");
    }

    void cadastrarPessoa() {
        System.out.println("Digite o nome:");
        nome = s.nextLine().toUpperCase();
        System.out.println("Digite o tipo (Professor, Aluno ou Comunidade):");
        tipo = s.nextLine().toUpperCase();
        Professor p = new Professor(nome, dia, mes, ano, dia, mes, ano);
        Aluno a = new Aluno(nome, dia, mes, ano, dia, mes, ano);
        NaoEstudante e = new NaoEstudante(nome, dia, mes, ano, dia, mes, ano);
        if (null != tipo) {
            switch (tipo) {
                case "PROFESSOR":
                    usuarios.add(p);
                    System.out.println("Professor adicionado!");
                    break;
                case "ALUNO":
                    usuarios.add(a);
                    System.out.println("Aluno adicionado");
                    break;
                case "COMUNIDADE":
                    usuarios.add(e);
                    System.out.println("Usuario da comunidade adicionado!");
                    break;
                default:
                    System.out.println("ERRO: Tipo invalido");
                    break;
            }
        }

    }

    Pessoa cadastrarPessoaArq(String nome, String tipo, int cadastroDia, int cadastroMes, int cadastroAno) {
        Professor p = new Professor(nome, dia, mes, ano, cadastroDia, cadastroMes, cadastroAno);
        Aluno a = new Aluno(nome, dia, mes, ano, cadastroDia, cadastroMes, cadastroAno);
        NaoEstudante e = new NaoEstudante(nome, dia, mes, ano, cadastroDia, cadastroMes, cadastroAno);
        if (null != tipo) {
            switch (tipo) {
                case "PROFESSOR":
                    usuarios.add(p);
                    System.out.println("Professor adicionado!");
                    return p;
                case "ALUNO":
                    usuarios.add(a);
                    System.out.println("Aluno adicionado");
                    return a;
                case "COMUNIDADE":
                    usuarios.add(e);
                    System.out.println("Usuario da comunidade adicionado!");
                    return e;
                default:
                    System.out.println("ERRO: Tipo invalido");
                    break;
            }
        }
        return null;
    }

    void retirarLivro() {
        int indexLivro, indexUsuario, vencDia, vencMes, vencAno;
        System.out.println("Digite o nome do usuario:");
        nome = s.nextLine().toUpperCase();
        System.out.println("Digite o nome do livro");
        str = s.nextLine().toUpperCase();
        indexLivro = -1;
        for (Livro l : livrosDisponiveis) {
            if ((l.getCadastroDia() + (l.getCadastroMes() * 30) + (l.getCadastroAno() * 360)) <= (dia + (mes * 30) + (ano * 360))) {
                if (l.getNome().equals(str)) {
                    indexLivro = livrosDisponiveis.indexOf(l);
                }
            }
        }
        indexUsuario = -1;
        for (Pessoa p : usuarios) {
            if ((p.getCadastroDia() + ((p.getCadastroMes()) * 30) + (p.getCadastroAno() * 360)) <= (dia + (mes * 30) + (ano * 360))) {
                if (p.getNome().equals(nome)) {
                    indexUsuario = usuarios.indexOf(p);
                }
            }
        }
        if (indexLivro == -1) {
            System.out.println("Esse livro nao existe ou nao esta disponivel");
        } else if (indexUsuario == -1) {
            System.out.println("Usuario nao cadastrado");
        } else {
            if ("PROFESSOR".equals(str)) {
                vencDia = dia;
                vencMes = mes + 2;
                vencAno = ano;
                if (vencMes > 12) {
                    vencMes = vencMes - 12;
                    vencAno++;
                }
            } else {
                vencDia = dia + 15;
                vencMes = mes;
                vencAno = ano;
                if (vencDia > 30) {
                    vencDia = vencDia - 30;
                    vencMes++;
                    if (vencMes > 12) {
                        vencAno++;
                    }
                }
            }
            if (usuarios.get(indexUsuario).retirarLivro(livrosDisponiveis.get(indexLivro), vencDia, vencMes, vencAno, dia, mes, ano)) {
                livrosEmprestados.add(livrosDisponiveis.get(indexLivro));
                livrosDisponiveis.remove(livrosDisponiveis.get(indexLivro));
            }
        }
    }

    void retirarLivroArq(String nome, String str, int vencDia, int vencMes, int vencAno, int empDia, int empMes, int empAno) {
        int indexLivro, indexUsuario;

        indexLivro = -1;
        for (Livro l : livrosDisponiveis) {
            if (l.getNome().equals(str)) {
                indexLivro = livrosDisponiveis.indexOf(l);
            }
        }
        indexUsuario = -1;
        for (Pessoa p : usuarios) {
            if (p.getNome().equals(nome)) {
                indexUsuario = usuarios.indexOf(p);
            }
        }
        if (indexLivro == -1) {
            System.out.println("Esse livro nao existe ou nao esta disponivel");
        } else if (indexUsuario == -1) {
            System.out.println("Usuario nao cadastrado");
        } else {
            if (usuarios.get(indexUsuario).retirarLivro(livrosDisponiveis.get(indexLivro), vencDia, vencMes, vencAno, empDia, empMes, empAno)) {
                livrosEmprestados.add(livrosDisponiveis.get(indexLivro));
                livrosDisponiveis.remove(livrosDisponiveis.get(indexLivro));
            }
        }
    }

    void devolverLivro() {
        int indexUsuario = -1;
        System.out.println("Digite o nome do usuario:");
        nome = s.nextLine().toUpperCase();
        System.out.println("Digite o nome do livro");
        str = s.nextLine().toUpperCase();
        for (Pessoa p : usuarios) {
            if ((p.getCadastroDia() + ((p.getCadastroMes()) * 30) + (p.getCadastroAno() * 360)) <= (dia + (mes * 30) + (ano * 360))) {
                if (p.getNome().equals(nome)) {
                    indexUsuario = usuarios.indexOf(p);
                }
            }
        }
        if (indexUsuario == -1) {
            System.out.println("Usuario nao existe");
        } else {

            Livro l = usuarios.get(indexUsuario).devolverLivro(str);
            if (l != null) {
                livrosDisponiveis.add(l);
                livrosEmprestados.remove(l);
            }
        }
    }

    void imprimirPessoas() {
        usuarios.stream().forEach((p) -> {
            if ((p.getCadastroDia() + ((p.getCadastroMes()) * 30) + (p.getCadastroAno() * 360)) <= (dia + (mes * 30) + (ano * 360))) {
                System.out.println(p.getTipo() + ": " + p.getNome());
            }

        });
    }

    void imprimirLivros() {
        livros.stream().forEach((l) -> {
            if ((l.getEmprestimoDia() + (l.getEmprestimoMes() * 30) + (l.getEmprestimoAno() * 360)) <= (dia + (mes * 30) + (ano * 360))) {
                System.out.println(l.getAutor() + ", " + l.getNome());
            }
        });
    }

    void imprimirEmprestimos() {
        livrosEmprestados.stream().forEach((l) -> {
            if ((l.getEmprestimoDia() + (l.getEmprestimoMes() * 30) + (l.getEmprestimoAno() * 360)) <= (dia + (mes * 30) + (ano * 360))) {
                System.out.println(l.getResponsavel() + ": " + l.getNome());
            }

        });
    }

    void imprimirAtrasados() {
        livrosEmprestados.stream().forEach((l) -> {
            if ((l.getVencimentoDia() + (l.getVencimentoMes() * 30) + (l.getVencimentoAno() * 360)) <= (dia + (mes * 30) + (ano * 360))) {
                if ((l.getEmprestimoDia() + (l.getEmprestimoMes() * 30) + (l.getEmprestimoAno() * 360)) < (dia + (mes * 30) + (ano * 360))) {
                    System.out.println(l.getResponsavel() + ": " + l.getNome());
                }
            }
        });
    }

    int getDia() {
        return dia;
    }

    int getMes() {
        return mes;
    }

    int getAno() {
        return ano;
    }

    void AnalisarEmprestimos() {
        usuarios.stream().forEach((p) -> {
            p.verificarBloqueio();
        });
    }
}
