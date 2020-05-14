package servidor_v2;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class ServidorModerador {
    public void iniciar(){
        try {
            // Instancia o ServerSocket ouvindo a porta 12345
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Porta aberta: --> 12345");
            System.out.println("Aguardando alguem se conectar...");
            while(true) {
              // o método accept() bloqueia a execução até que
              // o servidor receba um pedido de conexão
              Socket cliente = servidor.accept();     
              System.out.println("Cliente conectado: --> " + cliente.getInetAddress().getHostName());
              ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
              System.out.println("____________________________________________________________");
              Scanner scan = new Scanner(cliente.getInputStream());
              while(scan.hasNextLine()){
                System.out.println(scan.nextLine());   
              } 
              //texto.setText(mm.insert(scan.nextLine()));   

              /*
              System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
              ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
              saida.flush();
              saida.writeObject(new Date());
              saida.close();
              cliente.close();*/
            }  
        }   
        catch(Exception e) {
             System.out.println("Erro: " + e.getMessage());
        }
     // finally {...}
    }  
}
     
