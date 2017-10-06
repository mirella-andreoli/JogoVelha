package jogovelha;

import java.util.Random;
import java.util.Scanner;

public class Util {

    //Variáveis
    private int nJogadaPc = 0;
    private int nJogadaUsuario = 0;
    private final int[][] Jogo = new int[3][3];
    private int linha = 0;
    private int coluna = 0;
    private boolean vazia = false;
    private int jogadaUsuarioLinha = 0;
    private int jogadaUsuarioColuna = 0;

    public boolean VerificarVencedor() {

        for (int i = 0; i < 3; i++) {
            // verificar linhas
            if (Jogo[i][0] == Jogo[i][1] && Jogo[i][2] == Jogo[i][0]) {
                if (Jogo[i][0] == 1) {
                    System.out.println("Parabéns, você venceu!");
                    return true;
                } else if (Jogo[i][0] == 2) {
                    System.out.println("Venci desta vez!");
                    return true;
                }
            }

            // verificar colunas
            if (Jogo[0][i] == Jogo[1][i] && Jogo[2][i] == Jogo[0][i]) {
                if (Jogo[0][i] == 1) {
                    System.out.println("Parabéns, você venceu!");
                    return true;
                } else if (Jogo[0][i] == 2) {
                    System.out.println("Venci desta vez!");
                    return true;
                }
            }
        }

        // verificar diagonal 1
        if (Jogo[0][0] == Jogo[1][1] && Jogo[2][2] == Jogo[0][0]) {
            if (Jogo[0][0] == 1) {
                System.out.println("Parabéns, você venceu!");
                return true;
            } else if (Jogo[0][0] == 2) {
                System.out.println("Venci desta vez!");
                return true;
            }
        }

        // verificar diagonal 2
        if (Jogo[2][0] == Jogo[1][1] && Jogo[2][0] == Jogo[0][2]) {
            if (Jogo[2][0] == 1) {
                System.out.println("Parabéns, você venceu!");
                return true;
            } else if (Jogo[2][0] == 2) {
                System.out.println("Venci desta vez!");
                return true;
            }
        }

        if (nJogadaPc + nJogadaUsuario == 9) {
            System.out.println("Empate!");
            return true;
        } else {
            return false;
        }
    }

    public void ImprimirJogo() {
        System.out.println("    1      2        3   ");
        System.out.println("1       |       |       ");
        System.out.println("    " + RetornarValor(Jogo[0][0])
                + "   |   " + RetornarValor(Jogo[0][1])
                + "   |   " + RetornarValor(Jogo[0][2]));
        System.out.println(" _______|_______|_______");
        System.out.println("2       |       |       ");
        System.out.println("    " + RetornarValor(Jogo[1][0])
                + "   |   " + RetornarValor(Jogo[1][1])
                + "   |   " + RetornarValor(Jogo[1][2]));
        System.out.println(" _______|_______|_______");
        System.out.println("3       |       |       ");
        System.out.println("    " + RetornarValor(Jogo[2][0])
                + "   |   " + RetornarValor(Jogo[2][1])
                + "   |   " + RetornarValor(Jogo[2][2]));
        System.out.println("        |       |       ");
        System.out.println("__________ // ___________");
        System.out.println(" ");
    }

    private String RetornarValor(int valor) {
        if (valor == 1) {
            return "X";
        } else if (valor == 2) {
            return "O";
        } else {
            return " ";
        }
    }

