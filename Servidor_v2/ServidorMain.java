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
import servidor_v2.Servidor;

public class ServidorMain {
//  metodo principal que faz a chamada do metodo iniciar()
//  atraves do objeto servidor
    public static void main(String[] args) throws IOException {
      new Servidor(12345).executa();
    }
    
  }
