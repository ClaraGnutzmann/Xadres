package xadres;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadres.pecas.Rei;
import xadres.pecas.Torre;

public class PartidaXadres {
    private Tabuleiro tabuleiro;

    public PartidaXadres(){
        tabuleiro = new Tabuleiro(8, 8);
        inicializacao();
    }
    public PecaXadres[][] getPecas(){
        PecaXadres[][] mat = new PecaXadres[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for(int i=0; i<tabuleiro.getLinhas(); i++){
            for (int j=0; j<tabuleiro.getColunas(); j++){
                mat[i][j] = (PecaXadres) tabuleiro.peca(i, j);
            }
        }
        return  mat;
    }
    private void inicializacao(){
        tabuleiro.colocarPeca(new Torre(tabuleiro, Cores.BRANCO), new Posicao(2, 1));
        tabuleiro.colocarPeca(new Rei(tabuleiro, Cores.BRANCO), new Posicao(0, 3));
        tabuleiro.colocarPeca(new Rei(tabuleiro, Cores.BRANCO), new Posicao(7, 1));
    }
}
