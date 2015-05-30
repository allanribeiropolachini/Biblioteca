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
public class Professor implements Pessoa {

    private String nome;
    private boolean bloqueado;
    private int dia, mes, ano, diabloq = 0, mesbloq = 0, anobloq = 0;
    private int cadastroDia, cadastroMes, cadastroAno;
    ArrayList<Livro> livros = new ArrayList<>();

    public Professor(String n, int d, int m, int an, int cadDia, int cadMes, int cadAno) {
        nome = n;
        dia = d;
        mes = m;
        ano = an;
        cadastroDia = cadDia;
        cadastroMes = cadMes;
        cadastroAno = cadAno;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String n) {
        nome = n;
    }

    @Override
    public boolean retirarLivro(Livro l, int vencDia, int vencMes, int vencAno, int empDia, int empMes, int empAno){
        if (this.podeRetirar()) {
            livros.add(l);
            l.emprestar(this.getNome(), this.getTipo(), empDia, empMes, empAno);
            l.setVencimento(vencDia, vencMes, vencAno);
            System.out.println("Retirado com sucesso!\nDevolução para: " + l.getVencimentoDia() + "/" + l.getVencimentoMes() + "/" + l.getVencimentoAno());
            return true;
        } else {
            System.out.println("Este usuario esta bloquado");
            return false;
        }
    }

    @Override
    public Livro devolverLivro(String nome) {
        int indexLivro = -1;
        boolean desbloq = true;
        for (Livro l : livros) {
            if (l.getNome().equals(nome)) {
                indexLivro = livros.indexOf(l);
            }
        }
        if (indexLivro == -1) {
            System.out.println("Esse usuario nao tinha emprestado esse livro!");
        } else {
            Livro laux = livros.get(indexLivro);
            livros.remove(livros.get(indexLivro));
            System.out.println("Livro devolvido com sucesso!");

            return laux;
        }
        return null;
    }

    /*@Override
    public void renovarLivro(String nome) {
        int indexLivro = -1;
        for (Livro l : livros) {
            if (l.getNome().equals(nome)) {
                indexLivro = livros.indexOf(l);
            }
        }
        if (indexLivro == -1) {
            System.out.println("Esse usuario nao tinha emprestado esse livro!");
        } else {
            livros.get(indexLivro).emprestar(this.getNome(), this.getTipo());
            System.out.println("Livro renovado com sucesso!");
            System.out.println("Devolução para: " + livros.get(indexLivro).getVencimentoDia() + "/" + livros.get(indexLivro).getVencimentoMes() + "/" + livros.get(indexLivro).getVencimentoAno());
        }
    }*/

    @Override
    public void imprimirLivros() {
        livros.stream().forEach((l) -> {
            System.out.println(l.getAutor() + ", " + l.getNome());
        });
    }

    @Override
    public void bloquear(int diab, int mesb, int anob) {
        if (anob - ano >= 0) {
            if (mesb - mes >= 0) {
                if (diab - dia > 0) {
                    diabloq = diab;
                    anobloq = anob;
                    mesbloq = mesb;
                    bloqueado = true;
                }
                else{
                    diabloq = 0;
                    anobloq = 0;
                    mesbloq = 0;
                    bloqueado = false;
                }
            }
        }
    }

    @Override
    public void desbloquear() {
        bloqueado = false;
    }

    @Override
    public boolean podeRetirar() {
        return !((livros.size() >= 6) || (bloqueado));
    }

    @Override
    public String getTipo() {
        return "PROFESSOR";
    }

    @Override
    public void verificarBloqueio() {
        int auxdia, auxmes, auxano;
        boolean atualiza = false;
        for (Livro la : livros) {
            if ((la.getVencimentoDia() + (la.getVencimentoMes() * 30) + (la.getVencimentoAno() * 360)) < (dia + (mes * 30) + (ano * 360))) {
                auxdia = dia + (dia - la.getVencimentoDia());
                auxmes = mes + (mes - la.getVencimentoMes());
                auxano = ano + (ano - la.getVencimentoAno());
                if (auxdia < 0) {
                    auxdia = 30 - auxdia;
                } else if (auxdia > 30) {
                    auxdia = auxdia - 30;
                    auxmes++;
                }
                if (auxmes < 0) {
                    auxmes = 30 - auxmes;
                } else if (auxdia > 12) {
                    auxmes = auxmes - 12;
                    auxano++;
                }
                if (anobloq < auxano) {
                    atualiza = true;
                } else if (mesbloq < auxmes) {
                    atualiza = true;
                } else if (diabloq < auxdia) {
                    atualiza = true;
                }
                if (atualiza) {
                    diabloq = dia + (dia - la.getVencimentoDia()); 
                    mesbloq = mes + (mes - la.getVencimentoMes());
                    anobloq = ano + (ano - la.getVencimentoAno());
                    if (diabloq > 30) {
                        diabloq = diabloq - 30;
                        mes++;
                    }
                    if (mes > 12) {
                        mes = mes - 12;
                        ano++;
                    }
                    atualiza = false;
                }
                diabloq++;
                if (auxdia > 30) {
                    auxdia = auxdia - 30;
                    auxmes++;
                }
                if (auxdia > 12) {
                    auxmes = auxmes - 12;
                    auxano++;
                }
                this.bloquear(diabloq, mesbloq, anobloq);
            }
        }
    }

    @Override
    public int getDiaBloq() {
        return diabloq;
    }

    @Override
    public int getMesBloq() {
        return mesbloq;
    }

    @Override
    public int getAnoBloq() {
        return anobloq;
    }
    @Override
    public int getCadastroDia() {
        return cadastroDia;
    }

    @Override
    public int getCadastroMes() {
        return cadastroMes;
    }

    @Override
    public int getCadastroAno() {
        return cadastroAno;
    }
}
