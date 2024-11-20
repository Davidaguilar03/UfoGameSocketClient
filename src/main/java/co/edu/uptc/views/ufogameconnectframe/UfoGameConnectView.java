package co.edu.uptc.views.ufogameconnectframe;

import javax.swing.JFrame;

public class UfoGameConnectView extends JFrame {
    
    public UfoGameConnectView(){
        this.initFrame();
        this.createUfoGameConnectHeader();
        this.createUfoGameConnectBody();
    }
    
    public void begin() {
        this.setVisible(true);
    }

    private void initFrame(){
        this.setUndecorated(true);
        this.setSize(600,500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void createUfoGameConnectHeader(){
        UfoGameConnectHeader ufoGameConnectHeader = new UfoGameConnectHeader(this);
        this.add(ufoGameConnectHeader);
    }

    private void createUfoGameConnectBody(){
        UfoGameConnectBody ufoGameConnectBody = new UfoGameConnectBody(this);
        this.add(ufoGameConnectBody);
    }
    

}
