package co.edu.uptc.views.ufogameconnectframe;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;

import co.edu.uptc.views.ufogamemainframe.UfoGameView;
import lombok.Getter;
@Getter
public class UfoGameConnectView extends JFrame {
    private UfoGameView ufoGameView;
    private UfoGameConnectHeader ufoGameConnectHeader;
    private UfoGameConnectBody ufoGameConnectBody;

    public UfoGameConnectView(UfoGameView ufoGameView){
        this.ufoGameView = ufoGameView;
        this.initFrame();
        this.createUfoGameConnectHeader();
        this.createUfoGameConnectBody();
    }
    
    public void begin() {
        this.setVisible(true);
    }

    private void initFrame(){
        this.setUndecorated(true);
        this.setLayout((LayoutManager) new BorderLayout());
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void createUfoGameConnectHeader(){
        ufoGameConnectHeader = new UfoGameConnectHeader(this);
        this.add(ufoGameConnectHeader, BorderLayout.NORTH);
    }

    private void createUfoGameConnectBody(){
        ufoGameConnectBody = new UfoGameConnectBody(this);
        this.add(ufoGameConnectBody, BorderLayout.CENTER);
    }
    

}
