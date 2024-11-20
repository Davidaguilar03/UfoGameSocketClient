package co.edu.uptc.views.ufogameconnectframe;

import javax.swing.JPanel;

import co.edu.uptc.utilities.ImagePanel;
import co.edu.uptc.utilities.PropertiesService;
import co.edu.uptc.views.GlobalView;
import lombok.Getter;

@Getter
public class UfoGameConnectBody extends JPanel{
    private UfoGameConnectView ufoGameConnectView;
    private ImagePanel playPanel;
    private PropertiesService propertiesService;
    
    public UfoGameConnectBody(UfoGameConnectView ufoGameConnectView){
        this.ufoGameConnectView=ufoGameConnectView;
        this.initPanel();
        propertiesService = new PropertiesService();
    }

    private void initPanel(){
        playPanel = new ImagePanel(propertiesService.getKeyValue("ConnectBackground"), 1);
        playPanel.setForeground(GlobalView.BODY_PLAY_FOREGROUND);
        this.add(playPanel);
        this.setLayout(null);
    }

}
