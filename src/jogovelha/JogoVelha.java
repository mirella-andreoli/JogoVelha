package jogovelha;

import java.util.Scanner;

public class JogoVelha {

    public static void main(String[] args) {

        String usuarioComeca;
        Scanner leia = new Scanner(System.in);
        Util util;

        do {
            //Ler se quem começa é o usuário ou o PC
            System.out.println("Você é o X e eu sou a O. Quer começar? s/n");
            usuarioComeca = leia.next();
            usuarioComeca = usuarioComeca.toLowerCase();
        } while (!usuarioComeca.equals("s") && !usuarioComeca.equals("n"));

        //Iniciar o jogo
        util = new Util(usuarioComeca);
    }
}
