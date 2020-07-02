package aplication;

import tabuleiro.TabuleiroExcecao;
import xadres.PartidaXadres;
import xadres.PecaXadres;
import xadres.PosicaoXadres;

import java.util.InputMismatchException;
import java.util.Scanner;

import static aplication.UI.limparTela;

public class Program {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PartidaXadres partidaXadres = new PartidaXadres();

        while (true){
            try {
                limparTela();
                UI.printPartida(partidaXadres);
                System.out.println();
                System.out.println("Origem: ");
                PosicaoXadres origem = UI.lerPosicaoXadres(sc);

                boolean[][] possibleMoves = partidaXadres.possibleMoves(origem);
                limparTela();
                UI.printTabuleiro(partidaXadres.getPecas(), possibleMoves);
                System.out.println();
                System.out.println("Destino: ");
                PosicaoXadres destino = UI.lerPosicaoXadres(sc);



                    PecaXadres pecaCapturada = partidaXadres.performMovimentoXadres(origem, destino);
            }
            catch (TabuleiroExcecao e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }


    }
}
