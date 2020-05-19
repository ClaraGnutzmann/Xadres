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
    private void colocarNovaPeca(char coluna, int linha, PecaXadres peca){
        tabuleiro.colocarPeca(peca, new PosicaoXadres(coluna, (char) linha).paraPosicao());
    }
    private void inicializacao(){
        colocarNovaPeca('c', 1, new Torre(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('c', 2, new Torre(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('d', 2, new Torre(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('e', 2, new Torre(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('e', 1, new Torre(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('d', 1, new Rei(tabuleiro, Cores.BRANCO));

        colocarNovaPeca('c', 7, new Torre(tabuleiro, Cores.PRETO));
        colocarNovaPeca('c', 8, new Torre(tabuleiro, Cores.PRETO));
        colocarNovaPeca('d', 7, new Torre(tabuleiro, Cores.PRETO));
        colocarNovaPeca('e', 7, new Torre(tabuleiro, Cores.PRETO));
        colocarNovaPeca('e', 8, new Torre(tabuleiro, Cores.PRETO));
        colocarNovaPeca('d', 8, new Rei(tabuleiro, Cores.PRETO));


    }
}
