package xadres.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadres.Cores;
import xadres.PecaXadres;

public class Cavalo extends PecaXadres {
    public Cavalo(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro, cores);
    }

    @Override
    public String toString() {
        return "C";
    }
    private boolean canMove(Posicao posicao){
        PecaXadres p = (PecaXadres)getTabuleiro().peca(posicao);
        return p == null ||  p.getCores() != getCores();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat= new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        p.setValues(posicao.getLinha()-1, posicao.getColuna()-2);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }

        p.setValues(posicao.getLinha()-2, posicao.getColuna()-1);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        p.setValues(posicao.getLinha()-2, posicao.getColuna()+1);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }

       p.setValues(posicao.getLinha()-1, posicao.getColuna()+2);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        p.setValues(posicao.getLinha()+1, posicao.getColuna()+2);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        p.setValues(posicao.getLinha()+2, posicao.getColuna()+1);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        p.setValues(posicao.getLinha()+2, posicao.getColuna()-1);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }
        //suldeste
        p.setValues(posicao.getLinha()+1, posicao.getColuna()-2);
        if (getTabuleiro().posicaoExiste(p) && canMove(p)){
            mat[p.getLinha()][p.getColuna()] =true;
        }



        return mat;
    }
}
