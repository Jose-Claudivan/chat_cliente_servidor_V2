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
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import cliente_v2.Cliente;
import servidor_v2.Servidor;

public class ServidorMain{
//  metodo principal que faz a chamada do metodo iniciar()
//  atraves do objeto servidor
    public static void main(final String[] args) throws IOException {
      final Servidor s = new Servidor(12345); 
      ServidorThread st = new ServidorMain().new ServidorThread(s);
      new Thread(st).start();
      ClienteThread ct = new ServidorMain().new ClienteThread(s);
      new Thread(ct).start();
      ClienteThread ct1 = new ServidorMain().new ClienteThread(s);
      new Thread(ct1).start();

    }

    public class ClienteThread implements Runnable {
      Servidor s;
      ClienteThread(final Servidor s) {
        this.s = s;
      }

      @Override
      public void run() {
        final Cliente cliente = new Cliente(s);
        cliente.iniciar();
		  }
    }
    
    public class ServidorThread implements Runnable {
      Servidor s;
      ServidorThread(final Servidor s) {
        this.s = s;
      }
      @Override
      public void run() {            
        try {
          s.executa();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
		  }
    } 

  }
