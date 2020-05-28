package servidor_v2;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TratadorMensagemCliente implements Runnable {
    
    private Socket cliente;
    private Servidor servidor;

    public TratadorMensagemCliente(Socket cliente, Servidor servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try(Scanner s = new Scanner(this.cliente.getInputStream())){
            while (s.hasNextLine()){
                servidor.distribuiMensagem(this.cliente, s.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}