    public void SocilitarJogada() {
        Scanner leia = new Scanner(System.in);
        vazia = false;
        jogadaUsuarioLinha = 0;
        jogadaUsuarioColuna = 0;

        while (vazia == false) {
            while (jogadaUsuarioLinha < 1 || jogadaUsuarioLinha > 3) {
                System.out.println("Escolha uma linha de 1 a 3: ");
                jogadaUsuarioLinha = leia.nextInt();
            }

            while (jogadaUsuarioColuna < 1 || jogadaUsuarioColuna > 3) {
                System.out.println("Escolha uma coluna de 1 a 3: ");
                jogadaUsuarioColuna = leia.nextInt();
            }

            if (Jogo[jogadaUsuarioLinha - 1][jogadaUsuarioColuna - 1] == 0) {
                vazia = true;
            } else {
                System.out.println("A posição " + jogadaUsuarioLinha + ", " + jogadaUsuarioColuna
                        + " já está ocupada, informe outra.");
            }
        }

        Jogo[jogadaUsuarioLinha - 1][jogadaUsuarioColuna - 1] = 1;
        nJogadaUsuario++;
        ImprimirJogo();
        if (VerificarVencedor() == false) {
            JogadaPc();
        }
    }

    public void JogadaPc() {
        boolean vencedor = false;
        if (nJogadaPc == 0) {
            PrimeiraJogada();
        } else {
            //Se tiver como ganhar ou evitar a derrota
            if (VerificaJogadaFinal() == true) {
                Jogo[linha][coluna] = 2;
            } else {
                //se for a segunda jogada
                if (nJogadaPc == 1) {
                    SegundaJogada();
                } else {
                    //verificar o vencedor a partir da 3ª rodada
                    if (VerificarVencedor() == true) {
                        vencedor = true;
                    }
                }
            }
        }

        nJogadaPc++; //Soma uma jogada
        ImprimirJogo(); //imprimir tabuleiro
        if (vencedor == false) { //verificar se alguém ganhou
            SocilitarJogada();
        }
    }

    private void PrimeiraJogada() {
        Random rd = new Random();
        int jogada = rd.nextInt(4);

        switch (jogada) {
            case 0:
                if (Jogo[0][0] == 0) {
                    Jogo[0][0] = 2;
                } else {
                    Jogo[2][0] = 2;
                }
                break;

            case 1:
                if (Jogo[0][2] == 0) {
                    Jogo[0][2] = 2;
                } else {
                    Jogo[0][0] = 2;
                }
                break;

            case 2:
                if (Jogo[2][0] == 0) {
                    Jogo[2][0] = 2;
                } else {
                    Jogo[2][2] = 2;
                }
                break;

            case 3:
                if (Jogo[2][2] == 0) {
                    Jogo[2][2] = 2;
                } else {
                    Jogo[0][2] = 2;
                }
                break;
        }
    }

    private void SegundaJogada() {
        Random rd = new Random();
        int jogada = rd.nextInt(3);

        // se o usuario jogou no meio
        if (Jogo[1][1] == 1) {
            if (Jogo[0][0] == 2) {
                if (jogada == 0) {
                    Jogo[2][2] = 2;
                } else {
                    Jogo[1][2] = 2;
                }
            } else if (Jogo[0][2] == 2) {
                if (jogada == 0) {
                    Jogo[2][0] = 2;
                } else {
                    Jogo[1][0] = 2;
                }
            } else if (Jogo[2][2] == 2) {
                if (jogada == 0) {
                    Jogo[0][0] = 2;
                } else {
                    Jogo[1][0] = 2;
                }
            } else if (Jogo[2][0] == 2) {
                if (jogada == 0) {
                    Jogo[0][2] = 2;
                } else {
                    Jogo[1][2] = 2;
                }
            }

            //Se o usuário jogar ao lado do meio
        } else if (Jogo[1][0] == 1 || Jogo[0][1] == 1
                || Jogo[1][2] == 1 || Jogo[2][1] == 1) {
            if (Jogo[2][2] == 2) {

            } else if (Jogo[2][0] == 2) {
                // if(Jogo[])

            } else if (Jogo[0][0] == 2) {
                if (Jogo[0][1] == 1 || Jogo[1][2] == 1 || Jogo[2][1] == 1) {
                    Jogo[2][0] = 2;
                } else {
                    Jogo[0][2] = 2;
                }
            } else if (Jogo[0][2] == 2) {

            }
        }
    }

