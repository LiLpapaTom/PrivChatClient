
package privchatclient;

public class PrivChatClient {

    public static void main(String[] args) {
        MasterUI ui  = new MasterUI();
        while(true){
                if(ui.client.getActiveChannels().contains("PUC")){
                    System.out.println(ui.client.getActiveChannels().toString());
                    System.out.println(ui.client.getActiveChannels().contains("PUC"));
                    ui.printPUCMsg();
                    System.out.println("Main");
                }
                else{
                    System.out.print("");
                }
                if(ui.client.getActiveChannels().contains("PSC")){

                }
                else{
                    System.out.print("");
                }
        }
    }
    
}
