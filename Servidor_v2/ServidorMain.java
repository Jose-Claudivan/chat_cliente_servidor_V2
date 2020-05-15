////////////////////////////////////////////////////////////////////////////////
///                                                                          ///
/// UNIFAVIP WYDEN                                                           ///
/// Professor: Jadson Almeida                                                ///
/// Disciplina: Programação de Serviços de Rede                              ///
/// Curso: Ciência da Computação                                             ///
/// Aluno: José Claudivan da Silva                                           ///
/// Matricula: 181096479                                                     ///
/// Projeto: Chat com Observer                                               ///
///                                                                          ///
////////////////////////////////////////////////////////////////////////////////

package servidor_v2;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.util.ArrayList;

public class ServidorMain {
//  metodo principal que faz a chamada do metodo iniciar()
//  atraves do objeto servidor
    public static void main(String[] args) throws IOException {
      //inicia o servidor
      new ServidorMain(12345).executa();
     /* ServidorModerador servidor = new ServidorModerador();
      try {
        servidor.iniciar();
      } catch (Exception ex) {
         // Logger.getLogger(Ap1_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    private int porta;
    private List<PrintStream>clientes;

    public ServidorMain(int porta) {
      this.porta = porta;
      this.clientes = new ArrayList<PrintStream>();
    }
    public void executa() throws IOException {
      ServerSocket servidor = new ServerSocket(this.porta);
      System.out.println("Porta aberta: -->" + porta);

      while(true){
        //aceita um cliente
        Socket cliente = servidor.accept();
        System.out.println("Nova conexão com o cliente " +     
                cliente.getInetAddress().getHostAddress());
        //adiciona saida do cliente a lista
        PrintStream ps = new PrintStream(cliente.getOutputStream());
        this.clientes.add(ps);
        //cria tratador de cliente numa nova therad
        TrataCliente tc = new TrataCliente(cliente.getInputStream(),this);
        new Thread(tc).start();
      }
    }
    public void distribuiMensagem(String msg) {
      // envia msg para todo mundo
      for (PrintStream cliente : this.clientes) {
          cliente.println(msg);
      }
  }
}