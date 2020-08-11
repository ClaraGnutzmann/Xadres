package xadres;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadres.pecas.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaXadres {
    private Tabuleiro tabuleiro;
    private int turno;
    private Cores jogadorAtual;
    private boolean check;
    private boolean checkMate;


    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();


    public PartidaXadres(){
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cores.BRANCO;
        inicializacao();
    }

    public boolean getCheck(){
        return check;
    }

    public boolean getCheckMate(){
        return checkMate;
    }

    public int getTurno(){

        return turno;
    }

    public Cores getJogadorAtual(){
        return jogadorAtual;
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
    public boolean[][] possibleMoves(PosicaoXadres possicaoOrigem){
        Posicao posicao = possicaoOrigem.paraPosicao();
        validacaoPosicaoorigem(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }


    public PecaXadres performMovimentoXadres(PosicaoXadres posicaoOrigem, PosicaoXadres posicaoDestino){
        Posicao origem = posicaoOrigem.paraPosicao();
        Posicao destino = posicaoDestino.paraPosicao();
        validacaoPosicaoorigem(origem);
        validarPosicaoDestino(origem, destino);
        Peca pecaCapturada = makeMove(origem, destino);

        if(testeCheck(jogadorAtual)) {
            undoMove(origem, destino,pecaCapturada);
            throw new ExcecaoXadres("Voce não pode se colocar em Check");
        }
        check = (testeCheck(oponente(jogadorAtual))) ? true : false;
        if(testeCheckMate(oponente(jogadorAtual))){
            checkMate = true;
        }
        else {
            proximoTurno();
        }
        return (PecaXadres)pecaCapturada;
    }
    private Peca makeMove(Posicao origem, Posicao destino){
        PecaXadres p = (PecaXadres)tabuleiro.removerPeca(origem);
        p.increaseMoveCount();
        Peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.colocarPeca(p, destino);

        if (pecaCapturada != null){
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        return pecaCapturada;
    }
    private void undoMove(Posicao origem, Posicao destino, Peca pecaCapturada){
        PecaXadres p = (PecaXadres)tabuleiro.removerPeca(destino);
        p.decreaseMoveCount();
        tabuleiro.colocarPeca(p, origem);

        if (pecaCapturada != null){
            tabuleiro.colocarPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
    }

    private void validacaoPosicaoorigem(Posicao posicao){
        if(!tabuleiro.aquiTemPeca(posicao)){
            throw new ExcecaoXadres("Não há peça nessa posição");
        }
        if(jogadorAtual != ((PecaXadres) tabuleiro.peca(posicao)).getCores()){
            throw new ExcecaoXadres("Essa peça não pertence a esse jogador");
        }
        if (!tabuleiro.peca(posicao).temAlgumMovimentoPossivel()){
            throw new ExcecaoXadres("Não tem nenhum movimento possivel para essa peça!");
        }
    }

    private void validarPosicaoDestino(Posicao origem, Posicao destino){
        if(!tabuleiro.peca(origem).movimentoPossivel(destino)){
            throw new ExcecaoXadres("Essa meça não pode ser movida para a posição de destino!");
        }
    }
    private void proximoTurno(){
        turno++;
        jogadorAtual = (jogadorAtual == Cores.BRANCO) ? Cores.PRETO :Cores.BRANCO;
    }

    private Cores oponente(Cores cor){
        return (cor == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;

    }

    private PecaXadres rei (Cores cor){
        List<Peca> list = pecasNoTabuleiro.stream().filter(x ->((PecaXadres)x).getCores() == cor).collect(Collectors.toList());
        for(Peca p : list){
            if (p instanceof Rei){
                return (PecaXadres) p;
            }
        }
        throw new IllegalStateException("Não tem Rei" + cor + "no tabuleiro");
    }
    private boolean testeCheck(Cores cor){
        Posicao reiPosicao = rei(cor).getChessPosition().paraPosicao();
        List<Peca> pecaOponente = pecasNoTabuleiro.stream().filter(x ->((PecaXadres)x).getCores() == oponente(cor)).collect(Collectors.toList());
    for (Peca p : pecaOponente){
        boolean [][] mat = p.movimentosPossiveis();
        if (mat[reiPosicao.getColuna()][reiPosicao.getLinha()]){
            return true;
        }
    }
    return false;

    }

    private boolean testeCheckMate(Cores cor){
        if(!testeCheck(cor)){
            return false;
        }
        List<Peca> list = pecasNoTabuleiro.stream().filter(x ->((PecaXadres)x).getCores() == cor).collect(Collectors.toList());
        for (Peca p : list){
            boolean[][] mat = p.movimentosPossiveis();
            for(int i=0; i<tabuleiro.getLinhas(); i++){
                for(int j=0; j<tabuleiro.getColunas(); j++){
                    Posicao origem = ((PecaXadres)p).getChessPosition().paraPosicao();
                    Posicao destino = new Posicao(i, j);
                    Peca pecaCapturada = makeMove(origem, destino);
                    boolean testCheck = testeCheck(cor);
                    undoMove(origem, destino, pecaCapturada);
                    if(!testeCheck(cor)){
                        return false;
                    }

                }

            }

        }
        return true;
    }


    private void colocarNovaPeca(char coluna, int linha, PecaXadres peca){
        tabuleiro.colocarPeca(peca, new PosicaoXadres(coluna, (char) linha).paraPosicao());
        pecasNoTabuleiro.add(peca);
    }
    private void inicializacao(){
        colocarNovaPeca('a', 1, new Torre(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('e', 1, new Rei(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('h', 1, new Torre(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('a', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('b', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('c', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('d', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('e', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('f', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('g', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('h', 2, new Peao(tabuleiro, Cores.BRANCO));

        colocarNovaPeca('a', 8, new Torre(tabuleiro, Cores.PRETO));
        colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cores.PRETO));
        colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cores.PRETO));
        colocarNovaPeca('e', 8, new Rei(tabuleiro, Cores.PRETO));
        colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cores.PRETO));
        colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cores.PRETO));
        colocarNovaPeca('h', 8, new Torre(tabuleiro, Cores.PRETO));
        colocarNovaPeca('a', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('b', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('c', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('d', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('e', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('f', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('g', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('h', 7, new Peao(tabuleiro, Cores.PRETO));
    }
}
