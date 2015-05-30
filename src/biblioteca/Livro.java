/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

/**
 *
 * @author Adriller
 */
public class Livro {

    private String nome, autor, tipo;
    private String nomeResponsavel, tipoResponsavel;
    private int vencDia, vencMes, vencAno;
    private final int cadastroDia, cadastroMes, cadastroAno;
    private int emprestadoDia, emprestadoMes, emprestadoAno;

    public Livro(String n, String a, String t, int cadDia, int cadMes, int cadAno) {
        nome = n;
        autor = a;
        tipo = t;
        cadastroDia = cadDia;
        cadastroMes = cadMes;
        cadastroAno = cadAno;
    }

    String getNome() {
        return nome;
    }

    void setNome(String n) {
        nome = n;
    }

    String getAutor() {
        return autor;
    }

    void setAutor(String a) {
        autor = a;
    }

    void setTipo(String t) {
        tipo = t;
    }

    String getTipo() {
        return tipo;
    }

    void emprestar(String nome, String tipo, int dia, int mes, int ano) {
        nomeResponsavel = nome;
        tipoResponsavel = tipo;
        emprestadoDia = dia;
        emprestadoMes = mes;
        emprestadoAno = ano;

    }

    void setVencimento(int d, int m, int a) {
        vencDia = d;
        vencMes = m;
        vencAno = a;
    }

    String getResponsavel() {
        return nomeResponsavel;
    }

    int getVencimentoDia() {
        return vencDia;
    }

    int getVencimentoMes() {
        return vencMes;
    }

    int getVencimentoAno() {
        return vencAno;
    }
    int getCadastroDia() {
        return cadastroDia;
    }

    int getCadastroMes() {
        return cadastroMes;
    }

    int getCadastroAno() {
        return cadastroAno;
    }
    int getEmprestimoDia() {
        return emprestadoDia;
    }

    int getEmprestimoMes() {
        return emprestadoMes;
    }

    int getEmprestimoAno() {
        return emprestadoAno;
    }
}
