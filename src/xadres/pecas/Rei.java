package xadres.pecas;

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
}
