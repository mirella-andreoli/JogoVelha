package jogovelha;

import java.util.Random;
import java.util.Scanner;

public class Util {

    public Util(String inicio) {
        //Se o computador começar
        if (inicio.equals("n")) {
            JogadaPc();
        } else {
            //Se o usuário começar
            ImprimirJogo();
            SocilitarJogada();
        }
    }
    
    //nº de jogadas que o computador realizou
    private int nJogadaPc = 0;
    //nº de jogadas que o usuário realizou
    private int nJogadaUsuario = 0;
    //Tabuleiro do jogo
    private int[][] Jogo = new int[3][3];
    //Variáveis que determinam a jogada do PC
    private int linha = 0;
    private int coluna = 0;

    //Verifica que alguém venceu o jogo
    public boolean VerificarVencedor() {

        for (int i = 0; i < 3; i++) {
            //Verifica se tem 3 X ou 0 na linha
            if (Jogo[i][0] == Jogo[i][1] && Jogo[i][2] == Jogo[i][0]) { // verificar linhas
                if (Jogo[i][0] == 1) {
                    System.out.println("Parabéns, você venceu!");
                    return true;
                } else if (Jogo[i][0] == 2) {
                    System.out.println("Venci desta vez!");
                    return true;
                }
            }

            //Verifica se tem 3 X ou 0 na coluna
            if (Jogo[0][i] == Jogo[1][i] && Jogo[2][i] == Jogo[0][i]) { // verificar colunas
                if (Jogo[0][i] == 1) {
                    System.out.println("Parabéns, você venceu!");
                    return true;
                } else if (Jogo[0][i] == 2) {
                    System.out.println("Venci desta vez!");
                    return true;
                }
            }
        } //fim for

        // verificar se tem 3 X ou 0 diagonal 1
        if (Jogo[0][0] == Jogo[1][1] && Jogo[2][2] == Jogo[0][0]) {
            if (Jogo[0][0] == 1) {
                System.out.println("Parabéns, você venceu!");
                return true;
            } else if (Jogo[0][0] == 2) {
                System.out.println("Venci desta vez!");
                return true;
            }
        }

        // verificar se tem 3 X ou 0 diagonal 2
        if (Jogo[2][0] == Jogo[1][1] && Jogo[2][0] == Jogo[0][2]) {
            if (Jogo[2][0] == 1) {
                System.out.println("Parabéns, você venceu!");
                return true;
            } else if (Jogo[2][0] == 2) {
                System.out.println("Venci desta vez!");
                return true;
            }
        }

        //Se já foram 9 jogadas deu velha
        if (nJogadaPc + nJogadaUsuario == 9) {
            System.out.println("Empate!");
            return true;
        } else {
            return false;
        }
    }

    //Imprime o tabuleiro com as jogadas
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

    //Retorna X para 1 e 0 para 2
    private String RetornarValor(int valor) {
        if (valor == 1) {
            return "X";
        } else if (valor == 2) {
            return "O";
        } else {
            return " ";
        }
    }

    //Solicita uma jogada do usuário 
    public void SocilitarJogada() {
        Scanner leia = new Scanner(System.in);
        boolean vazia = false;
        int jogadaUsuarioLinha = 0;
        int jogadaUsuarioColuna = 0;

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
                jogadaUsuarioColuna = 50;
                jogadaUsuarioLinha = 50;
            }
        }

        Jogo[jogadaUsuarioLinha - 1][jogadaUsuarioColuna - 1] = 1;
        nJogadaUsuario++;
        ImprimirJogo();
        if (VerificarVencedor() == false) {
            JogadaPc();
        }
    }

    //Faz a jogada do PC
    public void JogadaPc() {
        boolean vencedor = false;

        if (nJogadaPc == 0) { // se for a primeira jogada
            PrimeiraJogada();

        } else {
            if (VerificaJogadaFinal() == true) { //Se tiver como ganhar ou evitar a derrota
                Jogo[linha][coluna] = 2;
                vencedor = VerificarVencedor();
            } else {
                if (nJogadaPc == 1) { //se for a segunda jogada
                    JogarPc();

                } else {
                    //verificar o vencedor a partir da 3ª rodada
                    if (VerificarVencedor() == true) {
                        vencedor = true;
                    } else {
                        JogarPc();
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

    //Primeira jogada do PC
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

    //Segunda jogada do PC
    private void JogarPc() {
        // ---------------- Segunda jogada -----------------//
        if (nJogadaPc == 1) {
            // se o usuario jogou no meio
            if (Jogo[1][1] == 1) {
                //encontrar a útima jogada do PC e jogar no canto contrário
                if (Jogo[0][0] == 2) {
                    Jogo[2][2] = 2;
                } else if (Jogo[2][0] == 2) {
                    Jogo[0][2] = 2;
                } else if (Jogo[0][2] == 2) {
                    Jogo[2][0] = 2;
                } else {
                    Jogo[0][0] = 2;
                }
            } else if (Jogo[0][1] == 1 || Jogo[0][1] == 1 || Jogo[1][2] == 1 || Jogo[2][1] == 1) {

            } else {
                if (Jogo[1][1] == 0) {
                    Jogo[1][1] = 2;
                } else if (Jogo[0][1] == 0) {
                    Jogo[0][1] = 2;
                } else {
                    Jogo[2][1] = 2;
                }
            }
            // ------------- Fim da segunda jogada ---------------//
            // ---------------- Terceira jogada -----------------//
        } else if (nJogadaPc == 3) {
            if (Jogo[1][1] == 0) {
                Jogo[1][1] = 2;
            } else if (Jogo[0][1] == 0) {
                Jogo[0][1] = 2;
            } else {
                Jogo[2][1] = 2;
            }
            // -------------- Fim terceira jogada -------------//
        }
        nJogadaPc++;
    }

    //Verifica se tem como ganhar ou evitar a derrota (linhas e colunas)
    private boolean VerificaJogadaFinal() {
        boolean defesaEncontrada = false;
        for (int i = 0; i < 3; i++) {
            //linhas
            int p0 = Jogo[i][0];
            int p1 = Jogo[i][1];
            int p2 = Jogo[i][2];

            //colunas
            int c0 = Jogo[0][i];
            int c1 = Jogo[1][i];
            int c2 = Jogo[2][i];

            // Verificar se a linha tem 2 O na linha
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
            } else if (c0 + c1 + c2 == 2 && defesaEncontrada == false) {
                coluna = i;
                if (c2 == 0) {
                    linha = 2;
                    defesaEncontrada = true;
                } else if (c1 == 0) {
                    linha = 1;
                    defesaEncontrada = true;
                } else if (c0 == 0) {
                    linha = 0;
                    defesaEncontrada = true;
                }

                // Verificar se a linha tem 2 X na linha
            } else if (p0 + p1 + p2 == 2 && defesaEncontrada == false) {
                linha = i;
                if (p2 == 0) {
                    coluna = 2;
                    defesaEncontrada = true;
                } else if (p1 == 0) {
                    coluna = 1;
                    defesaEncontrada = true;
                } else if (p0 == 0) {
                    coluna = 0;
                    defesaEncontrada = true;
                }
            }
        }

        if (defesaEncontrada == false) {
            //Verificar o ataque na diagonal
            if (VerificarAtaqueDiagonais()) {
                //Retornar de tiver
                return true;
            } else {
                //Se não houver, verificar a defesa
                return VerificarDefesaDiagonais();
            }
        }

        return defesaEncontrada;
    }

    //Verifica se o usuário pode ganhar na diagonal
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

    //Verifica se o PC pode ganhar na diagonal
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