    private boolean VerificaJogadaFinal() {
        for (int i = 0; i < 3; i++) {
            //linhas
            int p0 = Jogo[i][0];
            int p1 = Jogo[i][1];
            int p2 = Jogo[i][2];

            //colunas
            int c0 = Jogo[0][i];
            int c1 = Jogo[1][i];
            int c2 = Jogo[2][i];

            // Veriicar se a linha tem 2 O na linha
            if (p0 + p1 + p2 == 4) {
                linha = i;

                if (p2 == 0) {
                    coluna = 2;
                    return true;
                } else if (p1 == 0) {
                    coluna = 1;
                    return true;
                } else if (p0 == 0) {
                    coluna = 0;
                    return true;
                }

                // Veriicar se a linha tem 2 X na linha
            } else if (p0 + p1 + p2 == 2) {
                linha = i;
                if (p2 == 0) {
                    coluna = 2;
                    return true;
                } else if (p1 == 0) {
                    coluna = 1;
                    return true;
                } else if (p0 == 0) {
                    coluna = 0;
                    return true;
                }
            }

            // Verificar se a coluna tem 2 O
            if (c0 + c1 + c2 == 4) {
                coluna = i;
                if (c2 == 0) {
                    linha = 2;
                    return true;
                } else if (c1 == 0) {
                    linha = 1;
                    return true;
                } else if (c0 == 0) {
                    linha = 0;
                    return true;
                }

                // Verificar se a coluna tem 2 X
            } else if (c0 + c1 + c2 == 2) {
                coluna = i;
                if (c2 == 0) {
                    linha = 2;
                    return true;
                } else if (c1 == 0) {
                    linha = 1;
                    return true;
                } else if (c0 == 0) {
                    linha = 0;
                    return true;
                }
            }
        }

        //Verificar o ataque na diagonal
        if (VerificarAtaqueDiagonais()) {
            //Retornar de tiver
            return true;
        } else {
            //Se não houver, verificar a defesa
            return VerificarDefesaDiagonais();
        }
    }

    private boolean VerificarDefesaDiagonais() {

        if (Jogo[0][0] == 1 && Jogo[1][1] == 1 && Jogo[2][2] == 0) {
            linha = 2;
            coluna = 2;
            return true;
        } else if (Jogo[2][0] == 1 && Jogo[1][1] == 1 && Jogo[0][2] == 0) {
            linha = 0;
            coluna = 2;
            return true;
        } else if (Jogo[0][2] == 1 && Jogo[1][1] == 1 && Jogo[2][0] == 0) {
            linha = 2;
            coluna = 0;
            return true;
        } else if (Jogo[2][2] == 1 && Jogo[1][1] == 1 && Jogo[0][0] == 0) {
            linha = 0;
            coluna = 0;
            return true;
        } else if ((Jogo[0][0] == 1 && Jogo[2][2] == 1 && Jogo[1][1] == 0)
                || Jogo[2][0] == 1 && Jogo[0][2] == 1 && Jogo[1][1] == 0) {
            linha = 1;
            coluna = 1;
            return true;

        } else {
            return false;
        }
    }

    private boolean VerificarAtaqueDiagonais() {

        if (Jogo[0][0] == 1 && Jogo[1][1] == 1 && Jogo[2][2] == 0) {
            linha = 2;
            coluna = 2;
            return true;
        } else if (Jogo[2][0] == 1 && Jogo[1][1] == 1 && Jogo[0][2] == 0) {
            linha = 0;
            coluna = 2;
            return true;
        } else if (Jogo[0][2] == 1 && Jogo[1][1] == 1 && Jogo[2][0] == 0) {
            linha = 2;
            coluna = 0;
            return true;
        } else if (Jogo[2][2] == 1 && Jogo[1][1] == 1 && Jogo[0][0] == 0) {
            linha = 0;
            coluna = 0;
            return true;
        } else if ((Jogo[0][0] == 1 && Jogo[2][2] == 1 && Jogo[1][1] == 0)
                || Jogo[2][0] == 1 && Jogo[0][2] == 1 && Jogo[1][1] == 0) {
            linha = 1;
            coluna = 1;
            return true;

        } else {
            return false;
        }
    }

}
