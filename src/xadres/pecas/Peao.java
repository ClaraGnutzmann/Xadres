package xadres.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadres.Cores;
import xadres.PecaXadres;

public class Peao extends PecaXadres{

    public Peao(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro, cores);
    }

    public boolean[][] movimentosPossiveis() {
        boolean [][] mat = new boolean[getTabuleiro().getColunas()][getTabuleiro().getLinhas()];

        Posicao p = new Posicao(0, 0);

        if(getCores() == Cores.BRANCO){
            p.setValues(posicao.getLinha() , posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p)){
                mat[p.getColuna()][p.getLinha()] = true;
            }
            p.setValues(posicao.getLinha(), posicao.getColuna() - 2);
            Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p) && getTabuleiro().posicaoExiste(p2) && getTabuleiro().aquiTemPeca(p2) && getMoveCount() == 0){
                mat[p.getColuna()][p.getLinha()] = true;

            }
            p.setValues(posicao.getLinha() -1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
                mat[p.getColuna()][p.getLinha()] = true;
            }
            p.setValues(posicao.getLinha() -1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
                mat[p.getColuna()][p.getLinha()] = true;
            }

            }
        else{
            p.setValues(posicao.getLinha() , posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p)){
                mat[p.getColuna()][p.getLinha()] = true;
            }
            p.setValues(posicao.getLinha(), posicao.getColuna() + 2);
            Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiTemPeca(p) && getTabuleiro().posicaoExiste(p2) && getTabuleiro().aquiTemPeca(p2) && getMoveCount() == 0){
                mat[p.getColuna()][p.getLinha()] = true;

            }
            p.setValues(posicao.getLinha() +1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
                mat[p.getColuna()][p.getLinha()] = true;
            }
            p.setValues(posicao.getLinha() +1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && temPecaOponente(p)){
                mat[p.getColuna()][p.getLinha()] = true;
            }

        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}

