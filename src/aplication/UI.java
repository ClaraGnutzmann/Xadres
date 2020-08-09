package aplication;

import xadres.Cores;
import xadres.PartidaXadres;
import xadres.PecaXadres;
import xadres.PosicaoXadres;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    public static void limparTela(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static PosicaoXadres lerPosicaoXadres(Scanner sc){
        try {

            String s = sc.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));
            return new PosicaoXadres(coluna, linha);
        }
        catch (RuntimeException e){
            throw new InputMismatchException("Erro ao ler as posições! Valores validos são de A1 - H8");
        }
    }
    public static void printPartida(PartidaXadres partidaXadres, List<PecaXadres> capturada){
        printTabuleiro(partidaXadres.getPecas());
        System.out.println();
        printPecasCapturadas(capturada);
        System.out.println();
        System.out.println("Turno: " + partidaXadres.getTurno());
        if(!partidaXadres.getCheckMate()) {
            System.out.println("Aguardando jogador: " + partidaXadres.getJogadorAtual());
            if (partidaXadres.getCheck()) {
                System.out.print("CHECk");
            }
        }
        else {
            System.out.println("CHECKMATE");
            System.out.println("O VENCEDOR É" + partidaXadres.getJogadorAtual());
        }
    }

    public static void printTabuleiro(PecaXadres[][] pecas) {
        for (int i=0; i<pecas.length; i++){
            System.out.print((8 - i) + " ");
            for (int j=0; j<pecas.length; j++){
                printPecas(pecas[i][j], false);
            }
            System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");

    }
    public static void printTabuleiro(PecaXadres[][] pecas, boolean[][] possibleMoves) {
        for (int i=0; i<pecas.length; i++){
            System.out.print((8 - i) + " ");
            for (int j=0; j<pecas.length; j++){
                printPecas(pecas[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");

    }


    private static void printPecas(PecaXadres peca, boolean background) {
        if (background){
            System.out.println(ANSI_BLUE_BACKGROUND);
        }
        System.out.print(" " + ANSI_RESET);
        if (peca == null) {
            System.out.print("-"+ ANSI_RESET);
        }
        else {
            if (peca.getCores() == Cores.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
    private static void printPecasCapturadas(List<PecaXadres> capturada){
        List<PecaXadres> white = capturada.stream().filter(x -> x.getCores() == Cores.BRANCO).collect(Collectors.toList());
        List<PecaXadres> black = capturada.stream().filter(x -> x.getCores() == Cores.PRETO).collect(Collectors.toList());
        System.out.println("Peças Capturadas: ");
        System.out.print("BRANCAS: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("PRETAS: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);


    }



}


