package xadres.pecas;

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
        return mat;
    }
}
