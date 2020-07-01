package xadres.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadres.Cores;
import xadres.PecaXadres;

public class Rei extends PecaXadres {
    public Rei(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro, cores);
    }

    @Override
    public String toString() {
        return "R";
    }
    private boolean canMove(Posicao posicao){
        PecaXadres p = (PecaXadres)getTabuleiro().peca(posicao);
        return p == null ||  p.getCores() != getCores();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat= new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);
        //acima
        p.setValues(posicao.getLinha()-1, posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        //abaixo
        p.setValues(posicao.getLinha()+1, posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        //esquerda
        p.setValues(posicao.getLinha(), posicao.getColuna()-1);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        //direita
        p.setValues(posicao.getLinha(), posicao.getColuna()+1);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        //noroeste
        p.setValues(posicao.getLinha()-1, posicao.getColuna()-1);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        //nordeste
        p.setValues(posicao.getLinha()-1, posicao.getColuna()+1);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        //Suldoeste
        p.setValues(posicao.getLinha()+1, posicao.getColuna()-1);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        //suldeste
        p.setValues(posicao.getLinha()+1, posicao.getColuna()+1);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }



        return mat;
    }
}
