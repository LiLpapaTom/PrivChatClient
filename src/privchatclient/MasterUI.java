
package privchatclient;

import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MasterUI extends javax.swing.JFrame {

    public MasterUI() {
        initComponents();
        setVisible(true);
        clientSetup();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PUCStart = new javax.swing.JButton();
        PSCStart = new javax.swing.JButton();
        AFUStart = new javax.swing.JButton();
        TorStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PUCStart.setText("Public Unsecure Channel");
        PUCStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PUCStartMouseClicked(evt);
            }
        });

        PSCStart.setText("Public Secure Channel");
        PSCStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PSCStartMouseClicked(evt);
            }
        });

        AFUStart.setText("Anonymous File Upload");

        TorStart.setText("Activate Tor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PUCStart)
                    .addComponent(PSCStart)
                    .addComponent(AFUStart)
                    .addComponent(TorStart))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PUCStart)
                .addGap(18, 18, 18)
                .addComponent(PSCStart)
                .addGap(18, 18, 18)
                .addComponent(AFUStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TorStart)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 298, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 200, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PUCStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PUCStartMouseClicked
        initPUC();
    }//GEN-LAST:event_PUCStartMouseClicked

    private void PSCStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PSCStartMouseClicked
        initPSC();
    }//GEN-LAST:event_PSCStartMouseClicked
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MasterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MasterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MasterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MasterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MasterUI().setVisible(true);
            }
        });
    }
    
    public void clientSetup(){
        String msg;
        try{
            Socket sock = new Socket("localhost", 9999);
            DataInputStream in = new DataInputStream(sock.getInputStream());
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            msg = in.readUTF();
            client = new Client(sock, in, out);
            client.setUsername(msg);
            System.out.println(msg);
        }
        catch(IOException ex){
            System.out.println("Client error. Client shuting down.");
            System.exit(-1);
        }
    }
    
    public void printPUCMsg(){
        try{
            String msg = client.receiveMessage();
            System.out.println(msg);
            if(msg.trim().equals("newUsername")){
                msg = client.receiveMessage();
                client.setUsername(msg);
            }
            else{
                System.out.println(msg);
                PUCChat.append("\n" + msg);
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        
    }
    
    //Initialization of Public Unsecure Channel Graphics
    private void initPUC(){
        PUCFrame = new JFrame("Public Unsecure Channel");
        PUCPanel = new JPanel();
        
        PUCChat = new JTextArea(10, 50);
        PUCLabel = new JLabel();
        PUCText = new JTextField(50);
        PUCChatScroll = new JScrollPane();
        PUCOnlineScroll = new JScrollPane();
        sendButton = new JButton("Send Message");
        changeAliasButton = new JButton("Change Username");
        onlinePUC = new javax.swing.JList<>();
        
        onlinePUC.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        PUCOnlineScroll.setViewportView(onlinePUC);

        PUCLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PUCLabel.setText("Online Users");
        PUCLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        PUCChat.setBackground(new java.awt.Color(204, 204, 204));
        PUCChat.setColumns(20);
        PUCChat.setForeground(new java.awt.Color(0, 0, 0));
        PUCChat.setRows(5);
        PUCChat.setFocusable(false);
        PUCChatScroll.setViewportView(PUCChat);
        
        PUCText.setBackground(new java.awt.Color(204, 204, 204));
        PUCText.setForeground(new java.awt.Color(0, 0, 0));
        PUCText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PUCTextKeyPressed(evt);
            }
        });

        sendButton.setText("Send Message");
        sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendButtonMouseClicked(evt);
            }
        });

        changeAliasButton.setText("Change Username");
        changeAliasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeAliasButtonMouseClicked(evt);
            }
        });
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(PUCPanel);
        PUCPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PUCText)
                    .addComponent(PUCChatScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PUCOnlineScroll)
                    .addComponent(PUCLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changeAliasButton, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PUCChatScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PUCLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PUCOnlineScroll)
                        .addGap(18, 18, 18)
                        .addComponent(changeAliasButton)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PUCText)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        
        PUCFrame.setContentPane(PUCPanel);
        
        PUCFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PUCFrame.pack();
        PUCFrame.setVisible(true);

        client.setActiveChannel("PUC");
        
    }

    //Send PUC button event
    private void sendButtonMouseClicked(java.awt.event.MouseEvent evt) {
        String msg = PUCText.getText();
        client.sendMessage(client.getUsername() + ": " + msg);
        PUCText.setText("");
    }
    
    //If user presses enter, send message
    private void PUCTextKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String msg = PUCText.getText();
            client.sendMessage(client.getUsername() + ": " + msg);
            PUCText.setText("");
        }
    }

    //If user presses Change Username button
    private void changeAliasButtonMouseClicked(java.awt.event.MouseEvent evt) {
       try{
            client.getOutputStream().writeUTF("changeUsernameRequest");
            client.getOutputStream().flush();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    
    //Initialization of Public Secure Channel Graphics
    private void initPSC(){
        PSCFrame = new JFrame("Public Secure Channel");
        PSCPanel = new JPanel();
        
        PSCChat = new JTextArea(10, 50);
        PSCLabel = new JLabel();
        PSCText = new JTextField(50);
        PSCChatScroll = new JScrollPane();
        PSCOnlineScroll = new JScrollPane();
        PSCSendButton = new JButton("Send Message");
        PSCChangeAliasButton = new JButton("Change Username");
        onlinePSC = new javax.swing.JList<>();
        
        onlinePSC.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        PSCOnlineScroll.setViewportView(onlinePSC);

        PSCLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PSCLabel.setText("Online Users");
        PSCLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        PSCChat.setBackground(new java.awt.Color(204, 204, 204));
        PSCChat.setColumns(20);
        PSCChat.setForeground(new java.awt.Color(0, 0, 0));
        PSCChat.setRows(5);
        PSCChat.setFocusable(false);
        PSCChatScroll.setViewportView(PSCChat);
        
        PSCText.setBackground(new java.awt.Color(204, 204, 204));
        PSCText.setForeground(new java.awt.Color(0, 0, 0));
        PSCText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PSCTextKeyPressed(evt);
            }
        });

        PSCSendButton.setText("Send Message");
        PSCSendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PSCSendButtonMouseClicked(evt);
            }
        });

        PSCChangeAliasButton.setText("Change Username");
        PSCChangeAliasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PSCChangeAliasButtonMouseClicked(evt);
            }
        });
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(PSCPanel);
        PSCPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PSCText)
                    .addComponent(PSCChatScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PSCOnlineScroll)
                    .addComponent(PSCLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PSCSendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PSCChangeAliasButton, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PSCChatScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PSCLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PSCOnlineScroll)
                        .addGap(18, 18, 18)
                        .addComponent(PSCChangeAliasButton)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PSCText)
                    .addComponent(PSCSendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        
        PSCFrame.setContentPane(PSCPanel);
        
        PSCFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PSCFrame.pack();
        PSCFrame.setVisible(true);
        
        client.setActiveChannel("PSC");
    }
    
    //Send PSC button event
    private void PSCSendButtonMouseClicked(java.awt.event.MouseEvent evt) {
        String msg = PSCText.getText();
        client.sendMessage(client.getUsername() + ": " + msg);
        PSCText.setText("");
    }
    
    //If user presses enter, send message
    private void PSCTextKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String msg = PSCText.getText();
            client.sendMessage(client.getUsername() + ": " + msg);
            PSCText.setText("");
        }
    }

    //If user presses Change Username button
    private void PSCChangeAliasButtonMouseClicked(java.awt.event.MouseEvent evt) {
       try{
            client.getOutputStream().writeUTF("changeUsernameRequest");
            client.getOutputStream().flush();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AFUStart;
    private javax.swing.JButton PSCStart;
    private javax.swing.JButton PUCStart;
    private javax.swing.JButton TorStart;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    //PUBLIC UNSECURE CHANNEL GRAPHICS
    private javax.swing.JFrame PUCFrame;
    private javax.swing.JPanel PUCPanel;
    private javax.swing.JTextField PUCText;
    private javax.swing.JTextArea PUCChat;
    private javax.swing.JLabel PUCLabel;
    private javax.swing.JScrollPane PUCChatScroll;
    private javax.swing.JScrollPane PUCOnlineScroll;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton changeAliasButton;
    private javax.swing.JList<String> onlinePUC;
    //PUBLIC SECURE CHANNEL GRAPHICS
    private javax.swing.JFrame PSCFrame;
    private javax.swing.JPanel PSCPanel;
    private javax.swing.JTextField PSCText;
    private javax.swing.JTextArea PSCChat;
    private javax.swing.JLabel PSCLabel;
    private javax.swing.JScrollPane PSCChatScroll;
    private javax.swing.JScrollPane PSCOnlineScroll;
    private javax.swing.JButton PSCSendButton;
    private javax.swing.JButton PSCChangeAliasButton;
    private javax.swing.JList<String> onlinePSC;
    //ANONYMOUS FILE UPLOAD GRAPHICS
    private javax.swing.JFrame AFUFrame;
    private javax.swing.JPanel AFUPanel;
    private javax.swing.JButton UploadButton;
    private javax.swing.JButton ViewUploadedButton;
    
    //Extra declarations
    Client client;
    
}