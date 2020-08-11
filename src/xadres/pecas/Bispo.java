package xadres.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadres.Cores;
import xadres.PecaXadres;

public class Bispo extends PecaXadres {

    public Bispo(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro, cores);
    }

    @Override
    public String toString() {
        return "B";
    }
    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat= new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);
        //nw
        p.setValues(posicao.getLinha()-1, posicao.getColuna()-1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() -1, p.getColuna()-1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //ne
        p.setValues(posicao.getLinha()-1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() -1, p.getColuna()+1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //se
        p.setValues(posicao.getLinha()+1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() +1, p.getColuna()+1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //sw
        p.setValues(posicao.getLinha() + 1, posicao.getColuna()-1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() +1, p.getColuna()-1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }



        return mat;
    }


}
