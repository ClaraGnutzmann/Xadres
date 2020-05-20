package aplication;

import xadres.PartidaXadres;
import xadres.PecaXadres;
import xadres.PosicaoXadres;

import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PartidaXadres partidaXadres = new PartidaXadres();
        UI.printTabuleiro(partidaXadres.getPecas());

        while (true){
            UI.printTabuleiro(partidaXadres.getPecas());
            System.out.println();
            System.out.println("Origem: ");
            PosicaoXadres origem = UI.lerPosicaoXadres(sc);

            UI.printTabuleiro(partidaXadres.getPecas());
            System.out.println();
            System.out.println("Destino: ");
            PosicaoXadres destino = UI.lerPosicaoXadres(sc);

            PecaXadres pecaCapturada = new partidaXadres.performMovimentoXadres(origem, destino);
        }


    }
}
