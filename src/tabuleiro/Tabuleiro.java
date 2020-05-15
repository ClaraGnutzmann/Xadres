package tabuleiro;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if(linhas < 1 || colunas < 1){
            throw new TabuleiroExcecao("Erro ao criar tabuleiro! O Tabuleiro deve ter ao menos uma linha e uma coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca peca(int linhas, int colunas){
        if (!posicaoExiste(linhas, colunas)){
            throw new TabuleiroExcecao("Essa posição não esta no tabuleiro");
        }
        return pecas[linhas][colunas];
    }

    public Peca peca(Posicao posicao){
        if (!posicaoExiste(posicao)){
            throw new TabuleiroExcecao("Essa posição não esta no tabuleiro");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void colocarPeca(Peca peca, Posicao posicao){
        if (aquiTemPeca(posicao)){
            throw new TabuleiroExcecao("Já existe uma peça na posição " + posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }
    private boolean posicaoExiste(int linha, int coluna){
        return linha >= 0 && linha < linhas && coluna >=0 && coluna < colunas;
    }


    public boolean posicaoExiste(Posicao posicao){
        return posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }

    public boolean aquiTemPeca(Posicao posicao){
        if (!posicaoExiste(posicao)){
            throw new TabuleiroExcecao("Essa posição não esta no tabuleiro");
        }
        return peca(posicao) != null;
    }
}
