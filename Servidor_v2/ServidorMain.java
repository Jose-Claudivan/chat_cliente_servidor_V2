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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorMain {
//  metodo principal que faz a chamada do metodo iniciar()
//  atraves do objeto servidor
    public static void main(String[] args) {

      ServidorModerador servidor = new ServidorModerador();
      try {
        servidor.iniciar();
      } catch (Exception ex) {
         // Logger.getLogger(Ap1_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}