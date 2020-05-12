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
package cliente_v2;

import java.awt.Color;                  //biblioteca responsavel pela atribuiçao de cores
import java.awt.Dimension;              //biblioteca responsavel pela definiçao do tamanho das telas
import java.io.*;                       //biblioteca responsavel pelos metodos de entrad/saida de dados
import java.net.*;                      //biblioteca responsavel pelos metodos do Socket
import java.awt.event.ActionEvent;      //biblioteca responsavel pelas açoes dos botoes
import java.awt.event.ActionListener;   //biblioteca responsavel pelas açoes dos botoes
import java.awt.event.KeyEvent;         //biblioteca responsavel pelas açoes das teclas
import java.awt.event.KeyListener;      //biblioteca responsavel pelas açoes dos botoes
import javax.swing.*;                   //biblioteca responsavel pelor toda a parte grafica

public class Cliente extends JFrame implements ActionListener, KeyListener {
//  declaraçao de todas as variaveis utilizadas
    private JEditorPane texto;
    private JTextField txtMsg;
    private JButton btnSend;
    private JButton btnSair;
    private JButton btnAtencao;
    private JLabel lblHistorico;
    private JLabel lblMsg;
    private JPanel pnlContent;
    private Socket socket;
    private OutputStream ou;
    private Writer ouw;
    private BufferedWriter bfw;
    private JTextField txtIP;
    private JTextField txtPorta;
    private JTextField txtNome;
    private Socket conexao;
    ManagerMessages mm;
    
// metodo responsavel pela parte de conexao do socket
    public void iniciar() {
        try {
//          assim q executado o programa o metodo conectar()
//          responsavel pela conexao é chamado junto com
//          o metodo escutar() responsavel por receber tudo do servidor
            this.conectar();
            this.escutar();
            
//          define o objeto do tipo Sockete q sera tera como parametro
//          o endereço IP do servidor e a porta de comunicacao 
//          disponivel pelo mesmo
            conexao = new Socket("localhost", 12345);
            System.out.println("Usuario Conectado ao Servidor");

        } catch (IOException e) {
//          caso ocorra alguma erro de conexao
            System.out.println("IOException: " + e);
        }
    }
    
// construtor com toda a parte visual do programa
    public Cliente(){
//      objeto responsavel por passas a mensagens formatada         
        this.mm = new ManagerMessages();
//      tela com informaçoes sobre o cliente
        JLabel lblMessage = new JLabel("Verificar");
        txtIP = new JTextField("127.0.0.1");
        txtPorta = new JTextField("12345");
        txtNome = new JTextField("Cliente");
        Object[] texts = { lblMessage, txtIP, txtPorta, txtNome };
        JOptionPane.showMessageDialog(null, texts);
//      todos os componentes da tela do chat 
        pnlContent = new JPanel();
        texto = new JEditorPane();
        texto.setContentType("text/html");
        texto.setPreferredSize(new Dimension(330, 300));
        texto.setEditable(false);
        texto.setBackground(new Color(238, 233, 233));
        txtMsg = new JTextField(23);
        lblHistorico = new JLabel("Histórico");
        lblMsg = new JLabel("Mensagem");
        btnSend = new JButton("Enviar");
        btnSend.setToolTipText("Enviar mensagem");
        btnSair = new JButton("Sair");
        btnSair.setToolTipText("Sair do Chat");
        btnAtencao = new JButton("Psiu!");
        btnAtencao.setToolTipText("Chamar atenção");
        btnSend.addActionListener(this);
        btnSair.addActionListener(this);
        btnAtencao.addActionListener(this);
        btnSend.addKeyListener(this);
        txtMsg.addKeyListener(this);
        JScrollPane scroll = new JScrollPane(texto);
        pnlContent.add(lblHistorico);
        pnlContent.add(scroll);
        pnlContent.add(lblMsg);
        pnlContent.add(txtMsg);
        pnlContent.add(btnSair);
        pnlContent.add(btnSend);
        pnlContent.add(btnAtencao);
        pnlContent.setBackground(Color.LIGHT_GRAY);
        texto.setBorder(BorderFactory.createEtchedBorder(Color.GREEN, Color.GREEN));
        txtMsg.setBorder(BorderFactory.createEtchedBorder(Color.GREEN, Color.GREEN));
        setTitle(txtNome.getText());
        setContentPane(pnlContent);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(350, 440);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

//  metodo responsavel pela conexao do socket
    public void conectar() throws IOException {
//      passa as informações necessarias para socket para realizar a conexao
        socket = new Socket(txtIP.getText(), Integer.parseInt(txtPorta.getText()));
        ou = socket.getOutputStream();
        ouw = new OutputStreamWriter(ou);
        bfw = new BufferedWriter(ouw);
        bfw.flush();

    }
//  metodo responsavel por enviar a mensagem no formato certo
    private void send(String msg) {
        try {
//          formata a mensagem a ser enviada (nome;horário;mensagem)
            String text = mm.formatMessage(this.txtNome.getText(),msg);  
//          envia a mensagem para o servidor
            bfw.write(text+"\r\n");
//          insere a mensagem na tela atual
            texto.setText(mm.insert(text));
            } catch (IOException e) {
//              caso ocorra algum erro no envio da mensagem            
                e.printStackTrace();
            }
    }

//  metodo responsavel por enviar a mensagem inserida       
    public void enviarMensagem(String msg) throws IOException{
//      envia desconectado para o servidor 
//      caso a condiçao seja atendida
        if(msg.equals("Sair")){
                this.send("Desconectado");
        }
//      envia a mensagem inserida e formatada            
         else{
            this.send(msg);
        }
        
        bfw.flush();
        txtMsg.setText("");
    }
        
//  metodo responsavel por receber as mensagens enviadas
    public void escutar() throws IOException{
//      define objetos para receber as mensagens enviadas           
        InputStream in = socket.getInputStream();
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader bfr = new BufferedReader(inr);
        String msg = "";
//      executa enquanto a mensagem for diferente de sair        
        while(!"Sair".equalsIgnoreCase(msg)){
//          se o buffer estiver pronto pra ser lido,
//          ler o valor armazenado
            if(bfr.ready()){
                msg = bfr.readLine();
//              se for igual sair, chama o metodo sair()  
                if(msg.equals("Sair")){
                        this.sair();
                }
//              caso contrario envia a mensagem desejada
                else {
                       texto.setText(mm.insert(msg));
                } 
            }
        }
    }
    
//  metodo responsavel por encerrar a conexao         
    public void sair() throws IOException{
//      chama o metodo enviarMensagem() e passa SAIR
//      para ser enviado para o servidor, logo em seguida
//      fechar a conexao e as stream atraves do close()        
        enviarMensagem("Sair");
        bfw.close();
        ouw.close();
        ou.close();
        socket.close();
    }
    
//  metodo chamar atençao() abre uma tela e exibe uma mensagem pré definida      
    public void atencao() throws IOException{
        JOptionPane.showMessageDialog(null, "Ei você me deixou no vacuo!\n"
                + " ME RESPONDE INFELIZ!");

        }

//  metodos responsaveis pelas açoes dos botoes
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
//        se o botao enviar for pressionado
//        envia a mensagem            
          if(e.getActionCommand().equals(btnSend.getActionCommand())){
             enviarMensagem(txtMsg.getText());
          }
//        se o botao sair for pressionado
//        chama o metodo sair()            
          else if(e.getActionCommand().equals(btnSair.getActionCommand())){
              sair();
          }
//        se o botao atencao for pressionado
//        chama o metodo atencao()            
          else if(e.getActionCommand().equals(btnAtencao.getActionCommand())){
              atencao();
          }
        } catch (Exception e1) {
//          caso ocorra algum erro na chamada das açoes
            e1.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//      metodo implementado devio a interface
//      porem nao utilizado no codigo
    }
    
//  metodo responsavel pelas açoes das teclas
    @Override
    public void keyPressed(KeyEvent e) {
//      se a tecla enter for pressionada
//      chama o metodo enviarMensagem()        
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            try{
                enviarMensagem(txtMsg.getText());
            } catch(Exception e1){
//              caso ocorra algum erro na chamada das açoes
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//      metodo implementado devio a interface
//      porem nao utilizado no codigo
    }
    
    

    
}
