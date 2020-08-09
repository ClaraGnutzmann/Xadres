package xadres;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadres extends Peca {
    private Cores cores;
    private int moveCount;

    public PecaXadres(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro);
        this.cores = cores;
    }


    public Cores getCores() {

        return cores;
    }
    public int getMoveCount(){
        return moveCount;
    }

    public void increaseMoveCount(){
        moveCount++;
    }
    public void decreaseMoveCount(){
        moveCount--;
    }

    public PosicaoXadres getChessPosition(){
        return PosicaoXadres.dePosicao(posicao);
    }

    protected boolean temPecaOponente(Posicao posicao){
        PecaXadres p = (PecaXadres)getTabuleiro().peca(posicao);
        return p != null && p.getCores() != cores;
    }

}
