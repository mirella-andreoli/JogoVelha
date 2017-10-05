package jogovelha;

import java.util.Scanner;

public class JogoVelha {

    public static void main(String[] args) {

        String usuarioComeca;
        Scanner leia = new Scanner(System.in);
        Util util = new Util();

        do {
            System.out.println("Você é o X e eu sou a O. Quer começar? s/n");
            usuarioComeca = leia.next();
            usuarioComeca = usuarioComeca.toLowerCase();
        } while (!usuarioComeca.equals("s") && !usuarioComeca.equals("n"));

        //Se o computador começar
        if (usuarioComeca.equals("n")) {
            util.JogadaPc();
        } else { //Se o usuário perguntar
            //Exibir tela inicial
            util.ImprimirJogo();
            util.SocilitarJogada();
        }
    }
}
