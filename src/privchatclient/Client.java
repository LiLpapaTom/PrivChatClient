//Grevenitis Ioannis icsd13045
//Papaloukas Thomas icsd14155

package privchatclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket sock;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private ArrayList<String> activeChannels;
    
    //Constructor
    public Client(Socket sock, DataInputStream in, DataOutputStream out){
        this.sock = sock;
        this.in = in;
        this.out = out;
        this.username = null;
        this.activeChannels = new ArrayList<>();
       // activeChannels.add("PUC");
    }
    
    //Getters & Setters
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setActiveChannel(String activeChannel){
        activeChannels.add(activeChannel);
    }
    
    public ArrayList<String> getActiveChannels(){
        return activeChannels;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public DataInputStream getInputStream(){
        return in;
    }
    
    public DataOutputStream getOutputStream(){
        return out;
    }
    
    //Loipes methodoi
    
    public void sendMessage(String msg){
        try{
            out.writeUTF(msg);
            out.flush();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public String receiveMessage() throws IOException{
        return in.readUTF();
    }
}
