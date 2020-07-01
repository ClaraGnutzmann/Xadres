package xadres.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadres.Cores;
import xadres.PecaXadres;

public class Torre extends PecaXadres {

    public Torre(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro, cores);
    }

    @Override
    public String toString() {
        return "T";
    }
    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat= new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);
        //acima
        p.setValues(posicao.getLinha()-1, posicao.getColuna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //esquerda
        p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna()-1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //direirta
        p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //abaixo
        p.setValues(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }



        return mat;
    }


}
