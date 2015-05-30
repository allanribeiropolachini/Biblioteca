/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.ArrayList;

/**
 *
 * @author Adriller
 */
public interface Pessoa {

    public String getNome();

    public void setNome(String n);

    public boolean retirarLivro(Livro l, int vencDia, int vencMes, int vencAno, int empDia, int empMes, int empAno);

    public Livro devolverLivro(String nome);

    //public void renovarLivro(String nome);

    public void imprimirLivros(); 

    public void bloquear(int dia, int mes, int ano);

    public void desbloquear();

    public boolean podeRetirar();

    public String getTipo();

    public void verificarBloqueio();

    public int getDiaBloq();

    public int getMesBloq();

    public int getAnoBloq();
    
    public int getCadastroDia();

    public int getCadastroMes();

    public int getCadastroAno();

}
