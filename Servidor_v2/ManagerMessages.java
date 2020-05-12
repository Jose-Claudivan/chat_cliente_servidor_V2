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

import java.io.File;                  //biblioteca responsavel pelas busca de arquivos no sistema

//classe responsavel por todo tratamento das mensagens
public class ManagerMessages {

    private String text="";
//  metodo responsavel por colocar as mensagens no
//  formato HTML devido o tipo de interface escolhida 
//  para a tela do chat    
    public String insert(String msg){

       String body="";
        
       String[] str =  msg.split(";",3);

       String title = "<span>"+str[0]+" ("+str[1]+"):"+"</span><br>";

//     condição para exibir o emoji do coraçao
       if(str[2].equals("<3")){

        String pathSeparator = File.separator;
        String path = "file:C:\\Users\\Juciana\\Documents\\NetBeansProjects\\Ap1_Servidor\\src\\ap1_servidor\\"+pathSeparator+"heart_icon.png";
        
        body = "<img src="+path+"/><br>";   
    }
       
//     condição para exibir o emoji do sorriso       
       else if(str[2].equals(":)")){
           
        String pathSeparator = File.separator;
        String path = "file:C:\\Users\\Juciana\\Documents\\NetBeansProjects\\Ap1_Servidor\\src\\ap1_servidor\\"+pathSeparator+"smile_icon.png";
        
        body = "<img src="+path+"/><br>"; 
       }
       
//     condição para exibir o emoji do chorao       
       else if(str[2].equals(":(")){
           
        String pathSeparator = File.separator;
        String path = "file:C:\\Users\\Juciana\\Documents\\NetBeansProjects\\Ap1_Servidor\\src\\ap1_servidor\\"+pathSeparator+"crying_icon.png";
        
        body = "<img src="+path+"/><br>"; 
       }
//     condição para exibir o emoji do like       
       else if(str[2].equals("(y)")){
           
        String pathSeparator = File.separator;
        String path = "file:C:\\Users\\Juciana\\Documents\\NetBeansProjects\\Ap1_Servidor\\src\\ap1_servidor\\"+pathSeparator+"like_icon.png";
        
        body = "<img src="+path+"/><br>"; 
       }
//     caso nenhuma das condiçoes seja executada
//     exibe a mensagem de texto normal       
       else{
        body = "<span>"+str[2]+"</span><br>";
       }

       String message = title+body;
       this.text = this.text+message;

       return this.text;
    }
//  formata a mensgem da forma que queremos que ela seja exibida
    public String formatMessage(String name, String msg) {

        String text = name+ ";" + TimeUtil.getTime() + ";" + msg;

        return text;

    }
}
