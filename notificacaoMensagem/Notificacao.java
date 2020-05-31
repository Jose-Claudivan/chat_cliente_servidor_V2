package notificacaoMensagem;

import design_pattern_observer.Observer;
import design_pattern_observer.Subject;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;

public class Notificacao implements Observer{
        
    private Subject subject;

    public Notificacao(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }
    @Override
    public void update() {
        this.reproduzSom();
        System.out.println("\n--> NOTIFICACAO RECEBIDA <--\n");
    }
    
    public void reproduzSom() {
        try {
            // Carrega o arquivo de áudio (não funciona com .mp3, só .wav) 
        String resource = "./somNotificacao.wav";
        InputStream input = getClass().getResourceAsStream(resource);
        Clip oClip = AudioSystem.getClip();
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(input);
        oClip.open(audioInput);
 
        oClip.loop(0); // Toca uma vez
        //clip.loop(Clip.LOOP_CONTINUOUSLY); // Toca continuamente (para o caso de músicas)
 
        // Para a execução (senão o programa termina antes de você ouvir o som)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
          
            }
        });
        } catch (Exception e) {
        }
    }   
}

/*public class Notificacao {
 
    public static void main(String[] args) {
        new Notificacao().notificacao();
    }
     
    
}*/