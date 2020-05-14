package xadres;

import tabuleiro.Tabuleiro;

public class PartidaXadres {
    private Tabuleiro tabuleiro;

    public PartidaXadres(){
        tabuleiro = new Tabuleiro(8, 8);
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
}
