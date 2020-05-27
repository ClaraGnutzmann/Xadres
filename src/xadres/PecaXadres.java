package xadres;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

public abstract class PecaXadres extends Peca {
    private Cores cores;

    public PecaXadres(Tabuleiro tabuleiro, Cores cores) {
        super(tabuleiro);
        this.cores = cores;
    }

    public Cores getCores() {
        return cores;
    }
}
